package com.playx.gtx;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.playx.gtx.materials.GTXElements;
import com.playx.gtx.materials.GTXTags;
import com.playx.gtx.recipes.GTXRecipes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@GTAddon
public class GTXGTAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return GTXRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }

    @Override
    public String addonModId() {
        return GTXMod.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        GTXTags.init();
    }

    @Override
    public boolean requiresHighTier() {
        return true;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        //CustomRecipes.init(provider);
        GTXRecipes.registerRecipes(provider);
    }

    @Override
    public void registerElements() {
        GTXElements.init();
    }

    // If you have custom ingredient types, uncomment this & change to match your capability.
    // KubeJS WILL REMOVE YOUR RECIPES IF THESE ARE NOT REGISTERED.
    /*
    public static final ContentJS<Double> PRESSURE_IN = new ContentJS<>(NumberComponent.ANY_DOUBLE, GregitasRecipeCapabilities.PRESSURE, false);
    public static final ContentJS<Double> PRESSURE_OUT = new ContentJS<>(NumberComponent.ANY_DOUBLE, GregitasRecipeCapabilities.PRESSURE, true);

    @Override
    public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        event.registerKey(CustomRecipeCapabilities.PRESSURE, Pair.of(PRESSURE_IN, PRESSURE_OUT));
    }
    */
}
