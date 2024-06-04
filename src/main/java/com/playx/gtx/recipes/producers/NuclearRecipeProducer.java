package com.playx.gtx.recipes.producers;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.playx.gtx.recipes.NuclearHandler;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class NuclearRecipeProducer {
    public static final NuclearRecipeProducer DEFAULT_PRODUCER = new NuclearRecipeProducer();

    public void produce(Material material, Consumer<FinishedRecipe> provider) {
        NuclearHandler.processNuclearMaterial(material, provider);
    }
}
