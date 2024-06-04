package com.playx.gtx;

import com.playx.gtx.items.Tooltips;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GTXClient {
    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(GTXClient::attachTooltips);
    }

    private static void attachTooltips(ItemTooltipEvent event) {

        Tooltips.attachTooltip(event.getItemStack(), event.getToolTip());
    }
}
