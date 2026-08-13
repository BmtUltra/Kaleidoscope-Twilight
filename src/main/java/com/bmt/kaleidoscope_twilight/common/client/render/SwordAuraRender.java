package com.bmt.kaleidoscope_twilight.common.client.render;

import com.bmt.kaleidoscope_twilight.common.client.model.SwordAuraModel;
import com.bmt.kaleidoscope_twilight.common.entity.SwordAuraEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SwordAuraRender extends EntityRenderer<SwordAuraEntity> {
    private final SwordAuraModel<SwordAuraEntity> model;

    public SwordAuraRender(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SwordAuraModel<>(context.bakeLayer(SwordAuraModel.LAYER_LOCATION));
    }

    @Override
    public void render(SwordAuraEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        if (entity.isPlayerAura()) {
            float yRot = Mth.lerp(partialTick, entity.yRotO, entity.getYRot()) + 180.0F;
            float xRot = Mth.lerp(partialTick, entity.xRotO, entity.getXRot());
            poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
            poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
            poseStack.translate(0.0D, -22.0D / 16.0D, 0.0D);
            model.renderToBuffer(poseStack, bufferSource.getBuffer(model.renderType(getTextureLocation(entity))),
                    packedLight, 0, -1);
        } else {
            poseStack.translate(0.0D, -2.2D, 0.0D);
            float yRot = Mth.lerp(partialTick, entity.yRotO, entity.getYRot()) + 180.0F;
            poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
            poseStack.scale(2.5F, 2.5F, 2.5F);
            model.renderToBuffer(poseStack, bufferSource.getBuffer(model.renderType(getTextureLocation(entity))),
                    packedLight, 0, -1);
        }
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(SwordAuraEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("kaleidoscope_twilight", "textures/entity/sword_aura.png");
    }
}
