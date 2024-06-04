package com.playx.gtx.common.util;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.lowdragmc.lowdraglib.syncdata.IEnhancedManaged;
import com.lowdragmc.lowdraglib.syncdata.IManaged;
import com.lowdragmc.lowdraglib.syncdata.IManagedStorage;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

public class NuclearEfficiencyHistory extends IntegerHistory<NuclearEfficiencyHistory.Type> {
    public enum Type {
        euProduction,
        euFuelConsumption,
    }

    public NuclearEfficiencyHistory() {
        super(Type.class, 300);
    }

    public void registerEuFuelConsumption(double eu) {
        addValue(Type.euFuelConsumption, (int) eu);
    }

    public void registerEuProduction(double eu) {
        addValue(Type.euProduction, (int) eu);
    }
}
