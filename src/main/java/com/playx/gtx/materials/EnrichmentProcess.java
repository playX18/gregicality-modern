package com.playx.gtx.materials;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import lombok.Getter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;

public class EnrichmentProcess {
    public Material fluidHexachloride;
    public Material fluidHexafluoride;
    public Material depletedFuelNitrateSolution;
    public Material hexafluorideSteamCracked;

    @Getter
    protected Material material;

    public EnrichmentProcess() {

    }

    public ItemStack getItemStack(TagPrefix prefix, int amount) {
        return ChemicalHelper.get(prefix, material, amount);
    }

    public ItemStack getItemStack(TagPrefix prefix) {
        return ChemicalHelper.get(prefix, material, 1);
    }

    @Nullable
    public FluidStack getFluidDepletedFuelNitrateSolution(int amount) {
        return depletedFuelNitrateSolution != null ? depletedFuelNitrateSolution.getFluid(amount) : null;
    }


    @Nullable
    public FluidStack getFluidHexachloride(int amount) {
        return fluidHexachloride != null ? fluidHexachloride.getFluid(amount) : null;
    }

    @Nullable
    public FluidStack getFluidHexafluoride(int amount) {
        return fluidHexafluoride != null ? fluidHexafluoride.getFluid(amount) : null;
    }

    @Nullable
    public FluidStack getFluidHexafluorideSteamCracked(int amount) {
        return hexafluorideSteamCracked != null ? hexafluorideSteamCracked.getFluid(amount) : null;
    }

}
