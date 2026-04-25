package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopecookery.block.food.FoodBiteBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoodBiteBlock.class)
public class FoodBiteBlockMixin {

    @Inject(method = "useWithoutItem", at = @At("HEAD"), cancellable = true)
    private void kaleidoscope_twilight$removeDestroyParticles(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        FoodBiteBlock self = (FoodBiteBlock) (Object) this;
        int bites = state.getValue(self.getBites());

        if (bites >= self.getMaxBites() && kaleidoscopeTwilight_1_21_1_NeoForge$isTargetCake(self)) {
            level.removeBlock(pos, false);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    @Unique
    private static boolean kaleidoscopeTwilight_1_21_1_NeoForge$isTargetCake(FoodBiteBlock block) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(block);
        return key.equals(KaleidoscopeTwilight.id("glacier_cake")) ||
               key.equals(KaleidoscopeTwilight.id("witchcraft_cake"));
    }
}