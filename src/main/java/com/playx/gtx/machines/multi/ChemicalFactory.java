package com.playx.gtx.machines.multi;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.playx.gtx.blocks.GTXCoils;

public class ChemicalFactory extends CoilWorkableElectricMultiblockMachine {
    private int energyBonus;

    public ChemicalFactory(IMachineBlockEntity holder) {
        super(holder);
    }

    public static GTRecipe recipeModifier(MetaMachine machine, GTRecipe recipe) {
        var type = recipe.recipeType;
        if (machine instanceof ChemicalFactory factory) {
            var newRecipe = recipe.copy();
            var eut = RecipeHelper.getInputEUt(recipe);
            RecipeHelper.setInputEUt(newRecipe, (int) (eut * factory.energyBonus * 0.01f));
            if (type == GTRecipeTypes.LARGE_CHEMICAL_RECIPES) {
                return GTRecipeModifiers.PARALLEL_HATCH.apply(machine, newRecipe);
            } else {
                return newRecipe;
            }
        } else {
            return recipe;
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        var coils = this.getCoilType();
        var temp = coils.getCoilTemperature();

        if (temp == GTBlocks.COIL_CUPRONICKEL.get().coilType.getCoilTemperature()) {
            energyBonus = 5;
        } else if (temp == GTBlocks.COIL_KANTHAL.get().coilType.getCoilTemperature()) {
            energyBonus = 10;
        } else if (temp == GTBlocks.COIL_NICHROME.get().coilType.getCoilTemperature()) {
            energyBonus = 15;
        } else if (temp == GTBlocks.COIL_RTMALLOY.get().coilType.getCoilTemperature()) {
            energyBonus = 20;
        } else if (temp == GTBlocks.COIL_HSSG.get().coilType.getCoilTemperature()) {
            energyBonus = 25;
        } else if (temp == GTBlocks.COIL_NAQUADAH.get().coilType.getCoilTemperature()) {
            energyBonus = 30;
        } else if (temp == GTBlocks.COIL_TRINIUM.get().coilType.getCoilTemperature()) {
            energyBonus = 35;
        } else if (temp == GTBlocks.COIL_TRITANIUM.get().coilType.getCoilTemperature()) {
            energyBonus = 40;
        } else if (temp == GTXCoils.BLACK_TITANIUM_COIL.get().coilType.getCoilTemperature()) {
            energyBonus = 45;
        } else if (temp == GTXCoils.NEUTRONIUM_COIL.get().coilType.getCoilTemperature()) {
            energyBonus = 50;
        } else if (temp == GTXCoils.COSMIC_NEUTRONIUM_COIL.get().coilType.getCoilTemperature()) {
            energyBonus = 55;
        } else {
            energyBonus = 0;
        }
    }
}
