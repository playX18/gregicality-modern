package com.playx.gtx.machines.multi;

import com.gregtechceu.gtceu.api.capability.IControllable;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;


public class VoidMiner extends WorkableElectricMultiblockMachine implements IControllable {
    private int maxTemperature;
    private static final int CONSUME_START = 100;
    private int tier;

    private IEnergyContainer energyContainer;

    public VoidMiner(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }
}
