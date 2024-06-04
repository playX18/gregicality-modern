package com.playx.gtx.materials.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.playx.gtx.fission.INeutronBehaviour;

public class NuclearItemProperty implements IMaterialProperty<NuclearItemProperty> {
    public final int maxTemperature;
    public final double heatConduction;
    public final INeutronBehaviour behaviour;

    public NuclearItemProperty(int maxTemperature, double heatConduction, INeutronBehaviour behaviour) {
        this.maxTemperature = maxTemperature;
        this.heatConduction = heatConduction;
        this.behaviour = behaviour;
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.INGOT);
        // TODO: Verify maxTemperature == blast temperature?
    }
}
