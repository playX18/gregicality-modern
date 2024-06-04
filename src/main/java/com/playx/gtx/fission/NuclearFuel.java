package com.playx.gtx.fission;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.common.util.NuclearEfficiencyHistory;
import com.playx.gtx.materials.GTXTags;
import com.playx.gtx.materials.properties.GTXPropertyKeys;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class NuclearFuel extends ReactorAbsorbable {
    public NuclearFuel(Properties settings, Material mat) {

        this(settings, mat, 0);
    }

    private NuclearFuel(Properties settings, Material mat, int desintegrationMax) {

        super(settings, GTXTags.nuclearFuel, mat, desintegrationMax);
    }

    public int getSize() {
        return material.getProperty(GTXPropertyKeys.NUCLEAR_FUEL).size;
    }

    public int getDirectEUbyDesintegration() {
        return material.getProperty(GTXPropertyKeys.NUCLEAR_FUEL).directEUbyDesintegration;
    }

    public int getTotalEUbyDesintegration() {

        return material.getProperty(GTXPropertyKeys.NUCLEAR_FUEL).totalEUbyDesintegration;
    }

    public double getDirectEnergyFactor() {
        return material.getProperty(GTXPropertyKeys.NUCLEAR_FUEL).directEnergyFactor;
    }

    public double getNeutronMultiplicationFactor() {
        return material.getProperty(GTXPropertyKeys.NUCLEAR_FUEL).neutronMultiplicationFactor;
    }

    public int getTempLimitLow() {
        return material.getProperty(GTXPropertyKeys.NUCLEAR_FUEL).tempLimitLow;
    }

    public int getTempLimitHigh() {
        return material.getProperty(GTXPropertyKeys.NUCLEAR_FUEL).tempLimitHigh;
    }

    private static int clampTemp(int temperature) {
        return 25 * (int) (temperature / 25d);
    }
    @Override
    public Item getNeutronProduct() {
        return ChemicalHelper.get(GTXTags.depletedFuel, material).getItem();
        //return BuiltInRegistries.ITEM.getOptional(GTXMod.id(depletedVersion)).orElseThrow(() -> new IllegalStateException("Missing item " + depletedVersion)).asItem();
    }



    @Override
    public long getNeutronProductAmount() {
        return getSize();
    }

    public double efficiencyFactor(double temperature) {
        double factor = 1;
        var tempLimitLow = getTempLimitLow();
        var tempLimitHigh = getTempLimitHigh();

        if (temperature > tempLimitLow) {
            factor = Math.max(0, 1 - (temperature - tempLimitLow) / (tempLimitHigh - tempLimitLow));
        }
        return factor;
    }

    public int simulateDesintegration(double neutronsReceived, ItemStack stack, double temperature, RandomSource rand,
                                      NuclearEfficiencyHistory efficiencyHistory) {
        int absorption = simulateAbsorption(neutronsReceived, stack, rand);
        double fuelEuConsumed = absorption * getTotalEUbyDesintegration();
        efficiencyHistory.registerEuFuelConsumption(fuelEuConsumed);
        return randIntFromDouble(efficiencyFactor(temperature) * absorption * getNeutronMultiplicationFactor(), rand);
    }


}
