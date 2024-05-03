package com.playx.gtx.common.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class GTXCreativeModeTabs {
    public static RegistryEntry<CreativeModeTab> GTX = GTXRegistries.REGISTRATE.defaultCreativeTab(GTXMod.MOD_ID,
                builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(GTXMod.MOD_ID, GTXRegistries.REGISTRATE)
                ).title(Component.literal("Gregtech eXtra"))
                        .build()
            ).register();

    static {
        GTXRegistries.REGISTRATE.creativeModeTab(() -> GTX);
    }

    public static void init() {}
}
