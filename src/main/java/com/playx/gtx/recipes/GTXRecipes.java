package com.playx.gtx.recipes;


import com.playx.gtx.recipes.chain.GoldChain;
import com.playx.gtx.recipes.chain.LithiumChain;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class GTXRecipes {

    public static void registerRecipes(Consumer<FinishedRecipe> provider) {
        GoldChain.goldChain(provider);
        LithiumChain.lithiumChain(provider);
    }
}
