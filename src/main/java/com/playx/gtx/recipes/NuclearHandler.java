package com.playx.gtx.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import com.playx.gtx.materials.GTXTags;
import com.playx.gtx.materials.IsotopeMaterial;
import com.playx.gtx.materials.RadioactiveMaterial;
import dev.arbor.gtnn.data.GTNNMaterials;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class NuclearHandler {

    public static void processNuclearDepletedFuel(Material material, @NotNull Consumer<FinishedRecipe> provider) {
        var isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);

        if (isotopeMaterial == null) {
            return;
        }

        // Fuel + O = [Fuel + O]
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("oxidize_" + isotopeMaterial.getMaterial().getName()))
                .duration(300)
                .EUt(30)
                .inputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuel, 1))
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .outputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuelOxide, 1))
                .save(provider);

        // HNO3 + [Fuel + O] + O = [Fuel + NO3 + H2O]
        LARGE_CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("nitrate_solution_" + isotopeMaterial.getMaterial().getName()))
                .duration(1000)
                .EUt(30)
                .notConsumable(TagPrefix.dust, GTMaterials.Boron)
                .inputFluids(GTMaterials.NitricAcid.getFluid(1000))
                .inputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuelOxide, 1))
                .outputFluids(Objects.requireNonNull(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000)))
                .save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder(GTXMod.id("nitride_" + isotopeMaterial.getMaterial().getName()))
                .duration(1000)
                .EUt(30)
                .inputFluids(Objects.requireNonNull(isotopeMaterial.getFluidDepletedFuelNitrateSolution(1000)))
                .notConsumableFluid(GTNNMaterials.Hydrazine.getFluid(1000))
                .notConsumableFluid(GTNNMaterials.RP1.getFluid(1000))
                .notConsumableFluid(GTXMaterials.TributylPhosphate.getFluid(1000))
                .notConsumable(TagPrefix.dust, GTMaterials.FerriteMixture)
                .outputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuelNitride, 1))
                .save(provider);

        GTXMod.LOGGER.info("Processing nuclear depleted fuel for " + isotopeMaterial.getMaterial().getName() + " into " + isotopeMaterial.getRadioactiveMaterial());

        ELECTROLYZER_RECIPES.recipeBuilder(GTXMod.id("electrolyze_" + isotopeMaterial.getMaterial().getName()))
                .duration(1000)
                .EUt(30)
                .inputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuelNitride, 1))
                .outputItems(isotopeMaterial.getRadioactiveMaterial().waste.asStack(1))
                .save(provider);
    }

    public static void processNuclearFuel(Material material, @NotNull Consumer<FinishedRecipe> provider) {
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);
        if (isotopeMaterial == null) {
            return;
        }

        // Fuel + O = [Fuel + O]
        CHEMICAL_RECIPES.recipeBuilder(GTXMod.id(isotopeMaterial.getMaterial().getName() + "_oxidize"))
                .duration(300)
                .EUt(30)
                .inputItems(TagPrefix.ingot, isotopeMaterial.getMaterial(), 1)
                .inputFluids(GTMaterials.Oxygen.getFluid(1000))
                .outputItems(isotopeMaterial.getItemStack(GTXTags.oxide, 1))
                .save(provider);

        // Fuel2N3 + 4H2O + 3O = 2[Fuel + 2O] + H2O + NO2 + 2NH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder(GTXMod.id(isotopeMaterial.getMaterial().getName() + "_nitride_to_oxide"))
                .EUt(30)
                .duration(300)
                .inputItems(isotopeMaterial.getItemStack(GTXTags.nitride, 1))
                .inputFluids(GTMaterials.Water.getFluid(4000))
                .inputFluids(GTMaterials.Oxygen.getFluid(3000))
                .outputItems(isotopeMaterial.getItemStack(GTXTags.oxide, 2))
                .outputFluids(GTMaterials.Water.getFluid(1000))
                .outputFluids(GTMaterials.NitrogenDioxide.getFluid(1000))
                .outputFluids(GTMaterials.Ammonia.getFluid(2000));

        EXTRUDER_RECIPES.recipeBuilder(GTXMod.id(isotopeMaterial.getMaterial().getName() + "_fuel_oxide"))
                .duration(200)
                .EUt(30)
                .notConsumable(GTItems.SHAPE_MOLD_BALL)
                .inputItems(isotopeMaterial.getItemStack(GTXTags.oxide, 1))
                .outputItems(isotopeMaterial.getItemStack(GTXTags.fuelOxide, 1))
                .save(provider);

        EXTRUDER_RECIPES.recipeBuilder(GTXMod.id(isotopeMaterial.getMaterial().getName() + "_fuel_pure"))
                .duration(200)
                .EUt(30)
                .notConsumable(GTItems.SHAPE_MOLD_BALL)
                .inputItems(isotopeMaterial.getItemStack(GTXTags.nitride, 1))
                .outputItems(isotopeMaterial.getItemStack(GTXTags.fuelPure, 1))
                .save(provider);
    }

    public static void processNuclearMaterial(Material material, @NotNull Consumer<FinishedRecipe> provider) {
        RadioactiveMaterial radioactiveMaterial = RadioactiveMaterial.REGISTRY.get(material);
        IsotopeMaterial isotopeMaterial = IsotopeMaterial.REGISTRY.get(material);

        if (radioactiveMaterial != null && !radioactiveMaterial.composition.isEmpty()) {
            int complexity = radioactiveMaterial.complexity;

            // Mat + 2HNO3 = [Mat + 2NO3] + 2H
            CHEMICAL_RECIPES.recipeBuilder(GTXMod.id(radioactiveMaterial.getMaterial().getName() + "_nitrite"))
                    .duration(200 * complexity / 100)
                    .inputItems(TagPrefix.dust, radioactiveMaterial.getMaterial())
                    .inputFluids(GTMaterials.NitricAcid.getFluid(2000))
                    .outputItems(radioactiveMaterial.getItemStack(GTXTags.nitrite, 3))
                    .outputFluids(GTMaterials.Hydrogen.getFluid(2000))
                    .save(provider);

            ARC_FURNACE_RECIPES.recipeBuilder(GTXMod.id(radioactiveMaterial.getMaterial().getName() + "_nitride"))
                    .duration(200 * complexity / 100)
                    .EUt(120)
                    .inputItems(TagPrefix.ingot, radioactiveMaterial.getMaterial(), 2)
                    .inputFluids(GTMaterials.Nitrogen.getFluid(3000))
                    .outputItems(GTXTags.nitride, radioactiveMaterial.getMaterial(), 5)
                    .save(provider);

            // [Mat + 2NO3] = [Mat + 2O] + 2NO
            BLAST_RECIPES.recipeBuilder(GTXMod.id(radioactiveMaterial.getMaterial().getName() + "_nitrite_blast"))
                    .duration(90 * complexity / 100)
                    .blastFurnaceTemp(600)
                    .EUt(120)
                    .inputItems(radioactiveMaterial.getItemStack(GTXTags.nitrite, 3))
                    .outputItems(radioactiveMaterial.getItemStack(GTXTags.dioxide, 1))
                    .outputFluids(GTMaterials.NitricOxide.getFluid(2000))
                    .save(provider);

            // [Mat + 2O] + 6Cl = [Mat + 6Cl] + 2O
            CHEMICAL_RECIPES.recipeBuilder(GTXMod.id(radioactiveMaterial.getMaterial().getName() + "_dioxide_to_hexafluoride"))
                    .duration(1000 * complexity / 100)
                    .inputItems(radioactiveMaterial.getItemStack(GTXTags.dioxide, 1))
                    .inputFluids(GTMaterials.Chlorine.getFluid(6000))
                    .outputFluids(Objects.requireNonNull(radioactiveMaterial.getFluidHexachloride(1000)))
                    .outputFluids(GTMaterials.Oxygen.getFluid(2000))
                    .save(provider);

            // [Mat + 6Cl] + 6HF = 6HCl + [Mat + 6F]
            CHEMICAL_RECIPES.recipeBuilder(GTXMod.id(radioactiveMaterial.getMaterial().getName() + "_hexachloride_to_hexafluoride"))
                    .duration(1000 * complexity / 100)
                    .inputFluids(Objects.requireNonNull(radioactiveMaterial.getFluidHexachloride(1000)))
                    .inputFluids(GTMaterials.HydrofluoricAcid.getFluid(6000))
                    .outputFluids(GTMaterials.HydrochloricAcid.getFluid(6000))
                    .outputFluids(Objects.requireNonNull(radioactiveMaterial.getFluidHexafluoride(1000)))
                    .save(provider);


        } else if (isotopeMaterial != null && isotopeMaterial.fissile) {
            IntStream.range(1, 6).forEach(operand -> {
                GTXRecipeTypes.NUCLEAR_REACTOR_RECIPES.recipeBuilder(GTXMod.id(isotopeMaterial.getMaterial().getName() + "_fuel_" + operand))
                        .duration(8000)
                        .EUt(((long) (isotopeMaterial.baseHeat + operand) * operand * 2) * 105 / 100)
                        .circuitMeta(operand + 10)
                        .inputItems(isotopeMaterial.getItemStack(GTXTags.fuelOxide, operand))
                        .outputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuel, operand))
                        .save(provider);
            });

            IntStream.range(1, 6).forEach(operand ->
                    IsotopeMaterial.REGISTRY.entrySet().stream()
                            .filter(entry -> entry.getValue().fertile)
                            .forEach(isotopeMaterialEntry -> {
                                GTXRecipeTypes.NUCLEAR_REACTOR_RECIPES
                                        .recipeBuilder(GTXMod.id(isotopeMaterial.getMaterial().getName() + "_fuel_" + operand + "_with_" + isotopeMaterialEntry.getKey().getName()))
                                        .duration(16000)
                                        .EUt(((long) (isotopeMaterial.baseHeat + operand) * operand / 2) * 105 / 100)
                                        .circuitMeta(operand)
                                        .inputItems(isotopeMaterial.getItemStack(GTXTags.fuelOxide, operand))
                                        .inputItems(isotopeMaterialEntry.getValue().getItemStack(GTXTags.fuelPure, 9))
                                        .outputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuelOxide, operand))
                                        .outputItems(isotopeMaterialEntry.getValue().getItemStack(GTXTags.depletedFuel, 9))
                                        .save(provider);

                                var builder2 = GTXRecipeTypes.NUCLEAR_BREEDER_REACTOR_RECIPES
                                        .recipeBuilder(GTXMod.id(isotopeMaterial.getMaterial().getName() + "_fuel_oxide_" + operand + "_with_" + isotopeMaterialEntry.getKey().getName()))
                                        .circuitMeta(operand)
                                        .duration(4000)
                                        .EUt((long) (isotopeMaterial.baseHeat + operand) * operand * 105 / 100)
                                        .inputItems(isotopeMaterial.getItemStack(GTXTags.fuelOxide, operand))
                                        .inputItems(isotopeMaterialEntry.getValue().getItemStack(GTXTags.fuelPure, 9))
                                        .outputItems(isotopeMaterial.getItemStack(GTXTags.depletedFuelOxide, operand));

                                isotopeMaterialEntry.getValue().isotopeDecay.forEach((key, value) -> {
                                    builder2.chancedOutput(key.getItemStack(GTXTags.depletedFuel, 9), value, 100);
                                });

                                builder2.save(provider);
                            }));
        }
    }
}
