package com.playx.gtx.items;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IDataItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.api.item.component.IMaterialPartItem;
import com.gregtechceu.gtceu.api.item.component.ISubItemHandler;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTModels;
import com.gregtechceu.gtceu.common.item.TurbineRotorBehaviour;
import com.lowdragmc.lowdraglib.client.renderer.IItemRendererProvider;
import com.lowdragmc.lowdraglib.side.fluid.FluidHelper;
import com.lowdragmc.lowdraglib.side.fluid.FluidTransferHelper;
import com.playx.gtx.GTXMod;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.materials.GTXMaterials;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GTXItems {
    static class WasteBehaviour implements IMaterialPartItem {

        @Override
        public int getPartMaxDurability(ItemStack itemStack) {
            return 0;
        }

        @Nullable
        public static WasteBehaviour getBehaviour(@NotNull ItemStack itemStack) {
            if (itemStack.getItem() instanceof ComponentItem componentItem) {
                for (var component : componentItem.getComponents()) {
                    if (component instanceof WasteBehaviour behaviour) {
                        return behaviour;
                    }
                }
            }
            return null;
        }
    }



    public static ItemEntry<ComponentItem> WASTE = GTXRegistries.REGISTRATE.item("waste_nucler", ComponentItem::create)
            .lang("Nuclear Waste")
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, GTXMod.id("items/metaitems/nuclear_waste")))
            .color(() -> IMaterialPartItem::getItemStackColor)
            .onRegister(attach(new WasteBehaviour())).register();;

    public static ItemStack THORIUM_WASTE = makeWasteStack("thorium_waste", GTXMaterials.ThoriumRadioactive.getMaterial());
    public static ItemStack PROTACTINIUM_WASTE = makeWasteStack("protactinium_stack", GTXMaterials.Protactinium.getMaterial());
    public static ItemStack URANIUM_WASTE;
    public static ItemStack NEPTUNIUM_WASTE;
    public static ItemStack PLUTONIUM_WASTE;
    public static ItemStack AMERICIUM_WASTE;
    public static ItemStack CURIUM_WASTE;
    public static ItemStack BERKELIUM_WASTE;
    public static ItemStack CALIFORNIUM_WASTE;
    public static ItemStack EINSTEINIUM_WASTE;
    public static ItemStack FERMIUM_WASTE;
    public static ItemStack MENDELEVIUM_WASTE;
    public static ItemStack NUCLEAR_WASTE;
    public static ItemStack NUCLEAR_WASTE_LANTHANIDE_A;
    public static ItemStack NUCLEAR_WASTE_LANTHANIDE_B;
    public static ItemStack NUCLEAR_WASTE_ALKALINE;
    public static ItemStack NUCLEAR_WASTE_METAL_A;
    public static ItemStack NUCLEAR_WASTE_METAL_B;
    public static ItemStack NUCLEAR_WASTE_METAL_C;
    public static ItemStack NUCLEAR_WASTE_HEAVY_METAL;
    public static ItemStack NUCLEAR_WASTE_METALOID;
    public static ItemStack NUCLEAR_WASTE_REACTIVE_NONMETAL;



    public static ItemStack makeWasteStack(String name, Material material) {
        ItemStack wasteStack = WASTE.asStack();
        var behaviour = Objects.requireNonNull(WasteBehaviour.getBehaviour(wasteStack));
        behaviour.setPartMaterial(wasteStack, material);

        return wasteStack;
    }

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static void init() {

    }

}
