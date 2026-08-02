package com.bmt.kaleidoscope_twilight.client.render;

import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerTrophyModel;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import twilightforest.client.event.ClientGameEvents;
import twilightforest.client.renderer.block.TrophyRenderer;
import twilightforest.config.TFConfig;
import twilightforest.enums.BossVariant;

public class TrophyItemRender extends BlockEntityWithoutLevelRenderer {
    private static final ModelResourceLocation TROPHY_SPRITE_MODEL =
            ModelResourceLocation.standalone(ResourceLocation.fromNamespaceAndPath("twilightforest", "item/trophy"));

    private UmbralSunflowerTrophyModel trophyModel;

    public TrophyItemRender() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.trophyModel = new UmbralSunflowerTrophyModel(
                Minecraft.getInstance().getEntityModels().bakeLayer(UmbralSunflowerModel.LAYER_LOCATION));
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack,
                             MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (this.trophyModel == null) {
            this.onResourceManagerReload(Minecraft.getInstance().getResourceManager());
        }

        Minecraft mc = Minecraft.getInstance();
        float animationTime = mc.isPaused() ? 0.0F : ClientGameEvents.time + mc.getTimer().getRealtimeDeltaTicks();

        if (context == ItemDisplayContext.GUI) {
            BakedModel spriteModel = mc.getModelManager().getModel(TROPHY_SPRITE_MODEL);
            MultiBufferSource.BufferSource guiBuffer = mc.renderBuffers().bufferSource();
            poseStack.pushPose();
            Lighting.setupForFlatItems();
            poseStack.translate(0.5F, 0.5F, -1.5F);
            mc.getItemRenderer().render(TrophyRenderer.stack, ItemDisplayContext.GUI, false, poseStack, guiBuffer,
                    15728880, OverlayTexture.NO_OVERLAY,
                    spriteModel.applyTransform(ItemDisplayContext.GUI, poseStack, false));
            poseStack.popPose();
            guiBuffer.endBatch();
            Lighting.setupFor3DItems();
            poseStack.pushPose();
            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));
            poseStack.mulPose(Axis.YN.rotationDegrees(
                    TFConfig.rotateTrophyHeadsGui && !mc.isPaused() ? ClientGameEvents.time % 360 : -45.0F));
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            poseStack.translate(0.0F, 0.25F, 0.0F);
            TrophyRenderer.render(null, 180.0F, this.trophyModel, BossVariant.NAGA,
                    animationTime, poseStack, bufferSource, packedLight, context);
            poseStack.popPose();
        } else {
            TrophyRenderer.render(null, 180.0F, this.trophyModel, BossVariant.NAGA,
                    animationTime, poseStack, bufferSource, packedLight, context);
        }
    }
}
