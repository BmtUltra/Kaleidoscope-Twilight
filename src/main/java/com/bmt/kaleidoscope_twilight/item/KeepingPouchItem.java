package com.bmt.kaleidoscope_twilight.item;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public class KeepingPouchItem extends Item {
    private static final int MAX_SIZE = 8;
    private static final String TAG_ITEMS = "Items";

    public KeepingPouchItem() {
        super((new Item.Properties()).stacksTo(1));
    }

    public static boolean hasItems(ItemStack pouch) {
        CompoundTag tag = pouch.getTag();
        return tag != null && tag.contains(TAG_ITEMS, Tag.TAG_COMPOUND);
    }

    public static ItemStackHandler getItems(ItemStack pouch) {
        ItemStackHandler handler = new ItemStackHandler(MAX_SIZE);
        CompoundTag tag = pouch.getOrCreateTag();
        if (tag.contains(TAG_ITEMS, Tag.TAG_COMPOUND)) {
            handler.deserializeNBT(tag.getCompound(TAG_ITEMS));
        }
        return handler;
    }

    public static void setItems(ItemStack pouch, ItemStackHandler items) {
        boolean allEmpty = true;
        for (int i = 0; i < items.getSlots(); i++) {
            if (!items.getStackInSlot(i).isEmpty()) {
                allEmpty = false;
                break;
            }
        }
        if (allEmpty) {
            pouch.removeTagKey(TAG_ITEMS);
        } else {
            CompoundTag tag = pouch.getOrCreateTag();
            tag.put(TAG_ITEMS, items.serializeNBT());
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (player.isSecondaryUseActive()) {
            return InteractionResultHolder.pass(itemInHand);
        }
        return InteractionResultHolder.fail(itemInHand);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack pouch, Slot slot, ClickAction action, Player player) {
        if (pouch.getCount() != 1 || action != ClickAction.SECONDARY) {
            return false;
        }
        ItemStack clickItem = slot.getItem();
        if (clickItem.isEmpty()) {
            this.playRemoveOneSound(player);
            removeOne(pouch).ifPresent(stack -> add(pouch, slot.safeInsert(stack)));
        } else if (clickItem.getItem().canFitInsideContainerItems()) {
            int addCount = add(pouch, clickItem, true);
            if (addCount > 0) {
                ItemStack takeout = slot.safeTake(clickItem.getCount(), addCount, player);
                if (!takeout.isEmpty()) {
                    add(pouch, takeout);
                }
                this.playInsertSound(player);
            }
        }
        return true;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pouch, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (pouch.getCount() != 1) {
            return false;
        }
        if (action != ClickAction.SECONDARY || !slot.allowModification(player)) {
            return false;
        }
        if (other.isEmpty()) {
            removeOne(pouch).ifPresent(stack -> {
                this.playRemoveOneSound(player);
                access.set(stack);
            });
        } else {
            int added = add(pouch, other);
            if (added > 0) {
                this.playInsertSound(player);
                other.shrink(added);
            }
        }
        return true;
    }

    private static Optional<ItemStack> removeOne(ItemStack pouch) {
        if (!hasItems(pouch)) {
            return Optional.empty();
        }
        ItemStackHandler items = getItems(pouch);
        for (int i = 0; i < items.getSlots(); i++) {
            ItemStack extractItem = items.extractItem(i, items.getSlotLimit(i), false);
            if (!extractItem.isEmpty()) {
                setItems(pouch, items);
                return Optional.of(extractItem);
            }
        }
        return Optional.empty();
    }

    private static int add(ItemStack pouch, ItemStack item) {
        return add(pouch, item, false);
    }

    private static int add(ItemStack pouch, ItemStack item, boolean simulate) {
        if (item.isEmpty() || !item.getItem().canFitInsideContainerItems()) {
            return 0;
        }
        int totalCount = item.getCount();

        ItemStackHandler items = getItems(pouch);
        ItemStack remaining = ItemHandlerHelper.insertItemStacked(items, item, simulate);

        int addCount = totalCount - (remaining.isEmpty() ? 0 : remaining.getCount());
        if (!simulate && addCount > 0) {
            setItems(pouch, items);
        }
        return addCount;
    }

    private void playRemoveOneSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        if (!hasItems(stack)) {
            return Optional.empty();
        }
        ItemStackHandler items = getItems(stack);
        return Optional.of(new KeepingPouchTooltip(items));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.kaleidoscope_twilight.keeping_pouch").withStyle(ChatFormatting.GRAY));
    }

    public static class KeepingPouchTooltip implements TooltipComponent {
        private final ItemStackHandler items;

        public KeepingPouchTooltip(ItemStackHandler items) {
            this.items = items;
        }

        public ItemStackHandler getItems() {
            return items;
        }
    }
}