package com.playx.gtx.fission;

import com.playx.gtx.GTXMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface INuclearTileData {

    double getTemperature();

    double getHeatTransferCoeff();

    double getMeanNeutronAbsorption(NeutronType type);

    double getMeanNeutronFlux(NeutronType type);

    double getMeanNeutronGeneration();

    double getMeanEuGeneration();

    Object getVariant();

    long getVariantAmount();

    boolean isFluid();

    @Nullable
    default INuclearComponent<?> getComponent() {
        var variant = getVariant();
        if (variant instanceof Item resource) {
            if (resource != Items.AIR && getVariantAmount() > 0 && resource instanceof INuclearComponent<?>comp) {
                return comp;
            }

        } else if (variant instanceof Fluid resource) {
            if (resource != Fluids.EMPTY && getVariantAmount() > 0) {
                return FluidNuclearComponent.get(resource);
            }
        }

        return null;
    }

    static boolean areEquals(Optional<INuclearTileData> a, Optional<INuclearTileData> b) {
        if (a.isPresent() != b.isPresent()) {
            return false;
        } else if (a.isPresent()) {
            INuclearTileData A = a.get();
            INuclearTileData B = b.get();
            for (NeutronType type : NeutronType.TYPES) {
                if (A.getMeanNeutronAbsorption(type) != B.getMeanNeutronAbsorption(type)) {
                    return false;
                }
                if (A.getMeanNeutronFlux(type) != B.getMeanNeutronFlux(type)) {
                    return false;
                }
            }
            return A.getTemperature() == B.getTemperature() && A.getHeatTransferCoeff() == B.getTemperature()
                    && A.getVariantAmount() == B.getVariantAmount() && A.getMeanNeutronGeneration() == B.getMeanNeutronGeneration()
                    && A.getVariant().equals(B.getVariant()) && A.getMeanEuGeneration() == B.getMeanEuGeneration();
        } else {
            return true;
        }

    }

}