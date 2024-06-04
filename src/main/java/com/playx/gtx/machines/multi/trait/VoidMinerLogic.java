package com.playx.gtx.machines.multi.trait;

import com.google.common.collect.Table;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VoidMinerLogic extends RecipeLogic implements IRecipeCapabilityHolder {
    public VoidMinerLogic(IRecipeLogicMachine machine) {
        super(machine);
    }

    @Override
    public @NotNull Table<IO, RecipeCapability<?>, List<IRecipeHandler<?>>> getCapabilitiesProxy() {
        return null;
    }
    //dpublic static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(MinerLogic.class, RecipeLogic.MANAGED_FIELD_HOLDER);
}
