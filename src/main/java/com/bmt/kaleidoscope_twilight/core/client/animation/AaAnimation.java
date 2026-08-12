package com.bmt.kaleidoscope_twilight.core.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.core.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class AaAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1948F, 0.3895F, 0.5008F},
                new float[][]{{26.83696F, -20.69734F, -7.95831F}, {13.59829F, 3.44557F, -1.51879F}, {25.01001F, -2.6727F, 0.93379F}, {26.83696F, -20.69734F, -7.95831F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;
        float[] headPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0556F, 0.1669F, 0.3061F, 0.473F},
                new float[][]{{0, 0, 3}, {0, 0, 1}, {0, 0, 0}, {-1, 0, 0.45F}, {0, 0, 1}});
        model.head.x = headPos[0];
        model.head.y = -headPos[1];
        model.head.z = headPos[2];

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0835F, 0.1669F, 0.4174F, 0.5008F},
                new float[][]{{-17.5F, 0, 0}, {-0.01257F, -39.19107F, -2.04842F}, {-0.01257F, -39.19107F, -2.04842F}, {-0.00977F, -4.19107F, -2.05566F}, {-17.5F, 0, 0}});
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0835F, 0.1669F, 0.1948F, 0.3617F, 0.4452F, 0.473F, 0.5008F},
                new float[][]{{23.1712F, -12.21153F, 57.70596F}, {-145.34644F, 79.37068F, -42.98168F}, {-98.72894F, -1.07859F, 12.0651F}, {-118.52065F, -71.47116F, 39.15946F}, {-118.52065F, -71.47116F, 39.15946F}, {-107.61742F, -59.91181F, 27.26322F}, {-145.34644F, 79.37068F, -42.98168F}, {23.1712F, -12.21153F, 57.70596F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;
        float[] rightArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0835F, 0.2504F, 0.4452F, 0.473F, 0.5008F},
                new float[][]{{0, 0, 0}, {0, 0, -5}, {0, 0, -6}, {0, 0, -5}, {0, 0, -5}, {0, 0, 0}});
        model.rightArm.x = -5.0F + rightArmPos[0];
        model.rightArm.y = -rightArmPos[1];
        model.rightArm.z = rightArmPos[2];

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0835F, 0.1669F, 0.5008F},
                new float[][]{{10.47479F, 17.22575F, -14.36612F}, {32.5034F, -30.8198F, -34.73156F}, {32.5034F, -30.8198F, -34.73156F}, {10.47479F, 17.22575F, -14.36612F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;
        float[] leftArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0835F, 0.1669F, 0.4174F, 0.5008F},
                new float[][]{{0, 0, 0}, {-2, 0, 1}, {-2, 0, 1}, {0, 1, -1}, {0, 0, 0}});
        model.leftArm.x = 5.0F + leftArmPos[0];
        model.leftArm.y = -leftArmPos[1];
        model.leftArm.z = leftArmPos[2];

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0556F, 0.1113F, 0.1391F, 0.3895F},
                new float[][]{{10.7198F, -18.62164F, 3.80606F}, {10.7198F, -18.62164F, 3.80606F}, {28.2198F, -18.62164F, 3.80606F}, {9.96785F, -18.62164F, 3.80606F}, {10.7198F, -18.62164F, 3.80606F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;
        float[] rightLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0556F, 0.1113F, 0.1391F, 0.3895F},
                new float[][]{{0, 0, 0}, {0, 0, 0}, {0, 2, -4}, {0, -0.75F, -3.5F}, {0, 0, 0}});
        model.rightLeg.y = -rightLegPos[1];
        model.rightLeg.z = rightLegPos[2];

        model.leftLeg.xRot = 12.31594F * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = 2.15393F * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = -9.76758F * Mth.DEG_TO_RAD;
        float[] leftLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1948F, 0.3061F, 0.3339F, 0.5008F},
                new float[][]{{0, 0, 0}, {0, 0, 2}, {0, 1, 1.27F}, {0, 0, 0}, {0, 0, 0}});
        model.leftLeg.x = 3.8F + leftLegPos[0];
        model.leftLeg.y = -leftLegPos[1];
        model.leftLeg.z = leftLegPos[2];

        float[] bone4Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.4174F, 0.5008F},
                new float[][]{{15.0F, 0, 0}, {15.69284F, 16.88547F, 4.6653F}, {15.0F, 0, 0}});
        model.bone4.xRot = bone4Rot[0] * Mth.DEG_TO_RAD;
        model.bone4.yRot = bone4Rot[1] * Mth.DEG_TO_RAD;
        model.bone4.zRot = bone4Rot[2] * Mth.DEG_TO_RAD;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.0835F, 0.3617F, 0.5008F},
                new float[][]{{-16.52823F, 8.9283F, 0.54872F}, {-104.16583F, 62.53198F, 49.26499F}, {-104.16583F, 62.53198F, 49.26499F}, {-16.52823F, 8.9283F, 0.54872F}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], 1.0F, -1.0F, -1.0F);
    }
}