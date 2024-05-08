package com.playx.gtx.recipes.chain;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import dev.arbor.gtnn.data.GTNNRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class AluminiumChain {
    public static void registerAluminiumChain(Consumer<FinishedRecipe> provider) {
        // 2Al(OH)3 -> Al2O3 + 3H2O
        BLAST_RECIPES.recipeBuilder(GTXMod.id("aluminium_hydrooxide_blast_alumina"))
                .duration(200)
                .EUt(120)
                .blastFurnaceTemp(1100)
                .inputItems(TagPrefix.dust, GTXMaterials.AluminiumHydroxide, 14)
                .outputItems(TagPrefix.dust, GTXMaterials.Alumina, 5)
                .outputFluids(GTMaterials.Water.getFluid(3000))
                .save(provider);

        // 6NaOH + Al2O3 + 12HF -> 2Na3AlF6 + 9H2O
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("alumina_to_aluminium"))
                .duration(450)
                .EUt(120)
                .inputItems(TagPrefix.dust, GTMaterials.SodiumHydroxide, 18)
                .inputItems(TagPrefix.dust, GTXMaterials.Alumina, 5)
                .inputFluids(GTMaterials.HydrofluoricAcid.getFluid(12000))
                .outputFluids(GTXMaterials.SodiumHexafluoroaluminate.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(9000))
                .save(provider);

        // 2Al2O3 + Na3AlF6 -> 4Al + 3NaF + AlF3 + 6O
        ELECTROLYZER_RECIPES.recipeBuilder(GTXMod.id("alumina_electrolyze_trifluoride"))
                .duration(200)
                .EUt(120)
                .inputItems(TagPrefix.dust, GTXMaterials.Alumina, 10)
                .inputFluids(GTXMaterials.SodiumHexafluoroaluminate.getFluid(1000))
                .outputFluids(GTMaterials.Oxygen.getFluid(6000))
                .outputItems(TagPrefix.dust, GTMaterials.Aluminium, 4)
                .outputItems(TagPrefix.dust, GTXMaterials.SodiumFluoride, 6)
                .outputItems(TagPrefix.dust, GTXMaterials.AluminiumTrifluoride, 4)
                .save(provider);

        // 3NaF + AlF3 -> Na3AlF6
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("trifluoride_to_hexafluoroluminate"))
                .duration(200)
                .EUt(120)
                .inputItems(TagPrefix.dust, GTXMaterials.SodiumFluoride, 6)
                .inputItems(TagPrefix.dust, GTXMaterials.AluminiumTrifluoride, 4)
                .outputFluids(GTXMaterials.SodiumHexafluoroaluminate.getFluid(1000))
                .save(provider);

        // 24[H2O + NaOH] + (TiO2)2Al16H10O29 -> [24H2O + 24NaOH + (TiO2)2Al16H10O29 + ?]
        MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_hydroxide_bauxite"))
                .duration(240)
                .EUt(30)
                .inputFluids(GTXMaterials.SodiumHexafluoroaluminate.getFluid(24000))
                .inputItems(TagPrefix.dust, GTMaterials.Bauxite, 39)
                .outputFluids(GTXMaterials.SodiumHydroxideBauxite.getFluid(24000))
                .save(provider);
        // [24H2O + 24NaOH + (TiO2)2Al16H10O29 + ?] = [4TiO2 + 16Al(OH)3 + 24NaOH + 5 H2O] - Increase to 4 TiO2 to make process worth doing
        FLUID_HEATER_RECIPES.recipeBuilder(GTXMod.id("hydroxide_bauxite_to_impure_aluminium_hydroxide"))
                .duration(30)
                .EUt(30)
                .circuitMeta(0)
                .inputFluids(GTXMaterials.SodiumHydroxideBauxite.getFluid(1000))
                .outputFluids(GTXMaterials.ImpureAluminiumHydroxideSolution.getFluid(1000))
                .save(provider);

        // [4TiO2 + 16Al(OH)3 + 24NaOH + 5 H2O] + 9 H2O = 8 Red Mud [Contains Total: 4TiO2 + 24NaOH + 6 H2O] + 8 [2 Al(OH)3 + H2O]
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("purify_aluminium_hydroxide"))
                .duration(230)
                .EUt(120)
                .inputFluids(GTMaterials.Water.getFluid(5000))
                .inputFluids(GTXMaterials.ImpureAluminiumHydroxideSolution.getFluid(24000))
                .outputFluids(GTXMaterials.RedMud.getFluid(8000))
                .outputFluids(GTXMaterials.PureAluminiumHydroxideSolution.getFluid(16000))
                .save(provider);
        // [2 Al(OH)3 + H2O] = 2 Al(OH)3 + H2O
        GTNNRecipeTypes.INSTANCE.getDEHYDRATOR_RECIPES().recipeBuilder(GTXMod.id("dehydrate_pure_aluminium_hydroxide"))
                .duration(240)
                .EUt(120)
                .inputFluids(GTXMaterials.PureAluminiumHydroxideSolution.getFluid(1000))
                .circuitMeta(0)
                .outputItems(TagPrefix.dust, GTXMaterials.AluminiumHydroxide, 14)
                .save(provider);
        // [2 Al(OH)3 + H2O] = 2 Al(OH)3 + H2O
        GTNNRecipeTypes.INSTANCE.getDEHYDRATOR_RECIPES().recipeBuilder(GTXMod.id("dehydrate_pure_aluminium_hydroxide_boosted"))
                .duration(240)
                .EUt(1920)
                .inputFluids(GTXMaterials.PureAluminiumHydroxideSolution.getFluid(4000))
                .notConsumable(TagPrefix.dust, GTXMaterials.AluminiumHydroxide)
                .circuitMeta(0)
                .outputItems(TagPrefix.dust, GTXMaterials.AluminiumHydroxide, 56)
                .chancedOutput(TagPrefix.dust, GTMaterials.Gallium, 1, 7500, 0)
                .save(provider);

        // 8 Red Mud [Contains Total: 4TiO2 + 24 NaOH + 6 H2O] + 32 HCl = 8 Neutralized Mud [Contains Total: 4TiO2 + 24NaCl + 30 H2O + 12HCl]
        MIXER_RECIPES.recipeBuilder(GTXMod.id("neutralise_red_mud"))
                .duration(100)
                .EUt(120)
                .inputFluids(GTXMaterials.RedMud.getFluid(1000))
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(4000))
                .outputFluids(GTXMaterials.NeutralisedRedMud.getFluid(2000))
                .save(provider);

        // 8 Neutralized Mud [Contains Total: 4TiO2 + 24NaCl + 30 H2O + 12 HCl] = 4 Red Slurry [Contains Total: 4TiO2] + 4 Ferric REE Chloride [Contains Total: 12 HCl + 6 H2O] + 16 [NaCl + H2O]
        CENTRIFUGE_RECIPES.recipeBuilder(GTXMaterials.id("centrifuge_neutralized_red_mut"))
                .duration(100)
                .EUt(120)
                .inputFluids(GTXMaterials.NeutralisedRedMud.getFluid(2000))
                .outputFluids(GTXMaterials.RedSlurry.getFluid(1000))
                .outputFluids(GTXMaterials.FerricREEChloride.getFluid(1000))
                .outputFluids(GTMaterials.SaltWater.getFluid(4000))
                .save(provider);

        // 4 Ferric REE Chloride [Contains Total: 12 HCl + 6 H2O] = 2 Rare Earth Chlorides [Contains Total: REECl3 + 3 H2O] + 2 Iron III Chloride [Contains Total: FeCl3] + 6 H2O
        CENTRIFUGE_RECIPES.recipeBuilder(GTXMaterials.id("ferric_chloride_to_iron_chloride"))
                .duration(320)
                .EUt(480)
                .inputFluids(GTXMaterials.FerricREEChloride.getFluid(2000))
                .outputFluids(GTXMaterials.RareEarthChloridesSolution.getFluid(1000))
                .outputFluids(GTXMaterials.IronChloride.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(3000))
                .save(provider);

        // 4 Red Slurry [Contains Total: 4TiO2] + 4 H2SO4 = 4 TiO(SO4) + 4H2O
        CHEMICAL_RECIPES.recipeBuilder(GTXMaterials.id("red_slurry_to_titanyl"))
                .duration(160)
                .EUt(120)
                .inputFluids(GTXMaterials.RedSlurry.getFluid(2000))
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(2000))
                .outputFluids(GTXMaterials.TitanylSulfate.getFluid(2000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .save(provider);

        // TiO(SO4) + 4HCl = TiCl4 + H2SO4 + H2O (water voided)
        CHEMICAL_RECIPES.recipeBuilder(GTXMaterials.id("titanyl_sulfate_to_titan_tetrachloride"))
                .duration(160)
                .EUt(960)
                .inputFluids(GTXMaterials.TitanylSulfate.getFluid(1000))
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(4000))
                .outputFluids(GTMaterials.TitaniumTetrachloride.getFluid(1000))
                .outputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .save(provider);
    }
}
