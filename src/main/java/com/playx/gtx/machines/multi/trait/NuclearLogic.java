package com.playx.gtx.machines.multi.trait;

import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.misc.FluidRecipeHandler;
import com.gregtechceu.gtceu.api.misc.IgnoreEnergyRecipeHandler;
import com.gregtechceu.gtceu.api.misc.ItemRecipeHandler;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.playx.gtx.machines.multi.NuclearReactor;
import lombok.Getter;

import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.List;

public class NuclearLogic extends RecipeLogic {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NuclearReactor.class, RecipeLogic.MANAGED_FIELD_HOLDER);
    @Getter
    private final Table<IO, RecipeCapability<?>, List<IRecipeHandler<?>>> capabilitiesProxy;
    private final ItemRecipeHandler inputItemHandler, outputItemHandler;
    private final IgnoreEnergyRecipeHandler inputEnergyHandler;

    public NuclearLogic(IRecipeLogicMachine machine) {
        super(machine);

        this.capabilitiesProxy = Tables.newCustomTable(new EnumMap<>(IO.class), IdentityHashMap::new);
        this.inputItemHandler = new ItemRecipeHandler(IO.IN, machine.getRecipeType().getMaxInputs(ItemRecipeCapability.CAP));
        this.outputItemHandler = new ItemRecipeHandler(IO.OUT, machine.getRecipeType().getMaxOutputs(ItemRecipeCapability.CAP));
        this.inputEnergyHandler = new IgnoreEnergyRecipeHandler();
        this.capabilitiesProxy.put(IO.IN, inputItemHandler.getCapability(), List.of(inputItemHandler));
        this.capabilitiesProxy.put(IO.IN, inputEnergyHandler.getCapability(), List.of(inputEnergyHandler));
        this.capabilitiesProxy.put(IO.OUT, outputItemHandler.getCapability(), List.of(outputItemHandler));
    }

    public void serverTick() {

    }
}
