package com.bmt.kaleidoscope_twilight.core.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.core.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class StandAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] rootRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9583F, 1.4583F, 2.125F, 2.35F},
                new float[][]{{0, 0, 0}, {7.5F, 0, 0}, {2.5F, 0, 0}, {2.5F, 0, 0}, {0, 0, 0}});
        model.bone2.xRot = rootRot[0] * Mth.DEG_TO_RAD;
        float[] rootPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 1.125F, 1.2917F, 1.4583F, 2.125F, 2.35F},
                new float[][]{{0, -10, 0}, {0, -10, 0}, {0, -5, 0}, {0, -2, 0}, {0, -1, 2}, {0, -1, 2}, {0, -1, 1}});
        model.bone2.x = rootPos[0];
        model.bone2.y = -2.0F - rootPos[1];
        model.bone2.z = rootPos[2];

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.8333F, 0.875F, 0.9167F, 1.2083F, 1.25F, 1.2917F, 1.4583F, 1.625F, 2.125F, 2.35F},
                new float[][]{{52.5F, 0, 0}, {52.5F, 0, 0}, {67.5F, 0, 0}, {59.68821F, 9.23186F, -3.86032F}, {72.59173F, 9.98831F, -1.44357F}, {77.59173F, 9.98831F, -1.44357F}, {62.5F, 0, 0}, {-25.0F, 0, 0}, {25.35952F, -17.34551F, -2.35666F}, {26.13331F, -21.84978F, -4.66159F}, {26.13331F, -21.84978F, -4.66159F}, {26.83696F, -20.69734F, -7.95831F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        float[] headPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.8333F, 1.125F, 1.2917F, 1.625F},
                new float[][]{{0, -2, -5}, {0, -2, -5}, {0, -2, -5}, {0, -2, -5}, {0, 0, -1}, {0, 0, 3}});
        model.head.x = headPos[0];
        model.head.y = -headPos[1];
        model.head.z = headPos[2];

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 1.2917F, 1.625F, 2.125F, 2.35F},
                new float[][]{{25.0F, 0, 0}, {25.0F, 0, 0}, {2.5F, 0, 0}, {-20.0F, 0, 0}, {-20.0F, 0, 0}, {-17.5F, 0, 0}});
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;
        model.body.y = 11.0F;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9167F, 0.9583F, 1.625F, 1.7083F, 2.125F, 2.35F},
                new float[][]{{0, 0, 0}, {7.5F, 0, 0}, {-12.5F, 0, 0}, {18.99192F, 53.69197F, 18.39544F}, {3.99192F, 53.69197F, 18.39544F}, {3.99192F, 53.69197F, 18.39544F}, {23.1712F, -12.21153F, 57.70596F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9167F, 1.625F, 1.7083F, 2.125F, 2.35F},
                new float[][]{{0, 0, 0}, {-5.0F, 0, 0}, {5.96434F, -13.71988F, -4.01664F}, {7.72342F, -12.82478F, -11.66777F}, {7.72342F, -12.82478F, -11.66777F}, {10.47479F, 17.22575F, -14.36612F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;

        float[] bone3Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 1.125F, 2.125F, 2.35F},
                new float[][]{{-90.0F, 0, 0}, {-90.0F, 0, 0}, {-62.5F, 0, 0}, {-62.5F, 0, 0}, {0, 0, 0}});
        model.bone3.xRot = bone3Rot[0] * Mth.DEG_TO_RAD;

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9167F, 0.9583F, 1.2917F, 2.125F, 2.35F},
                new float[][]{{0, 0, 0}, {0, 0, 5.0F}, {32.5F, 0, 5.0F}, {75.0F, 0, 5.0F}, {75.0F, 0, 5.0F}, {10.7198F, -18.62164F, 3.80606F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;
        float[] rightLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9583F, 1.2917F},
                new float[][]{{0, 0, 0}, {0, 0, -3}, {0, 0, 0}});
        model.rightLeg.z = rightLegPos[2];

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 1.0417F, 1.2917F, 1.75F, 2.125F, 2.35F},
                new float[][]{{0, 0, 0}, {7.5F, 0, -22.5F}, {62.5F, 0, -22.5F}, {72.5F, 0, -22.5F}, {72.5F, 0, -22.5F}, {12.31594F, 2.15393F, -9.76758F}});
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;

        float[] bone4Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 1.125F, 1.2083F, 1.2917F, 1.3333F, 1.4583F, 1.625F, 2.125F, 2.35F},
                new float[][]{{0, 0, 0}, {0, 0, 10.0F}, {0, 0, -25.0F}, {0, 0, -20.0F}, {-7.5F, 0, 7.5F}, {-7.43656F, -0.9762F, 0.06344F}, {12.56344F, -0.9762F, 0.06344F}, {12.56344F, -0.9762F, 0.06344F}, {15.0F, 0, 0}});
        model.bone4.xRot = bone4Rot[0] * Mth.DEG_TO_RAD;
        model.bone4.yRot = bone4Rot[1] * Mth.DEG_TO_RAD;
        model.bone4.zRot = bone4Rot[2] * Mth.DEG_TO_RAD;
        float[] bone4Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 1.2917F, 1.3333F, 1.625F, 2.125F, 2.35F},
                new float[][]{{0, -2, -6}, {0, -2, -6}, {0, -2, 1}, {0, -1, 1}, {0, -1, 3}, {0, -1, 3}, {0, 0, 3}});
        model.bone4.x = bone4Pos[0];
        model.bone4.y = -bone4Pos[1];
        model.bone4.z = bone4Pos[2];

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.9167F, 1.0F, 1.25F, 1.4583F, 1.75F, 2.125F, 2.35F},
                new float[][]{{-190.91807F, -12.69317F, -77.44843F}, {-168.88516F, -12.67122F, -102.74204F}, {-154.55149F, -9.4087F, -141.12287F}, {-191.38516F, -12.67122F, -102.74204F}, {-186.54332F, 17.0039F, -109.31959F}, {-232.27914F, -4.86321F, -124.87732F}, {-262.65801F, 13.33056F, -17.4774F}, {-262.65801F, 13.33056F, -17.4774F}, {-376.52823F, 8.9283F, 0.54872F}});
        float[] bone5Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{2.125F, 2.35F},
                new float[][]{{0, 0, 0}, {1, -1, -1}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], bone5Pos[0], bone5Pos[1], bone5Pos[2]);
    }
}