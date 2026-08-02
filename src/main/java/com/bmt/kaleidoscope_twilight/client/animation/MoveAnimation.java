package com.bmt.kaleidoscope_twilight.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class MoveAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] rootRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.3743F, 0.998F},
                new float[][]{{20.28356F, 9.39129F, 3.45118F}, {20.0F, 0, 0}, {20.28356F, 9.39129F, 3.45118F}});
        model.bone2.xRot = rootRot[0] * Mth.DEG_TO_RAD;
        model.bone2.yRot = rootRot[1] * Mth.DEG_TO_RAD;
        model.bone2.zRot = rootRot[2] * Mth.DEG_TO_RAD;
        model.bone2.x = 0;
        model.bone2.y = 0;
        model.bone2.z = -3;

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2745F, 0.7984F, 0.998F},
                new float[][]{{0, 0, 0}, {20.0F, 0, 0}, {5.0F, 0, 0}, {0, 0, 0}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = (netHeadYaw + headRot[1]) * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        float[] headPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2745F, 0.7984F},
                new float[][]{{0, 0, 0}, {0, 0, -1}, {0, 0, 0}});
        model.head.x = headPos[0];
        model.head.y = -headPos[1];
        model.head.z = headPos[2];

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2745F, 0.524F, 0.998F},
                new float[][]{{0, 10.0F, 0}, {10.07022F, 7.06646F, 0.87381F}, {0, -5.0F, 0}, {0, 10.0F, 0}});
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;
        model.body.y = 11.0F;

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.524F, 0.7485F, 0.8483F, 0.9481F, 0.998F},
                new float[][]{{0, 0, 0}, {12.5F, 0, 0}, {-2.5F, 0, 0}, {-2.62116F, 17.48281F, -0.78795F}, {0, 0, 0}, {0, 0, 0}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;
        float[] rightLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.524F, 0.6487F, 0.7485F, 0.8483F, 0.9481F},
                new float[][]{{0, 0, 0}, {0, 0, -2}, {0, 2, -2}, {0, 1, -3}, {0, 0, -1}, {0, 0, 0}});
        model.rightLeg.y = -rightLegPos[1];
        model.rightLeg.z = rightLegPos[2];

        model.leftLeg.xRot = -14.18829F * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = -23.00396F * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = 0.90153F * Mth.DEG_TO_RAD;
        float[] leftLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1747F, 0.3244F, 0.524F, 0.998F},
                new float[][]{{0, 0, 0}, {0, 3, -2}, {1, 1, -4}, {0, 0, 0}, {0, 0, 0}});
        model.leftLeg.x = 3.8F + leftLegPos[0];
        model.leftLeg.y = -leftLegPos[1];
        model.leftLeg.z = leftLegPos[2];

        float[] bone4Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1747F, 0.3493F, 0.7236F, 0.998F},
                new float[][]{{0, 0, 0}, {0, -15.0F, 0}, {0, -20.0F, 0}, {0, 5.71F, 0}, {0, 0, 0}});
        model.bone4.xRot = bone4Rot[0] * Mth.DEG_TO_RAD;
        model.bone4.yRot = bone4Rot[1] * Mth.DEG_TO_RAD;
        model.bone4.zRot = bone4Rot[2] * Mth.DEG_TO_RAD;
        model.bone4.y = 0;
        model.bone4.z = 0;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1248F, 0.7485F, 0.998F},
                new float[][]{{42.39121F, -3.37562F, 38.69066F}, {44.6591F, -15.75082F, 37.07377F}, {37.39121F, -3.37562F, 38.69066F}, {42.39121F, -3.37562F, 38.69066F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;
        float[] rightArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1248F, 0.3493F, 0.7485F, 0.998F},
                new float[][]{{0, 0, 0}, {1, 0, 0.71F}, {0, 0, 2}, {0, 0, 2}, {0, 0, 0}});
        model.rightArm.x = -5.0F + rightArmPos[0];
        model.rightArm.y = -rightArmPos[1];
        model.rightArm.z = rightArmPos[2];

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.7236F, 0.998F},
                new float[][]{{16.66613F, 22.81781F, -5.55225F}, {-16.24738F, 20.89998F, -13.36596F}, {16.66613F, 22.81781F, -5.55225F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.998F},
                new float[][]{{0, 0, 0}, {4.68271F, 15.42943F, -15.84966F}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], 0.0F, 0.0F, 0.0F);
    }
}