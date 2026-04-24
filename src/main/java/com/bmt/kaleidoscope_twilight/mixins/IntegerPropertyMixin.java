package com.bmt.kaleidoscope_twilight.mixins;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IntegerProperty.class)
public class IntegerPropertyMixin {

    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static void kaleidoscope_twilight$modifyMaxCount(String name, int min, int max, CallbackInfoReturnable<IntegerProperty> cir) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().contains("DrinkBlock") && element.getMethodName().equals("<init>")) {
                if (min == 1 && max == 1) {
                    cir.setReturnValue(IntegerProperty.create(name, min, 2));
                    cir.cancel();
                }
                break;
            }
        }
    }
}