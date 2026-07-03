package com.bmt.kaleidoscope_twilight.mixins.accessor;

import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.PotBlockEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PotBlockEntity.class)
public interface PotBlockEntityAccessor {
    @Invoker("startCooking")
    void kaleidoscope_twilight$invokeStartCooking(Level level);

    @Accessor("stirFryCount")
    int kaleidoscope_twilight$getStirFryCount();

    @Accessor("stirFryCount")
    void kaleidoscope_twilight$setStirFryCount(int count);

    @Accessor("currentTick")
    void kaleidoscope_twilight$setCurrentTick(int tick);
}