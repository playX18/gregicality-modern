package com.playx.gtx.machines.multi;



import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveWorkableMachine;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.playx.gtx.GTXMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;


public class IndustrialPrimitiveBlastFurnace extends PrimitiveWorkableMachine implements IFancyUIMachine, IDisplayUIMachine {
    public int size = 0;
    protected final static int MAX_SIZE = 64;
    public int efficiency;


    public IndustrialPrimitiveBlastFurnace(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void clientTick() {
        super.clientTick();
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        initializeAbilities();
    }



    public static GTRecipe recipeModifier(MetaMachine machine, GTRecipe recipe) {

        if (machine instanceof IndustrialPrimitiveBlastFurnace ipbf) {
            var fuel = recipe.duration * ipbf.efficiency / 100;
            var newrecipe = recipe.copy();
            newrecipe.duration = fuel;
            GTXMod.LOGGER.info("modify recipe for IPBF: {} ticks -> {} ticks", recipe.duration, newrecipe.duration);
            return newrecipe;
        }

        return recipe;
    }

    @Override
    public void addDisplayText(List<Component> textList) {
        MultiblockDisplayText.builder(textList, isFormed())
                .setWorkingStatus(recipeLogic.isWorkingEnabled(), recipeLogic.isActive())
                .addMachineModeLine(getRecipeType())
                .addWorkingStatusLine()
                .addProgressLine(recipeLogic.getProgressPercent())
                .addCustom(tl -> {
                    tl.add(Component.literal("Efficiency: " + this.efficiency + "%"));
                    tl.add(Component.literal("Size: " + this.size));
                });
        getDefinition().getAdditionalDisplay().accept(this, textList);
        IDisplayUIMachine.super.addDisplayText(textList);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        group.addWidget(new DraggableScrollableWidgetGroup(4, 4, 182, 117).setBackground(GuiTextures.PRIMITIVE_BACKGROUND)
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







    private void initializeAbilities() {
        BlockPos pos;
        Direction direction = this.getFrontFacing().getOpposite();
        int length = 0;

        do {
            pos = this.getPos().relative(direction, ++length);
        } while (getLevel().getBlockState(pos).getBlock().equals(Blocks.AIR) || getLevel().getBlockState(pos).getBlock().equals(Blocks.LAVA));
        var PI = Math.PI;
        this.size = Math.min(MAX_SIZE, length - 1);
        // a little bit boosted efficiency here for making this multi more worthwhile
        // in GCYL it requires you to craft far too much bricks in order to be fast
        // in GTX we make it more worth making as a source of steel when you do not have enough
        // power production to make steel in EBF fast enough.
        this.efficiency = (int) ((((-Math.atan(((double)size * 1.85) / 4.0 / PI - MAX_SIZE / 4.0 / PI / 2) + (PI / 2)) / PI + ((-Math.atan(MAX_SIZE / 4.0 / PI / 2) + PI / 2) / PI)/2)) * 100.0);
        GTXMod.LOGGER.info("iPBF formed, efficiency: {}, size: {}", efficiency, size);
    }


}

