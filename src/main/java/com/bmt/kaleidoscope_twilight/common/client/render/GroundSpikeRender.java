package com.bmt.kaleidoscope_twilight.common.client.render;

import com.bmt.kaleidoscope_twilight.common.entity.GroundSpikeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class GroundSpikeRender extends EntityRenderer<GroundSpikeEntity> {
    private final ItemRenderer itemRenderer;

    public GroundSpikeRender(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    protected int getBlockLightLevel(GroundSpikeEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public void render(GroundSpikeEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        double drop = entity.getHeightOffset(partialTick);
        if (drop >= 1.0D) {
            return;
        }

        ItemStack item = entity.getItem();

        for (GroundSpikeEntity.Spike spike : entity.getSpikes()) {
            poseStack.pushPose();
            poseStack.translate(spike.x, spike.groundY, spike.z);
            poseStack.mulPose(Axis.YP.rotationDegrees(spike.yawDeg));
            poseStack.mulPose(Axis.XP.rotationDegrees(spike.tiltDeg));
            poseStack.translate(0.0F, (float) (-drop * (1.3D + spike.extraDrop)), 0.0F);
            float scale = 3.0F * spike.scale;
            poseStack.scale(scale, scale, scale);
            poseStack.mulPose(Axis.ZP.rotationDegrees(45.0F));

            this.itemRenderer.renderStatic(
                    item,
                    ItemDisplayContext.GROUND,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    bufferSource,
                    entity.level(),
                    entity.getId()
            );
            poseStack.popPose();
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(GroundSpikeEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
