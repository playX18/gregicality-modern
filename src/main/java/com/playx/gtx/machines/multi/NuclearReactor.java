package com.playx.gtx.machines.multi;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.IManaged;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.playx.gtx.GTXMod;
import com.playx.gtx.common.util.NuclearEfficiencyHistory;
import com.playx.gtx.fission.ReactorGrid;
import com.playx.gtx.fission.ReactorGridHelper;
import com.playx.gtx.fission.ReactorHatch;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NuclearReactor extends WorkableMultiblockMachine implements IDisplayUIMachine, IFancyUIMachine {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NuclearReactor.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
    private static final boolean[][][] gridLayout;

    static {
        gridLayout = new boolean[4][][];
        for (var i = 0; i < gridLayout.length; i++) {
            gridLayout[i] = new boolean[5 + 2 * i][5 + 2 * i];
        }
    }
    private List<ISubscription> traitSubscriptions = new ArrayList<>();
    private final NuclearEfficiencyHistory efficiencyHistory = new NuclearEfficiencyHistory();
    @Nullable
    protected TickableSubscription serverSubs;
    private ReactorGrid nuclearGrid = null;

    public NuclearReactor(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        serverSubs = subscribeServerTick(serverSubs, this::update);
        if (nuclearGrid == null) {
            link();
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        serverSubs.unsubscribe();
        for (var sub : traitSubscriptions) {
            sub.unsubscribe();
        }
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();

        serverSubs.unsubscribe();
        for (var sub : traitSubscriptions) {
            sub.unsubscribe();
        }
    }

    private void link() {
        if (!isFormed()) {
            return;
        }
        var size = gridLayout[0].length;
        ReactorHatch[][] hatchesGrid = new ReactorHatch[size][size];
        for (var part : getParts()) {
            if (part instanceof ReactorHatch hatch) {
                var pos = hatch.getPos();
                var x0 = pos.getX() - getPos().getX();
                var z0 = pos.getZ() - getPos().getZ();

                int x, y;

                var rotation = this.getFrontFacing();

                if (rotation == Direction.NORTH) {
                    x = size / 2 + x0;
                    y = z0;
                } else if (rotation == Direction.SOUTH) {
                    x = size / 2 - x0;
                    y = -z0;
                } else if (rotation == Direction.EAST) {
                    x = size / 2 + z0;
                    y = -x0;
                } else {
                    x = size / 2 - z0;
                    y = x0;

                }
                GTXMod.LOGGER.info("Reactor hatch: x: {}, y: {}", x, y);
                if (!hatch.isFluid()) {
                    traitSubscriptions.add(hatch.inputItemInventory.addChangedListener(this::checkFuel));
                }
                hatchesGrid[x][y] = hatch;
            } else {
                GTXMod.LOGGER.info("{} is not a reactor hatch", part);
            }
        }

        nuclearGrid = new ReactorGrid(size, size, hatchesGrid);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        link();
    }

    private void checkFuel() {
        if (serverSubs.isStillSubscribed()) {
            return;
        }

        if (isFormed()) {
            for (var part : getParts()) {
                if (part instanceof ReactorHatch hatch) {
                    if (hatch.getFuel().isPresent()) {
                        serverSubs = subscribeServerTick(serverSubs, this::update);
                        return;
                    }
                }
            }

        }
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        int numParallels = 0;

        MultiblockDisplayText.builder(textList, isFormed())
                .setWorkingStatus(recipeLogic.isWorkingEnabled(), recipeLogic.isActive())
                .addMachineModeLine(getRecipeType())
                .addParallelsLine(numParallels)
                .addWorkingStatusLine()
                .addProgressLine(recipeLogic.getProgressPercent());
        getDefinition().getAdditionalDisplay().accept(this, textList);
        IDisplayUIMachine.super.addDisplayText(textList);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        group.addWidget(new DraggableScrollableWidgetGroup(4, 4, 182, 117).setBackground(getScreenTexture())
                .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                        .textSupplier(this.getLevel().isClientSide ? null : this::addDisplayText)
                        .setMaxWidthLimit(150)
                        .clickHandler(this::handleDisplayClick)));
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(198, 208, this, entityPlayer).widget(new FancyMachineUIWidget(this, 198, 208));
    }


    // server tick
    private void update() {
        if (!getLevel().isClientSide()) {
            if (isFormed()) {
                if (isWorkingEnabled()) {
                    GTXMod.LOGGER.info("grid: {}", nuclearGrid);
                    var hasFuel = ReactorGridHelper.simulate(nuclearGrid, efficiencyHistory);
                    if (hasFuel) {
                        recipeLogic.setStatus(RecipeLogic.Status.WORKING);
                    } else {
                        recipeLogic.setStatus(RecipeLogic.Status.IDLE);
                        serverSubs.unsubscribe();
                    }

                } else {
                    super.recipeLogic.setStatus(RecipeLogic.Status.IDLE);
                }
                efficiencyHistory.tick();
            } else {
                efficiencyHistory.clear();
            }
        }
    }
}

/*
public class NuclearReactor extends CoilWorkableElectricMultiblockMachine {
    public NuclearReactor(IMachineBlockEntity holder) {
        super(holder);
    }
    private boolean notEnoughCoolant = true;
    private Material coolant;
    private Fluid hotCoolant;
    private int recipeBaseHeat;
    private int rodAdditionalTemperature;
    // for coolant input
    private FluidTransferList inputFluidInventory;
    // for coolant output
    private FluidTransferList outputFluidInventory;

    private static final Map<Material, Material> HOT_FLUIDS = Map.of(
            GTMaterials.Water, GTMaterials.Steam,
            GTMaterials.DistilledWater, GTMaterials.Steam,
            GTXMaterials.SodiumPotassiumAlloy, GTXMaterials.SodiumPotassiumAlloy,
            GTMaterials.Sodium, GTMaterials.Sodium,
            GTXMaterials.FLiNaK, GTXMaterials.FLiNaK,
            GTXMaterials.FLiBe, GTXMaterials.FLiBe,
            GTXMaterials.LeadBismuthEutectic, GTXMaterials.LeadBismuthEutectic
    );

    /*public static TraceabilityPredicate rodPredicate() {
        return new TraceabilityPredicate(blockWorldState -> {
            var blockState = blockWorldState.getBlockState();

            return false;
        }, );
    }
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        initializeAbilities();
    }

    @Override
    public void onLoad() {
        super.onLoad();

    }

    private void initializeAbilities() {
        List<IFluidTransfer> fluidTanks = new ArrayList<>();
        List<IFluidTransfer> fluidOutputs = new ArrayList<>();
        Map<Long, IO> ioMap = getMultiblockState().getMatchContext().getOrCreate("ioMap", Long2ObjectMaps::emptyMap);
        for (IMultiPart part : getParts()) {
            IO io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if(io == IO.NONE) continue;
            for (var handler : part.getRecipeHandlers()) {
                // If IO not compatible
                if (io != IO.BOTH && handler.getHandlerIO() != IO.BOTH && io != handler.getHandlerIO()) continue;
                var handlerIO = io == IO.BOTH ? handler.getHandlerIO() : io;
                if (handlerIO == IO.IN && handler.getCapability() == FluidRecipeCapability.CAP && handler instanceof IFluidTransfer fluidTransfer) {
                    fluidTanks.add(fluidTransfer);
                }

                if (handlerIO == IO.OUT && handler.getCapability() == FluidRecipeCapability.CAP && handler instanceof IFluidTransfer fluidTransfer) {
                    fluidOutputs.add(fluidTransfer);
                }
            }
        }
        this.inputFluidInventory = new FluidTransferList(fluidTanks);
        this.outputFluidInventory = new FluidTransferList(fluidOutputs);
    }
    private boolean cool(FluidStack fluid, int duration) {
        var material = ChemicalHelper.getMaterial(fluid.getFluid());
        if (material == null) {
            return false;
        }

        var hotCoolant = HOT_FLUIDS.get(material);
        if (hotCoolant == null) {
            return false;
        }

        var coolAmount = 1000 / 20 * duration;

        if (fluid.getAmount() >= coolAmount) {
            fluid.setAmount(fluid.getAmount() - coolAmount);

        }

        return true;
    }

    public int getRecipeBaseHeat() {
        return recipeBaseHeat;
    }

    public int getRodAdditionalTemperature() {
        return rodAdditionalTemperature;
    }

    public int coolantNeeded() {
        return (recipeBaseHeat + rodAdditionalTemperature);
    }


}
*/