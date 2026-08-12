package com.bmt.kaleidoscope_twilight.common.client.render;

import com.bmt.kaleidoscope_twilight.common.blockentity.FieryPotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FieryPotBlockEntityRender implements BlockEntityRenderer<FieryPotBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public FieryPotBlockEntityRender(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(FieryPotBlockEntity pot, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        RandomSource source = RandomSource.create(pot.getSeed());
        FieryPotBlockEntity.StirFryAnimationData data = pot.animationData;
        long time = System.currentTimeMillis() - data.timestamp;

        if (data.preSeed == -1L) {
            data.preSeed = pot.getSeed();
        }
        if (data.preSeed != pot.getSeed()) {
            data.preSeed = pot.getSeed();
            if (time > 1000) {
                data.timestamp = System.currentTimeMillis();
                data.randomHeights = new float[9];
                for (int i = 0; i < 9; i++) {
                    data.randomHeights[i] = 0.25f + source.nextFloat() * 1;
                }
            }
        }

        ItemRenderer itemRenderer = this.context.getItemRenderer();
        int rotation = pot.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).get2DDataValue() * 90;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.1, 0.5);
        poseStack.mulPose(Axis.YN.rotationDegrees(rotation));
        poseStack.mulPose(Axis.XN.rotationDegrees(90));
        poseStack.scale(0.5f, 0.5f, 0.5f);

        boolean showInputs = pot.getStatus() != FieryPotBlockEntity.FINISHED && pot.getStatus() != FieryPotBlockEntity.BURNT;
        if (showInputs || pot.hasCarrier()) {
            List<ItemStack> items = pot.getInputs();
            for (int i = 0; i < items.size(); i++) {
                ItemStack item = items.get(i);
                if (!item.isEmpty()) {
                    renderItem(pot, poseStack, buffer, packedLight, packedOverlay, source, i, time, data, itemRenderer, item);
                    poseStack.translate(0, 0, 0.025);
                }
            }
        } else {
            renderItem(pot, poseStack, buffer, packedLight, packedOverlay, source, 0, time, data, itemRenderer, pot.getResult());
        }

        poseStack.popPose();
    }

    private void renderItem(FieryPotBlockEntity pot, PoseStack poseStack, MultiBufferSource buffer,
                            int packedLight, int packedOverlay, RandomSource source, int index,
                            long time, FieryPotBlockEntity.StirFryAnimationData data,
                            ItemRenderer itemRenderer, ItemStack item) {
        poseStack.pushPose();

        int count = 90 + source.nextInt(90);
        poseStack.mulPose(Axis.ZN.rotationDegrees(index * count));
        if (time < 1000) {
            poseStack.translate(0, 0, data.randomHeights[index] * Mth.sin(Mth.PI * time / 1000f));
            poseStack.mulPose(Axis.XN.rotationDegrees(720f / 1000 * time));
        }
        if (pot.getStatus() == FieryPotBlockEntity.BURNT) {
            int tick = pot.getCurrentTick();
            int burntLevel = Mth.clamp(tick / 25, 0, 16);
            packedLight = OverlayTexture.u(burntLevel);
        }

        itemRenderer.render(item, ItemDisplayContext.FIXED, false, poseStack, buffer, packedLight, packedOverlay, itemRenderer.getModel(item, null, null, 0));

        poseStack.popPose();
    }
}