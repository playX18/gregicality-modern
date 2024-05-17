package com.playx.gtx.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.playx.gtx.materials.properties.GTXPropertyKeys;
import com.playx.gtx.materials.properties.MixProperty;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class RecipeHandler {
    public static void init(Consumer<FinishedRecipe> provider) {

        TagPrefix.ingot.executeHandler(provider, GTXPropertyKeys.MIX, RecipeHandler::generateMixerRecipes);
    }

    private static void generateMixerRecipes(@Nullable TagPrefix unused, @NotNull Material material,
                                             @NotNull MixProperty property,
                                             @NotNull Consumer<FinishedRecipe> provider) {
        property.getRecipeProducer().produce(material, provider);
    }
}
