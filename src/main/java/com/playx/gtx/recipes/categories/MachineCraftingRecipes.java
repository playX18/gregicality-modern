package com.playx.gtx.recipes.categories;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class MachineCraftingRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        hullOverride(provider);
    }

    private static void hullOverride(Consumer<FinishedRecipe> provider) {
        // UHV+ casings
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTXMod.id("casing_uev"), GTBlocks.MACHINE_CASING_UEV.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft),
                "PPP", "PwP", "PPP",
                'P', new UnificationEntry(TagPrefix.plate, GTMaterials.Bohrium));

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTXMod.id("casing_uiv"), GTBlocks.MACHINE_CASING_UIV.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft),
                "PPP", "PwP", "PPP",
                'P', new UnificationEntry(TagPrefix.plate, GTXMaterials.Quantum));

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTXMod.id("casing_uxv"), GTBlocks.MACHINE_CASING_UXV.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft),
                "PPP", "PwP", "PPP",
                'P', new UnificationEntry(TagPrefix.plate, GTXMaterials.BlackTitanium));

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("casing_uev_asm"))
                .outputItems(GTBlocks.MACHINE_CASING_UEV.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .inputItems(TagPrefix.plate, GTMaterials.Bohrium, 8)
                .circuitMeta(8)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("casing_uiv_asm"))
                .outputItems(GTBlocks.MACHINE_CASING_UEV.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .inputItems(TagPrefix.plate, GTXMaterials.Quantum, 8)
                .circuitMeta(8)
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("casing_uxv_asm"))
                .outputItems(GTBlocks.MACHINE_CASING_UXV.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .inputItems(TagPrefix.plate, GTXMaterials.Quantum, 8)
                .circuitMeta(8)
                .save(provider);


        // UHV+ hulls

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTXMod.id("hull_uev"),
                GTMachines.HULL[GTValues.UEV].asStack(1),
                "PHP", "CMC",
                'M', GTBlocks.MACHINE_CASING_UEV,
                'C', new UnificationEntry(TagPrefix.cableGtSingle, GTXMaterials.Pikyonium),
                'H', new UnificationEntry(TagPrefix.plate, GTMaterials.Bohrium),
                'P', new UnificationEntry(TagPrefix.plate, GTXMaterials.Polyetheretherketone));
        VanillaRecipeHelper.addShapedRecipe(provider, true, GTXMod.id("hull_uiv"),
                GTMachines.HULL[GTValues.UIV].asStack(1),
                "PHP", "CMC",
                'M', GTBlocks.MACHINE_CASING_UIV,
                'C', new UnificationEntry(TagPrefix.cableGtQuadruple, GTXMaterials.Cinobite),
                'H', new UnificationEntry(TagPrefix.plate, GTXMaterials.Quantum),
                'P', new UnificationEntry(TagPrefix.plate, GTXMaterials.Polyetheretherketone));

        VanillaRecipeHelper.addShapedRecipe(provider, true, GTXMod.id("hull_uxv"),
                GTMachines.HULL[GTValues.UXV].asStack(1),
                "PHP", "CMC",
                'M', GTBlocks.MACHINE_CASING_UXV,
                'C', new UnificationEntry(TagPrefix.cableGtQuadruple, GTXMaterials.NaquadriaticTaranium),
                'H', new UnificationEntry(TagPrefix.plate, GTXMaterials.BlackTitanium),
                'P', new UnificationEntry(TagPrefix.plate, GTXMaterials.Zylon));

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("hull_uev_asm"))
                .inputItems(GTBlocks.MACHINE_CASING_UEV.asStack(1))
                .inputItems(TagPrefix.cableGtSingle, GTXMaterials.Pikyonium, 2)
                .inputFluids(GTXMaterials.Polyetheretherketone.getFluid(GTValues.L * 2))
                .outputItems(GTMachines.HULL[GTValues.UEV])
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("hull_uiv_asm"))
                .inputItems(GTBlocks.MACHINE_CASING_UIV.asStack(1))
                .inputItems(TagPrefix.cableGtQuadruple, GTXMaterials.Cinobite, 2)
                .inputFluids(GTXMaterials.Polyetheretherketone.getFluid(GTValues.L * 2))
                .outputItems(GTMachines.HULL[GTValues.UIV])
                .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTXMod.id("hull_uxv_asm"))
                .inputItems(GTBlocks.MACHINE_CASING_UXV.asStack(1))
                .inputItems(TagPrefix.cableGtQuadruple, GTXMaterials.NaquadriaticTaranium, 2)
                .inputFluids(GTXMaterials.Zylon.getFluid(GTValues.L * 2))
                .outputItems(GTMachines.HULL[GTValues.UXV])
                .save(provider);
    }
}
