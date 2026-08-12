package com.bmt.kaleidoscope_twilight.common.client.render;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.common.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.common.entity.boss.UmbralSunflower;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class UmbralSunflowerRender extends MobRenderer<UmbralSunflower, UmbralSunflowerModel> {
    private static final ResourceLocation TEXTURE =
            KaleidoscopeTwilight.id("textures/entity/umbral_sunflower.png");

    public UmbralSunflowerRender(EntityRendererProvider.Context context) {
        super(context, new UmbralSunflowerModel(context.bakeLayer(UmbralSunflowerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(UmbralSunflower entity) {
        return TEXTURE;
    }
}