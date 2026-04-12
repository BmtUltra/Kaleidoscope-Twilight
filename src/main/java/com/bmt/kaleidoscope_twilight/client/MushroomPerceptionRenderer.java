package com.bmt.kaleidoscope_twilight.client;

import com.bmt.kaleidoscope_twilight.init.KTEffects;
import com.bmt.kaleidoscope_twilight.util.TagUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = "kaleidoscope_twilight", value = Dist.CLIENT)
public class MushroomPerceptionRenderer {

    @SubscribeEvent
    public static void onRenderLevelStage(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            return;
        }

        if (!mc.player.hasEffect(KTEffects.MUSHROOM_PERCEPTION)) {
            return;
        }

        var effectInstance = mc.player.getEffect(KTEffects.MUSHROOM_PERCEPTION);
        if (effectInstance == null) {
            return;
        }

        int amplifier = effectInstance.getAmplifier();
        int range = 14 + (amplifier * 8);

        PoseStack poseStack = event.getPoseStack();
        Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        MultiBufferSource.BufferSource bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(CustomRenderTypes.NO_DEPTH_LINES);

        BlockPos playerPos = mc.player.blockPosition();

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = playerPos.offset(x, y, z);
                    BlockState blockState = mc.level.getBlockState(pos);

                    if (blockState.is(TagUtil.Blocks.CHEST_LIKE)) {
                        AABB aabb = new AABB(pos).inflate(0.002);
                        LevelRenderer.renderLineBox(poseStack, vertexConsumer, aabb, 1.0f, 1.0f, 1.0f, 0.8f);
                    }
                }
            }
        }
        poseStack.popPose();
        bufferSource.endBatch(CustomRenderTypes.NO_DEPTH_LINES);
    }
}