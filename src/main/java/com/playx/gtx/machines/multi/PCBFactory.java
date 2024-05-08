package com.playx.gtx.machines.multi;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.TieredWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;

public class PCBFactory extends TieredWorkableElectricMultiblockMachine {
    public PCBFactory(IMachineBlockEntity holder, int tier, boolean bioUpgrade, boolean ocTier1, boolean ocTier2, Object... args) {
        super(holder, tier, args);
        this.tier = tier;
        this.bioUpgrade = bioUpgrade;
        this.OCTier1 = ocTier1;
        this.OCTier2 = ocTier2;

    }


    public enum PCBFactoryTier {
        TIER1,
        TIER2,
        TIER3,
        OC_TIER1,
        OC_TIER2,
    }

    public static String BIO_UPGRADE = "bioUpgrade";

    private float roughnessMultiplier = 1;
    private int tier = 1, setTier = 1, upgradesInstalled = 0, crrentParallel = 0, maxParallel = 0;
    private boolean bioUpgrade = false, BioRotate = false, OCTier1 = false, OCTier2 = false;
    private final int[] mBioOffsets = new int[] { -5, -1 };
    private final int[] mOCTier1Offsets = new int[] { 2, -11 };
    private final int[] mOCTier2Offsets = new int[] { 2, -11 };
    private FluidHatchPartMachine coolantInputHatch;
    private static final int mBioRotateBitMap = 0b1000000;
    private static final int mOCTier2BitMap = 0b100000;
    private static final int mOCTier1BitMap = 0b10000;
    private static final int mBioBitMap = 0b1000;
    private static final int mTier3BitMap = 0b100;
    private static final int mTier2BitMap = 0b10;
    private static final int mTier1BitMap = 0b1;
    private static final int COOLANT_CONSUMED_PER_SEC = 10;

}
