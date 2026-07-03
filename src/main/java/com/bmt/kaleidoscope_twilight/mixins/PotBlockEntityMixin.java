package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.block.FieryPotBlock;
import com.bmt.kaleidoscope_twilight.mixins.accessor.PotBlockEntityAccessor;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.PotBlockEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotBlockEntity.class)
public abstract class PotBlockEntityMixin {

    @Inject(method = "hasHeatSource", at = @At("HEAD"), cancellable = true)
    private void onHasHeatSource(Level level, CallbackInfoReturnable<Boolean> cir) {
        BlockState state = level.getBlockState(((PotBlockEntity) (Object) this).getBlockPos());
        if (state.getBlock() instanceof FieryPotBlock) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "onShovelHit", at = @At("HEAD"))
    private void onShovelHit(Level level, LivingEntity user, ItemStack shovel, CallbackInfo ci) {
        PotBlockEntity self = (PotBlockEntity) (Object) this;
        BlockState state = level.getBlockState(self.getBlockPos());
        
        if (!(state.getBlock() instanceof FieryPotBlock)) {
            return;
        }

        PotBlockEntityAccessor accessor = (PotBlockEntityAccessor) this;
        int status = self.getStatus();
        
        if (status == PotBlockEntity.PUT_INGREDIENT) {
            if (!self.isEmpty()) {
                accessor.kaleidoscope_twilight$invokeStartCooking(level);
            }
        } else if (status == PotBlockEntity.COOKING) {
            int stirFryCount = accessor.kaleidoscope_twilight$getStirFryCount();
            if (stirFryCount > 0) {
                accessor.kaleidoscope_twilight$setStirFryCount(stirFryCount - 1);
            }
            if (accessor.kaleidoscope_twilight$getStirFryCount() <= 0) {
                accessor.kaleidoscope_twilight$setCurrentTick(0);
            }
        }
    }
}