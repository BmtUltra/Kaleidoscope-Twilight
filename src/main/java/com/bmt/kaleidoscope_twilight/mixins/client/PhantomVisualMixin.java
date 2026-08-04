package com.bmt.kaleidoscope_twilight.mixins.client;

import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class PhantomVisualMixin {

    @Inject(method = "isViewBlocking(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z",
            at = @At("HEAD"), cancellable = true)
    private void kaleidoscope_twilight$preventViewBlocking(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        Player player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(KTEffects.PHANTOM)) {
            cir.setReturnValue(false);
        }
    }
}