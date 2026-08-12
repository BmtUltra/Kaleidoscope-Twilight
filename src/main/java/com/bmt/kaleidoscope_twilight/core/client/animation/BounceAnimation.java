package com.bmt.kaleidoscope_twilight.core.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.core.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class BounceAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0361F, 0.1443F},
                new float[][]{{26.83696F, -20.69734F, -7.95831F}, {17.69334F, -7.201F, -1.19468F}, {17.40778F, -3.42922F, 17.5578F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        float[] headPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0361F},
                new float[][]{{0, 0, 3}, {0, 0, 1}});
        model.head.z = headPos[2];

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0361F},
                new float[][]{{-17.5F, 0, 0}, {-10.14168F, -7.15102F, 2.26708F}});
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0361F, 0.1082F, 0.1443F},
                new float[][]{{23.1712F, -12.21153F, 57.70596F}, {-6.8288F, -12.21153F, 57.70596F}, {-44.3288F, -12.21153F, 57.70596F}, {-48.71824F, 27.60691F, 115.8102F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1082F},
                new float[][]{{10.47479F, 17.22575F, -14.36612F}, {-106.35203F, 31.69215F, -18.74144F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;
        float[] leftArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1082F},
                new float[][]{{0, 0, 0}, {0, -1, -2}});
        model.leftArm.x = 5.0F + leftArmPos[0];
        model.leftArm.y = -leftArmPos[1];
        model.leftArm.z = leftArmPos[2];

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1443F},
                new float[][]{{10.7198F, -18.62164F, 3.80606F}, {14.58602F, 18.61037F, 24.21116F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0361F, 0.1443F},
                new float[][]{{12.31594F, 2.15393F, -9.76758F}, {2.92235F, -28.23861F, -15.54003F}, {-5.34651F, -23.1879F, -12.47418F}});
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1082F, 0.1443F},
                new float[][]{{-16.52823F, 8.9283F, 0.54872F}, {-198.34391F, 22.93037F, 164.07935F}, {-156.065F, 15.34288F, 258.55121F}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], 1.0F, -1.0F, -1.0F);
    }
}