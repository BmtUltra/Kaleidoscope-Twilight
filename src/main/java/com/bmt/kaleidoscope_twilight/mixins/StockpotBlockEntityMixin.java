package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.block.FieryStockPotBlock;
import com.bmt.kaleidoscope_twilight.blockentity.FieryStockpotBlockEntity;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.mixins.accessor.StockpotBlockEntityAccessor;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StockpotBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.StockpotBlockEntity;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopecookery.util.BlockDrop;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = StockpotBlockEntity.class, remap = false)
public abstract class StockpotBlockEntityMixin {

    @Inject(method = "hasHeatSource", at = @At("HEAD"), cancellable = true, remap = false)
    private void onHasHeatSource(Level level, CallbackInfoReturnable<Boolean> cir) {
        BlockPos pos = ((StockpotBlockEntity) (Object) this).getBlockPos();
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof FieryStockPotBlock) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lcom/github/ysbbbbbb/kaleidoscopecookery/blockentity/kitchen/StockpotBlockEntity;setRecipe(Lnet/minecraft/world/level/Level;)V", shift = At.Shift.AFTER))
    private void onTickAfterSetRecipe(Level level, CallbackInfo ci) {
        StockpotBlockEntity self = (StockpotBlockEntity) (Object) this;
        BlockPos pos = self.getBlockPos();
        BlockState state = level.getBlockState(pos);
        
        if (!(state.getBlock() instanceof FieryStockPotBlock)) {
            return;
        }

        if (self instanceof FieryStockpotBlockEntity) {
            ((StockpotBlockEntityAccessor) self).kaleidoscope_twilight$setCurrentTick(FieryStockpotBlockEntity.getCookingTime());
        }
    }

    @Inject(method = "onLitClick", at = @At("HEAD"), cancellable = true)
    private void onLitClick(Level level, LivingEntity user, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        StockpotBlockEntity self = (StockpotBlockEntity) (Object) this;
        BlockPos pos = self.getBlockPos();
        BlockState blockState = level.getBlockState(pos);
        
        if (!(blockState.getBlock() instanceof FieryStockPotBlock)) {
            if (stack.is(KTItems.FIERY_STOCKPOT_LID.get())) {
                cir.setReturnValue(false);
            }
            return;
        }
        
        boolean hasLid = self.hasLid();

        if (!hasLid && stack.is(KTItems.FIERY_STOCKPOT_LID.get())) {
            self.setLidItem(stack.split(1));
            self.setChanged();
            level.setBlockAndUpdate(pos, blockState.setValue(StockpotBlock.HAS_LID, true));
            user.playSound(SoundEvents.LANTERN_PLACE, 0.5F, 0.5F);
            if (self instanceof FieryStockpotBlockEntity fierySelf) {
                fierySelf.resetLidTicks();
            }
            cir.setReturnValue(true);
            return;
        }
        
        if (!hasLid && stack.is(ModItems.STOCKPOT_LID.get())) {
            cir.setReturnValue(false);
            return;
        }
        
        if (hasLid && !self.getLidItem().isEmpty() && self.getLidItem().is(KTItems.FIERY_STOCKPOT_LID.get())) {
            ItemStack lid = self.getLidItem().copy();
            self.setLidItem(ItemStack.EMPTY);
            if (stack.isEmpty()) {
                user.setItemInHand(InteractionHand.MAIN_HAND, lid);
            } else {
                BlockDrop.popResource(level, pos, 0.5, lid);
            }
            self.setChanged();
            level.setBlockAndUpdate(pos, blockState.setValue(StockpotBlock.HAS_LID, false));
            user.playSound(SoundEvents.LANTERN_BREAK, 0.5F, 0.5F);
            if (self instanceof FieryStockpotBlockEntity fierySelf) {
                fierySelf.resetLidTicks();
            }
            cir.setReturnValue(true);
        }
    }
}