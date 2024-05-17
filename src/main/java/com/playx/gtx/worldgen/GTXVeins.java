package com.playx.gtx.worldgen;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTLayerPattern;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTOres;
import com.playx.gtx.GTXMod;
import com.playx.gtx.materials.GTXMaterials;
import com.playx.gtx.mixin.GTOresAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class GTXVeins {


    public static void init() {}

    public static final GTOreDefinition MAGNETITE_VEIN = create("magnetite_vein_2", vein ->
            vein
                    .clusterSize(UniformInt.of(38, 44))
                    .weight(100)
                    .density(0.3f)
                    .heightRangeUniform(10, 60)
                    .layer(WorldGenLayers.STONE)
                    .biomes(BiomeTags.IS_OVERWORLD)
                    .layeredVeinGenerator(generator -> generator
                            .withLayerPattern(() -> GTLayerPattern.builder(GTOres.OVERWORLD_RULES)
                                    .layer(l -> l.weight(60).mat(GTMaterials.Magnetite))
                                    .layer(l -> l.weight(15).mat(GTMaterials.Iron))
                                    .layer(l -> l.weight(15).mat(GTMaterials.VanadiumMagnetite))
                                    .layer(l -> l.weight(10).mat(GTXMaterials.PreciousMetal))
                                    .build()))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Magnetite)
                            .surfaceRock(GTXMaterials.PreciousMetal)
                            .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE))
                );

    public static final GTOreDefinition MAGNETITE_VEIN_END = create("magnetite_vein_end_2", vein ->
            vein
                    .clusterSize(GTOres.MAGNETITE_VEIN_OW.clusterSize())
                    .weight(GTOres.MAGNETITE_VEIN_OW.weight())
                    .heightRange(GTOres.MAGNETITE_VEIN_OW.range())
                    .biomes(GTOres.MAGNETITE_VEIN_OW.biomes())
                    .layeredVeinGenerator(generator -> generator
                            .withLayerPattern(() -> GTLayerPattern.builder(GTOres.END_RULES)
                                    .layer(l -> l.weight(60).mat(GTMaterials.Magnetite))
                                    .layer(l -> l.weight(15).mat(GTMaterials.Iron))
                                    .layer(l -> l.weight(15).mat(GTMaterials.VanadiumMagnetite))
                                    .layer(l -> l.weight(10).mat(GTXMaterials.PreciousMetal))
                                    .build()))
                    .surfaceIndicatorGenerator(indicator -> indicator
                            .surfaceRock(GTMaterials.Magnetite)
                            .surfaceRock(GTXMaterials.PreciousMetal)
                            .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE))
    );

    private static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {
        GTXMod.LOGGER.info("registering vein {}", name);
        GTOreDefinition def = GTOres.blankOreDefinition();
        config.accept(def);

        ResourceLocation id = GTXMod.id(name);
        def.register(id);
        GTOresAccessor.getToReRegister().put(id, def);

        return def;
    }

}
