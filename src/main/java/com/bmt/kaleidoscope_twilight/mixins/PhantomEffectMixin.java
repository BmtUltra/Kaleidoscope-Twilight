package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class PhantomEffectMixin {

    @Inject(method = "getCollisionShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;",
            at = @At("RETURN"),
            cancellable = true)
    private void kaleidoscope_twilight$phantomPhase(BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (!(context instanceof EntityCollisionContext entityContext)) {
            return;
        }

        if (!(entityContext.getEntity() instanceof LivingEntity living)) {
            return;
        }

        if (!living.hasEffect(KTEffects.PHANTOM)) {
            return;
        }

        BlockPos onPos = BlockPos.containing(living.position().add(0, -0.3, 0));

        if (pos.equals(onPos)) {
            return;
        }
        cir.setReturnValue(Shapes.empty());
    }
}