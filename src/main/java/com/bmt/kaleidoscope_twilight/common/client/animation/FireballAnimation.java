package com.bmt.kaleidoscope_twilight.common.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.common.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class FireballAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] rootPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 1.75F, 1.8333F},
                new float[][]{{0, -1, 1}, {0, 10, 1}, {1, 4, 1}, {0, -1, 1}});
        model.bone2.x = rootPos[0];
        model.bone2.y = -2.0F - rootPos[1];
        model.bone2.z = rootPos[2];

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 0.5F, 1.75F, 1.8333F},
                new float[][]{{26.83696F, -20.69734F, -7.95831F}, {26.83696F, -20.69734F, -7.95831F}, {-38.16304F, -20.69734F, -7.95831F}, {-38.16F, -20.7F, -7.96F}, {26.83696F, -20.69734F, -7.95831F}});
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;

        model.body.xRot = -17.5F * Mth.DEG_TO_RAD;
        model.body.yRot = 0;
        model.body.zRot = 0;
        model.body.y = 11.0F;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 0.5F, 1.75F, 1.8333F},
                new float[][]{{23.1712F, -12.21153F, 57.70596F}, {-13.2467F, -22.61747F, 152.87899F}, {-23.66923F, -11.16897F, 187.56602F}, {-23.67F, -11.17F, 187.57F}, {23.1712F, -12.21153F, 57.70596F}});
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 0.5F, 1.75F, 1.8333F},
                new float[][]{{10.47479F, 17.22575F, -14.36612F}, {-125.95046F, -50.28766F, -19.04029F}, {-157.25242F, -13.17579F, -44.20473F}, {-157.25F, -13.18F, -44.2F}, {10.47479F, 17.22575F, -14.36612F}});
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 1.75F, 1.8333F},
                new float[][]{{10.7198F, -18.62164F, 3.80606F}, {18.07012F, -11.64494F, -23.78934F}, {18.07F, -11.64F, -23.79F}, {10.7198F, -18.62164F, 3.80606F}});
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 0.5F, 1.75F, 1.8333F},
                new float[][]{{12.31594F, 2.15393F, -9.76758F}, {34.81594F, 2.15393F, -9.76758F}, {34.35617F, -6.39387F, 2.57861F}, {34.36F, -6.39F, 2.58F}, {12.31594F, 2.15393F, -9.76758F}});
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;
        float[] leftLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.25F, 1.75F, 1.8333F},
                new float[][]{{0, 0, 0}, {2, 3, -3}, {2, 3, -3}, {0, 0, 0}});
        model.leftLeg.x = 3.8F + leftLegPos[0];
        model.leftLeg.y = -leftLegPos[1];
        model.leftLeg.z = leftLegPos[2];

        model.bone4.xRot = 15.0F * Mth.DEG_TO_RAD;
        model.bone4.yRot = 0;
        model.bone4.zRot = 0;
        model.bone4.y = 0;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 1.75F, 1.8333F},
                new float[][]{{-16.52823F, 8.9283F, 0.54872F}, {-8.99749F, 41.97502F, -2.3884F}, {-9.0F, 41.98F, -2.39F}, {-16.52823F, 8.9283F, 0.54872F}});
        float[] bone5Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 1.75F, 1.8333F},
                new float[][]{{1, -1, -1}, {-2, -1, -1}, {-2, -1, -1}, {1, -1, -1}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], bone5Pos[0], bone5Pos[1], bone5Pos[2]);
    }
}