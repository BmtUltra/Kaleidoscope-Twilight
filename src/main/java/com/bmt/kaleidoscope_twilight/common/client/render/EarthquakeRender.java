package com.bmt.kaleidoscope_twilight.common.client.render;

import com.bmt.kaleidoscope_twilight.common.entity.EarthquakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;

public class EarthquakeRender extends EntityRenderer<EarthquakeEntity> {

    public EarthquakeRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(EarthquakeEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(EarthquakeEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}