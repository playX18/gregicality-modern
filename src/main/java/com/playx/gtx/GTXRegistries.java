package com.playx.gtx;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.playx.gtx.materials.GTXMaterials;
import com.playx.gtx.recipes.GTXRecipes;

public class GTXRegistries {
    private static MaterialRegistry MATERIAL_REGISTRY;

    public static GTRegistrate REGISTRATE = GTRegistrate.create(GTXMod.MOD_ID);

    public static void registerMaterials() {
        GTXMaterials.init();
    }

    public static void registerMaterialRegistryEvent(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(GTXMod.MOD_ID);
        GTXMod.LOGGER.info("GTX Materials being registered");
    }
}
