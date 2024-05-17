package com.playx.gtx.recipes.categories;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.playx.gtx.GTXMod;
import com.playx.gtx.blocks.GTXBlocks;
import com.playx.gtx.blocks.GTXCoils;
import com.playx.gtx.items.GTXItems;
import com.playx.gtx.materials.GTXMaterials;
import com.playx.gtx.recipes.Helper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import java.util.function.Consumer;

public class CasingRecipes {
    public static void coilCasings(Consumer<FinishedRecipe> provider) {
        Helper.removeRecipeByName(provider, GTRecipeTypes.ASSEMBLER_RECIPES, "coil_cupronickel");
        Helper.removeRecipeByName(provider, GTRecipeTypes.ASSEMBLER_RECIPES, "coil_kanthal");
        Helper.removeRecipeByName(provider, GTRecipeTypes.ASSEMBLER_RECIPES, "coil_nichrome");
        Helper.removeRecipeByName(provider, GTRecipeTypes.ASSEMBLER_RECIPES, "coil_rtm_alloy");
        Helper.removeRecipeByName(provider, GTRecipeTypes.ASSEMBLER_RECIPES, "coil_hssg");
        Helper.removeRecipeByName(provider, GTRecipeTypes.ASSEMBLER_RECIPES, "coil_naquadah");
        Helper.removeRecipeByName(provider, GTRecipeTypes.ASSEMBLER_RECIPES, "coil_trinium");
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_cupronickel"))
                .EUt(8)
                .duration(100)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Cupronickel, 8)
                .inputItems(TagPrefix.dust, GTXMaterials.AluminoSilicateWool, 12)
                .inputItems(TagPrefix.foil, GTMaterials.Bronze, 8)
                .inputFluids(GTMaterials.TinAlloy.getFluid(144))
                .outputItems(GTBlocks.COIL_CUPRONICKEL.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_cupronickel_mica"))
                .EUt(8)
                .duration(200)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Cupronickel, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.Bronze, 8)
                .inputFluids(GTMaterials.TinAlloy.getFluid(144))
                .outputItems(GTBlocks.COIL_CUPRONICKEL.asStack(1))
                .save(provider);


        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_kanthal"))
                .EUt(30)
                .duration(300)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Kanthal, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.Aluminium, 8)
                .inputFluids(GTMaterials.Copper.getFluid(144))
                .outputItems(GTBlocks.COIL_KANTHAL.asStack(1))
                .save(provider);


        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_nichrome"))
                .EUt(120)
                .duration(400)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Nichrome, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.StainlessSteel, 8)
                .inputFluids(GTMaterials.Aluminium.getFluid(144))
                .outputItems(GTBlocks.COIL_NICHROME.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_rtm"))
                .EUt(480)
                .duration(500)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.RTMAlloy, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.VanadiumSteel, 8)
                .inputFluids(GTMaterials.Nichrome.getFluid(144))
                .outputItems(GTBlocks.COIL_RTMALLOY.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_hssg"))
                .EUt(1920)
                .duration(600)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.HSSG, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.TungstenCarbide, 8)
                .inputFluids(GTMaterials.Tungsten.getFluid(144))
                .outputItems(GTBlocks.COIL_HSSG.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_naquadah"))
                .EUt(30720)
                .duration(700)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Naquadah, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.Osmium, 8)
                .inputFluids(GTMaterials.TungstenSteel.getFluid(144))
                .outputItems(GTBlocks.COIL_NAQUADAH.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_trinium"))
                .EUt(122880)
                .duration(800)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Trinium, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.NaquadahEnriched, 8)
                .inputFluids(GTMaterials.Naquadah.getFluid(144))
                .outputItems(GTBlocks.COIL_TRINIUM.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_tritanium"))
                .EUt(491520)
                .duration(900)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Tritanium, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputItems(TagPrefix.foil, GTMaterials.Naquadria, 8)
                .inputFluids(GTMaterials.Trinium.getFluid(144))
                .outputItems(GTBlocks.COIL_TRITANIUM.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_black_steel"))
                .EUt(GTValues.V[GTValues.UEV])
                .duration(100)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.BlackSteel, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputFluids(GTXMaterials.Vibranium.getFluid(144))
                .outputItems(GTXCoils.BLACK_TITANIUM_COIL.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_neutronium"))
                .EUt(GTValues.V[GTValues.UIV])
                .duration(100)
                .inputItems(TagPrefix.wireGtDouble, GTMaterials.Neutronium, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputFluids(GTXMaterials.Cinobite.getFluid(144))
                .outputItems(GTXCoils.NEUTRONIUM_COIL.asStack(1))
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("assemble_coil_cosmic_neutronium"))
                .EUt(GTValues.V[GTValues.OpV])
                .duration(100)
                .inputItems(TagPrefix.wireGtDouble, GTXMaterials.CosmicNeutronium, 8)
                .inputItems(GTXItems.MICA_INSULATOR_FOIL, 8)
                .inputFluids(GTMaterials.Neutronium.getFluid(144))
                .outputItems(GTXCoils.COSMIC_NEUTRONIUM_COIL.asStack(1))
                .save(provider);
    }

    private static void tieredGlass(Consumer<FinishedRecipe> provider) {
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(GTXMod.id("solidify_nickel_glass"))
                .EUt(64)
                .duration(400)
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS.asStack(1))
                .inputFluids(GTMaterials.Nickel.getFluid(GTValues.L))
                .outputItems(GTXBlocks.NICKEL_REINFORCED_GLASS.asStack(1))
                .save(provider);

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(GTXMod.id("solidify_chrome_glass"))
                .EUt(256)
                .duration(400)
                .inputItems(GTXBlocks.NICKEL_REINFORCED_GLASS.asStack(1))
                .inputFluids(GTMaterials.Chromium.getFluid(GTValues.L))
                .outputItems(GTXBlocks.CHROME_REINFORCED_GLASS.asStack(1))
                .save(provider);

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(GTXMod.id("solidify_tungsten_glass"))
                .EUt(1024)
                .duration(400)
                .inputItems(GTXBlocks.CHROME_REINFORCED_GLASS.asStack(1))
                .inputFluids(GTMaterials.Tungsten.getFluid(GTValues.L))
                .outputItems(GTXBlocks.TUNGSTEN_REINFORCED_GLASS.asStack(1))
                .save(provider);

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(GTXMod.id("solidify_iridium_glass"))
                .EUt(4096)
                .duration(400)
                .inputItems(GTXBlocks.TUNGSTEN_REINFORCED_GLASS.asStack(1))
                .inputFluids(GTMaterials.Iridium.getFluid(GTValues.L))
                .outputItems(GTXBlocks.IRIDIUM_REINFORCED_GLASS.asStack(1))
                .save(provider);

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder(GTXMod.id("solidify_osmiridium_glass"))
                .EUt(16384)
                .duration(400)
                .inputItems(GTXBlocks.IRIDIUM_REINFORCED_GLASS.asStack(1))
                .inputFluids(GTMaterials.Chromium.getFluid(GTValues.L))
                .outputItems(GTXBlocks.OSMIRIDIUM_REINFORCED_GLASS.asStack(1))
                .save(provider);
    }
    public static void init(Consumer<FinishedRecipe> provider) {
        coilCasings(provider);
        tieredGlass(provider) ;
    }
}
