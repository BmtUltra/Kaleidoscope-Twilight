package com.bmt.kaleidoscope_twilight.common.client.render;

import com.bmt.kaleidoscope_twilight.common.entity.GiantSwordEntity;
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

public class GiantSwordRender extends EntityRenderer<GiantSwordEntity> {
    private static final float SCALE = 16.0F;

    private final ItemRenderer itemRenderer;

    public GiantSwordRender(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    protected int getBlockLightLevel(GiantSwordEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public void render(GiantSwordEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        float spin = (entity.tickCount + partialTick) * 12.0F;
        poseStack.mulPose(Axis.YP.rotationDegrees(spin));
        poseStack.scale(SCALE, SCALE, SCALE);
        poseStack.mulPose(Axis.ZP.rotationDegrees(-135.0F));

        this.itemRenderer.renderStatic(
                entity.getItem(),
                ItemDisplayContext.GROUND,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                bufferSource,
                entity.level(),
                entity.getId()
        );
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(GiantSwordEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
