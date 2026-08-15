package com.bmt.kaleidoscope_twilight.common.inventory;

import com.bmt.kaleidoscope_twilight.common.item.KeepingPouchItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class KeepingPouchMenu extends ShulkerBoxMenu {
    private final int pouchSlotIndex;

    public KeepingPouchMenu(int containerId, Inventory inventory, FriendlyByteBuf buf) {
        this(containerId, inventory);
    }

    public KeepingPouchMenu(int containerId, Inventory inventory) {
        super(containerId, inventory, new PouchContainer(findPouch(inventory.player)));
        this.pouchSlotIndex = findPouchSlot(findPouch(inventory.player));
    }

    @Override
    public void clicked(int slotId, int button, @NotNull ClickType clickType, @NotNull Player player) {
        if (slotId == this.pouchSlotIndex) {
            return;
        }
        super.clicked(slotId, button, clickType, player);
    }

    private int findPouchSlot(ItemStack pouch) {
        if (pouch.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.slots.size(); i++) {
            if (this.slots.get(i).getItem() == pouch) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        if (index == this.pouchSlotIndex) {
            return ItemStack.EMPTY;
        }
        return super.quickMoveStack(player, index);
    }

    private static ItemStack findPouch(Player player) {
        if (player.getMainHandItem().getItem() instanceof KeepingPouchItem) {
            return player.getMainHandItem();
        }
        if (player.getOffhandItem().getItem() instanceof KeepingPouchItem) {
            return player.getOffhandItem();
        }
        return ItemStack.EMPTY;
    }

    private static class PouchContainer extends SimpleContainer {
        private final ItemStack pouch;
        private final boolean loading;

        private PouchContainer(ItemStack pouch) {
            super(KeepingPouchItem.MAX_SIZE);
            this.pouch = pouch;
            if (!pouch.isEmpty()) {
                ItemStackHandler stored = KeepingPouchItem.getItems(pouch);
                for (int i = 0; i < Math.min(stored.getSlots(), this.getContainerSize()); i++) {
                    this.setItem(i, stored.getStackInSlot(i));
                }
            }
            this.loading = false;
        }

        @Override
        public void setChanged() {
            super.setChanged();
            if (!this.loading) {
                this.save();
            }
        }

        private void save() {
            if (this.pouch.isEmpty()) {
                return;
            }
            ItemStackHandler handler = new ItemStackHandler(this.getContainerSize());
            for (int i = 0; i < this.getContainerSize(); i++) {
                handler.setStackInSlot(i, this.getItem(i));
            }
            KeepingPouchItem.setItems(this.pouch, handler);
        }

        @Override
        public boolean canPlaceItem(int index, @NotNull ItemStack stack) {
            return KeepingPouchItem.canPouchAccept(stack);
        }

        @Override
        public boolean stillValid(@NotNull Player player) {
            return !this.pouch.isEmpty()
                    && (player.getMainHandItem() == this.pouch || player.getOffhandItem() == this.pouch);
        }
    }
}