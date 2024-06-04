package com.playx.gtx.items;

import com.playx.gtx.fission.NuclearFuel;
import com.playx.gtx.fission.ReactorAbsorbable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Tooltips {
    public static final PriorityQueue<TooltipAttachment> TOOLTIPS = new PriorityQueue<>();

    public static final Style DEFAULT_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(0xa9a9a9)).withItalic(false);
    public static final Style NUMBER_TEXT = Style.EMPTY.withColor(TextColor.fromRgb(0xffde7d)).withItalic(false);

    private static final Map<Class<?>, Style> DEFAULT_ARGUMENT_STYLE = new HashMap<>();

    static {
        DEFAULT_ARGUMENT_STYLE.put(Integer.class, NUMBER_TEXT);
        DEFAULT_ARGUMENT_STYLE.put(Long.class, NUMBER_TEXT);
        DEFAULT_ARGUMENT_STYLE.put(Float.class, NUMBER_TEXT);
        DEFAULT_ARGUMENT_STYLE.put(Double.class, NUMBER_TEXT);
    }

    public static void attachTooltip(ItemStack stack, List<Component> lines) {
        Item item = stack.getItem();
        if (item != null) {
            boolean hasPrintRequiredShift = false;
            for (var tooltip : TOOLTIPS) {
                Optional<List<? extends Component>> maybeComponents = tooltip.tooltipLines.apply(stack, stack.getItem());
                maybeComponents.ifPresent(lines::addAll);
            }
        }
    }


    public static int colorFromProgress(double progress, boolean zeroIsGreen) {
        // clip to [0, 1]
        progress = Math.max(0, Math.min(1, progress));
        if (!zeroIsGreen) {
            progress = 1 - progress;
        }

        double r = Math.min(2 * progress, 1);
        double g = Math.min(1, 2 - 2 * progress);
        return (int) (r * 255) << 16 | (int) (g * 255) << 8;
    }

    public static Style styleFromProgress(double progress, boolean zeroIsGreen) {
        return Style.EMPTY.withColor(TextColor.fromRgb(colorFromProgress(progress, zeroIsGreen))).withItalic(false);
    }

    @FunctionalInterface
    public interface Parser<T> {
        Component parse(T t);
    }

    public static class TooltipAttachment implements Comparable<TooltipAttachment> {

        public final BiFunction<ItemStack, Item, Optional<List<? extends Component>>> tooltipLines;
        public boolean requiresShift = true;
        public int priority = 0;

        private TooltipAttachment(BiFunction<ItemStack, Item, Optional<List<? extends Component>>> tooltipLines) {
            this.tooltipLines = tooltipLines;
            Tooltips.TOOLTIPS.add(this);
        }

        public static TooltipAttachment of(ItemLike itemLike, Component line) {

            return new TooltipAttachment(
                    (itemStack, item) -> itemStack.getItem() == itemLike.asItem() ? Optional.of(List.of(line)) : Optional.empty());
        }

        public static TooltipAttachment of(BiFunction<ItemStack, Item, Optional<? extends Component>> tooltipLines) {

            return new TooltipAttachment((itemStack, item) -> tooltipLines.apply(itemStack, item).map(List::of));
        }

        public static TooltipAttachment ofMultilines(BiFunction<ItemStack, Item, Optional<List<? extends Component>>> tooltipLines) {
            return new TooltipAttachment(tooltipLines);
        }

        public static TooltipAttachment ofMultilines(ItemLike itemLike, List<? extends Component> tooltipLines) {
            return new TooltipAttachment((itemStack, item) -> {
                if (itemStack.getItem() == itemLike.asItem()) {
                    return Optional.of(tooltipLines);
                } else {
                    return Optional.empty();
                }
            });
        }

        public static TooltipAttachment ofMultilines(ItemLike itemLike, Component... tooltipLines) {
            return ofMultilines(itemLike, Arrays.stream(tooltipLines).collect(Collectors.toList()));
        }

        public TooltipAttachment noShiftRequired() {
            this.requiresShift = false;
            return this;
        }

        public TooltipAttachment setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        @Override
        public int compareTo(@NotNull Tooltips.TooltipAttachment o) {
            return -Integer.compare(priority, o.priority);
        }
    }

    public static final TooltipAttachment NUCLEAR = TooltipAttachment.ofMultilines(
            ((itemStack, item) -> {
                if (item instanceof ReactorAbsorbable) {
                    List<Component> tooltips = new LinkedList<>();
                    long remAbs = ((ReactorAbsorbable) itemStack.getItem()).getRemainingDesintegrations(itemStack);
                    long maxAbs = ((ReactorAbsorbable) itemStack.getItem()).desintegrationMax;
                    tooltips.add(Component.translatableWithFallback("tooltip.gtx.remaining_des", "Remaining Desintegrations: %s/%s", remAbs, maxAbs));
                    if (itemStack.getItem() instanceof NuclearFuel fuel) {
                        long totalEu = (long) fuel.getTotalEUbyDesintegration() * fuel.desintegrationMax;
                        tooltips.add(Component.translatableWithFallback("tooltip.gtx.total_eu", "Total EU: %s", totalEu));
                    }
                    return Optional.of(tooltips);
                } else {
                    return Optional.empty();
                }
            }));

}
