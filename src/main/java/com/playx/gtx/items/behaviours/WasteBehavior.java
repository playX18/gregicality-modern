package com.playx.gtx.items.behaviours;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.component.ICustomDescriptionId;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.playx.gtx.GTXMod;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class WasteBehavior implements ItemColor, ICustomDescriptionId, IItemComponent {
    private Supplier<Material> material;
    private String unlocalizedName;
    private int color;

    public WasteBehavior(@NotNull Supplier<Material> material) {
        this.material = material;
    }

    public WasteBehavior(String name, int color) {
        this.unlocalizedName = name;
        this.color = color;
    }



    @Nullable
    @Override
    public Component getItemName(ItemStack stack) {
        if (unlocalizedName != null) {
            return Component.translatable(GTXMod.id(unlocalizedName).toString());
        } else {
            return Component.translatable(GTXMod.id(material.get().getName() + "_waste").toString());
        }
    }

    @Override
    public int getColor(ItemStack arg, int i) {
        if (color != 0) {
            return color;
        }
        int colorValue = material.get().getMaterialRGB();
        int colorOffset = 0x25;
        int r = (colorValue >> 16) & 0xFF;
        int g = (colorValue >> 8) & 0xFF;
        int b = (colorValue & 0xFF);

        r = Math.min(r + colorOffset, 0xFF);
        g = Math.min(g + colorOffset, 0xFF);
        b = Math.min(b + colorOffset, 0xFF);
        color = (r & 0xFF) << 16 | (g & 0xFF) << 8 | (b & 0xFF);
        return color;
    }
}
