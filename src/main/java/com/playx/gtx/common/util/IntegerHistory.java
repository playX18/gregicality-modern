package com.playx.gtx.common.util;

import com.lowdragmc.lowdraglib.syncdata.ITagSerializable;
import net.minecraft.nbt.CompoundTag;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;


public class IntegerHistory<K extends Enum<K>> implements ITagSerializable<CompoundTag> {
    protected final Map<K, int[]> histories;
    protected final int[] updatingValues; // indexed by enum ordinal
    protected final double[] averages; // indexed by enum ordinal

    private final K[] keys;
    private final int tickHistorySize;

    public IntegerHistory(Class<K> keyType, int tickHistorySize) {
        this.tickHistorySize = tickHistorySize;
        this.keys = keyType.getEnumConstants();
        this.histories = new EnumMap<>(keyType);
        this.updatingValues = new int[keys.length];
        this.averages = new double[keys.length];
        for (K key : keys) {
            histories.put(key, new int[tickHistorySize]);
        }
    }

    public double getAverage(K key) {
        double ret = averages[key.ordinal()];
        // Round to zero if very small - negative values might lead to problems.
        return Math.abs(ret) < 1e-9 ? 0 : ret;
    }

    public void clear() {
        for (var array : histories.values()) {
            Arrays.fill(array, 0);
        }
        Arrays.fill(updatingValues, 0);
        Arrays.fill(averages, 0);
    }

    public void tick() {
        for (K key : keys) {
            int i = key.ordinal();
            int[] valuesArray = histories.get(key);

            // Update average
            averages[i] += (double) (updatingValues[i] - valuesArray[tickHistorySize - 1]) / tickHistorySize;

            // Shift values by 1 and add updating value at the beginning.
            System.arraycopy(valuesArray, 0, valuesArray, 1, tickHistorySize - 1);
            valuesArray[0] = updatingValues[i];
            updatingValues[i] = 0;
        }
    }

    public void addValue(K key, int delta) {
        updatingValues[key.ordinal()] += delta;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        CompoundTag historyTag = new CompoundTag();
        for (K key : keys) {
            historyTag.putIntArray(key.name(), histories.get(key));
        }
        tag.put("history", historyTag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        CompoundTag historyTag = tag.getCompound("history");
        for (K key : keys) {
            int[] values = historyTag.getIntArray(key.name());
            histories.put(key, new int[tickHistorySize]);
            System.arraycopy(values, 0, histories.get(key), 0, Math.min(values.length, tickHistorySize));
        }
    }
}
