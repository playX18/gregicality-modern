package com.playx.gtx.fission;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.materials.GTXTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

public class ReactorAbsorbable extends ReactorComponentItem {
    public final int desintegrationMax;

    public ReactorAbsorbable(Properties settings, TagPrefix prefix, Material mat, int desintegrationMax) {
        super(settings, prefix, mat);
        this.desintegrationMax = desintegrationMax;
    }

    protected static int randIntFromDouble(double value, RandomSource rand) {
        return (int) Math.floor(value) + (rand.nextDouble() < (value % 1) ? 1 : 0);
    }

    public void setRemainingDesintegrations(ItemStack stack, int value) {
        if (value < 0 || value > desintegrationMax) {
            throw new IllegalArgumentException(
                    String.format("Remaining desintegration %d must be between 0 and max desintegration = %d", value, desintegrationMax));
        }

        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("desRem", value);
    }

    public double getDurabilityBarProgress(ItemStack stack) {
        return (double) getRemainingDesintegrations(stack) / desintegrationMax;
    }

    public int getRemainingDesintegrations(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("desRem")) {
            return desintegrationMax;
        }
        return tag.getInt("desRem");
    }

    public int simulateAbsorption(double neutronsReceived, ItemStack stack, RandomSource rand) {
        int absorbNeutrons = Math.min(randIntFromDouble(neutronsReceived, rand), getRemainingDesintegrations(stack));

        setRemainingDesintegrations(stack, getRemainingDesintegrations(stack) - absorbNeutrons);
        return absorbNeutrons;

    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = (float) getRemainingDesintegrations(stack) / desintegrationMax;
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getRemainingDesintegrations(stack) != desintegrationMax;
    }

}
