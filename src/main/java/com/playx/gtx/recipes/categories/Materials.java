package com.playx.gtx.recipes.categories;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMachines;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import com.playx.gtx.GTXMod;
import com.playx.gtx.recipes.GTXRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class Materials {
    public static void init(Consumer<FinishedRecipe> provider) {
        GTXRecipeTypes.LARGE_MIXER_RECIPES.recipeBuilder(GTXMod.id("mix_zircon_100"))
                .circuitMeta(6)
                .inputItems(TagPrefix.dust, Steel, 20)
                .inputItems(TagPrefix.dust, Chromium, 13)
                .inputItems(TagPrefix.dust, Copper, 10)
                .inputItems(TagPrefix.dust, Nickel, 3)
                .inputItems(TagPrefix.dust, Molybdenum, 2)
                .inputItems(TagPrefix.dust, Tungsten, 2)
                .save(provider);


    }
}
