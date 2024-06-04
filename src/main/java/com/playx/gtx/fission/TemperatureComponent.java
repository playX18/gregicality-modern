package com.playx.gtx.fission;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.lowdragmc.lowdraglib.syncdata.IContentChangeAware;
import com.lowdragmc.lowdraglib.syncdata.IManaged;
import com.lowdragmc.lowdraglib.syncdata.IManagedStorage;
import com.lowdragmc.lowdraglib.syncdata.ITagSerializable;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.FieldManagedStorage;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;


public class TemperatureComponent implements IItemComponent, ITagSerializable<CompoundTag>, IContentChangeAware {
    private double temperature = 0.0D;
    public final double temperatureMax;

    @Getter @Setter
    protected Runnable onContentsChanged = () -> {};

    public TemperatureComponent(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
        this.temperature = Math.min(Math.max(temperature, 0), temperatureMax);
    }

    public void increaseTemperature(double temp) {
        setTemperature(getTemperature() + temp);
    }

    public void decreaseTemperature(double temp) {
        setTemperature(getTemperature() - temp);
    }

    public double getTemperature() {
        return temperature;
    }

    public void writeNbt(CompoundTag tag) {
        tag.putDouble("temperature", temperature);
    }

    public void readNbt(CompoundTag tag) {
        temperature = tag.getDouble("temperature");
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putDouble("temperature", temperature);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        temperature = nbt.getDouble("temperature");
    }
}
