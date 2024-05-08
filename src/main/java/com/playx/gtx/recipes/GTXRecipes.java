package com.playx.gtx.recipes;


import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.playx.gtx.recipes.chain.AluminiumChain;
import com.playx.gtx.recipes.chain.GoldChain;
import com.playx.gtx.recipes.chain.LithiumChain;
import com.playx.gtx.recipes.chain.SEX;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class GTXRecipes {

    public static void registerRecipes(Consumer<FinishedRecipe> provider) {
        GoldChain.goldChain(provider);
        AluminiumChain.registerAluminiumChain(provider);
        LithiumChain.lithiumChain(provider);
        SEX.registerSEX(provider);
    }
}
