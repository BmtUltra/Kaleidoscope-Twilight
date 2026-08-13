package com.bmt.kaleidoscope_twilight.mixins;

import com.github.ysbbbbbb.kaleidoscopecookery.item.StockpotLidItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class StockpotLidItemMixin {

    @Shadow
    public abstract ItemStack getItem();

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void onHurt(DamageSource pSource, float pAmount, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemStack = this.getItem();
        if (itemStack.getItem() instanceof StockpotLidItem) {
            cir.setReturnValue(false);
        }
    }
}