package com.playx.gtx.items;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.TagPrefixItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.fission.*;
import com.playx.gtx.items.behaviours.WasteBehavior;
import com.playx.gtx.materials.GTXMaterials;
import com.playx.gtx.materials.GTXTags;
import com.playx.gtx.materials.properties.GTXPropertyKeys;
import com.playx.gtx.materials.properties.IsotopeProperty;
import com.playx.gtx.materials.properties.NuclearFuelProperty;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.client.color.item.ItemColor;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Arrays;
import java.util.stream.Collectors;


public class GTXItems {
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
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE = addItem("waste_nuclear", "waste_nuclear", "Nuclear Waste", new WasteBehavior("waste_nuclear", 0xDEDEDE));
    public static final ItemEntry<ComponentItem> PROTACTINIUM_WASTE = addItem("waste_nuclear_protactinium", "waste_nuclear", "Protactinium Waste", new WasteBehavior(() -> GTMaterials.Protactinium));
    public static final ItemEntry<ComponentItem> THORIUM_WASTE = addItem("waste_nuclear_thorium", "waste_nuclear", "Thorium Waste", new WasteBehavior(() -> GTMaterials.Thorium));
    public static final ItemEntry<ComponentItem> URANIUM_WASTE = addItem("waste_nuclear_uranium", "waste_nuclear", "Uranium Waste", new WasteBehavior(() -> GTMaterials.Uranium238));
    public static final ItemEntry<ComponentItem> NEPTUNIUM_WASTE = addItem("waste_neptunium", "waste_nuclear", "Neptunium Waste", new WasteBehavior(() -> GTMaterials.Neptunium));
    public static final ItemEntry<ComponentItem> PLUTONIUM_WASTE = addItem("waste_plutonium", "waste_nuclear", "Plutonium Waste", new WasteBehavior(() -> GTMaterials.Plutonium239));
    public static final ItemEntry<ComponentItem> AMERICIUM_WASTE = addItem("waste_americium", "waste_nuclear", "Americium Waste", new WasteBehavior(() -> GTMaterials.Americium));
    public static final ItemEntry<ComponentItem> CURIUM_WASTE = addItem("waste_curium", "waste_nuclear", "Curium Waste", new WasteBehavior(() -> GTMaterials.Curium));
    public static final ItemEntry<ComponentItem> BERKELIUM_WASTE = addItem("waste_berkelium", "waste_nuclear", "Berkelium Waste", new WasteBehavior(() -> GTMaterials.Berkelium));
    public static final ItemEntry<ComponentItem> CALIFORNIUM_WASTE = addItem("waste_californium", "waste_nuclear", "Californium Waste", new WasteBehavior(() -> GTMaterials.Californium));
    public static final ItemEntry<ComponentItem> EINSTEINIUM_WASTE = addItem("waste_einsteinium", "waste_nuclear", "Einsteinium Waste", new WasteBehavior(() -> GTMaterials.Einsteinium));
    public static final ItemEntry<ComponentItem> FERMIUM_WASTE = addItem("waste_fermium", "waste_nuclear", "Fermium Waste", new WasteBehavior(() -> GTMaterials.Fermium));
    public static final ItemEntry<ComponentItem> MENDELEVIUM_WASTE = addItem("waste_mendelevium", "waste_nuclear", "Mendelevium Waste", new WasteBehavior(() -> GTMaterials.Mendelevium));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_LANTHANIDE_A = addItem("waste_lanthanide_a", "waste_nuclear", "Lanthanide A Waste", new WasteBehavior("waste_nuclear_lanthanide_a", 0xC9CBCF));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_LANTHANIDE_B = addItem("waste_lanthanide_b", "waste_nuclear", "Lanthanide B Waste", new WasteBehavior("waste_nuclear_lanthanide_b", 0xA9A8AA));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_ALKALINE = addItem("waste_alkaline", "waste_nuclear", "Alkaline Waste", new WasteBehavior("waste_nuclear_alkaline", 0xDEDEDE));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_METAL_A = addItem("waste_metal_a", "waste_nuclear", "Metal A Waste", new WasteBehavior("waste_nuclear_metal_a", 0x48484D));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_METAL_B = addItem("waste_metal_b", "waste_nuclear", "Metal B Waste", new WasteBehavior("waste_nuclear_metal_b", 0x626065));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_METAL_C = addItem("waste_metal_c", "waste_nuclear", "Metal C Waste", new WasteBehavior("waste_nuclear_metal_c", 0x828485));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_HEAVY_METAL = addItem("waste_heavy_metal", "waste_nuclear", "Heavy Metal Waste", new WasteBehavior("waste_nuclear_heavy_metal", 0x738198));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_METALOID = addItem("waste_metaloid", "waste_nuclear", "Metaloid Waste", new WasteBehavior("waste_nuclear_metaloid", 0xD16D4F));
    public static final ItemEntry<ComponentItem> NUCLEAR_WASTE_REACTIVE_NONMETAL = addItem("waste_nonmetal", "waste_nuclear", "Reactive Nonmetal Waste", new WasteBehavior("waste_nuclear_reactive_nonmetal", 0xD1CB4F));
    public static final ItemEntry<ReactorComponentItem> SMALL_HEAT_EXCHANGER = ReactorComponentItem.of("small_heat_exchanger", 2500, 15 * NuclearConstant.BASE_HEAT_CONDUCTION, INeutronBehaviour.NO_INTERACTION);
    public static final ItemEntry<ReactorComponentItem> LARGE_HEAT_EXCHANGER = ReactorComponentItem.of("large_heat_exchanger", 1800, 30 * NuclearConstant.BASE_HEAT_CONDUCTION, INeutronBehaviour.NO_INTERACTION);
    public static Table<TagPrefix, Material, ItemEntry<ReactorAbsorbable>> ABSORBABLE_ITEMS;
    public static Table<TagPrefix, Material, ItemEntry<NuclearFuel>> NUCLEAR_FUEL_ITEMS;

    public static void initNuclearWaste() {

        GTXMaterials.ThoriumRadioactive.waste = THORIUM_WASTE;
        GTXMaterials.Protactinium.waste = PROTACTINIUM_WASTE;
        GTXMaterials.UraniumRadioactive.waste = URANIUM_WASTE;
        GTXMaterials.Neptunium.waste = NEPTUNIUM_WASTE;
        GTXMaterials.PlutoniumRadioactive.waste = PLUTONIUM_WASTE;
        GTXMaterials.AmericiumRadioactive.waste = AMERICIUM_WASTE;
        GTXMaterials.Curium.waste = CURIUM_WASTE;
        GTXMaterials.Berkelium.waste = BERKELIUM_WASTE;
        GTXMaterials.Californium.waste = CALIFORNIUM_WASTE;
        GTXMaterials.Einsteinium.waste = EINSTEINIUM_WASTE;
        GTXMaterials.Fermium.waste = FERMIUM_WASTE;
        GTXMaterials.Mendelevium.waste = MENDELEVIUM_WASTE;

    }

    private static ItemEntry<ComponentItem> addItem(String name, String lang) {
        return GTXRegistries.REGISTRATE.item(name.replace('.', '_'), ComponentItem::create)
                .lang(lang)
                .properties(p -> p.stacksTo(64))
                .defaultModel()

                .register();
    }

    private static ItemEntry<ComponentItem> addItem(String name, String texture, String lang, IItemComponent... components) {
        GTXMod.LOGGER.info("Register {} with texture {}", name, texture);
        var builder = GTXRegistries.REGISTRATE.item(name.replace('.', '_'), ComponentItem::create)
                .lang(lang)
                .properties(p -> p.stacksTo(64))
                .defaultModel()
                .onRegister(item -> item.attachComponents(components));
        for (var component : components) {
            if (component instanceof ItemColor color) {
                builder.color(() -> () -> color);
                break;
            }
        }
        return builder.register();
    }

    private static ItemEntry<ComponentItem> addItem(int _id, String name) {

        return GTXRegistries.REGISTRATE.item(name, ComponentItem::create)
                .lang(Arrays.stream(name.split("\\.")).map(WordUtils::capitalize).collect(Collectors.joining()))
                .properties(p -> p.stacksTo(64))
                .defaultModel()
                .register();
    }

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {

        return item -> item.attachComponents(components);
    }

    public static void generateAbsorbableItems() {
        ImmutableTable.Builder<TagPrefix, Material, ItemEntry<ReactorAbsorbable>> builder = ImmutableTable.builder();

        for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
            var registrate = registry.getRegistrate();
            for (var material : registry.getAllMaterials()) {
                if (material.hasFlag(GTXMaterials.GENERATE_NUCLEAR_ABSORBABLE)) {
                    /*builder.put(GTXTags.nuclearAbsorbable, material, registrate
                            .item(GTXTags.nuclearAbsorbable.idPattern().formatted(material.getName()), properties ->
                                    new ReactorAbsorbable(
                                            properties.stacksTo(1),
                                            GTXTags.nuclearAbsorbable,
                                            material,
                                            2500,
                                            2 * NuclearConstant.BASE_HEAT_CONDUCTION,
                                            INeutronBehaviour.of(
                                                    NuclearConstant.ScatteringType.MEDIUM,
                                                    NuclearConstant.INVAR,
                                                    2),
                                            NuclearConstant.DESINTEGRATION_BY_ROD * 2))
                            .setData(ProviderType.LANG, NonNullBiConsumer.noop())
                            .transform(GTItems.unificationItem(GTXTags.nuclearAbsorbable, material))
                            .properties(p -> p.stacksTo(1))
                            .model(NonNullBiConsumer.noop())
                            .color(() -> TagPrefixItem::tintColor)
                            .register());*/
                }
            }
        }

        ABSORBABLE_ITEMS = builder.build();
    }

    public static void generateFuelItems() {
        ImmutableTable.Builder<TagPrefix, Material, ItemEntry<NuclearFuel>> builder = ImmutableTable.builder();

        for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
            var registrate = registry.getRegistrate();
            for (var material : registry.getAllMaterials()) {
                if (material.hasFlag(GTXMaterials.GENERATE_NUCLEAR_FUEL) && material.hasProperty(GTXPropertyKeys.ISOTOPE)) {
                    var params = IsotopeProperty.of(material);


                    var fuelParams = new NuclearFuelProperty(
                            NuclearConstant.DESINTEGRATION_BY_ROD,
                            params.directEnergyFactor,
                            params.neutronsMultiplication,
                            1,
                            params.tempLimitLow,
                            params.tempLimitHigh
                    );

                    var behaviour = INeutronBehaviour.of(NuclearConstant.ScatteringType.HEAVY, params, 1);
                    var prefix = GTXTags.nuclearFuel;

                    builder.put(prefix, material, registrate
                            .item(prefix.idPattern().formatted(material.getName()), properties ->
                                    new NuclearFuel(
                                            properties.stacksTo(1),
                                            material))
                            .setData(ProviderType.LANG, NonNullBiConsumer.noop())
                            .transform(GTItems.unificationItem(GTXTags.nuclearAbsorbable, material))
                            .properties(p -> p.stacksTo(1))
                            .model(NonNullBiConsumer.noop())
                            .color(() -> TagPrefixItem::tintColor)
                            .register());

                }
            }
        }

        NUCLEAR_FUEL_ITEMS = builder.build();
    }

    public static void init() {
        generateAbsorbableItems();
        generateFuelItems();
    }
}
