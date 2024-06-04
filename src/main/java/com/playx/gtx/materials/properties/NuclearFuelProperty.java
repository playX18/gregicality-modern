package com.playx.gtx.materials.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.playx.gtx.fission.INeutronBehaviour;
import com.playx.gtx.fission.NuclearConstant;

public class NuclearFuelProperty implements IMaterialProperty<NuclearFuelProperty> {
    public final int desintegrationMax;
    public final double directEnergyFactor;
    public final double neutronMultiplicationFactor;

    public final int size;

    public final int directEUbyDesintegration;
    public final int totalEUbyDesintegration;

    public final int tempLimitLow;
    public final int tempLimitHigh;

    private static int clampTemp(int temperature) {
        return 25 * (int) (temperature / 25d);
    }
    public NuclearFuelProperty(int desintegrationMax, double directEnergyFactor, double neutronMultiplicationFactor, int size, int tempLimitLow, int tempLimitHigh) {
        this.desintegrationMax = desintegrationMax;
        this.directEnergyFactor = directEnergyFactor;
        this.neutronMultiplicationFactor = neutronMultiplicationFactor;
        this.size = size;
        this.directEUbyDesintegration = (int) (NuclearConstant.EU_FOR_FAST_NEUTRON * directEnergyFactor * neutronMultiplicationFactor);
        this.totalEUbyDesintegration = (int) (NuclearConstant.EU_FOR_FAST_NEUTRON * (1.0 + directEnergyFactor) * neutronMultiplicationFactor);
        this.tempLimitLow = clampTemp(tempLimitLow);
        this.tempLimitHigh = clampTemp(tempLimitHigh);
    }


    @Override
    public void verifyProperty(MaterialProperties properties) {
        var material = properties.getMaterial();

        properties.ensureSet(PropertyKey.INGOT, true);
    }

}
