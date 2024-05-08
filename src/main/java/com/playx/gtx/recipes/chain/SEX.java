package com.playx.gtx.recipes.chain;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.playx.gtx.materials.GTXMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class SEX {
    public static void init() {

    }

    public static void registerSEX(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CHEMICAL_RECIPES
                .recipeBuilder("sodium_enthoxide_chem")
                .inputFluids(GTMaterials.Ethanol.getFluid(2000))
                .inputItems(TagPrefix.dust, GTMaterials.Sodium, 2)
                .outputItems(TagPrefix.dust, GTXMaterials.SodiumEthoxide, 2)
                .outputFluids(GTMaterials.Hydrogen.getFluid(2000))
                .save(provider);

        // carbon disulfide
        GTRecipeTypes.CHEMICAL_RECIPES
                .recipeBuilder("methane_sulfur_carbon_disulfide")
                .inputFluids(GTMaterials.Methane.getFluid(2000))
                .inputItems(TagPrefix.dust, GTMaterials.Sulfur, 8)
                .outputFluids(GTXMaterials.CarbonDisulfide.getFluid(2000))
                .outputFluids(GTMaterials.HydrogenSulfide.getFluid(4000))
                .save(provider);
        // cheaper reaction with charcoal
        GTRecipeTypes.CHEMICAL_RECIPES
                .recipeBuilder("charcoal_sulfur_carbon_disulfide")
                .inputItems(TagPrefix.gem, GTMaterials.Charcoal, 1)
                .inputItems(TagPrefix.dust, GTMaterials.Sulfur, 2)
                .outputFluids(GTXMaterials.CarbonDisulfide.getFluid(1000))
                .save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES
                .recipeBuilder("sodium_ethyl_xanthate_reaction")
                .inputItems(TagPrefix.dust, GTXMaterials.SodiumEthoxide, 1)
                .inputFluids(GTXMaterials.CarbonDisulfide.getFluid(1000))
                .outputItems(TagPrefix.dust, GTXMaterials.SodiumEthylXanthate, 1)
                .save(provider);
    }
}
