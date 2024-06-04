package com.playx.gtx.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GCyMRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.common.data.machines.GCyMMachines;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import org.apache.commons.lang3.ArrayUtils;

import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class GTXRecipeTypes {

    public static final GTRecipeType FROTH_FLOTATION_RECIPES = GTRecipeTypes.register("froth_flotation", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(4, 4, 4, 4)
            .setEUIO(IO.IN)
            .prepareBuilder(gtRecipeBuilder -> gtRecipeBuilder.EUt((long) GTValues.VA[GTValues.HV]))
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT).setMaxTooltips(4)
            .setSound(GTSoundEntries.BATH);

    public static final GTRecipeType LARGE_MIXER_RECIPES = GTRecipeTypes.register("large_mixer", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(9, 1, 6, 1)
            .setEUIO(IO.IN)
            .prepareBuilder(gtRecipeBuilder -> gtRecipeBuilder.EUt((long) GTValues.VA[GTValues.ULV]))
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setIconSupplier(GTRecipeTypes.MIXER_RECIPES.getIconSupplier())
            .setSound(GTSoundEntries.MIXER);
    public static final GTRecipeType CHEMICAL_FACTORY_RECIPES = GTRecipeTypes.register("chemical_factory", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(9, 6, 9, 6)
            .setEUIO(IO.IN)
            .prepareBuilder(gtRecipeBuilder -> gtRecipeBuilder.EUt((long) GTValues.VA[GTValues.HV]))
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL)
            .setMaxTooltips(4);

    public static final GTRecipeType NUCLEAR_REACTOR_RECIPES = GTRecipeTypes.register("nuclear_reactor", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(4, 6, 0, 0)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING)
            .setMaxTooltips(4);

    public static final GTRecipeType NUCLEAR_BREEDER_REACTOR_RECIPES = GTRecipeTypes.register("nuclear_breeder_reactor", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(4, 6, 0, 0)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING)
            .setMaxTooltips(4);

    public static void init() {
        GCyMMachines.LARGE_MIXER.setRecipeTypes(ArrayUtils.add(GCyMMachines.LARGE_MIXER.getRecipeTypes(), LARGE_MIXER_RECIPES));
    }
}
