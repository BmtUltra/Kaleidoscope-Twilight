package com.bmt.kaleidoscope_twilight.common.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.common.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class DeflectAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1787F, 0.5958F},
                new float[][]{{26.83696F, -20.69734F, -7.95831F}, {-0.62056F, 1.80128F, -8.21217F}, {26.83696F, -20.69734F, -7.95831F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0894F, 0.1787F, 0.5064F, 0.5958F},
                new float[][]{{23.1712F, -12.21153F, 57.70596F}, {-24.3287F, -5.3045F, 73.98679F}, {-84.3287F, -5.3045F, 73.98679F}, {-84.33F, -5.3F, 73.99F}, {23.1712F, -12.21153F, 57.70596F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;
        float[] rightArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0894F, 0.5064F, 0.5958F},
                new float[][]{{0, 0, 0}, {0, 2, 0}, {0, 2, 0}, {0, 0, 0}});
        model.rightArm.x = -5.0F + rightArmPos[0];
        model.rightArm.y = -rightArmPos[1];
        model.rightArm.z = rightArmPos[2];

        model.leftArm.xRot = 10.47479F * Mth.DEG_TO_RAD;
        model.leftArm.yRot = 17.22575F * Mth.DEG_TO_RAD;
        model.leftArm.zRot = -14.36612F * Mth.DEG_TO_RAD;

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1787F, 0.5064F, 0.5958F},
                new float[][]{{10.7198F, -18.62164F, 3.80606F}, {7.64785F, -0.03695F, 14.41803F}, {10.72F, -18.62F, 3.81F}, {10.7198F, -18.62164F, 3.80606F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1787F, 0.5064F, 0.5958F},
                new float[][]{{12.31594F, 2.15393F, -9.76758F}, {15.43413F, -36.78112F, -19.62438F}, {15.43F, -36.78F, -19.62F}, {12.31594F, 2.15393F, -9.76758F}});
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;

        float[] bone4Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0894F, 0.5064F, 0.5958F},
                new float[][]{{15.0F, 0.0F, 0.0F}, {19.27896F, -38.3808F, -12.25294F}, {19.28F, -38.38F, -12.25F}, {15.0F, 0.0F, 0.0F}});
        model.bone4.xRot = bone4Rot[0] * Mth.DEG_TO_RAD;
        model.bone4.yRot = bone4Rot[1] * Mth.DEG_TO_RAD;
        model.bone4.zRot = bone4Rot[2] * Mth.DEG_TO_RAD;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1787F, 0.2383F, 0.2979F, 0.3575F, 0.417F, 0.5064F, 0.5958F},
                new float[][]{{-16.52823F, 8.9283F, 0.54872F}, {-142.30832F, 13.44387F, 127.42415F}, {-131.60139F, 70.54072F, 165.29176F}, {-41.98037F, 93.08162F, 246.07696F}, {-7.058F, 11.94914F, 325.63576F}, {-167.77369F, 38.17607F, 469.80009F}, {-167.77F, 38.18F, 469.8F}, {-16.52823F, 8.9283F, 0.54872F}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], 1.0F, -1.0F, -1.0F);
    }
}