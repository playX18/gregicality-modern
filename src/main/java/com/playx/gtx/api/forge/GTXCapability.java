package com.playx.gtx.api.forge;

import com.playx.gtx.fission.INuclearComponent;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class GTXCapability {
    public static final Capability<INuclearComponent<Item>> CAPABILITY_NUCLEAR_ITEM = CapabilityManager.get(new CapabilityToken<>(){});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(INuclearComponent.class);
    }
}
