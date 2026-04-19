package com.bmt.kaleidoscope_twilight.client.tooltip;

import com.bmt.kaleidoscope_twilight.item.KeepingPouchItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class KeepingPouchTooltipComponent implements ClientTooltipComponent {
    private final NonNullList<ItemStack> items = NonNullList.create();

    public KeepingPouchTooltipComponent(KeepingPouchItem.KeepingPouchTooltip tooltip) {
        ItemStack pouchStack = tooltip.getPouchStack();

        if (KeepingPouchItem.hasItems(pouchStack)) {
            ItemStackHandler itemsHandler = KeepingPouchItem.getItems(pouchStack);

            for (int i = 0; i < itemsHandler.getSlots(); i++) {
                ItemStack stack = itemsHandler.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    this.items.add(stack.copy());

                    if (this.items.size() >= 64) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int getHeight() {
        if (items.isEmpty()) {
            return 0;
        }
        int row = (items.size() - 1) / 8 + 1;
        return 20 * row;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        if (items.isEmpty()) {
            return 0;
        }
        int maxInRow = Math.min(items.size(), 8);
        return maxInRow * 20;
    }

    @Override
    public void renderImage(@NotNull Font font, int pX, int pY, @NotNull GuiGraphics guiGraphics) {
        if (!items.isEmpty()) {
            int i = 0;
            for (ItemStack stack : this.items) {
                int xOffset = pX + (i % 8) * 20;
                int yOffset = pY + (i / 8) * 20;

                guiGraphics.renderFakeItem(stack, xOffset, yOffset);

                if (stack.getCount() > 1) {
                    guiGraphics.renderItemDecorations(font, stack, xOffset, yOffset);
                }
                i++;
            }
        }
    }
}