package com.playx.gtx.materials.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.playx.gtx.recipes.producers.NuclearRecipeProducer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class NuclearProperty implements IMaterialProperty<NuclearProperty> {

    @Override
    public void verifyProperty(MaterialProperties properties) {

    }

    public NuclearProperty() {}

    @Getter
    @Setter
    @NotNull
    private NuclearRecipeProducer recipeProducer = NuclearRecipeProducer.DEFAULT_PRODUCER;
}
