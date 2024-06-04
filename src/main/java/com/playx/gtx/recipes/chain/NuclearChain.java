package com.playx.gtx.recipes.chain;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import dev.arbor.gtnn.data.GTNNRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.playx.gtx.items.GTXItems.*;
import static com.playx.gtx.materials.GTXMaterials.*;

public class NuclearChain {
    public static void nuclearChain(Consumer<FinishedRecipe> provider) {
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_protactinium_waste"))
                .EUt(30)
                .duration(300)
                .inputItems(PROTACTINIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 560, 0)
                .chancedOutput(TagPrefix.dust, Protactinium233.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, Thorium, 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_thorium_waste"))
                .EUt(30)
                .duration(300)
                .inputItems(THORIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 560, 0)
                .chancedOutput(TagPrefix.dust, Protactinium233.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, Uranium238, 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_uranium_waste"))
                .EUt(60)
                .duration(300)
                .inputItems(URANIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 760, 0)
                .chancedOutput(TagPrefix.dust, UraniumRadioactive.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, GTXMaterials.Neptunium.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_neptunium_waste"))
                .EUt(120)
                .duration(300)
                .inputItems(NEPTUNIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, GTXMaterials.Neptunium.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, PlutoniumRadioactive.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_plutonium_waste"))
                .EUt(240)
                .duration(300)
                .inputItems(PLUTONIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, PlutoniumRadioactive.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, AmericiumRadioactive.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_americium_waste"))
                .EUt(480)
                .duration(300)
                .inputItems(AMERICIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, AmericiumRadioactive.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, GTXMaterials.Curium.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_curium_waste"))
                .EUt(960)
                .duration(300)
                .inputItems(CURIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, GTXMaterials.Curium.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, GTXMaterials.Berkelium.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_berkelium_waste"))
                .EUt(1920)
                .duration(300)
                .inputItems(BERKELIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, GTXMaterials.Berkelium.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, GTXMaterials.Californium.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_californium_waste"))
                .EUt(3840)
                .duration(300)
                .inputItems(CALIFORNIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, GTXMaterials.Californium.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, GTXMaterials.Einsteinium.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_einsteinium_waste"))
                .EUt(7680)
                .duration(300)
                .inputItems(EINSTEINIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, GTXMaterials.Einsteinium.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, GTXMaterials.Fermium.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_fermium_waste"))
                .EUt(15360)
                .duration(300)
                .inputItems(FERMIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, GTXMaterials.Fermium.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, GTXMaterials.Mendelevium.getMaterial(), 1)
                .save(provider);

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("recycle_mendelevium_waste"))
                .EUt(30720)
                .duration(300)
                .inputItems(MENDELEVIUM_WASTE.asStack(1))
                .chancedOutput(NUCLEAR_WASTE.asStack(1), 1000, 0)
                .chancedOutput(TagPrefix.dust, GTXMaterials.Mendelevium.getMaterial(), 1, 3000, 0)
                .outputItems(TagPrefix.dust, Nobelium, 1)
                .save(provider);
        // 3K + 7Na -> Na7K3
        MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_sodium_potassium_alloy"))
                .EUt(120)
                .duration(300)
                .inputItems(TagPrefix.dust, Potassium, 3)
                .inputItems(TagPrefix.dust, Sodium, 7)
                .outputItems(TagPrefix.dust, SodiumPotassiumAlloy, 10)
                .save(provider);
        // LiOH(H2O) + HF -> LiF + 2H2O
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("lithium_hydroxide_to_fluoride"))
                .EUt(30)
                .duration(200)
                .inputFluids(LithiumHydroxideSolution.getFluid(1000))
                .inputFluids(HydrofluoricAcid.getFluid(1000))
                .outputItems(TagPrefix.dust, LithiumFluoride, 2)
                .outputFluids(Water.getFluid(2000))
                .save(provider);
        // Na + F -> NaF
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("mix_sodium_fluoride"))
                .EUt(120)
                .duration(300)
                .inputItems(TagPrefix.dust, Sodium, 1)
                .inputFluids(Fluorine.getFluid(1000))
                .outputItems(TagPrefix.dust, SodiumFluoride, 2)
                .save(provider);

        // K + F -> KF
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("mix_potassium_fluoride"))
                .EUt(120)
                .duration(300)
                .inputItems(TagPrefix.dust, Potassium, 1)
                .inputFluids(Fluorine.getFluid(1000))
                .outputItems(TagPrefix.dust, PotassiumFluoride, 2)
                .save(provider);
        // LiF + NaF + KF -> LiNaKF3
        MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_flinak"))
                .EUt(64)
                .duration(480)
                .inputItems(TagPrefix.dust, LithiumFluoride, 2)
                .inputItems(TagPrefix.dust, SodiumFluoride, 2)
                .inputItems(TagPrefix.dust, PotassiumFluoride, 2)
                .outputItems(TagPrefix.dust, FLiNaK, 6)
                .save(provider);
        // Be + 2F -> BeF2
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("mix_beryllium_fluoride"))
                .EUt(120)
                .duration(300)
                .inputItems(TagPrefix.dust, Beryllium, 1)
                .inputFluids(Fluorine.getFluid(2000))
                .outputItems(TagPrefix.dust, BerylliumFluoride, 3)
                .save(provider);

        // LiF + BeF2 -> F3LiBe
        MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_fl_3_libe"))
                .EUt(64)
                .duration(480)
                .inputItems(TagPrefix.dust, LithiumFluoride, 2)
                .inputItems(TagPrefix.dust, BerylliumFluoride, 3)
                .outputItems(TagPrefix.dust, FLiBe, 5)
                .save(provider);

        // 3Pb + 7Bi -> Pb3Bi7
        MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_lead_bismuth_alloy_1"))
                .EUt(120)
                .duration(300)
                .inputItems(TagPrefix.dust, Lead, 3)
                .inputItems(TagPrefix.dust, Bismuth, 7)
                .outputItems(TagPrefix.dust, LeadBismuthEutectic, 10)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("mix_lead_bismuth_alloy_2"))
                .EUt(16)
                .duration(300)
                .inputItems(TagPrefix.dust, Lead, 3)
                .inputItems(TagPrefix.dust, Bismuth, 7)
                .outputItems(TagPrefix.dust, LeadBismuthEutectic, 10)
                .save(provider);
        ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("mix_lead_bismuth_alloy_3"))
                .EUt(16)
                .duration(300)
                .inputItems(TagPrefix.ingot, Lead, 3)
                .inputItems(TagPrefix.dust, Bismuth, 7)
                .outputItems(TagPrefix.dust, LeadBismuthEutectic, 10)
                .save(provider);
        ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("mix_lead_bismuth_alloy_4"))
                .EUt(16)
                .duration(300)
                .inputItems(TagPrefix.dust, Lead, 3)
                .inputItems(TagPrefix.ingot, Bismuth, 7)
                .outputItems(TagPrefix.dust, LeadBismuthEutectic, 10)
                .save(provider);

        ALLOY_SMELTER_RECIPES.recipeBuilder(GTXMod.id("mix_lead_bismuth_alloy_5"))
                .EUt(16)
                .duration(300)
                .inputItems(TagPrefix.ingot, Lead, 3)
                .inputItems(TagPrefix.ingot, Bismuth, 7)
                .outputItems(TagPrefix.dust, LeadBismuthEutectic, 10)
                .save(provider);

        FURNACE_RECIPES.recipeBuilder(GTXMod.id("protactinium_233_to_protactinium"))
                .inputItems(TagPrefix.ingot, Protactinium233.getMaterial(), 1)
                .outputItems(TagPrefix.ingot, GTMaterials.Protactinium, 1)
                .save(provider);
        FURNACE_RECIPES.recipeBuilder(GTXMod.id("protactinium_to_protactinium_233"))
                .inputItems(TagPrefix.ingot, Protactinium233.getMaterial(), 1)
                .outputItems(TagPrefix.ingot, GTMaterials.Protactinium, 1)
                .save(provider);

        var DEHYDRATOR_RECIPES = GTNNRecipeTypes.INSTANCE.getDEHYDRATOR_RECIPES();

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_nuclear_waste"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE.asStack())
                .chancedOutput(NUCLEAR_WASTE_LANTHANIDE_A.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_LANTHANIDE_B.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_ALKALINE.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_HEAVY_METAL.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_A.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_B.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_C.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_REACTIVE_NONMETAL.asStack(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METALOID.asStack(), 1111, 111)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_heavy_metal_waste"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_HEAVY_METAL.asStack(9))
                .inputFluids(Mercury.getFluid(250 * 9))
                .chancedOutput(TagPrefix.dust, Zinc, 2, 5555, 200)
                .chancedOutput(TagPrefix.dust, Gallium, 2, 5555, 300)
                .chancedOutput(TagPrefix.dust, Cadmium, 2, 5555, 400)
                .chancedOutput(TagPrefix.dust, Indium, 2, 5555, 500)
                .chancedOutput(TagPrefix.dust, Tin, 2, 5555, 600)
                .chancedOutput(TagPrefix.dust, Thallium, 2, 5555, 700)
                .chancedOutput(TagPrefix.dust, Lead, 2, 5555, 800)
                .chancedOutput(TagPrefix.dust, Bismuth, 2, 5555, 900)
                .chancedOutput(TagPrefix.dust, Polonium, 2, 5555, 1000)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehdyrate_lanthanide_group_a_waste"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_LANTHANIDE_A.asStack(9))
                .chancedOutput(TagPrefix.dust, Dysprosium, 2, 8333, 500)
                .chancedOutput(TagPrefix.dust, Holmium, 2, 8333, 600)
                .chancedOutput(TagPrefix.dust, Erbium, 2, 8333, 700)
                .chancedOutput(TagPrefix.dust, Thulium, 2, 8333, 800)
                .chancedOutput(TagPrefix.dust, Ytterbium, 2, 8333, 900)
                .chancedOutput(TagPrefix.dust, Lutetium, 2, 8333, 1000)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_lanthanide_group_b_waste"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_LANTHANIDE_B.asStack(9))
                .chancedOutput(TagPrefix.dust, Lanthanum, 2, 5555, 200)
                .chancedOutput(TagPrefix.dust, Cerium, 2, 5555, 300)
                .chancedOutput(TagPrefix.dust, Praseodymium, 2, 5555, 400)
                .chancedOutput(TagPrefix.dust, Neodymium, 2, 5555, 500)
                .chancedOutput(TagPrefix.dust, Promethium, 2, 5555, 600)
                .chancedOutput(TagPrefix.dust, Samarium, 2, 5555, 700)
                .chancedOutput(TagPrefix.dust, Europium, 2, 5555, 800)
                .chancedOutput(TagPrefix.dust, Gadolinium, 2, 5555, 900)
                .chancedOutput(TagPrefix.dust, Terbium, 2, 5555, 1000)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_metal_waste_group_a"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_METAL_A.asStack(9))
                .chancedOutput(TagPrefix.dust, Hafnium, 2, 6250, 300)
                .chancedOutput(TagPrefix.dust, Tantalum, 2, 6250, 400)
                .chancedOutput(TagPrefix.dust, Tungsten, 2, 6250, 500)
                .chancedOutput(TagPrefix.dust, Osmium, 2, 6250, 700)
                .chancedOutput(TagPrefix.dust, Iridium, 2, 6250, 800)
                .chancedOutput(TagPrefix.dust, Platinum, 2, 6250, 900)
                .chancedOutput(TagPrefix.dust, Gold, 2, 6250, 1000)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_metal_waste_group_b"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_METAL_B.asStack(9))
                .chancedOutput(TagPrefix.dust, Yttrium, 2, 5555, 200)
                .chancedOutput(TagPrefix.dust, Zirconium, 2, 5555, 300)
                .chancedOutput(TagPrefix.dust, Niobium, 2, 5555, 400)
                .chancedOutput(TagPrefix.dust, Molybdenum, 2, 5555, 500)
                .chancedOutput(TagPrefix.dust, Technetium, 2, 5555, 600)
                .chancedOutput(TagPrefix.dust, Ruthenium, 2, 5555, 700)
                .chancedOutput(TagPrefix.dust, Rhodium, 2, 5555, 800)
                .chancedOutput(TagPrefix.dust, Palladium, 2, 5555, 900)
                .chancedOutput(TagPrefix.dust, Silver, 2, 5555, 1000)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_metal_waste_group_c"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_METAL_C.asStack(9))
                .outputItems(TagPrefix.dust, Iron, 2)
                .outputItems(TagPrefix.dust, Cobalt, 2)
                .outputItems(TagPrefix.dust, Nickel, 2)
                .outputItems(TagPrefix.dust, Copper, 2)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_metaloid_waste"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_METALOID.asStack(9))
                .chancedOutput(TagPrefix.dust, Germanium, 2, 6250, 500)
                .chancedOutput(TagPrefix.dust, Arsenic, 2, 6250, 600)
                .chancedOutput(TagPrefix.dust, Antimony, 2, 6250, 700)
                .chancedOutput(TagPrefix.dust, Tellurium, 2, 6250, 800)
                .chancedOutput(TagPrefix.dust, Astatine, 2, 6250, 900)
                .chancedOutput(TagPrefix.dust, Actinium, 2, 6250, 1000)
                .save(provider);

        DEHYDRATOR_RECIPES.recipeBuilder(GTXMod.id("dehydrate_waste_alkaline"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_ALKALINE.asStack(9))
                .chancedOutput(TagPrefix.dust, Rubidium, 2, 6250, 500)
                .chancedOutput(TagPrefix.dust, Strontium, 2, 6250, 600)
                .chancedOutput(TagPrefix.dust, Caesium, 2, 6250, 700)
                .chancedOutput(TagPrefix.dust, Barium, 2, 6250, 800)
                .chancedOutput(TagPrefix.dust, Francium, 2, 6250, 900)
                .chancedOutput(TagPrefix.dust, Radium, 2, 6250, 1000)
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder(GTXMod.id("centrifuge_nonmetal_waste"))
                .EUt(32)
                .duration(300)
                .inputItems(NUCLEAR_WASTE_REACTIVE_NONMETAL.asStack(9))
                .outputFluids(Krypton.getFluid(250 * 9))
                .outputFluids(Xenon.getFluid(500 * 9))
                .outputFluids(Radon.getFluid(9000))
                .chancedOutput(TagPrefix.dust, Selenium, 2, 6250, 800)
                .chancedOutput(TagPrefix.dust, Bromine, 2, 6250, 900)
                .chancedOutput(TagPrefix.dust, Iodine, 2, 6250, 1000)
                .save(provider);

    }
}
