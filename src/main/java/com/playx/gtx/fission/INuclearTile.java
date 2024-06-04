package com.playx.gtx.fission;

import com.playx.gtx.common.util.NuclearEfficiencyHistory;

import java.util.Optional;

public interface INuclearTile extends INuclearTileData {
    void setTemperature(double temp);
    void putHeat(double eu);
    void absorbNeutrons(int neutronNumber, NeutronType type);
    void addNeutronsToFlux(int neutronNumber, NeutronType type);
    int neutronGenerationTick(NuclearEfficiencyHistory efficiencyHistory);
    void nuclearTick(NuclearEfficiencyHistory efficiencyHistory);
    default Optional<NuclearFuel> getFuel() {
        if (getComponent() instanceof NuclearFuel fuel) {
            return Optional.of(fuel);
        }
        return Optional.empty();
    }
}
