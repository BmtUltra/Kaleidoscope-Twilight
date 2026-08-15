package com.bmt.kaleidoscope_twilight.mixins.accessor;

import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.CraftingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CraftingMenu.class)
public interface CraftingMenuAccessor {
    @Accessor("craftSlots")
    CraftingContainer getCraftSlots();
}