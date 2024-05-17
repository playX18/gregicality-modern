package com.playx.gtx.materials.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.playx.gtx.recipes.producers.MixerRecipeProducer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class MixProperty implements IMaterialProperty<MixProperty> {

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.DUST);
    }

    public MixProperty() {}

    @Getter
    @Setter
    @NotNull
    private MixerRecipeProducer recipeProducer = MixerRecipeProducer.DEFAULT_PRODUCER;
}
