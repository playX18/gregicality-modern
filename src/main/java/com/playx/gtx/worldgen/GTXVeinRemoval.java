package com.playx.gtx.worldgen;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.playx.gtx.GTXMod;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class GTXVeinRemoval {
    private static final Set<ResourceLocation> VEINS_TO_REMOVE = Set.of(
            GTCEu.id("magnetite_vein_ow"),
            GTCEu.id("magnetite_vein_end")
    );

    public static void removeOres() {
        GTXMod.LOGGER.info("GTOres mixin applied");
        for (var vein : VEINS_TO_REMOVE) {
            GTRegistries.ORE_VEINS.remove(vein);
        }
    }
}
