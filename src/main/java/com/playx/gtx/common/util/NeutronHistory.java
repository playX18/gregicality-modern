package com.playx.gtx.common.util;

import com.lowdragmc.lowdraglib.syncdata.ITagSerializable;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.playx.gtx.fission.NeutronType;
import net.minecraft.nbt.CompoundTag;

public class NeutronHistory extends IntegerHistory<NeutronHistory.Type> implements ITagSerializable<CompoundTag> {

    public enum Type {
        FastNeutronReceived,
        FastNeutronFlux,
        ThermalNeutronReceived,
        ThermalNeutronFlux,
        NeutronGeneration, EUGeneration
    }

    public NeutronHistory() {
        super(Type.class, 100);
    }
    public double getAverageReceived(NeutronType type) {
        if (type == NeutronType.FAST) {
            return getAverage(Type.FastNeutronReceived);
        } else if (type == NeutronType.THERMAL) {
            return getAverage(Type.ThermalNeutronReceived);
        } else if (type == NeutronType.BOTH) {
            return getAverageReceived(NeutronType.FAST) + getAverageReceived(NeutronType.THERMAL);
        } else {
            return 0;
        }
    }

    public double getAverageFlux(NeutronType type) {
        if (type == NeutronType.FAST) {
            return getAverage(Type.FastNeutronFlux);
        } else if (type == NeutronType.THERMAL) {
            return getAverage(Type.ThermalNeutronFlux);
        } else if (type == NeutronType.BOTH) {
            return getAverageFlux(NeutronType.FAST) + getAverageFlux(NeutronType.THERMAL);
        } else {
            return 0;
        }
    }

    public double getAverageGeneration() {
        return getAverage(Type.NeutronGeneration);
    }

    public double getAverageEuGeneration() {
        return getAverage(Type.EUGeneration);
    }
}
