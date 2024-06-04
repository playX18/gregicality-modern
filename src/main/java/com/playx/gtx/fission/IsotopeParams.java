package com.playx.gtx.fission;

public class IsotopeParams {
    public final double thermalAbsorption;
    public final double fastAbsorption;
    public final double fastScattering;
    public final double thermalScattering;

    public IsotopeParams(double thermalAbsorbProba, double fastAbsorptionProba, double thermalScatteringProba, double fastScatteringProba) {
        this.thermalAbsorption = INeutronBehaviour.crossSectionFromProba(thermalAbsorbProba);
        this.fastAbsorption = INeutronBehaviour.crossSectionFromProba(fastAbsorptionProba);
        this.thermalScattering = INeutronBehaviour.crossSectionFromProba(thermalScatteringProba);
        this.fastScattering = INeutronBehaviour.crossSectionFromProba(fastScatteringProba);
    }
}