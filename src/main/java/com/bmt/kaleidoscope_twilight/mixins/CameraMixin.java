package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class CameraMixin {

    @Shadow
    private Entity entity;

    @Inject(method = "getMaxZoom", at = @At("HEAD"), cancellable = true)
    private void kaleidoscope_twilight$phantomCameraZoom(double maxZoom, CallbackInfoReturnable<Double> cir) {
        if (this.entity instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(KTEffects.PHANTOM.get())) {
                cir.setReturnValue(maxZoom);
                cir.cancel();
            }
        }
    }
}