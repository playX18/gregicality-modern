package com.playx.gtx.fission;


import com.google.common.base.Preconditions;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableFluidTank;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.syncdata.IContentChangeAware;
import com.lowdragmc.lowdraglib.syncdata.ITagSerializable;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.playx.gtx.GTXMod;
import com.playx.gtx.common.util.NeutronHistory;
import com.playx.gtx.common.util.NuclearEfficiencyHistory;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/*
 * This class represents one single nuclear reactor rod and what it contains.
 */

public class ReactorHatch extends MultiblockPartMachine implements INuclearTile, IContentChangeAware {
    public static final long capacity = 64 * FluidType.BUCKET_VOLUME;
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(ReactorHatch.class, MultiblockPartMachine.MANAGED_FIELD_HOLDER);
    public boolean isFluid = false;
    public NeutronHistory neutronHistory;
    @Nullable
    protected TickableSubscription serverSubs;
    @Persisted
    private NotifiableFluidTank inputFluidInventory;
    @Persisted
    private NotifiableFluidTank outputFluidInventory;
    @Persisted
    public NotifiableItemStackHandler inputItemInventory;
    @Persisted
    private NotifiableItemStackHandler outputItemInventory;
    @Persisted
    private TemperatureComponent nuclearReactorComponent;

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public ReactorHatch(IMachineBlockEntity holder) {
        super(holder);
    }

    public ReactorHatch(IMachineBlockEntity holder, boolean isFluid) {
        super(holder);
        this.isFluid = isFluid;
        if (isFluid) {
            inputFluidInventory = new NotifiableFluidTank(this, 1, capacity, IO.IN);
            // hot coolant + byproduct (e.g water -> deuterium)
            outputFluidInventory = new NotifiableFluidTank(this, 2, capacity, IO.OUT);
            nuclearReactorComponent = new SteamHeaterComponent(NuclearConstant.MAX_TEMPERATURE, NuclearConstant.MAX_HATCH_EU_PRODUCTION,
                    NuclearConstant.EU_PER_DEGREE, true, true, false);
            inputItemInventory = new NotifiableItemStackHandler(this, 0, IO.IN);
            outputItemInventory = new NotifiableItemStackHandler(this, 0, IO.OUT);
        } else {
            inputFluidInventory = new NotifiableFluidTank(this, 0, capacity, IO.IN);
            outputFluidInventory = new NotifiableFluidTank(this, 0, capacity, IO.OUT);
            // fuel/moderator/etc
            inputItemInventory = new NotifiableItemStackHandler(this, 1, IO.IN);
            // depleted fuel
            outputItemInventory = new NotifiableItemStackHandler(this, 1, IO.OUT);
            nuclearReactorComponent = new TemperatureComponent(NuclearConstant.MAX_TEMPERATURE);
        }
        neutronHistory = new NeutronHistory();
    }

    private static int randIntFromDouble(double value, RandomSource rand) {
        return (int) Math.floor(value) + (rand.nextDouble() < (value % 1) ? 1 : 0);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        updateServerSubscription();
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (serverSubs != null) {
            serverSubs.unsubscribe();
        }
    }

    protected void updateServerSubscription() {
        //GTXMod.LOGGER.info("Subscribing reactor hatch to server tick");
        //serverSubs = subscribeServerTick(serverSubs, this::update);
    }

    private void update() {
        if (isFluid) {
            fluidNeutronProductTick(1, true);
        } else {
            var item = inputItemInventory.getStackInSlot(0);
            if (!item.isEmpty() && item.getItem() instanceof ReactorAbsorbable abs) {
                if (abs.getNeutronProduct() != null) {
                    this.outputItemInventory.insertItem(0, new ItemStack(Holder.direct(abs.getNeutronProduct()), (int) abs.getNeutronProductAmount()), true);
                }
            }
        }
    }

    public void fluidNeutronProductTick(int neutron, boolean simul) {
        if (isFluid) {
            var component = (INuclearComponent<Fluid>) this.getComponent();

            if (component == null) {
                return;
            } else {
                GTXMod.LOGGER.info("Component is not null for fluid hatch and has: {}", component.getVariant());
            }

            double fluidConsumption = neutron * component.getNeutronProductProbability();
            int actualRecipe = randIntFromDouble(fluidConsumption, this.getLevel().random);

            if (simul) {
                actualRecipe = neutron;
            }

            if (simul || actualRecipe > 0) {
                if (!inputFluidInventory.getFluidInTank(0).getFluid().isSame(component.getVariant())) {
                    GTXMod.LOGGER.info("Fluids are not the same: {} != {}", inputFluidInventory.getFluidInTank(0).getFluid(), component.getVariant());
                    return;
                }
                var extracted = inputFluidInventory.drain(Integer.MAX_VALUE, simul);
                outputFluidInventory.fill(FluidStack.create(component.getNeutronProduct(), extracted.getAmount() * component.getNeutronProductAmount()), simul);
            }
        }
    }

    @Override
    public int neutronGenerationTick(NuclearEfficiencyHistory efficiencyHistory) {
        double meanNeutron = getMeanNeutronAbsorption(NeutronType.BOTH);
        int neutronsProduced = 0;

        if (!isFluid) {
            Item item = (Item) this.getVariant();

            if (item != Items.AIR && item instanceof ReactorAbsorbable abs) {
                if (item instanceof NuclearFuel) {
                    meanNeutron += NuclearConstant.BASE_NEUTRON;
                }

                var stack = new ItemStack(Holder.direct(item), (int) getVariantAmount());

                var rand = this.getLevel().random;

                if (abs instanceof NuclearFuel fuel) {
                    neutronsProduced = fuel.simulateDesintegration(meanNeutron, stack, this.nuclearReactorComponent.getTemperature(), rand, efficiencyHistory);
                } else {
                    abs.simulateAbsorption(meanNeutron, stack, rand);
                }

                if (abs.getRemainingDesintegrations(stack) == 0) {
                    this.inputItemInventory.setStackInSlot(0, ItemStack.EMPTY);

                    if (abs.getNeutronProduct() != null) {
                        var _notInserted = this.outputItemInventory.insertItem(0, new ItemStack(Holder.direct(abs.getNeutronProduct()), (int) abs.getNeutronProductAmount()), true);

                    }
                } else {
                    this.inputItemInventory.setStackInSlot(0, stack);
                }
            }

            neutronHistory.addValue(NeutronHistory.Type.NeutronGeneration, neutronsProduced);
            return neutronsProduced;
        } else {
            return 0;
        }
    }

    private void checkComponentMaxTemperature() {
        if (!isFluid) {

            INuclearComponent<?> component = this.getComponent();

            if (component != null) {
                if (component.getMaxTemperature() < this.getTemperature()) {
                    this.inputItemInventory.setStackInSlot(0, ItemStack.EMPTY);
                }
            }
        }
    }

    @Override
    public void nuclearTick(NuclearEfficiencyHistory efficiencyHistory) {
        neutronHistory.tick();
        fluidNeutronProductTick(randIntFromDouble(neutronHistory.getAverageReceived(NeutronType.BOTH), this.getLevel().random), false);

        if (isFluid) {
            double euProduced = ((SteamHeaterComponent) nuclearReactorComponent).tick(inputFluidInventory, outputFluidInventory);
            efficiencyHistory.registerEuProduction(euProduced);
        }

        checkComponentMaxTemperature();
    }

    @Override
    public void putHeat(double eu) {
        Preconditions.checkArgument(eu >= 0);
        setTemperature(getTemperature() + eu / NuclearConstant.EU_PER_DEGREE);
        neutronHistory.addValue(NeutronHistory.Type.EUGeneration, (int) eu);
    }

    @Override
    public void absorbNeutrons(int neutronNumber, NeutronType type) {
        Preconditions.checkArgument(type != NeutronType.BOTH);
        if (type == NeutronType.FAST) {
            neutronHistory.addValue(NeutronHistory.Type.FastNeutronReceived, neutronNumber);
        } else {
            neutronHistory.addValue(NeutronHistory.Type.ThermalNeutronReceived, neutronNumber);
        }
    }

    @Override
    public void addNeutronsToFlux(int neutronNumber, NeutronType type) {
        Preconditions.checkArgument(type != NeutronType.BOTH);
        if (type == NeutronType.FAST) {
            neutronHistory.addValue(NeutronHistory.Type.FastNeutronFlux, neutronNumber);
        } else {
            neutronHistory.addValue(NeutronHistory.Type.ThermalNeutronFlux, neutronNumber);
        }
    }

    @Override
    public double getTemperature() {
        return nuclearReactorComponent.getTemperature();
    }

    @Override
    public void setTemperature(double temp) {
        nuclearReactorComponent.setTemperature(temp);
    }

    @Override
    public double getHeatTransferCoeff() {
        @Nullable
        INuclearComponent<?> component = getComponent();

        return Math.max(NuclearConstant.BASE_HEAT_CONDUCTION + (component != null ? component.getHeatConduction() : 0), 0);
    }

    @Override
    public double getMeanNeutronAbsorption(NeutronType type) {
        return neutronHistory.getAverageReceived(type);
    }

    @Override
    public double getMeanNeutronFlux(NeutronType type) {
        return neutronHistory.getAverageFlux(type);
    }

    @Override
    public double getMeanNeutronGeneration() {
        return neutronHistory.getAverageGeneration();
    }

    @Override
    public double getMeanEuGeneration() {
        return neutronHistory.getAverageEuGeneration();
    }

    @Override
    public Object getVariant() {
        if (isFluid) {
            return this.inputFluidInventory.getFluidInTank(0).getFluid();
        } else {
            return this.inputItemInventory.getStackInSlot(0).getItem();
        }
    }

    @Override
    public long getVariantAmount() {
        if (isFluid) {
            return this.inputFluidInventory.getFluidInTank(0).getAmount();
        } else {
            return this.inputItemInventory.getStackInSlot(0).getCount();
        }
    }

    @Override
    public boolean isFluid() {
        return isFluid;
    }

    public void addDisplayText(List<Component> tl) {
        tl.add(Component.translatable("hatch.nuclear_reactor.temperature", Double.valueOf(nuclearReactorComponent.getTemperature())));
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        var title = isFluid ? "hatch.nuclear_reactor_fluid.title" : "hatch.nuclear_reactor_item.title";
        group.addWidget(new LabelWidget(4, 5, title))
                .addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                        .textSupplier(this.getLevel().isClientSide ? null : this::addDisplayText)
                        .setMaxWidthLimit(150));

        if (isFluid) {
            group.addWidget(new TankWidget(inputFluidInventory.getStorages()[0], 4, 40, true, IO.IN.support(IO.IN)).setBackground(GuiTextures.FLUID_SLOT));
            group.addWidget(new TankWidget(outputFluidInventory.getStorages()[0], 4 + 18 + 18, 40, false, IO.OUT.support(IO.OUT)).setBackground(GuiTextures.FLUID_SLOT));
            group.addWidget(new TankWidget(outputFluidInventory.getStorages()[1], 4 + 18 + 18, 58, false, IO.OUT.support(IO.OUT)).setBackground(GuiTextures.FLUID_SLOT));
        } else {
            group.addWidget(new SlotWidget(inputItemInventory.storage, 0, 4, 40, true, IO.IN.support(IO.IN)).setBackground(GuiTextures.SLOT));
            group.addWidget(new SlotWidget(outputItemInventory.storage, 0, 4 + 18 + 18, 40, true, false).setBackground(GuiTextures.SLOT));
        }

        return group;
    }

    protected Widget createMultiSlotGUI() {

        /*var rowSize = 2;
        var colSize = 2;


        var group = new WidgetGroup(0, 0, 32 * rowSize + 16, 32 * colSize + 16);

        var inputContainer = new WidgetGroup(4, 4, 18 * 1 + 8, 18 * 1 + 8);
        var outputContainer = new WidgetGroup(18 * 2 + 8, 4, 18 * 1 + 8, 18 * 2 + 8);
        var infoContainer = new WidgetGroup(4, 18 * 1 + 8, 128, 4);
        if (isFluid) {
            inputContainer.addWidget(new TankWidget(inputFluidInventory.getStorages()[0], 4, 4, true, IO.IN.support(IO.IN)).setBackground(GuiTextures.FLUID_SLOT));
            outputContainer.addWidget(new TankWidget(outputFluidInventory.getStorages()[0], 4, 4, true, IO.OUT.support(IO.OUT)).setBackground(GuiTextures.FLUID_SLOT));
            outputContainer.addWidget(new TankWidget(outputFluidInventory.getStorages()[1], 4, 22, true, IO.OUT.support(IO.OUT)).setBackground(GuiTextures.FLUID_SLOT));
        } else {
            inputContainer.addWidget(new SlotWidget(inputItemInventory.storage, 0, 4, 4, true, IO.IN.support(IO.IN)).setBackground(GuiTextures.SLOT));\
            outputContainer.addWidget(new SlotWidget(outputItemInventory.storage, 0, 4, 4, true, IO.OUT.support(IO.OUT)).setBackground(GuiTextures.SLOT));
        }

        inputContainer.setBackground(GuiTextures.BACKGROUND_INVERSE);
        outputContainer.setBackground(GuiTextures.BACKGROUND_INVERSE);

        infoContainer.addWidget(new TextFieldWidget())

        group.addWidget(inputContainer);
        group.addWidget(outputContainer);
        return group;*/
        return null;
    }

    @Getter
    @Setter
    private Runnable onContentsChanged = () -> {};


    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();
        tag.putBoolean("isFluid", isFluid);
        if (isFluid) {
            tag.put("inputFluidInventory", inputFluidInventory.getStorages()[0].serializeNBT());
            tag.put("outputFluidInventory", outputFluidInventory.getStorages()[0].serializeNBT());
            tag.put("outputFluidInventory2", outputFluidInventory.getStorages()[1].serializeNBT());
        } else {
            tag.put("inputItemInventory", inputItemInventory.storage.serializeNBT());
            tag.put("outputItemInventory", outputItemInventory.storage.serializeNBT());
        }

        tag.put("nuclearReactorComponent", nuclearReactorComponent.serializeNBT());
        tag.put("history", neutronHistory.serializeNBT());

        return tag;
    }


    public void deserializeNBT(CompoundTag nbt) {
        GTXMod.LOGGER.info("Deserializing reactor hatch");
        isFluid = nbt.getBoolean("isFluid");
        if (isFluid) {
            outputFluidInventory = new NotifiableFluidTank(this, 2, capacity, IO.OUT);
            inputFluidInventory = new NotifiableFluidTank(this, 1, capacity, IO.IN);
            inputFluidInventory.getStorages()[0].deserializeNBT(nbt.getCompound("inputFluidInventory"));
            outputFluidInventory.getStorages()[0].deserializeNBT(nbt.getCompound("outputFluidInventory"));
            outputFluidInventory.getStorages()[1].deserializeNBT(nbt.getCompound("outputFluidInventory2"));
        } else {
            inputItemInventory = new NotifiableItemStackHandler(this, 1, IO.IN);
            outputItemInventory = new NotifiableItemStackHandler(this, 1, IO.OUT);
            inputItemInventory.storage.deserializeNBT(nbt.getCompound("inputItemInventory"));
            outputItemInventory.storage.deserializeNBT(nbt.getCompound("outputItemInventory"));
        }

        nuclearReactorComponent.deserializeNBT(nbt.getCompound("nuclearReactorComponent"));
        neutronHistory.deserializeNBT(nbt.getCompound("history"));
    }
}
