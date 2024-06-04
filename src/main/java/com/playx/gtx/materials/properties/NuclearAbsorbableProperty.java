package com.playx.gtx.materials.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.playx.gtx.fission.INeutronBehaviour;

public class NuclearAbsorbableProperty implements IMaterialProperty<NuclearAbsorbableProperty> {
    public final int desintegrationMax;

    public NuclearAbsorbableProperty(int desintegrationMax) {
        this.desintegrationMax = desintegrationMax;
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.INGOT);


    }
}
