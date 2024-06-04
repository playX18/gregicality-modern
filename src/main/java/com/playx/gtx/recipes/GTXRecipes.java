package com.playx.gtx.recipes;


import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.playx.gtx.materials.GTXTags;
import com.playx.gtx.recipes.categories.CasingRecipes;
import com.playx.gtx.recipes.categories.MachineCraftingRecipes;
import com.playx.gtx.recipes.chain.*;
import com.playx.gtx.recipes.overrides.GTCEUOverrides;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class GTXRecipes {

    public static void registerRecipes(Consumer<FinishedRecipe> provider) {
        RecipeHandler.init(provider);
        GTCEUOverrides.init(provider);

        GoldChain.goldChain(provider);
        AluminiumChain.registerAluminiumChain(provider);
        LithiumChain.lithiumChain(provider);
        CombinedChain.combinedChain(provider);
        NuclearChain.nuclearChain(provider);
        SEX.registerSEX(provider);
        CasingRecipes.init(provider);
        MachineCraftingRecipes.init(provider);
    }


}
