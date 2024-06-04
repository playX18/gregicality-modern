package com.playx.gtx.fission;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.TagPrefixItem;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.materials.GTXTags;
import com.playx.gtx.materials.properties.GTXPropertyKeys;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

public class ReactorComponentItem extends Item implements INuclearComponent<Item> {
    public final int maxTemperature;
    public final double heatConduction;
    public final INeutronBehaviour behaviour;
    public final TagPrefix prefix;
    public final Material material;

    public ReactorComponentItem(Properties properties, TagPrefix prefix, Material mat, int maxTemperature, double heatConduction, INeutronBehaviour behaviour) {
        super(properties);
        this.prefix = prefix;
        this.material = mat;
        this.maxTemperature = maxTemperature;
        this.heatConduction = heatConduction;
        this.behaviour = behaviour;
    }


    public ReactorComponentItem(Properties properties, TagPrefix prefix, Material mat) {

        super(properties);
        var property = mat.getProperty(GTXPropertyKeys.NUCLEAR_ITEM);
        this.maxTemperature = property.maxTemperature;
        this.heatConduction = property.heatConduction;
        this.behaviour = property.behaviour;
        this.prefix = prefix;
        this.material = mat;
    }


    public static ItemEntry<ReactorComponentItem> of(String id, int maxTemperature, double heatConduction, INeutronBehaviour behaviour) {
        return GTXRegistries.REGISTRATE.item(id, (p) -> new ReactorComponentItem(new Item.Properties(), GTXTags.nuclearComponent, GTMaterials.Air, maxTemperature, heatConduction, behaviour))
                .model((ctx, prov) -> prov.basicItem(ctx.get())).onRegister(item -> {
                    GTXMod.LOGGER.error("REGISTERED {}", item);
                }).register();
    }

    @Override
    public double getHeatConduction() {
        return heatConduction;
    }

    @Override
    public INeutronBehaviour getNeutronBehaviour() {
        return behaviour;
    }

    @Override
    public Item getVariant() {
        return this;
    }

    @Override
    public int getMaxTemperature() {
        return maxTemperature;
    }

}
