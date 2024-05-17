package com.playx.gtx.recipes;


import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.ItemMaterialInfo;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTRecipes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.integration.kjs.builders.prefix.TagPrefixBuilder;
import com.playx.gtx.materials.RadioactiveMaterial;
import com.playx.gtx.recipes.categories.CasingRecipes;
import com.playx.gtx.recipes.categories.MachineCraftingRecipes;
import com.playx.gtx.recipes.chain.AluminiumChain;
import com.playx.gtx.recipes.chain.GoldChain;
import com.playx.gtx.recipes.chain.LithiumChain;
import com.playx.gtx.recipes.chain.SEX;
import com.playx.gtx.recipes.overrides.GTCEUOverrides;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.function.Consumer;

public class GTXRecipes {

    public static void registerRecipes(Consumer<FinishedRecipe> provider) {
        RecipeHandler.init(provider);
        GTCEUOverrides.init(provider);

        GoldChain.goldChain(provider);
        AluminiumChain.registerAluminiumChain(provider);
        LithiumChain.lithiumChain(provider);
        SEX.registerSEX(provider);
        CasingRecipes.init(provider);
        MachineCraftingRecipes.init(provider);


    }


}
