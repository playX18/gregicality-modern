package com.playx.gtx.materials;

import com.google.common.collect.ImmutableList;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.HazardProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.properties.GTXPropertyKeys;
import com.playx.gtx.materials.properties.NuclearProperty;
import com.tterrag.registrate.util.entry.ItemEntry;
import lombok.NonNull;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadioactiveMaterial extends EnrichmentProcess {
    public static Map<Material, RadioactiveMaterial> REGISTRY = new HashMap<>(3);

    public final Map<Material, Integer> composition = new HashMap<>(3);
    public int complexity = 100;
    public ItemEntry<ComponentItem> waste;


    public RadioactiveMaterial(String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, List<MaterialFlag> flags, Element element, float toolSpeed, float attackDamage, int toolDurability, int blastFurnaceTemperature) {
        this(new Material.Builder(GTXMod.id(name))
                .ingot(harvestLevel)
                .color(materialRGB)
                .iconSet(materialIconSet)
                .hazard(HazardProperty.HazardType.RADIOACTIVE)
                .componentStacks(materialComponents)
                .element(element)
                .appendFlags(flags)
                .buildAndRegister());

    }

    public RadioactiveMaterial(@NonNull Material from) {

        this.material = from;
        if (!from.hasProperty(PropertyKey.INGOT)) {
            from.setProperty(PropertyKey.INGOT, new IngotProperty());
            from.addFlags(MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.GENERATE_ROD);
        }
        from.setProperty(GTXPropertyKeys.NUCLEAR, new NuclearProperty());
        this.material.addFlags(GTXMaterials.GENERATE_NUCLEAR_COMPOUND, GTXMaterials.DISABLE_REPLICATION);
        REGISTRY.put(from, this);
    }

    public int getMaterialRGB() {
        return material.getMaterialRGB();
    }

    @Override
    public String toString() {
        return material.toString();
    }
}
