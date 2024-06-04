package com.playx.gtx.fission;

import com.gregtechceu.gtceu.api.fluids.GTFluid;
import com.gregtechceu.gtceu.common.data.GTFluids;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;

public record FluidNuclearComponent(
        Fluid fluid,
        double heatConduction,
        INeutronBehaviour neutronBehaviour,
        Fluid neutronProduct,
        long neutronProductAmount,
        double neutronProductProbability
) implements INuclearComponent<Fluid> {

    public FluidNuclearComponent(
            Fluid fluid,
            double heatConduction,
            double density,
            NuclearConstant.ScatteringType type,
            IsotopeParams params,
            Fluid neutronProduct,
            long neutronProductAmount,
            double neutronProductProbability) {
        this(
                fluid,
                heatConduction * density,
                INeutronBehaviour.of(type, params, density),
                neutronProduct,
                neutronProductAmount,
                neutronProductProbability);
    }

    @Override
    public Fluid getVariant() {
        return fluid;
    }

    @Override
    public double getHeatConduction() {
        return heatConduction;
    }

    @Override
    public INeutronBehaviour getNeutronBehaviour() {
        return neutronBehaviour;
    }

    @Override
    public Fluid getNeutronProduct() {
        return neutronProduct;
    }

    @Override
    public long getNeutronProductAmount() {
        return neutronProductAmount;
    }

    @Override
    public double getNeutronProductProbability() {
        return neutronProductProbability;
    }

    private static final Map<Fluid, FluidNuclearComponent> REGISTRY = new IdentityHashMap<>();


    public static void register(FluidNuclearComponent component) {
        Fluid fluid = component.getVariant();
        if (REGISTRY.containsKey(fluid)) {
            throw new IllegalArgumentException("Already registered fluid-neutron interaction for " + fluid);
        }
        GTXMod.LOGGER.info("Registering fluid-neutron interaction for " + fluid);
        REGISTRY.put(fluid, component);
    }


    public static void remove(Fluid fluid) {
        REGISTRY.remove(fluid);
    }

    @Nullable
    public static FluidNuclearComponent get(Fluid fluid) {
        return REGISTRY.get(fluid);

    }


    public static void init() {

        register(new FluidNuclearComponent(Fluids.WATER,
                NuclearConstant.BASE_HEAT_CONDUCTION * 5,
                1,
                NuclearConstant.ScatteringType.ULTRA_LIGHT,
                NuclearConstant.HYDROGEN,
                GTMaterials.Deuterium.getFluid(),
                1,
                1));
        register(new FluidNuclearComponent(GTXMaterials.HeavyWater.getFluid(),
                NuclearConstant.BASE_HEAT_CONDUCTION * 6,
                1,
                NuclearConstant.ScatteringType.LIGHT,
                NuclearConstant.DEUTERIUM,
                GTMaterials.Tritium.getFluid(),
                1,
                1));
    }
}
