package com.bmt.kaleidoscope_twilight.core.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.core.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class ResurrectionAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] rootRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.6667F, 0.9583F, 1.2083F, 1.5833F, 2.5132F, 2.76F},
                new float[][]{{0, 0, 0}, {5.0F, 0, 0}, {12.5F, 0, 0}, {14.35885F, 29.21893F, 7.12284F}, {24.35885F, 29.21893F, 7.12284F}, {6.85885F, 29.21893F, 7.12284F}, {6.85885F, 29.21893F, 7.12284F}, {0, 0, 0}});
        model.bone2.xRot = rootRot[0] * Mth.DEG_TO_RAD;
        model.bone2.yRot = rootRot[1] * Mth.DEG_TO_RAD;
        model.bone2.zRot = rootRot[2] * Mth.DEG_TO_RAD;
        float[] rootPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1667F, 0.3333F, 0.375F, 0.5F, 0.625F, 0.6667F, 0.9583F, 1.2083F, 1.5833F},
                new float[][]{{0, -57, 0}, {0, -50, 0}, {0, -50, 0}, {0, -34, 0}, {0, -31, 0}, {0, -22, 0}, {0, -17, 0}, {0, -11, 0}, {0, -8, 0}, {0, -1, 1}});
        model.bone2.x = rootPos[0];
        model.bone2.y = -2.0F - rootPos[1];
        model.bone2.z = rootPos[2];

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.625F, 0.6667F, 1.5833F},
                new float[][]{{0, 0, 0}, {33.40659F, -12.60837F, -8.19252F}, {46.42339F, -19.98077F, -1.14701F}, {26.83696F, -20.69734F, -7.95831F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        float[] headPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 1.5833F},
                new float[][]{{0, -1, 0}, {0, 0, 0}, {0, 0, 3}});
        model.head.x = headPos[0];
        model.head.y = -headPos[1];
        model.head.z = headPos[2];

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 1.2083F, 1.5833F},
                new float[][]{{0, 0, 0}, {0, 25.0F, 0}, {-17.5F, 0, 0}});
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.625F, 0.6667F, 0.9583F, 1.5833F},
                new float[][]{{0, 0, 182.5F}, {0, 0, 167.5F}, {-72.59256F, -43.65747F, 155.2873F}, {-84.86745F, -20.94332F, 120.21003F}, {80.75927F, -58.64818F, -50.04109F}, {23.1712F, -12.21153F, 57.70596F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.625F, 0.6667F, 0.9583F, 1.5833F},
                new float[][]{{0, 0, 0}, {-120.0F, 0, 0}, {-97.5F, 0, 0}, {-70.43162F, -26.12797F, -8.89674F}, {-12.93162F, -26.12797F, -8.89674F}, {10.47479F, 17.22575F, -14.36612F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;
        float[] leftArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.625F, 0.9583F, 1.2083F, 1.5833F, 2.5132F, 2.76F},
                new float[][]{{0, 0, 0}, {0, 6, 0}, {0, 1, 0}, {0, -1, 0}, {-1, -4, 0}, {-1, -1, 2}, {-1, -1, 2}, {0, 0, 0}});
        model.leftArm.x = 5.0F + leftArmPos[0];
        model.leftArm.y = -leftArmPos[1];
        model.leftArm.z = leftArmPos[2];

        float[] bone3Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 1.5833F, 2.5132F, 2.76F},
                new float[][]{{0, 0, 0}, {0, -3, 5}, {0, -3, 5}, {0, 0, 0}});
        model.bone3.x = -1.9F + bone3Pos[0];
        model.bone3.y = 12.0F - bone3Pos[1];
        model.bone3.z = bone3Pos[2];

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9583F, 1.2083F, 1.5833F},
                new float[][]{{0, 0, 0}, {57.5F, 0, 0}, {42.5F, 0, 0}, {10.7198F, -18.62164F, 3.80606F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;
        float[] rightLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 1.2083F, 1.3333F, 1.5833F, 2.5132F, 2.76F},
                new float[][]{{0, 0, 0}, {0, 3, -6}, {0, 6, -7}, {0, 3, -6}, {0, 3, -6}, {0, 0, 0}});
        model.rightLeg.x = rightLegPos[0];
        model.rightLeg.y = -rightLegPos[1];
        model.rightLeg.z = rightLegPos[2];

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9583F, 1.5833F},
                new float[][]{{0, 0, 0}, {35.0F, 0, 0}, {12.31594F, 2.15393F, -9.76758F}});
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;
        float[] leftLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.9583F, 1.2083F, 1.3333F, 1.5F, 1.5833F, 2.5132F, 2.76F},
                new float[][]{{0, 0, 0}, {2, 4, -3}, {0, 4, -3}, {0.33F, 8.5F, -8.92F}, {0.44F, 6, -6.89F}, {1, 4, -5}, {1, 4, -5}, {0, 0, 0}});
        model.leftLeg.x = 3.8F + leftLegPos[0];
        model.leftLeg.y = -leftLegPos[1];
        model.leftLeg.z = leftLegPos[2];

        model.bone4.xRot = 15.0F * Mth.DEG_TO_RAD;
        model.bone4.yRot = 0;
        model.bone4.zRot = 0;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.4167F, 0.5F, 0.625F, 0.6667F, 0.9583F, 1.5833F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {-84.76885F, -2.48302F, 10.83988F}, {-82.01248F, 64.9015F, 12.76007F}, {-39.19928F, 19.47954F, 51.78035F}, {-14.38884F, 49.85271F, 5.03103F}, {-16.52823F, 8.9283F, 0.54872F}});
        float[] bone5Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.3333F, 0.5F, 0.625F, 0.6667F, 1.5833F},
                new float[][]{{0, 0, 0}, {0, -9.5F, 5}, {1, -0.25F, -0.5F}, {0.25F, -0.81F, 2.88F}, {0, 1, 0}, {1, -1, -1}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], bone5Pos[0], bone5Pos[1], bone5Pos[2]);
    }
}