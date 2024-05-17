package com.playx.gtx.blocks;

import com.gregtechceu.gtceu.api.block.RendererBlock;
import com.gregtechceu.gtceu.api.block.RendererGlassBlock;
import com.gregtechceu.gtceu.api.item.RendererBlockItem;
import com.gregtechceu.gtceu.client.renderer.block.TextureOverrideRenderer;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.lowdragmc.lowdraglib.Platform;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;
@SuppressWarnings({"unused", "removal"})
public class GTXBlocks {
    public static BlockEntry<Block> CLADDED_REACTOR_CASING = createCasingBlock("cladded_reactor_casing", GTXMod.id("block/casings/solid/cladded_reactor_casing"));
    public static BlockEntry<Block> HYPER_CASING = createCasingBlock("hyper_casing", GTXMod.id("block/casings/solid/hyper_casing"));
    public static BlockEntry<Block> HYPER_CASING2 = createCasingBlock("hyper_casing", GTXMod.id("block/casings/solid/hyper_casing_2"));

    public static BlockEntry<Block> BIO_REACTOR = simpleCasing("casings/solid/bio_reactor_casing");
    public static BlockEntry<Block> THORIUM_CASING = simpleCasing("casings/metal_casings/thorium");
    public static BlockEntry<Block> PROTACTINIUM_CASING = simpleCasing("casings/metal_casings/protactinium");
    public static BlockEntry<Block> URANIUM_CASING = simpleCasing("casings/metal_casings/uranium");
    public static BlockEntry<Block> NEPTUNIUM_CASING = simpleCasing("casings/metal_casings/neptunium");
    public static BlockEntry<Block> PLUTONIUM_CASING = simpleCasing("casings/metal_casings/plutonium");
    public static BlockEntry<Block> AMERICIUM_CASING = simpleCasing("casings/metal_casings/americium");
    public static BlockEntry<Block> CURIUM_CASING = simpleCasing("casings/metal_casings/curium");
    public static BlockEntry<Block> BERKELIUM_CASING = simpleCasing("casings/metal_casings/berkelium");
    public static BlockEntry<Block> CALIFORNIUM_CASING = simpleCasing("casings/metal_casings/californium");
    public static BlockEntry<Block> EINSTEINIUM_CASING = simpleCasing("casings/metal_casings/einsteinium");
    public static BlockEntry<Block> FERMIUM_CASING = simpleCasing("casings/metal_casings/fermium");
    public static BlockEntry<Block> MENDELEVIUM_CASING = simpleCasing("casings/metal_casings/mendelevium");
    public static BlockEntry<Block> HASTELLOY_X78_CASING = simpleCasing("casings/metal_casings/hastelloy_x78");
    public static BlockEntry<Block> HASTELLOY_N_CASING = simpleCasing("casings/metal_casings/hastelloy_n");
    public static BlockEntry<Block> HASTELLOY_K243_CASING = simpleCasing("casings/metal_casings/hastelloy_k243");
    public static BlockEntry<Block> INCOLOY_813_CASING = simpleCasing("casings/metal_casings/incoloy_813");
    public static BlockEntry<Block> INCOLOY_MA956_CASING = simpleCasing("casings/metal_casings/incoloy_ma956");
    public static BlockEntry<Block> MARAGING_STEEL_250_CASING = simpleCasing("casings/metal_casings/maraging_steel_250");
    public static BlockEntry<Block> NITINOL_60_CASING = simpleCasing("casings/metal_casings/nitinol_60");
    public static BlockEntry<Block> INCONEL_625_CASING = simpleCasing("casings/metal_casings/inconel_625");
    public static BlockEntry<Block> GRISIUM_CASING = simpleCasing("casings/metal_casings/grisium");
    public static BlockEntry<Block> EGLIN_STEEL_CASING = simpleCasing("casings/metal_casings/eglin_steel");
    public static BlockEntry<Block> BABBITT_ALLOY_CASING = simpleCasing("casings/metal_casings/babbitt_alloy");
    public static BlockEntry<Block> HG_1223_CASING = simpleCasing("casings/metal_casings/hg_1223");
    public static BlockEntry<Block> TUMBAGA_CASING = simpleCasing("casings/metal_casings/tumbaga");
    public static BlockEntry<Block> TALONITE_CASING = simpleCasing("casings/metal_casings/talonite");
    public static BlockEntry<Block> ZIRCONIUM_CARBIDE_CASING = simpleCasing("casings/metal_casings/zirconium_carbide");
    public static BlockEntry<Block> POTIN_CASING = simpleCasing("casings/metal_casings/potin");
    public static BlockEntry<Block> STABALLOY_CASING = simpleCasing("casings/metal_casings/staballoy");
    public static BlockEntry<Block> STELLITE_CASING = simpleCasing("casings/metal_casings/stellite");
    public static BlockEntry<Block> ENRICHED_NAQUADAH_ALLOY_CASING = simpleCasing("casings/metal_casings/enriched_naquadah_alloy");
    public static BlockEntry<Block> QUANTUM_CASING = simpleCasing("casings/metal_casings/quantum");
    public static BlockEntry<Block> TRITANIUM_CASING = simpleCasing("casings/metal_casings/tritanium");
    public static BlockEntry<Block> BLACK_STEEL_CASING = simpleCasing("casings/metal_casings/black_steel");
    public static BlockEntry<Block> RED_STEEL_CASING = simpleCasing("casings/metal_casings/red_steel");
    public static BlockEntry<Block> IRON_CASING = simpleCasing("casings/metal_casings/iron");
    public static BlockEntry<Block> HSS_G_CASING = simpleCasing("casings/metal_casings/hss_g");
    public static BlockEntry<Block> HSS_S_CASING = simpleCasing("casings/metal_casings/hss_s");
    public static BlockEntry<Block> LEAD_CASING = simpleCasing("casings/metal_casings/lead");
    public static BlockEntry<Block> NAQUADRIA_CASING = simpleCasing("casings/metal_casings/naquadria");
    public static BlockEntry<Block> BOROSILICATE_REINFORCED_GLASS = createGlassCasingBlock("borosilicate_reinforced_glass", GTXMod.id("block/casings/transparent/borosilicate_reinforced_glass"), () -> RenderType::translucent);
    public static BlockEntry<Block> NICKEL_REINFORCED_GLASS = createGlassCasingBlock("nickel_reinforced_glass", GTXMod.id("block/casings/transparent/nickel_reinforced_glass"), () -> RenderType::translucent);
    public static BlockEntry<Block> CHROME_REINFORCED_GLASS = createGlassCasingBlock("chrome_reinforced_glass", GTXMod.id("block/casings/transparent/chrome_reinforced_glass"), () -> RenderType::translucent);
    public static BlockEntry<Block> TUNGSTEN_REINFORCED_GLASS = createGlassCasingBlock("tungsten_reinforced_glass", GTXMod.id("block/casings/transparent/tungsten_reinforced_glass"), () -> RenderType::translucent);
    public static BlockEntry<Block> IRIDIUM_REINFORCED_GLASS = createGlassCasingBlock("iridium_reinforced_glass", GTXMod.id("block/casings/transparent/iridium_reinforced_glass"), () -> RenderType::translucent);
    public static BlockEntry<Block> OSMIRIDIUM_REINFORCED_GLASS = createGlassCasingBlock("osmiridium_reinforced_glass", GTXMod.id("block/casings/transparent/osmium_reinforced_glass"), () -> RenderType::translucent);


    public static BlockEntry<Block> simpleCasing(String location) {
        var path = location.split("/");
        var name = path[path.length - 1];

        return createCasingBlock("casing_" + name, GTXMod.id("block/" + location));
    }

    private static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, RendererBlock::new, texture, () -> Blocks.IRON_BLOCK, () -> RenderType::cutoutMipped);
    }

    private static BlockEntry<Block> createCasingBlock(String name, BiFunction<BlockBehaviour.Properties, IRenderer, ? extends RendererBlock> blockSupplier, ResourceLocation texture, NonNullSupplier<? extends Block> properties, Supplier<Supplier<RenderType>> type) {
        return GTXRegistries.REGISTRATE.block(name, p -> (Block) blockSupplier.apply(p,
                        Platform.isClient() ? new TextureOverrideRenderer(new ResourceLocation("block/cube_all"),
                                Map.of("all", texture)) : null))
                .initialProperties(properties)
                .addLayer(type)
                .blockstate(NonNullBiConsumer.noop())
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(RendererBlockItem::new)
                .model(NonNullBiConsumer.noop())
                .build()
                .register();
    }

    private static BlockEntry<Block> createBottomTopCasingBlock(String name, BiFunction<BlockBehaviour.Properties, IRenderer, ? extends RendererBlock> blockSupplier, ResourceLocation sideTexture, ResourceLocation topTexture, ResourceLocation bottomTexture, com.tterrag.registrate.util.nullness.NonNullSupplier<? extends Block> properties, Supplier<Supplier<RenderType>> type) {
        return GTXRegistries.REGISTRATE.block(name, p -> (Block) blockSupplier.apply(p,
                        Platform.isClient() ? new TextureOverrideRenderer(new ResourceLocation("block/cube_bottom_top"),
                                Map.of("side", sideTexture, "top", topTexture, "bottom", bottomTexture)) : null))
                .initialProperties(properties)
                .addLayer(type)
                .blockstate(NonNullBiConsumer.noop())
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(RendererBlockItem::new)
                .model(NonNullBiConsumer.noop())
                .build()
                .register();
    }

    private static BlockEntry<Block> createSidedCasingBlock(String name, String texture) {
        return createCasingBlock(
                name, (properties, iRenderer) -> new RendererBlock(properties,
                        Platform.isClient() ? new TextureOverrideRenderer(new ResourceLocation("block/cube_bottom_top"),
                                Map.of("bottom", GTXMod.id(texture + "/bottom"),
                                        "top", GTXMod.id(texture + "/top"),
                                        "side", GTXMod.id(texture + "/side"))) : null),
                GTXMod.id(texture), () -> Blocks.IRON_BLOCK, () -> RenderType::cutoutMipped
        );
    }

    private static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture, Supplier<Supplier<RenderType>> type) {
        return createCasingBlock(name, RendererGlassBlock::new, texture, () -> Blocks.GLASS, type);
    }


    public static void init() {
        GTXCoils.init();
    }
}
