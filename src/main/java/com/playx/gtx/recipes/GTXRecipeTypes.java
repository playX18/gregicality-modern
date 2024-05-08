package com.playx.gtx.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

public class GTXRecipeTypes {

    public static final GTRecipeType FROTH_FLOTATION_RECIPES = GTRecipeTypes.register("froth_flotation", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(4, 4, 4, 4)
            .setEUIO(IO.IN)
            .prepareBuilder(gtRecipeBuilder -> gtRecipeBuilder.EUt((long) GTValues.VA[GTValues.HV]))
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT).setMaxTooltips(4)
            .setSound(GTSoundEntries.BATH);

    public static void init() {}
}
