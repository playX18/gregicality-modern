package com.playx.gtx.materials.properties;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;

public class GTXPropertyKeys {

    public static final PropertyKey<MixProperty> MIX = new PropertyKey<>("mix", MixProperty.class);
    public static final PropertyKey<NuclearProperty> NUCLEAR = new PropertyKey<>("nuclear", NuclearProperty.class);
    public static final PropertyKey<NuclearAbsorbableProperty> NUCLEAR_ABSORBABLE = new PropertyKey<>("nuclear_absorbable", NuclearAbsorbableProperty.class);
    public static final PropertyKey<NuclearFuelProperty> NUCLEAR_FUEL = new PropertyKey<NuclearFuelProperty>("nuclear_fuel", NuclearFuelProperty.class);
    public static final PropertyKey<NuclearItemProperty> NUCLEAR_ITEM = new PropertyKey<>("nuclear_item", NuclearItemProperty.class);
    public static final PropertyKey<IsotopeProperty> ISOTOPE = new PropertyKey<>("isotope", IsotopeProperty.class);
    public static void init() {

    }
}
