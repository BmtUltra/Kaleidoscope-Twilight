package com.bmt.kaleidoscope_twilight.common.client.render;

import com.bmt.kaleidoscope_twilight.common.entity.EruptingBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.RenderTypeHelper;
import net.neoforged.neoforge.client.model.data.ModelData;

public class EruptingBlockRenderer extends EntityRenderer<EruptingBlockEntity> {
    private final BlockRenderDispatcher dispatcher;

    public EruptingBlockRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.dispatcher = ctx.getBlockRenderDispatcher();
    }

    @Override
    public void render(EruptingBlockEntity entity, float yaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        BlockState state = entity.getBlockState();
        if (state.getRenderShape() != RenderShape.MODEL) return;

        Level level = entity.level();
        if (state == level.getBlockState(entity.blockPosition()) || state.getRenderShape() == RenderShape.INVISIBLE)
            return;

        poseStack.pushPose();
        BlockPos blockpos = BlockPos.containing(entity.getX(), entity.getBoundingBox().maxY, entity.getZ());
        poseStack.translate(-0.5, 0.0, -0.5);
        BakedModel model = this.dispatcher.getBlockModel(state);

        for (RenderType renderType : model.getRenderTypes(state, RandomSource.create(state.getSeed(entity.blockPosition())), ModelData.EMPTY)) {
            this.dispatcher.getModelRenderer().tesselateBlock(
                    level,
                    this.dispatcher.getBlockModel(state),
                    state,
                    blockpos,
                    poseStack,
                    bufferSource.getBuffer(RenderTypeHelper.getMovingBlockRenderType(renderType)),
                    false,
                    RandomSource.create(),
                    state.getSeed(entity.blockPosition()),
                    OverlayTexture.NO_OVERLAY,
                    ModelData.EMPTY,
                    renderType
            );
        }
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EruptingBlockEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}