package com.playx.gtx.recipes.chain;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import org.arbor.gtnn.data.GTNNRecipeTypes;

import java.util.function.Consumer;

public class LithiumChain {
    public static void init() {
        GTXMaterials.RoastedSpodumene = new Material.Builder(GTXMod.id("roasted_spodumene"))
                .dust()
                .color(0x3d3d29)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister()
                .setFormula("LiAlSi2O6");

        GTXMaterials.RoastedLepidolite = new Material.Builder(GTXMod.id("roasted_lepidolite"))
                .dust()
                .color(0x470024)
                .iconSet(MaterialIconSet.DULL)
                .buildAndRegister()
                .setFormula("KLi3Al4O11");
        GTXMaterials.DissolvedLithiumOre = new Material.Builder(GTXMod.id("dissolved_lithium_ore"))
                .fluid()
                .color(0x664850)
                .buildAndRegister()
                .setFormula("LiAlO2(H2SO4)");
        GTXMaterials.AluminiumSulfate = new Material.Builder(GTXMod.id("aluminium_sulfate"))
                .dust()
                .color((GTMaterials.Aluminium.getMaterialRGB() + GTMaterials.Sulfur.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.SHINY)
                .components(GTMaterials.Aluminium, 2, GTMaterials.Sulfur, 3, GTMaterials.Oxygen, 12)
                .buildAndRegister();

        GTXMaterials.LithiumCarbonateSolution = new Material.Builder(GTXMod.id("lithium_carbonate_solution"))
                .fluid()
                .color((GTMaterials.Carbon.getMaterialRGB() + GTMaterials.Oxygen.getMaterialRGB()) / 3)
                .buildAndRegister()
                .setFormula("Li2CO3(H20)");

        GTXMaterials.PotassiumSulfate = new Material.Builder(GTXMod.id("potassium_sulfate"))
                .dust()
                .color((GTMaterials.Potassium.getMaterialRGB() + GTMaterials.Sulfur.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FINE)
                .components(GTMaterials.Potassium, 2, GTMaterials.Sulfur, 1, GTMaterials.Oxygen, 4)
                .buildAndRegister();

        GTXMaterials.LithiumChlorideSolution = new Material.Builder(GTXMod.id("lithium_chloride_solution"))
                .fluid()
                .color((GTMaterials.Lithium.getMaterialRGB() + GTMaterials.Chlorine.getMaterialRGB()) / 2)
                .components(GTMaterials.Lithium, 1, GTMaterials.Chlorine, 1, GTMaterials.Water, 1)
                .buildAndRegister();

        GTXMaterials.LithiumFluoride = new Material.Builder(GTXMod.id("lithium_fluoride"))
                .ingot()
                .color(0x757575)
                .iconSet(MaterialIconSet.SHINY)
                .components(GTMaterials.Lithium, 1, GTMaterials.Fluorine, 1)
                .buildAndRegister();

        GTXMaterials.LithiumHydroxide = new Material.Builder(GTXMod.id("lithium_hydroxide"))
                .dust()
                .color((GTMaterials.Lithium.getMaterialRGB() + GTMaterials.Oxygen.getMaterialRGB() + GTMaterials.Hydrogen.getMaterialRGB()) / 3)
                .iconSet(MaterialIconSet.DULL)
                .components(GTMaterials.Lithium, 1, GTMaterials.Oxygen, 1, GTMaterials.Hydrogen, 1)
                .buildAndRegister();

        GTXMaterials.LithiumHydroxideSolution = new Material.Builder(GTXMod.id("lithium_hydroxide_solution"))
                .fluid()
                .components(GTMaterials.Water, 1, GTXMaterials.LithiumHydroxide, 1)
                .color((GTMaterials.Oxygen.getMaterialRGB() + GTMaterials.Hydrogen.getMaterialRGB()) / 3)
                .buildAndRegister();

        GTXMaterials.Fluorite = new Material.Builder(GTXMod.id("fluorite"))
                .dust()
                .color( 0x009933)
                .iconSet(MaterialIconSet.SHINY)
                .components(GTMaterials.Calcium, 1, GTMaterials.Fluorine, 2)
                .ore()
                .buildAndRegister();

        GTXMaterials.Alumina = new Material.Builder(GTXMod.id("alumina"))
                .dust()
                .color(0x0b585c)
                .iconSet(MaterialIconSet.ROUGH)
                .components(GTMaterials.Aluminium, 2, GTMaterials.Oxygen, 3)
                .buildAndRegister();

    }

    public static void lithiumChain(Consumer<FinishedRecipe> provider) {
        // LiAlSi2O6 = LiAlSi2O6
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(GTXMod.id("roast_spodumene"))
                .duration(80)
                .EUt(120)
                .blastFurnaceTemp(1400)
                .inputItems(TagPrefix.dust, GTMaterials.Spodumene, 4)
                .outputItems(TagPrefix.dust, GTXMaterials.RoastedSpodumene, 1)
                .save(provider);

        // KLi3Al4F2O10 + CaO = CaF2 + (KLi3Al4O10)O
        GTRecipeTypes.BLAST_RECIPES.recipeBuilder(GTXMod.id("roast_lepidolite"))
                .duration(160)
                .EUt(120)
                .blastFurnaceTemp(1400)
                .inputItems(TagPrefix.dust, GTMaterials.Lepidolite, 8)
                .inputItems(TagPrefix.dust, GTMaterials.Quicklime, 2)
                .outputItems(TagPrefix.dust, GTXMaterials.RoastedLepidolite, 1)
                .outputItems(TagPrefix.dust, GTXMaterials.Fluorite, 3)
                .save(provider);

        // LiAlSi2O6 + H2SO4 = [LiAlO2 + H2SO4] + 2SiO2
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("dissolved_lithium_ore"))
                .duration(120)
                .EUt(120)
                .inputItems(TagPrefix.dust, GTXMaterials.RoastedSpodumene, 1)
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .outputFluids(GTXMaterials.DissolvedLithiumOre.getFluid(1000))
                .outputItems(TagPrefix.dust, GTMaterials.SiliconDioxide, 6)
                .save(provider);

        // (KLi3Al4O10)O + Al + 3H2SO4 = 3[LiAlO2 + H2SO4] + Al2O3 + K2O
        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("large_dissolved_lithium_ore"))
                .duration(140)
                .EUt(120)
                .inputItems(TagPrefix.dust, GTXMaterials.RoastedLepidolite, 1)
                .inputItems(TagPrefix.dust, GTMaterials.Aluminium, 1)
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(3000))
                .outputFluids(GTXMaterials.DissolvedLithiumOre.getFluid(3000))
                .outputItems(TagPrefix.dust, GTMaterials.Potash, 3)
                .outputItems(TagPrefix.dust, GTXMaterials.Alumina, 5)
                .save(provider);

        // 2[LiAlO2 + H2SO4] + H2SO4 + CO2 = Al2(SO4)3 + [Li2CO3 + H2O] + 2H2O
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("ore_to_lithium_carbonate_solution"))
                .duration(140)
                .EUt(120)
                .inputFluids(GTXMaterials.DissolvedLithiumOre.getFluid(2000))
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(1000))
                .inputFluids(GTMaterials.CarbonDioxide.getFluid(1000))
                .outputItems(TagPrefix.dust, GTXMaterials.AluminiumSulfate, 17)
                .outputFluids(GTXMaterials.LithiumCarbonateSolution.getFluid(1000))
                .outputFluids(GTMaterials.Water.getFluid(2000))
                .save(provider);

        // [Li2CO3 + H2O] + 2HCl + 2Na = 2[LiCl + H2O] + Na2CO3
        // off by 1 oxygen, which is fine since water is lost in dehydrator step
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("lithium_carbonate_to_chloride_solution"))
                .duration(130)
                .EUt(120)
                .inputItems(TagPrefix.dust, GTMaterials.Sodium, 2)
                .inputFluids(GTXMaterials.LithiumCarbonateSolution.getFluid(1000))
                .inputFluids(GTMaterials.HydrochloricAcid.getFluid(2000))
                .outputItems(TagPrefix.dust, GTMaterials.SodaAsh, 6)
                .outputFluids(GTXMaterials.LithiumChlorideSolution.getFluid(2000))
                .save(provider);

        // [LiCl + H2O] = LiCl + H2O (H2O Voided - Dehydrator)
        GTNNRecipeTypes.DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_lithium_chloride_solution"))
                .duration(180)
                .EUt(120)
                .inputFluids(GTXMaterials.LithiumChlorideSolution.getFluid(1000))
                .outputFluids(GTMaterials.Chlorine.getFluid(1000))
                .outputItems(TagPrefix.dust, GTMaterials.Lithium, 1)
                .save(provider);

        // Combined Step - Lepidolite/Spodumene
        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder(GTXMod.id("lepidolite_to_lithium_fluoride"))
                .duration(200)
                .EUt(1920)
                .inputItems(TagPrefix.dust, GTMaterials.Lepidolite, 8)
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(3000))
                .outputItems(TagPrefix.dust, GTXMaterials.LithiumFluoride, 4)
                .outputItems(TagPrefix.dust, GTMaterials.Potash, 3)
                .outputItems(TagPrefix.dust, GTXMaterials.AluminiumSulfate, 17)
                .save(provider);

        GTRecipeTypes.CHEMICAL_BATH_RECIPES.recipeBuilder(GTXMod.id("spodumene_to_lithium_fluoride"))
                .duration(200)
                .EUt(1920)
                .inputItems(TagPrefix.dust, GTMaterials.Spodumene, 8)
                .inputFluids(GTMaterials.SulfuricAcid.getFluid(3000))
                .outputItems(TagPrefix.dust, GTMaterials.SiliconDioxide, 12)
                .outputItems(TagPrefix.dust, GTMaterials.Lithium, 2)
                .outputItems(TagPrefix.dust, GTXMaterials.AluminiumSulfate, 17)
                .save(provider);
    }
}
