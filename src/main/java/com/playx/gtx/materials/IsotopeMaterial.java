package com.playx.gtx.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.playx.gtx.GTXMod;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsotopeMaterial extends EnrichmentProcess {
    public static Map<Material, IsotopeMaterial> REGISTRY = new HashMap<>();

    private int nuclide = 0;
    @Getter
    private RadioactiveMaterial radioactiveMaterial = null;

    public boolean fertile = false;
    public boolean fissile = false;
    public int baseHeat = 10;

    public final Map<IsotopeMaterial, Integer> isotopeDecay = new HashMap<>();

    public IsotopeMaterial(RadioactiveMaterial material, int nuclide, MaterialFlag... flags) {
        /*this(new Material.Builder(GTXMod.id(material.getMaterial().getName() + "_" + nuclide))
                .color(material.getMaterial().getMaterialRGB())
                .iconSet(material.getMaterial().getMaterialIconSet())
                //.components(material.getMaterial().getMaterialComponents())
                .flags(flags)
                //.element(material.getMaterial().getElement())
                .buildAndRegister(), material, nuclide);*/
    }

    public IsotopeMaterial(Material from, RadioactiveMaterial material, int nuclide) {
        this.material = from;
        //this.material.addFlags(GENERATE_LONG_ROD | GENERATE_ISOTOPES_COMPOUND | DISABLE_REPLICATION | GENERATE_NUCLEAR_COMPOUND);
        this.material.addFlags(MaterialFlags.GENERATE_LONG_ROD, GTXMaterials.GENERATE_NUCLEAR_COMPOUND, GTXMaterials.GENERATE_ISOTOPES_COMPOUND, GTXMaterials.DISABLE_REPLICATION);
        this.radioactiveMaterial = material;
        this.nuclide = nuclide;
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
