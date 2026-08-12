package com.bmt.kaleidoscope_twilight.common.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.common.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class FlashAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] rootRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F, 0.4704F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {25.0F, 0, 0}, {17.5F, 0, 0}});
        model.bone2.xRot = rootRot[0] * Mth.DEG_TO_RAD;
        float[] rootPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F, 0.4032F, 0.4704F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, -37}, {0, 0, -43}, {0, -3, -39}});
        model.bone2.x = rootPos[0];
        model.bone2.y = -2.0F - rootPos[1];
        model.bone2.z = rootPos[2];

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F, 0.4704F, 0.5824F},
                new float[][]{{17.56274F, -4.76804F, -1.50701F}, {17.56274F, -4.76804F, -1.50701F}, {-4.93726F, -4.76804F, -1.50701F}, {25.41037F, -9.07262F, 5.69634F}, {0.41037F, -9.07262F, 5.69634F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        float[] headPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F},
                new float[][]{{0, -1, 2}, {0, -1, 2}, {0, -1, 3}});
        model.head.x = headPos[0];
        model.head.y = -headPos[1];
        model.head.z = headPos[2];

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.336F, 0.4704F, 0.56F},
                new float[][]{{-15.0F, 0, 0}, {-15.0F, 0, 0}, {12.5F, 0, 0}, {13.74538F, -24.3683F, -5.7632F}, {16.7357F, -41.26755F, -11.21792F}});
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;
        model.body.y = 11.0F;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F, 0.336F, 0.4256F, 0.56F},
                new float[][]{{54.78729F, -3.74196F, 44.00446F}, {54.78729F, -3.74196F, 44.00446F}, {-32.59629F, 17.16409F, -12.99652F}, {-155.09629F, 17.16409F, -12.99652F}, {0.73085F, -10.58522F, -29.57353F}, {20.73085F, -10.58522F, -29.57353F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F, 0.336F, 0.4032F, 0.4256F, 0.56F},
                new float[][]{{25.0F, 0, -25.0F}, {25.0F, 0, -25.0F}, {61.51653F, 25.49671F, 13.42684F}, {15.58869F, -4.52698F, -31.0213F}, {38.08869F, -4.52698F, -31.0213F}, {60.17283F, -0.19632F, -48.10608F}, {59.01893F, 14.92781F, -57.01074F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;
        float[] leftArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.4256F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {-2, 4, 5}});
        model.leftArm.x = 5.0F + leftArmPos[0];
        model.leftArm.y = -leftArmPos[1];
        model.leftArm.z = leftArmPos[2];

        float[] bone3Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.336F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {27.5F, 0, 0}});
        model.bone3.xRot = bone3Rot[0] * Mth.DEG_TO_RAD;
        float[] bone3Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.336F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {0, -3, 7}});
        model.bone3.x = -1.9F + bone3Pos[0];
        model.bone3.y = 12.0F - bone3Pos[1];
        model.bone3.z = bone3Pos[2];

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.336F},
                new float[][]{{15.99002F, 24.67468F, 13.56624F}, {15.99002F, 24.67468F, 13.56624F}, {-10.19241F, 20.41152F, -25.01982F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;
        float[] rightLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.336F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {0, 2, 0}});
        model.rightLeg.y = -rightLegPos[1];

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.336F},
                new float[][]{{17.25002F, 2.99318F, -9.54589F}, {17.25002F, 2.99318F, -9.54589F}, {-2.32194F, -7.27554F, -9.10739F}});
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;
        float[] leftLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F, 0.336F, 0.3584F},
                new float[][]{{0, 0, -1}, {0, 0, -1}, {0, 4, -4}, {1, 5, -2}, {0.93F, 9.67F, -1.93F}});
        model.leftLeg.x = 3.8F + leftLegPos[0];
        model.leftLeg.y = -leftLegPos[1];
        model.leftLeg.z = leftLegPos[2];

        model.bone4.xRot = -20.0F * Mth.DEG_TO_RAD;
        model.bone4.yRot = 0;
        model.bone4.zRot = 0;
        model.bone4.y = 0;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.2464F, 0.2688F, 0.336F, 0.4256F},
                new float[][]{{41.66378F, -27.24969F, -26.97102F}, {41.66378F, -27.24969F, -26.97102F}, {96.68181F, 11.00576F, -150.93196F}, {-119.18751F, -22.70283F, 131.00142F}, {-278.66951F, -22.23355F, 291.42781F}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], 1.0F, 0.0F, 0.0F);
    }
}