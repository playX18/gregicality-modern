package com.playx.gtx.fission;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.lowdragmc.lowdraglib.syncdata.IContentChangeAware;
import com.lowdragmc.lowdraglib.syncdata.IEnhancedManaged;
import com.lowdragmc.lowdraglib.syncdata.IManaged;
import com.lowdragmc.lowdraglib.syncdata.ITagSerializable;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.FieldManagedStorage;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.Random;



public final class ReactorGrid{


    private final int sizeX;

    private final int sizeY;
    private final @Nullable ReactorHatch[][] hatchesGrid;


    public ReactorGrid(int sizeX, int sizeY, @Nullable ReactorHatch[][] hatchesGrid) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.hatchesGrid = hatchesGrid;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    @Nullable
    public INuclearTile getNuclearTile(int x, int y) {
        return hatchesGrid[x][y];
    }

    public void registerNeutronFate(int neutronNumber, NeutronType type, boolean escape) {
    }

    public void registerNeutronCreation(int neutronNumber, NeutronType type) {
    }

}
