package com.playx.gtx.fission;

public interface INuclearComponent<T> {
    double getHeatConduction();

    INeutronBehaviour getNeutronBehaviour();

    T getVariant();

    default T getNeutronProduct() {
        return null;
    }

    default long getNeutronProductAmount() {
        return 0;
    }

    default double getNeutronProductProbability() {
        return 1;
    }

    default int getMaxTemperature() {
        return Integer.MAX_VALUE;
    }
}
