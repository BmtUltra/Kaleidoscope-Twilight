package com.bmt.kaleidoscope_twilight.client.render;

import com.bmt.kaleidoscope_twilight.block.trophy.UmbralSunflowerTrophyBlock;
import com.bmt.kaleidoscope_twilight.block.trophy.WallUmbralSunflowerTrophyBlock;
import com.bmt.kaleidoscope_twilight.blockentity.trophy.UmbralSunflowerTrophyBlockEntity;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerTrophyModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import twilightforest.enums.BossVariant;

public class TrophyRenderer implements BlockEntityRenderer<UmbralSunflowerTrophyBlockEntity> {
    private final UmbralSunflowerTrophyModel model;

    public TrophyRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new UmbralSunflowerTrophyModel(context.bakeLayer(UmbralSunflowerModel.LAYER_LOCATION));
    }

    @Override
    public void render(UmbralSunflowerTrophyBlockEntity entity, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        BlockState state = entity.getBlockState();
        boolean onWall = state.getBlock() instanceof WallUmbralSunflowerTrophyBlock;
        Direction direction = onWall ? state.getValue(WallUmbralSunflowerTrophyBlock.FACING) : null;
        float yRot = 22.5F * (onWall
                ? (2 + direction.get2DDataValue()) * 4
                : state.getValue(UmbralSunflowerTrophyBlock.ROTATION));
        twilightforest.client.renderer.block.TrophyRenderer.render(direction, yRot, this.model, BossVariant.NAGA,
                0.0F, poseStack, bufferSource, packedLight, ItemDisplayContext.NONE);
    }
}
