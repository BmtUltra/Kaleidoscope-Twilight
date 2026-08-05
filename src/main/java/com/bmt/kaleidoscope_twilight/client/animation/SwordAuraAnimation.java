package com.bmt.kaleidoscope_twilight.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class SwordAuraAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        model.bone2.x = 0.0F;
        model.bone2.y = -1.0F;
        model.bone2.z = 1.0F;

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.698F, 1.508F, 1.591F, 1.731F, 1.871F, 1.898F, 2.010F},
                new float[][]{
                        {26.837F, -20.697F, -7.958F},
                        {5.050F, 4.125F, 4.105F},
                        {10.080F, -4.938F, -0.127F},
                        {8.731F, -0.940F, -4.777F},
                        {9.101F, 16.352F, -2.051F},
                        {9.101F, 16.352F, -2.051F},
                        {8.731F, -0.940F, -4.777F},
                        {26.837F, -20.697F, -7.958F}
                });
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        model.head.x = 0.0F;
        model.head.y = 0.0F;
        model.head.z = 3.0F;

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{1.508F, 1.591F, 1.731F, 1.871F, 1.898F, 2.010F},
                new float[][]{
                        {-17.5F, 0.0F, 0.0F},
                        {-18.294F, -16.666F, 5.416F},
                        {-20.005F, -28.480F, 9.849F},
                        {-20.005F, -28.480F, 9.849F},
                        {-17.642F, 7.151F, -2.267F},
                        {-17.5F, 0.0F, 0.0F}
                });
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;
        model.body.y = 11.0F;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.307F, 0.475F, 0.586F, 0.698F, 0.838F, 1.005F, 1.508F, 1.591F, 1.731F, 1.871F, 1.898F, 2.010F},
                new float[][]{
                        {23.171F, -12.212F, 57.706F},
                        {21.485F, -15.065F, 64.844F},
                        {24.460F, -10.294F, 53.795F},
                        {21.485F, -15.065F, 64.844F},
                        {24.460F, -10.294F, 53.795F},
                        {21.485F, -15.065F, 64.844F},
                        {24.460F, -10.294F, 53.795F},
                        {23.171F, -12.212F, 57.706F},
                        {-120.334F, 5.859F, 88.382F},
                        {-127.834F, 5.859F, 88.382F},
                        {-127.834F, 5.859F, 88.382F},
                        {-45.334F, 5.859F, 88.382F},
                        {23.171F, -12.212F, 57.706F}
                });
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{1.508F, 1.731F, 1.871F, 2.010F},
                new float[][]{
                        {10.475F, 17.226F, -14.366F},
                        {12.853F, 0.543F, -7.596F},
                        {12.853F, 0.543F, -7.596F},
                        {10.475F, 17.226F, -14.366F}
                });
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{1.508F, 1.591F, 1.731F, 1.871F, 2.010F},
                new float[][]{
                        {0.0F, 0.0F, 0.0F},
                        {0.0F, 0.0F, 1.0F},
                        {0.0F, 0.0F, 3.0F},
                        {0.0F, 0.0F, 3.0F},
                        {0.0F, 0.0F, 0.0F}
                });
        model.leftArm.x = 5.0F + leftArmPos[0];
        model.leftArm.y = leftArmPos[1];
        model.leftArm.z = leftArmPos[2];

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{1.508F, 2.010F},
                new float[][]{
                        {10.720F, -18.622F, 3.806F},
                        {10.720F, -18.622F, 3.806F}
                });
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;

        float[] rightLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{1.508F, 1.898F, 2.010F},
                new float[][]{
                        {0.0F, 0.0F, 0.0F},
                        {0.0F, 0.0F, -2.0F},
                        {0.0F, 0.0F, 0.0F}
                });
        model.rightLeg.x = rightLegPos[0];
        model.rightLeg.y = rightLegPos[1];
        model.rightLeg.z = rightLegPos[2];

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{1.508F, 1.591F, 1.731F, 1.871F, 2.010F},
                new float[][]{
                        {12.316F, 2.154F, -9.768F},
                        {20.462F, -14.292F, -15.806F},
                        {32.962F, -14.292F, -15.806F},
                        {32.962F, -14.292F, -15.806F},
                        {12.316F, 2.154F, -9.768F}
                });
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;

        float[] leftLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{1.508F, 1.591F, 1.731F, 1.871F, 1.898F, 2.010F},
                new float[][]{
                        {0.0F, 0.0F, 0.0F},
                        {0.0F, 0.0F, -1.0F},
                        {0.0F, 0.0F, -2.0F},
                        {0.0F, 0.0F, -2.0F},
                        {0.0F, -1.0F, 1.0F},
                        {0.0F, 0.0F, 0.0F}
                });
        model.leftLeg.x = 3.8F + leftLegPos[0];
        model.leftLeg.y = -leftLegPos[1];
        model.leftLeg.z = leftLegPos[2];
        model.bone4.xRot = 15.0F * Mth.DEG_TO_RAD;
        model.bone4.yRot = 0.0F;
        model.bone4.zRot = 0.0F;
        model.bone4.y = 0.0F;
        model.bone4.z = 3.0F;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.503F, 1.508F, 1.591F, 1.898F},
                new float[][]{
                        {-16.528F, 8.928F, 0.549F},
                        {-162.047F, 3.685F, 157.423F},
                        {-16.528F, 8.928F, 0.549F},
                        {-182.899F, -20.801F, 178.386F},
                        {-358.170F, -6.660F, 354.209F}
                });

        float[] bone5Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 1.508F, 2.010F},
                new float[][]{
                        {1.0F, -1.0F, -1.0F},
                        {1.0F, -1.0F, -1.0F},
                        {1.0F, -1.0F, -1.0F}
                });
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], bone5Pos[0], bone5Pos[1], bone5Pos[2]);
    }
}
