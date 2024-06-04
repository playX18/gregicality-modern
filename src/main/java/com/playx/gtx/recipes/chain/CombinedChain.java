package com.playx.gtx.recipes.chain;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import com.playx.gtx.recipes.GTXRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CombinedChain {
    public static void combinedChain(Consumer<FinishedRecipe> provider) {
        // Polymers

        // Epoxy
        // 2C6H6 + 8O + 2C2H4 + C3H6 -> C2H4O + CO2 + 3H2O
        GTXRecipeTypes.CHEMICAL_FACTORY_RECIPES
                .recipeBuilder(GTXMod.id("epoxy_factory"))
                .duration(60)
                .EUt(30720)
                .inputFluids(GTMaterials.Benzene.getFluid(2000))
                .inputFluids(GTMaterials.Oxygen.getFluid(8000))
                .inputFluids(GTMaterials.Ethylene.getFluid(2000))
                .inputFluids(GTMaterials.Propene.getFluid(1000))
                .notConsumableFluid(GTMaterials.HydrochloricAcid.getFluid(1000))
                .outputFluids(GTMaterials.Epoxy.getFluid(1000))
                .outputFluids(GTMaterials.CarbonDioxide.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(3000))
                .save(provider);

        // Polybenzimidazole
        // 2NH3 + 2HNO3 + 3C6H6 + 3O + C2H4 -> C20H12N4 + 9H2O
        GTXRecipeTypes.CHEMICAL_FACTORY_RECIPES
                .recipeBuilder(GTXMod.id("polybenzimidazole_factory"))
                .duration(70)
                .EUt(1966080)
                .notConsumable(TagPrefix.dust, GTMaterials.Zinc)
                .inputFluids(GTMaterials.Oxygen.getFluid(3000))
                .inputFluids(GTMaterials.Ethylene.getFluid(1000))
                .inputFluids(GTMaterials.Ammonia.getFluid(2000))
                .inputFluids(GTMaterials.NitricAcid.getFluid(2000))
                .inputFluids(GTMaterials.Benzene.getFluid(3000))
                .outputFluids(GTMaterials.Polybenzimidazole.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(9000))
                .save(provider);
    }
}
