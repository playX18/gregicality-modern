package com.playx.gtx.machines.multi;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.TieredWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.playx.gtx.blocks.GTXBlocks;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;

public class NuclearReactor extends CoilWorkableElectricMultiblockMachine {
    public NuclearReactor(IMachineBlockEntity holder) {
        super(holder);
    }



    /*public static TraceabilityPredicate rodPredicate() {
        return new TraceabilityPredicate(blockWorldState -> {
            var blockState = blockWorldState.getBlockState();

            return false;
        }, );
    }*/



}
