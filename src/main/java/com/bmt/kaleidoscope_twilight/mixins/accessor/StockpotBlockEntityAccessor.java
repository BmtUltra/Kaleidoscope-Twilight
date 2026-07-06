package com.bmt.kaleidoscope_twilight.mixins.accessor;

import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.StockpotBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = StockpotBlockEntity.class, remap = false)
public interface StockpotBlockEntityAccessor {
    @Accessor("currentTick")
    void kaleidoscope_twilight$setCurrentTick(int currentTick);
}
