package com.playx.gtx.materials.properties;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IMaterialProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.playx.gtx.fission.INeutronBehaviour;
import com.playx.gtx.fission.IsotopeParams;

public class IsotopeProperty extends IsotopeParams implements IMaterialProperty<IsotopeProperty> {
    public final int maxTemp;
    public final double neutronsMultiplication;
    public final double directEnergyFactor;
    public final int tempLimitLow;
    public final int tempLimitHigh;


    public IsotopeProperty(double thermalAbsorbProba, double thermalScatterings, int maxTemp, int tempLimitLow, int tempLimitHigh,
                             double neutronsMultiplication, double directEnergyFactor) {

        super(thermalAbsorbProba, INeutronBehaviour.reduceCrossProba(thermalAbsorbProba, 0.1), thermalScatterings,
                INeutronBehaviour.reduceCrossProba(thermalScatterings, 0.5));

        this.maxTemp = maxTemp;
        this.neutronsMultiplication = neutronsMultiplication;
        this.directEnergyFactor = directEnergyFactor;
        this.tempLimitLow = tempLimitLow;
        this.tempLimitHigh = tempLimitHigh;
    }

    public static IsotopeProperty of(Material material) {
        return material.getProperty(GTXPropertyKeys.ISOTOPE);
    }

    public static IsotopeProperty mix(Material a, Material b, double factor) {
        return mix(of(a), of(b), factor);
    }

    public static IsotopeProperty mix(IsotopeProperty a, IsotopeProperty b, double factor) {
        factor = 1 - factor;

        double newThermalAbsorptionProba = INeutronBehaviour.probaFromCrossSection(mix(a.thermalAbsorption, b.thermalAbsorption, factor));
        double newScatteringProba = INeutronBehaviour.probaFromCrossSection(mix(a.thermalScattering, b.thermalScattering, factor));
        double newNeutronMultiplicationFactor = mix(a.neutronsMultiplication, b.neutronsMultiplication, factor);

        double totalEnergy = mix(a.neutronsMultiplication * (1 + a.directEnergyFactor), b.neutronsMultiplication * (1 + b.directEnergyFactor),
                factor);

        int newMaxTemp = (int) mix(a.maxTemp, b.maxTemp, factor);
        int newTempLimitLow = (int) mix(a.tempLimitLow, b.tempLimitLow, factor);
        int newTempLimitHigh = (int) mix(a.tempLimitHigh, b.tempLimitHigh, factor);

        double newDirectEnergyFactor = totalEnergy / (newNeutronMultiplicationFactor) - 1;

        return new IsotopeProperty(newThermalAbsorptionProba, newScatteringProba, newMaxTemp, newTempLimitLow, newTempLimitHigh,
                newNeutronMultiplicationFactor, newDirectEnergyFactor);

    }

    private static double mix(double a, double b, double r) {
        return r * a + (1 - r) * b;
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {

    }
}
