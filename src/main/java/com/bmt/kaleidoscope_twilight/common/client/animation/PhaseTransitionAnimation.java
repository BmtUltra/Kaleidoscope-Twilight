package com.bmt.kaleidoscope_twilight.common.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.common.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class PhaseTransitionAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] rootPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F},
                new float[][]{{0.0F, -1.0F, 1.0F}});
        model.bone2.x = rootPos[0];
        model.bone2.y = -2.0F - rootPos[1];
        model.bone2.z = rootPos[2];

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 0.625F, 1.5F, 1.5417F, 1.7917F},
                new float[][]{
                        {26.837F, -20.6973F, -7.9583F},
                        {6.54F, -1.86F, 1.28F},
                        {39.8755F, -12.4775F, -5.3788F},
                        {39.8755F, -12.4775F, -5.3788F},
                        {6.54F, -1.86F, 1.28F},
                        {26.837F, -20.6973F, -7.9583F}
                });
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;

        float[] headPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 1.5417F, 1.7917F},
                new float[][]{
                        {0.0F, 0.0F, 3.0F},
                        {0.0F, 0.0F, 1.0F},
                        {0.0F, 0.0F, 1.0F},
                        {0.0F, 0.0F, 3.0F}
                });
        model.head.x = headPos[0];
        model.head.y = -headPos[1];
        model.head.z = headPos[2];

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 1.5417F, 1.7917F},
                new float[][]{
                        {-17.5F, 0.0F, 0.0F},
                        {-5.2413F, -17.4313F, 1.5741F},
                        {-5.2413F, -17.4313F, 1.5741F},
                        {-17.5F, 0.0F, 0.0F}
                });
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 0.375F, 0.5417F, 1.5417F, 1.7083F, 1.7917F},
                new float[][]{
                        {23.1712F, -12.2115F, 57.706F},
                        {-113.8356F, -11.9689F, 5.8835F},
                        {-151.3356F, -11.9689F, 5.8835F},
                        {-58.8356F, -11.9689F, 5.8835F},
                        {-58.8356F, -11.9689F, 5.8835F},
                        {-151.3356F, -11.9689F, 5.8835F},
                        {23.1712F, -12.2115F, 57.706F}
                });
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] rightArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 1.5417F, 1.7917F},
                new float[][]{
                        {0.0F, 0.0F, 0.0F},
                        {0.0F, 1.0F, -4.0F},
                        {0.0F, 1.0F, -4.0F},
                        {0.0F, 0.0F, 0.0F}
                });
        model.rightArm.x = rightArmPos[0];
        model.rightArm.y = -rightArmPos[1];
        model.rightArm.z = rightArmPos[2];

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 0.5833F, 1.5F, 1.5417F, 1.7917F},
                new float[][]{
                        {10.4748F, 17.2258F, -14.3661F},
                        {-34.99F, 2.46F, -17.07F},
                        {24.2849F, 6.6392F, -26.1857F},
                        {24.2849F, 6.6392F, -26.1857F},
                        {-34.99F, 2.46F, -17.07F},
                        {10.4748F, 17.2258F, -14.3661F}
                });
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 1.5417F, 1.7917F},
                new float[][]{
                        {10.7198F, -18.6216F, 3.8061F},
                        {10.72F, -18.62F, 3.81F},
                        {10.72F, -18.62F, 3.81F},
                        {10.7198F, -18.6216F, 3.8061F}
                });
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 1.5417F, 1.7917F},
                new float[][]{
                        {12.3159F, 2.1539F, -9.7676F},
                        {0.9685F, -10.5534F, -2.6536F},
                        {0.9685F, -10.5534F, -2.6536F},
                        {12.3159F, 2.1539F, -9.7676F}
                });
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;

        float[] bone4Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 1.5417F, 1.7917F},
                new float[][]{
                        {15.0F, 0.0F, 0.0F},
                        {15.0F, 0.0F, 0.0F},
                        {15.0F, 0.0F, 0.0F},
                        {15.0F, 0.0F, 0.0F}
                });
        model.bone4.xRot = bone4Rot[0] * Mth.DEG_TO_RAD;
        model.bone4.yRot = bone4Rot[1] * Mth.DEG_TO_RAD;
        model.bone4.zRot = bone4Rot[2] * Mth.DEG_TO_RAD;

        float[] bone4Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5417F, 1.5417F, 1.7917F},
                new float[][]{
                        {0.0F, 0.0F, 3.0F},
                        {0.0F, 0.0F, 3.0F},
                        {0.0F, 0.0F, 3.0F},
                        {0.0F, 0.0F, 3.0F}
                });
        model.bone4.x = bone4Pos[0];
        model.bone4.y = -bone4Pos[1];
        model.bone4.z = bone4Pos[2];

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 0.375F, 0.4167F, 0.5417F, 1.5417F, 1.5833F, 1.625F, 1.6667F, 1.7083F, 1.7917F},
                new float[][]{
                        {-16.5282F, 8.9283F, 0.5487F},
                        {-8.1854F, 103.3393F, -11.0219F},
                        {-128.1444F, 131.6655F, -132.5233F},
                        {-42.7169F, 78.5541F, -124.4021F},
                        {37.0062F, 60.888F, -43.6023F},
                        {37.0062F, 60.888F, -43.6023F},
                        {8.0953F, 70.8149F, -61.6292F},
                        {-32.5976F, 87.5375F, -92.3956F},
                        {-33.7788F, 61.2234F, -145.8447F},
                        {-128.1444F, 131.6655F, -132.5233F},
                        {-16.5282F, 8.9283F, 0.5487F}
                });

        float[] bone5Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.4167F, 0.5417F, 1.5417F, 1.6667F, 1.7917F},
                new float[][]{
                        {1.0F, -1.0F, -1.0F},
                        {1.0F, 0.0F, 2.0F},
                        {1.0F, 0.0F, 2.0F},
                        {1.0F, 0.0F, 2.0F},
                        {1.0F, 0.0F, 2.0F},
                        {1.0F, -1.0F, -1.0F}
                });
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], bone5Pos[0], bone5Pos[1], bone5Pos[2]);
    }
}
