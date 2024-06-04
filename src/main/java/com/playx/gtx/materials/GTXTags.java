package com.playx.gtx.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.playx.gtx.GTXMod;
import com.playx.gtx.items.GTXItems;
import com.playx.gtx.items.GTXMaterialIconType;
import net.minecraft.tags.ItemTags;

import java.util.function.UnaryOperator;

public class GTXTags {
    public static final TagPrefix nitrite = new TagPrefix("nitrite")
            .idPattern("%s_nitrite")
            .defaultTagPath("nitrites/%s")
            .unformattedTagPath("nitrites")
            .langValue("%s Nitrite")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix nitride = new TagPrefix("nitride")
            .idPattern("%s_nitride")
            .defaultTagPath("nitrides/%s")
            .unformattedTagPath("nitrides")
            .langValue("%s Nitride")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix carbide = new TagPrefix("carbide")
            .idPattern("%s_carbide")
            .defaultTagPath("carbides/%s")
            .unformattedTagPath("carbides")
            .langValue("%s Carbide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix dioxide = new TagPrefix("dioxides")
            .idPattern("%s_dioxide")
            .defaultTagPath("dioxides/%s")
            .unformattedTagPath("dioxides")
            .langValue("%s Dioxide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));
    public static final TagPrefix oxide = new TagPrefix("oxide")
            .idPattern("%s_oxide")
            .defaultTagPath("oxides/%s")
            .unformattedTagPath("oxides")
            .langValue("%s Oxide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix fuelNitride = new TagPrefix("fuel_nitride")
            .idPattern("%s_fuel_nitride")
            .defaultTagPath("fuel_nitrides/%s")
            .unformattedTagPath("fuel_nitrides")
            .langValue("%s Fuel Nitride")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix fuelCarbide = new TagPrefix("fuel_carbide")
            .idPattern("%s_fuel_carbide")
            .defaultTagPath("fuel_nitrides/%s")
            .unformattedTagPath("fuel_nitrides")
            .langValue("%s Fuel Nitride")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix fuelOxide = new TagPrefix("fuel_oxide")
            .idPattern("%s_fuel_oxide")
            .defaultTagPath("fuel_oxides/%s")
            .unformattedTagPath("fuel_oxides")
            .langValue("%s Fuel Oxide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));
    public static final TagPrefix fuelPure = new TagPrefix("fuel_pure")
            .idPattern("%s_fuel_pure")
            .defaultTagPath("fuel_pure/%s")
            .unformattedTagPath("fuel_pure")
            .langValue("%s Fuel Isotope")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix fuelTRISO = new TagPrefix("fuel_triso")
            .idPattern("%s_fuel_triso")
            .defaultTagPath("fuel_triso/%s")
            .unformattedTagPath("fuel_triso")
            .langValue("%s Fuel TRISO")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix depletedFuel = new TagPrefix("depleted_fuel")
            .idPattern("%s_depleted_fuel")
            .defaultTagPath("depleted_fuel/%s")
            .unformattedTagPath("depleted_fuel")
            .langValue("%s Depleted Fuel")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    // depleted fuel oxide
    public static final TagPrefix depletedFuelOxide = new TagPrefix("depleted_fuel_oxide")
            .idPattern("%s_depleted_fuel_oxide")
            .defaultTagPath("depleted_fuel_oxide/%s")
            .unformattedTagPath("depleted_fuel_oxide")
            .langValue("%s Depleted Fuel Oxide")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    // depleted fuel nitride
    public static final TagPrefix depletedFuelNitride = new TagPrefix("depleted_fuel_nitride")
            .idPattern("%s_depleted_fuel_nitride")
            .defaultTagPath("depleted_fuel_nitride/%s")
            .unformattedTagPath("depleted_fuel_nitride")
            .langValue("%s Depleted Fuel Nitride")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    // depleted fuel triso
    public static final TagPrefix depletedFuelTRISO = new TagPrefix("depleted_fuel_triso")
            .idPattern("%s_depleted_fuel_triso")
            .defaultTagPath("depleted_fuel_triso/%s")
            .unformattedTagPath("depleted_fuel_triso")
            .langValue("%s Depleted Fuel TRISO")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.dust)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix nuclearAbsorbable = new TagPrefix("nuclear_absorbable")
            .itemTable(() -> GTXItems.ABSORBABLE_ITEMS)
            .idPattern("%s_absorbable")
            .defaultTagPath("absorbables/%s")
            .unformattedTagPath("absorbables")
            .langValue("%s Absorbable Plate")
            .materialIconType(MaterialIconType.plate)
            .unificationEnabled(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_ABSORBABLE));

    public static final TagPrefix nuclearFuel = new TagPrefix("nuclear_fuel")
            .itemTable(() -> GTXItems.NUCLEAR_FUEL_ITEMS)
            .idPattern("%s_fuel")
            .defaultTagPath("fuels/%s")
            .unformattedTagPath("fuels")
            .langValue("%s Fuel Rod")
            .materialIconType(GTXMaterialIconType.fuelRod)
            .unificationEnabled(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix nuclearFuelDouble = new TagPrefix("nuclear_fuel")
            .itemTable(() -> GTXItems.ABSORBABLE_ITEMS)
            .idPattern("%s_fuel_double")
            .defaultTagPath("fuels_doube/%s")
            .unformattedTagPath("fuels_double")
            .langValue("%s Double Fuel Rod")
            .materialIconType(GTXMaterialIconType.fuelRodDouble)
            .unificationEnabled(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix nuclearFuelQuad = new TagPrefix("nuclear_fuel")
            .itemTable(() -> GTXItems.ABSORBABLE_ITEMS)
            .idPattern("%s_fuel_quad")
            .defaultTagPath("fuels_quad/%s")
            .unformattedTagPath("fuels_quad")
            .langValue("%s Quad Fuel Rod")
            .materialIconType(GTXMaterialIconType.fuelRodQuad)
            .unificationEnabled(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));

    public static final TagPrefix nuclearFuelDepleted = new TagPrefix("nuclear_fuel_depleted")
            .itemTable(() -> GTXItems.ABSORBABLE_ITEMS)
            .idPattern("%s_fuel_depleted")
            .defaultTagPath("depleted_fuels/%s")
            .unformattedTagPath("depleted_fuels")
            .langValue("%s Depleted Fuel")
            .materialIconType(MaterialIconType.stickLong)
            .unificationEnabled(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.INGOT) && mat.hasFlag(GTXMaterials.GENERATE_NUCLEAR_COMPOUND));


    // Bogus tag for making ReactorComponentItem possible
    public static final TagPrefix nuclearComponent = new TagPrefix("nuclear_component")
            .materialIconType(MaterialIconType.dust)
            .generationCondition(mat -> false);

    public static final FluidStorageKey fluidHot = new FluidStorageKey(
            GTXMod.id("hot"),
            MaterialIconType.gas,
            UnaryOperator.identity(),
            m -> {
                return "gtx.fluid.hot";
            },
            FluidState.GAS,
            0);

    public static void init() {
    }
}
