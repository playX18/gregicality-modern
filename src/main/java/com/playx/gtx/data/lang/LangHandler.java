package com.playx.gtx.data.lang;

import java.lang.reflect.InvocationTargetException;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import org.jetbrains.annotations.NotNull;

import com.playx.gtx.materials.GTXMaterials;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.minecraftforge.common.data.LanguageProvider;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class LangHandler {
    public static void init(RegistrateLangProvider provider) {
        for (var tagPrefix : TagPrefix.values()) {
            provider.add(tagPrefix.getUnlocalizedName(), tagPrefix.langValue());
        }

        provider.add("hatch.nuclear_reactor.temperature", "Temperature: %sC");
        provider.add("hatch.nuclear_reactor_fluid.title", "Fluid Reactor Hatch");
        provider.add("hatch.nuclear_reactor_item.title", "Item Reactor Hatch");
    }    

    /**
     * Replace a value in a language provider's mappings
     *
     * @param provider the provider whose mappings should be modified
     * @param key the key for the value
     * @param value the value to use in place of the old one
     */
    @SuppressWarnings("unchecked")
    public static void replace(@NotNull RegistrateLangProvider provider, @NotNull String key, @NotNull String value) {
        try {
            // the regular lang mappings
            Field field = LanguageProvider.class.getDeclaredField("data");
            field.setAccessible(true);
            // noinspection unchecked
            Map<String, String> map = (Map<String, String>) field.get(provider);
            map.put(key, value);

            // upside-down lang mappings
            Field upsideDownField = RegistrateLangProvider.class.getDeclaredField("upsideDown");
            upsideDownField.setAccessible(true);
            // noinspection unchecked
            map = (Map<String, String>) field.get(upsideDownField.get(provider));

            Method toUpsideDown = RegistrateLangProvider.class.getDeclaredMethod("toUpsideDown", String.class);
            toUpsideDown.setAccessible(true);

            map.put(key, (String) toUpsideDown.invoke(provider, value));
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error replacing entry in datagen.", e);
        }
    }
}
