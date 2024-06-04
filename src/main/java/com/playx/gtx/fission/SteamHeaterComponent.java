package com.playx.gtx.fission;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.side.fluid.IFluidTransfer;
import com.lowdragmc.lowdraglib.syncdata.ITagSerializable;
import it.unimi.dsi.fastutil.objects.Reference2LongMap;
import it.unimi.dsi.fastutil.objects.Reference2LongOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;


public class SteamHeaterComponent extends TemperatureComponent implements ITagSerializable<CompoundTag> {
    public static final double INPUT_ENERGY_RATIO_FOR_STARTUP = 0.8; // only if requires continuous operation
    private static final int STEAM_TO_WATER = 16;

    /**
     * mb/t of steam produced at max heat, assuming enough water
     */
    public final long maxEuProduction;
    /**
     * How many eu in one degree of heat.
     */
    public final long euPerDegree;

    public final boolean acceptHighPressure;
    public final boolean acceptLowPressure;

    public final boolean requiresContinuousOperation;

    /**
     * Amount of steam for which we already consumed the water.
     */
    private final Reference2LongMap<Fluid> steamBuffer = new Reference2LongOpenHashMap<>();

    public SteamHeaterComponent(double temperatureMax, long maxEuProduction, long euPerDegree) {
        this(maxEuProduction, maxEuProduction, euPerDegree, true, false, false);
    }

    public SteamHeaterComponent(double temperatureMax, long maxEuProduction, long euPerDegree, boolean acceptLowPressure,
                                boolean acceptHighPressure, boolean requiresContinuousOperation) {
        super(temperatureMax);
        this.maxEuProduction = maxEuProduction;
        this.euPerDegree = euPerDegree;
        this.acceptLowPressure = acceptLowPressure;
        this.acceptHighPressure = acceptHighPressure;
        this.requiresContinuousOperation = requiresContinuousOperation;
    }

    // return eu produced
    public double tick(IFluidTransfer fluidInputs, IFluidTransfer fluidOutputs) {

        double euProducedLowPressure = 0;
        if (acceptLowPressure) {
            euProducedLowPressure = tryMakeSteam(fluidInputs, fluidOutputs, Fluids.WATER, GTMaterials.Steam.getFluid(), 1);
            if (euProducedLowPressure == 0) {

            }
        }

        double euProducedHighPressure = 0;


        double totalEuProduced = euProducedLowPressure + euProducedHighPressure;

        if (this.requiresContinuousOperation) {
            this.decreaseTemperature(INPUT_ENERGY_RATIO_FOR_STARTUP * (this.maxEuProduction - totalEuProduced) / this.euPerDegree);
        }

        return totalEuProduced;
    }

    // Return true if any steam was made.
    private double tryMakeSteam(IFluidTransfer input, IFluidTransfer output, Fluid water, Fluid steam, int euPerSteamMb) {
        if (getTemperature() > 100d) {
            long steamProduction = (long) ((getTemperature() - 100d) / (temperatureMax - 100d) * maxEuProduction / euPerSteamMb);

            long inserted;

            inserted = output.fill(FluidStack.create(steam, steamProduction), true);

            if (inserted > 0) {
                // Round water consumption up
                long waterToUse = (inserted - steamBuffer.getLong(steam) + STEAM_TO_WATER - 1) / STEAM_TO_WATER;
                // Extract water
                var drained = input.drain(FluidStack.create(water, waterToUse), true);
                // Add to steam buffer
                steamBuffer.mergeLong(steam, drained.getAmount() * STEAM_TO_WATER, Long::sum);

                // Produce steam
                long producedSteam = output.fill(FluidStack.create(steam, Math.min(steamProduction, steamBuffer.getLong(steam))), false);
                steamBuffer.mergeLong(steam, -producedSteam, Long::sum);

                double euProduced = producedSteam * euPerSteamMb;
                decreaseTemperature(euProduced / euPerDegree);
                return euProduced;
            }
        }

        return 0;
    }

}
