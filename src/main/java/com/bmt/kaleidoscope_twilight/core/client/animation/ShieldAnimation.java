package com.bmt.kaleidoscope_twilight.core.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.core.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class ShieldAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.375F, 1.75F},
                new float[][]{{26.837F, -20.6973F, -7.9583F}, {25.9811F, 15.4142F, 9.5603F}, {26.837F, -20.6973F, -7.9583F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        model.head.x = 0.0F;
        model.head.y = 0.0F;
        model.head.z = 3.0F;

        model.body.xRot = -17.5F * Mth.DEG_TO_RAD;
        model.body.yRot = 0.0F;
        model.body.zRot = 0.0F;

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F},
                new float[][]{{10.7198F, -18.6216F, 3.8061F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F},
                new float[][]{{12.3159F, 2.1539F, -9.7676F}});
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;

        model.bone4.xRot = 0.0F;
        model.bone4.yRot = 0.0F;
        model.bone4.zRot = 0.0F;
        model.bone4.x = 0.0F;
        model.bone4.y = 0.0F;
        model.bone4.z = 3.0F;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.375F, 1.0833F, 1.5417F, 1.75F},
                new float[][]{{23.1712F, -12.2115F, 57.706F}, {-70.17F, -8.0518F, 81.8098F}, {-35.17F, -8.0518F, 81.8098F}, {-7.67F, -8.0518F, 81.8098F}, {23.1712F, -12.2115F, 57.706F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.375F, 0.7917F, 0.9583F, 1.0417F, 1.0833F, 1.1667F, 1.25F, 1.3333F, 1.375F, 1.75F},
                new float[][]{
                        {-16.5282F, 8.9283F, 0.5487F},
                        {178.7099F, -6.4174F, -177.8027F},
                        {194.0802F, 27.1643F, -175.1782F},
                        {192.8099F, 42.301F, -171.0762F},
                        {197.8401F, 46.7102F, -166.9033F},
                        {179.2317F, 56.075F, -178.9599F},
                        {145.0729F, 31.7566F, -165.5699F},
                        {120.4742F, 24.8578F, -115.3238F},
                        {120.4742F, 24.8578F, -115.3238F},
                        {0.9119F, 13.9276F, 0.6504F},
                        {-16.5282F, 8.9283F, 0.5487F}
                });
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], 1.0F, 1.0F, 1.0F);

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.375F, 0.6667F, 1.0833F, 1.5417F, 1.75F},
                new float[][]{{10.4748F, 17.2258F, -14.3661F}, {-102.0252F, 17.2258F, -14.3661F}, {-114.373F, 36.7117F, -19.4657F}, {-102.9985F, -20.2361F, -11.0479F}, {12.0015F, -20.2361F, -11.0479F}, {10.4748F, 17.2258F, -14.3661F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;
    }
}
