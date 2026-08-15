package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.mixins.accessor.InventoryAccessor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(Inventory.class)
public class InventoryMixin {

    @Shadow @Final
    public Player player;

    @Unique
    private final Map<int[], ItemStack> kaleidoscope_twilight$reservedPouches = new HashMap<>();

    @Inject(method = "dropAll", at = @At("HEAD"))
    private void kaleidoscope_twilight$reserveKeepingPouches(CallbackInfo ci) {
        List<List<ItemStack>> compartments = ((InventoryAccessor) this).getCompartments();
        if (player.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            return;
        }

        for (int listIndex = 0; listIndex < compartments.size(); listIndex++) {
            List<ItemStack> list = compartments.get(listIndex);
            for (int itemIndex = 0; itemIndex < list.size(); itemIndex++) {
                ItemStack itemstack = list.get(itemIndex);
                if (!itemstack.isEmpty() && itemstack.getItem() == KTItems.KEEPING_POUCH_ITEM.get()) {
                    kaleidoscope_twilight$reservedPouches.put(new int[]{listIndex, itemIndex}, itemstack);
                    list.set(itemIndex, ItemStack.EMPTY);
                }
            }
        }
    }

    @Inject(method = "dropAll", at = @At("RETURN"))
    private void kaleidoscope_twilight$restoreKeepingPouches(CallbackInfo ci) {
        List<List<ItemStack>> compartments = ((InventoryAccessor) this).getCompartments();
        kaleidoscope_twilight$reservedPouches.forEach((position, stack) -> compartments.get(position[0]).set(position[1], stack));
        kaleidoscope_twilight$reservedPouches.clear();
    }
}