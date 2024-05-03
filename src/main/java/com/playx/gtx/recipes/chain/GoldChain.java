package com.playx.gtx.recipes.chain;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import org.arbor.gtnn.data.GTNNRecipeTypes;


import java.util.function.Consumer;

public class GoldChain {
    public static void init() {
        GTXMaterials.PotassiumMetabisulfite = new Material.Builder(GTXMod.id("potassium_metabisulfite"))
                .dust()
                .color(0xFFFFFF)
                .iconSet(MaterialIconSet.DULL)
                .components(GTMaterials.Potassium, 2, GTMaterials.Sulfur, 2, GTMaterials.Oxygen, 5)
                .buildAndRegister();

        GTXMaterials.GoldAlloy = new Material.Builder(GTXMod.id("gold_alloy"))
                .ingot()
                .color(0xBBA52B)
                .iconSet(MaterialIconSet.SHINY)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .components(GTMaterials.Copper, 3, GTMaterials.Gold, 1, GTMaterials.RareEarth, 1)
                .buildAndRegister();

        GTXMaterials.GoldLeach = new Material.Builder(GTXMod.id("gold_leach"))
                .dust()
                .color(0xBBA52B)
                .iconSet(MaterialIconSet.ROUGH)
                .buildAndRegister().setFormula("Cu3Au?");

        GTXMaterials.CopperLeach = new Material.Builder(GTXMod.id("copper_leach"))
                .dust()
                .color(0x765A30)
                .iconSet(MaterialIconSet.SHINY)
                .buildAndRegister().setFormula("Cu3?");

        GTXMaterials.PreciousMetal = new Material.Builder((GTXMod.id("precious_metal")))
                .ingot()
                .color( 0xB99023)
                .iconSet(MaterialIconSet.SHINY)
                .components(GTMaterials.Gold, 1, GTMaterials.RareEarth, 1)
                .flags(MaterialFlags.DISABLE_DECOMPOSITION)
                .buildAndRegister();

        GTXMaterials.ChloroauricAcid = new Material.Builder(GTXMod.id("chloroauric_acid"))
                .fluid()
                .color(0xDFD11F)
                .buildAndRegister().setFormula("HAuCl?");
    }

    public static void goldChain(Consumer<FinishedRecipe> provider) {

        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder("step_1_gold_alloy_recovery")
                .inputItems(TagPrefix.dust, GTXMaterials.GoldAlloy, 4)
                .outputItems(TagPrefix.dust, GTMaterials.Copper, 3)
                .chancedOutput(TagPrefix.dust, GTMaterials.Gold, 4444, 0)
                .duration(500)
                .EUt(30)
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder("step_2_gold_leach_recovery")
                .inputItems(TagPrefix.dust, GTXMaterials.GoldLeach, 4)
                .inputFluids(GTMaterials.Hydrogen.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .outputItems(TagPrefix.dust, GTMaterials.Copper, 3)
                .chancedOutput(TagPrefix.dust, GTMaterials.Gold, 8888, 0)
                .duration(300)
                .EUt(30)
                .save(provider);

        // MAIN CHAIN
        // step 1
        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("precious_metal_to_gold_alloy")
                .inputItems(TagPrefix.dust, GTXMaterials.PreciousMetal, 1)
                .inputItems(TagPrefix.dust, GTMaterials.Copper, 3)
                .outputItems(TagPrefix.ingot, GTXMaterials.GoldAlloy, 4)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("precious_metal_to_gold_alloy_1")
                .inputItems(TagPrefix.ingot, GTXMaterials.PreciousMetal, 1)
                .inputItems(TagPrefix.ingot, GTMaterials.Copper, 3)
                .outputItems(TagPrefix.ingot, GTXMaterials.GoldAlloy, 4)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("precious_metal_to_gold_alloy_2")
                .inputItems(TagPrefix.dust, GTXMaterials.PreciousMetal, 1)
                .inputItems(TagPrefix.ingot, GTMaterials.Copper, 3)
                .outputItems(TagPrefix.ingot, GTXMaterials.GoldAlloy, 4)
                .save(provider);

        GTRecipeTypes.ALLOY_SMELTER_RECIPES.recipeBuilder("precious_metal_to_gold_alloy_3")
                .inputItems(TagPrefix.ingot, GTXMaterials.PreciousMetal, 1)
                .inputItems(TagPrefix.dust, GTMaterials.Copper, 3)
                .outputItems(TagPrefix.ingot, GTXMaterials.GoldAlloy, 4)
                .save(provider);

        // step 2
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("gold_alloy_to_gold_leach").duration(80)
                .inputItems(TagPrefix.ingot, GTXMaterials.GoldAlloy, 4)
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))
                .outputItems(TagPrefix.dust, GTXMaterials.GoldLeach, 4)
                .outputFluids(GTMaterials.NitrogenDioxide.getFluid(1000))
                .save(provider);

        // step 3
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("gold_leach_to_copper_leach").duration(80)
                .inputItems(TagPrefix.dust, GTXMaterials.GoldLeach, 4)
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(1000))
                .outputItems(TagPrefix.dust, GTXMaterials.CopperLeach, 4)
                .outputFluids(GTXMaterials.ChloroauricAcid.getFluid(1000))
                .save(provider);

        // step 4
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder("chloroauric_acid_to_gold").duration(100)
                .notConsumable(TagPrefix.dust, GTXMaterials.PotassiumMetabisulfite)
                .inputFluids(GTXMaterials.ChloroauricAcid.getFluid(1000))
                .outputItems(TagPrefix.dust, GTMaterials.Gold, 2)
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTMaterials.Chlorine.getFluid(1000))
                .save(provider);

        // side ingridients
        GTRecipeTypes.MIXER_RECIPES.recipeBuilder("potassium_metabisulfite_recipe")
                .duration(100)
                .EUt(30)
                .circuitMeta(1)
                .inputItems(TagPrefix.dust, GTMaterials.Potassium, 2)
                .inputItems(TagPrefix.dust, GTMaterials.Sulfur, 2)
                .inputFluids(GTMaterials.Oxygen.getFluid(5000))
                .outputItems(TagPrefix.dust, GTXMaterials.PotassiumMetabisulfite, 9)
                .save(provider);

        GTNNRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder("dehydrate_copper_leach").duration(80)
                .inputItems(TagPrefix.dust, GTXMaterials.CopperLeach, 4)
                .outputItems(TagPrefix.dust, GTMaterials.Copper, 3)
                .chancedOutput(TagPrefix.dust, GTMaterials.Lead, 1500, 500)
                .chancedOutput(TagPrefix.dust, GTMaterials.Iron, 1200, 400)
                .chancedOutput(TagPrefix.dust, GTMaterials.Nickel, 1000, 300)
                .chancedOutput(TagPrefix.dust, GTMaterials.Silver, 800, 200)
                .save(provider);
    }
}
