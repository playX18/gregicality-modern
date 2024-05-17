package com.playx.gtx.items;

import com.google.common.base.CaseFormat;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.api.item.component.IMaterialPartItem;
import com.gregtechceu.gtceu.common.data.GTModels;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class GTXItems {
    static class WasteBehaviour implements IMaterialPartItem {

        @Override
        public int getPartMaxDurability(ItemStack itemStack) {
            return 0;
        }

        @Nullable
        public static WasteBehaviour getBehaviour(@NotNull ItemStack itemStack) {
            if (itemStack.getItem() instanceof ComponentItem componentItem) {
                for (var component : componentItem.getComponents()) {
                    if (component instanceof WasteBehaviour behaviour) {
                        return behaviour;
                    }
                }
            }
            return null;
        }
    }


    public static final ItemEntry<ComponentItem> MICA_SHEET = GTXRegistries.REGISTRATE.item("mica_sheet", ComponentItem::create)
            .lang("Mica Sheet")
            .properties(p -> p.stacksTo(64))
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> MICA_INSULATOR_SHEET = GTXRegistries.REGISTRATE.item("mica_insulator_sheet", ComponentItem::create)
            .lang("Mica Insulator Sheet")
            .properties(p -> p.stacksTo(64))
            .defaultModel()
            .register();

    public static final ItemEntry<ComponentItem> MICA_INSULATOR_FOIL = GTXRegistries.REGISTRATE.item("mica_insulator_foil", ComponentItem::create)
            .lang("Mica Insulator Foil")
            .properties(p -> p.stacksTo(64))
            .defaultModel()
            .register();

    private static ItemEntry<ComponentItem> addItem(String name, String lang) {
        return GTXRegistries.REGISTRATE.item(name.replace('.', '_'), ComponentItem::create)
                .lang(lang)
                .properties(p -> p.stacksTo(64))
                .defaultModel()
                .register();
    }

    private static ItemEntry<ComponentItem> addItem(int _id, String name) {

        return GTXRegistries.REGISTRATE.item(name, ComponentItem::create)

                .lang(Arrays.stream(name.split("\\.")).map(WordUtils::capitalize).collect(Collectors.joining()))
                .properties(p -> p.stacksTo(64))
                .defaultModel()
                .register();
    }

    public static final ItemEntry<ComponentItem> PLASMA_CONTAINMENT_CELL = addItem("plasma.containment.cell", "Plasma Containment Cell");
    public static final ItemEntry<ComponentItem> RHENIUM_PLASMA_CONTAINMENT_CELL = addItem("rhenium.plasma.containment.cell", "Rhenium Plasma Containment Cell");
    public static final ItemEntry<ComponentItem> DEGENERATE_RHENIUM_PLATE = addItem("degenerate_rhenium_plate", "Degenerate Rhenium Plate");
    public static final ItemEntry<ComponentItem> DEGENERATE_RHENIUM_DUST = addItem("degenerate.rhenium.dust", "Degenerate Rhenium Dust");
    public static final ItemEntry<ComponentItem> ZBLAN = addItem("zblan", "Zblan");
    public static final ItemEntry<ComponentItem> ZBLAN_INGOT = addItem("zblan.ingot", "Zblan Ingot");
    public static final ItemEntry<ComponentItem> ERBIUM_DOPED_ZBLAN = addItem("erbium_doped_zblan", "Erbium Doped Zblan");
    public static final ItemEntry<ComponentItem> CLADDED_OPTICAL_FIBER_CORE = addItem("cladded_optical_fiber_core", "Cladded Optical Fiber Core");

    public static final ItemEntry<ComponentItem> RAPIDLY_ROTATING_CRUCIBLE = addItem("rapidly_rotating_crucible", "Rapidly Rotating Crucible");
    public static final ItemEntry<ComponentItem> HEAVY_METAL_ABSORBING_YARN = addItem("heavy_metal_absorbing_yarn", "Heavy Metal Absorbing Yarn");
    public static final ItemEntry<ComponentItem> URANIUM_SATURATED_YARN = addItem("uranium_saturated_yarn", "Uranium Saturated Yarn");
    public static final ItemEntry<ComponentItem> LITHIUM_SIEVE = addItem("lithium_sieve", "Lithium Sieve");
    public static final ItemEntry<ComponentItem> LITHIUM_SATURATED_LITHIUM_SIEVE = addItem("lithium_saturated_lithium_sieve", "Lithium Saturated Lithium Sieve");
    public static final ItemEntry<ComponentItem> NANOTOME = addItem("nanotome", "Nanotome");
    public static final ItemEntry<ComponentItem> ACRYLIC_YARN = addItem("acrylic_yarn", "Acrylic Yarn");
    public static final ItemEntry<ComponentItem> NEUTRON_PLASMA_CONTAINMENT_CELL = addItem("neutron.plasma.containment.cell", "Neutron Plasma Containment Cell");

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static void init() {

    }

}
