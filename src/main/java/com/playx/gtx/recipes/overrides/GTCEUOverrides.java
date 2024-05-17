package com.playx.gtx.recipes.overrides;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.playx.gtx.GTXMod;
import com.playx.gtx.items.GTXItems;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.playx.gtx.materials.GTXMaterials.*;

public class GTCEUOverrides {
    public static void init(Consumer<FinishedRecipe> provider) {
        chemistryOverride(provider);
        gregtechOverrides(provider);
    }

    private static void chemistryOverride(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder(GTXMod.id("distill_severely_steam_cracked_naphtha"))
                .duration(120)
                .EUt(120)
                .inputFluids(SeverelySteamCrackedNaphtha.getFluid(1000))
                .outputFluids(HeavyFuel.getFluid(25))
                .outputFluids(LightFuel.getFluid(50))
                .outputFluids(Toluene.getFluid(20))
                .outputFluids(Benzene.getFluid(100))
                .outputFluids(Butene.getFluid(50))
                .outputFluids(Butadiene.getFluid(50))
                .outputFluids(Propane.getFluid(15))
                .outputFluids(Propene.getFluid(300))
                .outputFluids(Ethane.getFluid(65))
                .outputFluids(Ethylene.getFluid(500))
                .outputFluids(Methane.getFluid(500))
                .outputFluids(Cyclopentadiene.getFluid(75))
                .save(provider);

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder(GTXMod.id("distill_lightly_steam_cracked_naphtha"))
                .duration(120)
                .EUt(120)
                .inputFluids(LightlySteamCrackedNaphtha.getFluid(1000))
                .outputFluids(HeavyFuel.getFluid(75))
                .outputFluids(LightFuel.getFluid(150))
                .outputFluids(Toluene.getFluid(40))
                .outputFluids(Benzene.getFluid(150))
                .outputFluids(Butene.getFluid(80))
                .outputFluids(Butadiene.getFluid(150))
                .outputFluids(Propane.getFluid(15))
                .outputFluids(Propene.getFluid(200))
                .outputFluids(Ethane.getFluid(35))
                .outputFluids(Ethylene.getFluid(200))
                .outputFluids(Methane.getFluid(200))
                .outputFluids(Cyclopentadiene.getFluid(140))
                .save(provider);

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder(GTXMod.id("fermented_biomass_to_butanol"))
                .duration(75)
                .EUt(180)
                .inputFluids(FermentedBiomass.getFluid(2000))
                .outputFluids(AceticAcid.getFluid(25))
                .outputFluids(Water.getFluid(375))
                .outputFluids(Ethanol.getFluid(250))
                .outputFluids(Methanol.getFluid(150))
                .outputFluids(Ammonia.getFluid(100))
                .outputFluids(CarbonDioxide.getFluid(400))
                .outputFluids(Methane.getFluid(600))
                .outputFluids(Butanol.getFluid(100))
                .outputItems(GTItems.FERTILIZER, 1)
                .save(provider);

        GTRecipeTypes.DISTILLATION_RECIPES.recipeBuilder(GTXMod.id("distill_fermentation_base"))
                .duration(75)
                .EUt(180)
                .inputFluids(FermentationBase.getFluid(2000))
                .outputFluids(AceticAcid.getFluid(50))
                .outputFluids(Ethanol.getFluid(600))
                .outputFluids(Methanol.getFluid(150))
                .outputFluids(Ammonia.getFluid(100))
                .outputFluids(CarbonDioxide.getFluid(400))
                .outputFluids(Methane.getFluid(600))
                .outputFluids(Butanol.getFluid(100))
                .outputItems(GTItems.FERTILIZER, 1)
                .save(provider);


    }

    private static void gregtechOverrides(Consumer<FinishedRecipe> provider) {
        // GTNH Coils

        GTRecipeTypes.MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_mica_pulp"))
                .duration(400).EUt(8)
                .inputItems(TagPrefix.dust, Mica, 3)
                .inputItems(TagPrefix.dust, RawRubber, 2)
                .outputItems(TagPrefix.dust, MicaBased, 5)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("alumino_silicate_wool_smelt"))
                .duration(1200).EUt(30)
                .inputItems(TagPrefix.dust, Sapphire, 1)
                .inputItems(TagPrefix.dust, SiliconDioxide, 3)
                .outputItems(TagPrefix.dust, AluminoSilicateWool,2)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("alumino_silicate_wool_smelt"))
                .duration(1200).EUt(30)
                .inputItems(TagPrefix.dust, GreenSapphire, 1)
                .inputItems(TagPrefix.dust, SiliconDioxide, 3)
                .outputItems(TagPrefix.dust, AluminoSilicateWool,2)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("alumino_silicate_wool_smelt"))
                .inputItems(TagPrefix.dust, Ruby, 1)
                .inputItems(TagPrefix.dust, SiliconDioxide, 3)
                .outputItems(TagPrefix.dust, AluminoSilicateWool,2)
                .save(provider);

        GTRecipeTypes.FORMING_PRESS_RECIPES.recipeBuilder(GTXMod.id("mica_pulp_to_sheet"))
                .duration(1200).EUt(30)
                .inputItems(TagPrefix.dust, MicaBased, 4)
                .inputItems(TagPrefix.dust, Asbestos, 1)
                .outputItems(GTXItems.MICA_SHEET.get(), 5)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("mica_sheet_to_insulator"))
                .duration(400).EUt(30)
                .inputItems(GTXItems.MICA_SHEET, 5)
                .inputItems(TagPrefix.dust, SiliconDioxide, 3)
                .outputItems(GTXItems.MICA_INSULATOR_SHEET, 8)
                .save(provider);

        GTRecipeTypes.BENDER_RECIPES.recipeBuilder(GTXMod.id("mica_insulator_to_foil"))
                .duration(100).EUt(30)
                .inputItems(GTXItems.MICA_INSULATOR_SHEET, 1)
                .circuitMeta(0)
                .outputItems(GTXItems.MICA_INSULATOR_FOIL, 4)
                .save(provider);


    }
}
