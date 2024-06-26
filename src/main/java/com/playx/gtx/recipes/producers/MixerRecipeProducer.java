package com.playx.gtx.recipes.producers;

import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import com.playx.gtx.recipes.GTXRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MixerRecipeProducer {

    public static final MixerRecipeProducer DEFAULT_PRODUCER = new MixerRecipeProducer();


    public void produce(@NotNull Material material, Consumer<FinishedRecipe> provider) {
        if (material.getMaterialComponents().size() <= 1 || material.getBlastTemperature() == 0 || material.hasFlag(GTXMaterials.DISABLE_AUTOGENERATED_MIXER_RECIPE)) {
            GTXMod.LOGGER.info("No need to produce mixer recipe for {}", material);
            return;
        }

        int fluidCount = (int) material.getMaterialComponents().stream().filter(mat -> mat.material().hasProperty(PropertyKey.FLUID) && !mat.material().hasProperty(PropertyKey.DUST)).count();
        int itemCount = material.getMaterialComponents().size() - fluidCount;


        GTRecipeBuilder builder;

        // try to fit in mixer
        if (itemCount <= GTRecipeTypes.MIXER_RECIPES.getMaxInputs(ItemRecipeCapability.CAP)
                && fluidCount <= GTRecipeTypes.MIXER_RECIPES.getMaxInputs(FluidRecipeCapability.CAP)) {
            builder = GTRecipeTypes.MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_" + material.getName()))
            ;
        }
        // try to fit in large mixer
        else if (itemCount <= GTXRecipeTypes.LARGE_MIXER_RECIPES.getMaxInputs(ItemRecipeCapability.CAP)
                && fluidCount <= GTXRecipeTypes.LARGE_MIXER_RECIPES.getMaxInputs(FluidRecipeCapability.CAP)) {
            builder = GTXRecipeTypes.LARGE_MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_" + material.getName()))
                    .circuitMeta(material.getMaterialComponents().size());
        } else {
            // cannot fit in either
            GTXMod.LOGGER.error("Failed to generate mixer recipe for {}", material);
            return;
        }

        int totalMaterial = 0;

        for (var stack : material.getMaterialComponents()) {
            var amount = (int) stack.amount();

            if (stack.material().hasProperty(PropertyKey.DUST)) {
                builder.inputItems(TagPrefix.dust, stack.material(), amount);
            } else if (stack.material().hasProperty(PropertyKey.FLUID)) {
                builder.inputFluids(stack.material().getFluid(1000L * amount));
            }

            totalMaterial += amount;
        }

        builder.outputItems(TagPrefix.dust, material, totalMaterial)
                .duration((int) (material.getMass() * totalMaterial))
                .EUt(30)
                .save(provider);
    }

}
