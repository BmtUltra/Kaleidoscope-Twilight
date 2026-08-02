package com.bmt.kaleidoscope_twilight.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import net.minecraft.util.Mth;

public class StandbyAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float w = (1.0F - Mth.cos(time * Mth.TWO_PI)) * 0.5F;

        model.head.xRot = Mth.lerp(w, 26.83696F, 33.52668F) * Mth.DEG_TO_RAD;
        model.head.yRot = (netHeadYaw + w * 4.37674F) * Mth.DEG_TO_RAD;
        model.head.zRot = Mth.lerp(w, -7.95831F, -5.40812F) * Mth.DEG_TO_RAD;
        model.head.y = Mth.lerp(w, 0.0F, -1.0F);

        model.body.xRot = Mth.lerp(w, -17.5F, -22.5F) * Mth.DEG_TO_RAD;
        model.body.yRot = 0;
        model.body.zRot = 0;
        model.body.y = 11.0F + Mth.lerp(w, 0.0F, -1.0F);

        model.rightArm.xRot = Mth.lerp(w, 23.1712F, 22.65224F) * Mth.DEG_TO_RAD;
        model.rightArm.yRot = Mth.lerp(w, -12.21153F, -13.18496F) * Mth.DEG_TO_RAD;
        model.rightArm.zRot = Mth.lerp(w, 57.70596F, 60.06644F) * Mth.DEG_TO_RAD;

        model.leftArm.xRot = Mth.lerp(w, 10.47479F, 11.22513F) * Mth.DEG_TO_RAD;
        model.leftArm.yRot = Mth.lerp(w, 17.22575F, 16.75508F) * Mth.DEG_TO_RAD;
        model.leftArm.zRot = Mth.lerp(w, -14.36612F, -11.79875F) * Mth.DEG_TO_RAD;

        model.rightLeg.xRot = Mth.lerp(w, 10.7198F, 9.88014F) * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = Mth.lerp(w, -18.62164F, -19.06873F) * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = Mth.lerp(w, 3.80606F, 6.40511F) * Mth.DEG_TO_RAD;

        model.leftLeg.xRot = Mth.lerp(w, 12.31594F, 7.2127F) * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = Mth.lerp(w, 2.15393F, 2.68507F) * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = Mth.lerp(w, -9.76758F, -12.2127F) * Mth.DEG_TO_RAD;

        model.bone4.xRot = 15.0F * Mth.DEG_TO_RAD;
        model.bone4.yRot = 0;
        model.bone4.zRot = 0;
        model.bone4.y = Mth.lerp(w, 0.0F, -1.0F);

        model.setBone5Pose(
                Mth.lerp(w, -16.52823F, -18.57855F),
                Mth.lerp(w, 8.9283F, 1.77545F),
                Mth.lerp(w, 0.54872F, 3.59778F),
                Mth.lerp(w, 1.0F, 1.5F),
                -1.0F,
                Mth.lerp(w, -1.0F, -1.5F));
    }
}