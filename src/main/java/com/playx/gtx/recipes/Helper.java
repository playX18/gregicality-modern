package com.playx.gtx.recipes;

import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.lowdragmc.lowdraglib.Platform;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

import java.util.function.Consumer;

public class Helper {

    public static void removeRecipeByName(Consumer<FinishedRecipe> provider, GTRecipeType type, String name) {
        type.recipeBuilder(name).save(provider);
    }

}
