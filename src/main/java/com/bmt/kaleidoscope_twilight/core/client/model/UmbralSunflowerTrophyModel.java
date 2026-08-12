package com.bmt.kaleidoscope_twilight.core.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;
import twilightforest.client.model.entity.TrophyBlockModel;

public class UmbralSunflowerTrophyModel implements TrophyBlockModel {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("kaleidoscope_twilight", "textures/entity/umbral_sunflower.png");
    private final ModelPart head;

    public UmbralSunflowerTrophyModel(ModelPart root) {
        this.head = root.getChild("bone2").getChild("Head");
    }

    @Override
    public void setupRotationsForTrophy(float animationProgress, float yRotDeg, float xRotDeg, float unused) {
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.yRot = yRotDeg * Mth.DEG_TO_RAD;
        this.head.xRot = xRotDeg * Mth.DEG_TO_RAD;
        this.head.zRot = 0.0F;
    }

    @Override
    public void renderTrophy(PoseStack poseStack, MultiBufferSource buffer, int light, int overlay, int color, @NotNull ItemDisplayContext context) {
        poseStack.pushPose();
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        this.head.render(poseStack, consumer, light, overlay, color);
        poseStack.popPose();
    }
}
