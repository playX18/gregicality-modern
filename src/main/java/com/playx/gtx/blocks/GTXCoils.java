package com.playx.gtx.blocks;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.api.item.RendererBlockItem;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.materials.GTXMaterials;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;


public class GTXCoils {
    /*private static BlockEntry<CoilBlock> createCoilBlock(ICoilType coilType) {
        BlockEntry<CoilBlock> coilBlock = REGISTRATE.block("%s_coil_block".formatted(coilType.getName()), p -> new CoilBlock(p, coilType))
                .initialProperties(() -> Blocks.f_50075_)
                .addLayer(() -> RenderType::m_110457_)
                .blockstate(NonNullBiConsumer.noop())
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.f_144282_)
                .item(RendererBlockItem::new)
                .model(NonNullBiConsumer.noop())
                .onRegister(compassNodeExist(GTCompassSections.BLOCKS, "coil_block"))
                .build()
                .register();
        ALL_COILS.put(coilType, coilBlock);
        return coilBlock;
    }

    public static final BlockEntry<CoilBlock> BLACK_TITANIUM_COIL = new SimpleCoilType("black_titanium_coil", 11200, 64, 16, )*/

    public enum CoilType implements StringRepresentable, ICoilType {

        BLACK_TITANIUM_COIL("black_titanium", 11200, 64, 16, () -> GTXMaterials.BlackTitanium, GTXMod.id("block/casings/solid/heating_coil/heating.coil.uiv")),
        NEUTRONIUM_COIL("neutronium", 12600, 64, 16, () -> GTMaterials.Neutronium, GTXMod.id("block/casings/solid/heating_coil/machine_coil_neutronium")),
        COSMIC_NEUTRONIUM_COIL("cosmic_neutronium", 14200, 128, 32, () -> GTXMaterials.CosmicNeutronium, GTXMod.id("block/casings/solid/heating_coil/heating.coil.uxv"));
        @NotNull @Getter
        private final String name;
        //electric blast furnace properties
        @Getter
        private final int coilTemperature;
        //multi smelter properties
        @Getter
        private final int level;
        @Getter
        private final int energyDiscount;
        @NotNull
        private final Supplier<Material> material;
        @NotNull @Getter
        private final ResourceLocation texture;

        CoilType(@NotNull String name, int coilTemperature, int level, int energyDiscount, @NotNull Supplier<Material> material, @NotNull ResourceLocation texture) {
            this.name = name;
            this.coilTemperature = coilTemperature;
            this.level = level;
            this.energyDiscount = energyDiscount;
            this.material = material;
            this.texture = texture;
        }

        public int getTier() {
            return this.ordinal();
        }

        @NotNull
        @Override
        public String toString() {
            return getName();
        }

        public Material getMaterial() {
            return material.get();
        }

        @Override
        @NotNull
        public String getSerializedName() {
            return name;
        }
    }

    private static BlockEntry<CoilBlock> createCoilBlock(ICoilType coilType) {

        var coilBlock = GTXRegistries.REGISTRATE.block("%s_coil_block".formatted(coilType.getName()), p -> new CoilBlock(p, coilType))
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .blockstate(NonNullBiConsumer.noop())
                .tag(TagUtil.createBlockTag("mineable/wrench"), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(RendererBlockItem::new)
                .model(NonNullBiConsumer.noop())
                .build()
                .register();

        GTCEuAPI.HEATING_COILS.put(coilType, coilBlock);
        return coilBlock;
    }

    public static final BlockEntry<CoilBlock> BLACK_TITANIUM_COIL = createCoilBlock(CoilType.BLACK_TITANIUM_COIL);
    public static final BlockEntry<CoilBlock> NEUTRONIUM_COIL = createCoilBlock(CoilType.NEUTRONIUM_COIL);
    public static final BlockEntry<CoilBlock> COSMIC_NEUTRONIUM_COIL = createCoilBlock(CoilType.COSMIC_NEUTRONIUM_COIL);

    public static void init() {}
}
