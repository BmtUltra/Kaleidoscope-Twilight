package com.bmt.kaleidoscope_twilight.mixins.accessor;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(Inventory.class)
public interface InventoryAccessor {
    @Accessor("compartments")
    List<List<ItemStack>> getCompartments();
}