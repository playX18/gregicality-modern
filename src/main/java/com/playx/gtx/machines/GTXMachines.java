package com.playx.gtx.machines;

import com.google.common.base.Joiner;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.blocks.GTXBlocks;
import com.playx.gtx.machines.multi.IndustrialPrimitiveBlastFurnace;
import it.unimi.dsi.fastutil.ints.Int2LongFunction;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GTXMachines {
    public final static int[] ELECTRIC_TIERS = GTValues.tiersBetween(GTValues.LV, GTCEuAPI.isHighTier() ? GTValues.OpV : GTValues.UV);
    public final static int[] LOW_TIERS = GTValues.tiersBetween(GTValues.LV, GTValues.EV);
    public final static int[] HIGH_TIERS = GTValues.tiersBetween(GTValues.IV, GTCEuAPI.isHighTier() ? GTValues.OpV : GTValues.UHV);
    public static final Int2LongFunction defaultTankSizeFunction = tier -> (tier <= GTValues.LV ? 8 : tier == GTValues.MV ? 12 : tier == GTValues.HV ? 16 : tier == GTValues.EV ? 32 : 64) * FluidHelper.getBucket();

    public static final MultiblockMachineDefinition INDUSTRIAL_PRIMITIVE_BLAST_FURNACE = GTXRegistries.REGISTRATE.multiblock("industrial_primitive_blast_furnace", IndustrialPrimitiveBlastFurnace::new)
            .recipeType(GTRecipeTypes.PRIMITIVE_BLAST_FURNACE_RECIPES)
            .rotationState(RotationState.NON_Y_AXIS)
            .appearanceBlock(GTBlocks.CASING_PRIMITIVE_BRICKS)
            .pattern(def ->
                    FactoryBlockPattern.start()
                            .aisle("YYY", "YYY", "YYY", "YYY")
                            .aisle("YYY", "Y#Y", "Y#Y", "Y#Y").setRepeatable(1, 64)
                            .aisle("YYY", "BXB", "YYY", "YYY")
                            .where('X', Predicates.controller(Predicates.blocks(def.getBlock())))
                            .where('#', Predicates.air())
                            //.where('I', Predicates.abilities(PartAbility.IMPORT_ITEMS).or(Predicates.blocks(GTBlocks.CASING_PRIMITIVE_BRICKS.get())))
                            //.where('O', Predicates.abilities(PartAbility.EXPORT_ITEMS).or(Predicates.blocks(GTBlocks.CASING_PRIMITIVE_BRICKS.get())))
                            .where('B', Predicates.autoAbilities(def.getRecipeTypes()).or(Predicates.blocks(GTBlocks.CASING_PRIMITIVE_BRICKS.get())))
                            .where('Y', Predicates.blocks(GTBlocks.CASING_PRIMITIVE_BRICKS.get()))
                            .build()
            )
            .recipeModifier(IndustrialPrimitiveBlastFurnace::recipeModifier)
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                    GTCEu.id("block/multiblock/electric_blast_furnace"), false)
            .compassSections(GTCompassSections.STEAM)
            .compassNodeSelf()
            .register();

    /*public static final MultiblockMachineDefinition FROTH_FLOTATION_CELL = GTXRegistries.REGISTRATE.multiblock("froth_flotation_cell", WorkableMultiblockMachine::new)
            .recipeType(GTXRecipeTypes.FROTH_FLOTATION_RECIPES)
            .register();
    */

    public static MultiblockMachineDefinition NUCLEAR_REACTOR = GTXRegistries.REGISTRATE.multiblock("nuclear_reactor", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.CHEMICAL_RECIPES)
            .appearanceBlock(GTXBlocks.CLADDED_REACTOR_CASING)
            .workableCasingRenderer(
                    GTXMod.id("block/casings/solid/cladded_reactor_casing"),
                    GTXMod.id("block/machines/nuclear_reactor"),
                    false)
            .pattern(definition ->
                    FactoryBlockPattern.start()
                            .aisle("YYY", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "YYY")
                            .aisle("YYY", "XRX", "XRX", "XRX", "XRX", "XRX", "XRX", "XRX", "YYY")
                            .aisle("YSY", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "ZXZ", "YYY")
                            .where('S', Predicates.controller(Predicates.blocks(definition.getBlock())))
                            .where('Y', Predicates.autoAbilities(definition.getRecipeTypes()).or(Predicates.blocks(GTXBlocks.CLADDED_REACTOR_CASING.get())))
                            .where('Z', Predicates.blocks(GTXBlocks.CLADDED_REACTOR_CASING.get()))
                            .where('X', Predicates.blocks(GTBlocks.CASING_TEMPERED_GLASS.get()).or(Predicates.blocks(GTXBlocks.CLADDED_REACTOR_CASING.get())))
                            .where('R', Predicates.air())
                            .build())
            .register();




    public static MachineDefinition COMPONENT_ASSEMBLY_LINE = GTXRegistries.REGISTRATE.multiblock("component_assembly_line", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTRecipeTypes.ASSEMBLY_LINE_RECIPES)
            .appearanceBlock(GTBlocks.PLASTCRETE)
            .workableCasingRenderer(
                    GTCEu.id("block/casings/gcym/large_scale_assembling_casing"),
                    GTXMod.id("block/machines/nuclear_reactor"),
                    false)
            .pattern(definition -> {
                return GT5FactoryBlockPattern.start()

                        .aisle("         ", "   III   ", " HHI~IHH ", "HH III HH", "H       H", "H       H", "H  JJJ  H",
                                "H  JJJ  H", "H  N N  H", "HHHHHHHHH")
                        .aisle("         ", " ELHHHLE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   BBB   ", " EL   LE ", "E GGDGG E", "HGG D GGH", "AG  C  GA", "AG     GA", "AG     GA",
                                "AG HHH GA", "AG     GA", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   BBB   ", " EL   LE ", "E GGDGG E", "HGG D GGH", "HG  C  GH", "HG     GH", "HG     GH",
                                "HG HHH GH", "HG     GH", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   BBB   ", " EL   LE ", "E GGDGG E", "HGG D GGH", "AG  C  GA", "AG     GA", "AG     GA",
                                "AG HHH GA", "AG     GA", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   BBB   ", " EL   LE ", "E GGDGG E", "HGG D GGH", "HG  C  GH", "HG     GH", "HG     GH",
                                "HG HHH GH", "HG     GH", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   BBB   ", " EL   LE ", "E GGDGG E", "HGG D GGH", "AG  C  GA", "AG     GA", "AG     GA",
                                "AG HHH GA", "AG     GA", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   BBB   ", " EL   LE ", "E GGDGG E", "HGG D GGH", "HG  C  GH", "HG     GH", "HG     GH",
                                "HG HHH GH", "HG     GH", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("   BBB   ", " EL   LE ", "E GGDGG E", "HGG D GGH", "AG  C  GA", "AG     GA", "AG     GA",
                                "AG HHH GA", "AG     GA", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A  n n  A", "MHHHHHHHM")
                        .aisle("   HBH   ", " EL   LE ", "E       E", "HC     CH", "AC     CA", "AC     CA", "A D   D A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("         ", " ELHHHLE ", "E       E", "H       H", "A       A", "A       A", "A       A",
                                "A  HHH  A", "A       A", "MHHHHHHHM")
                        .aisle("         ", "         ", " HHHHHHH ", "HH     HH", "H       H", "H       H", "H       H",
                                "H       H", "H  KKK  H", "HHHHHHHHH")
                        .where('A', Predicates.blocks(GTBlocks.CLEANROOM_GLASS.get()))
                        .where('H', Predicates.blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                        .where('C', Predicates.blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                        .where('D', Predicates.blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                        .where('G', Predicates.blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                        .where('E', Predicates.blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                        .where('F', Predicates.blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                        .where('B', Predicates.blocks(GTBlocks.CASING_ASSEMBLY_CONTROL.get()))
                        .where('J', Predicates.abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1).or(Predicates.blocks(GTBlocks.CASING_ASSEMBLY_LINE.get())))
                        .where('N', Predicates.abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1).or(Predicates.frames(GTMaterials.TungstenSteel)))
                        .where('K', Predicates.abilities(PartAbility.EXPORT_ITEMS).setMinGlobalLimited(1).or(Predicates.blocks(GTBlocks.CASING_ASSEMBLY_LINE.get())))
                        .where('L', Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1).or(Predicates.blocks(GTBlocks.CASING_ASSEMBLY_LINE.get())))
                        .where('I', Predicates.abilities(PartAbility.MAINTENANCE).setMinGlobalLimited(1).or(Predicates.blocks(GTBlocks.CASING_ASSEMBLY_LINE.get())))
                        .where('M', Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1).or(Predicates.blocks(GTBlocks.CASING_ASSEMBLY_LINE.get())))
                        .where('~', Predicates.controller(Predicates.blocks(definition.getBlock())))
                        .where('n', Predicates.frames(GTMaterials.TungstenSteel))
                        .build();
            }).register();


    public static void init() {

    }
    //public static final MultiblockMachineDefinition INDUSTRIAL_PRIMITIVE_BLAST_FURNACE = GTXRegistries.REGISTRATE.multiblock("industrial_primitive_blast_furnace", IndustrialPrimitiveBlastFurnace::new);
}
