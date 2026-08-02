package com.bmt.kaleidoscope_twilight.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.util.KeyframeUtils;
import net.minecraft.util.Mth;

public class DieAnimation implements IAnimation {

    @Override
    public void apply(UmbralSunflowerModel model, float time, float netHeadYaw) {
        float[] rootRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.3333F, 0.8333F, 1.6667F},
                new float[][]{{0, 0, 0}, {-7.5F, 0, 0}, {-45.0F, 0, 0}, {-77.5F, 0, 0}});
        model.bone2.xRot = rootRot[0] * Mth.DEG_TO_RAD;
        float[] rootPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.3333F, 0.8333F, 1.6667F},
                new float[][]{{0, -1, 1}, {0, -1, 4}, {0, -7, 19}, {0, -18, 19}});
        model.bone2.x = rootPos[0];
        model.bone2.y = -2.0F - rootPos[1];
        model.bone2.z = rootPos[2];

        float[] headRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.6667F, 0.8333F, 1.0F, 1.1667F, 1.3333F, 1.6667F, 2.0F},
                new float[][]{
                        {26.83696F, -20.69734F, -7.95831F},
                        {7.75669F, -15.70329F, -7.70157F},
                        {-50.20318F, -18.20031F, -7.82994F},
                        {-38.16304F, -20.69734F, -7.95831F},
                        {-53.35386F, -32.13273F, 11.80623F},
                        {-68.86232F, -34.03626F, 28.73866F},
                        {-53.15542F, -23.98736F, 18.17577F},
                        {-56.74164F, -3.88957F, -2.95F},
                        {-58.99272F, -13.2515F, 12.02103F}
                });
        model.head.xRot = headRot[0] * Mth.DEG_TO_RAD;
        model.head.yRot = headRot[1] * Mth.DEG_TO_RAD;
        model.head.zRot = headRot[2] * Mth.DEG_TO_RAD;

        float[] bodyRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.3333F, 1.6667F, 2.0F},
                new float[][]{
                        {-17.5F, 0, 0},
                        {-18.29388F, -16.66577F, 5.41615F},
                        {-17.94228F, 14.30029F, -4.58465F},
                        {-17.43069F, -4.77234F, 1.48451F}
                });
        model.body.xRot = bodyRot[0] * Mth.DEG_TO_RAD;
        model.body.yRot = bodyRot[1] * Mth.DEG_TO_RAD;
        model.body.zRot = bodyRot[2] * Mth.DEG_TO_RAD;
        model.body.y = 11.0F;

        float[] rightArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.3333F, 1.6667F, 2.0F},
                new float[][]{
                        {0.66871F, -2.92513F, 51.61609F},
                        {25.60322F, 4.88559F, 21.32931F},
                        {-26.89678F, 4.88559F, 21.32931F},
                        {-28.18032F, -17.35819F, 32.8843F}
                });
        model.rightArm.xRot = rightArmRot[0] * Mth.DEG_TO_RAD;
        model.rightArm.yRot = rightArmRot[1] * Mth.DEG_TO_RAD;
        model.rightArm.zRot = rightArmRot[2] * Mth.DEG_TO_RAD;

        float[] leftArmRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.3333F, 1.6667F, 2.0F},
                new float[][]{
                        {10.47479F, 17.22575F, -14.36612F},
                        {10.23772F, -12.3071F, -19.70462F},
                        {-29.76228F, -12.3071F, -19.70462F},
                        {-40.26972F, -41.38216F, 2.59641F}
                });
        model.leftArm.xRot = leftArmRot[0] * Mth.DEG_TO_RAD;
        model.leftArm.yRot = leftArmRot[1] * Mth.DEG_TO_RAD;
        model.leftArm.zRot = leftArmRot[2] * Mth.DEG_TO_RAD;
        float[] leftArmPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.8333F},
                new float[][]{{0, 0, 0}, {0, 0, 3}});
        model.leftArm.z = leftArmPos[2];

        float[] rightLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.6667F, 0.8333F, 1.0F, 1.1667F},
                new float[][]{
                        {10.7198F, -18.62164F, 3.80606F},
                        {10.17181F, 3.51499F, 7.89562F},
                        {10.21538F, -6.32795F, 6.12751F},
                        {7.89604F, -13.75866F, 5.09378F},
                        {7.81287F, 11.01408F, 8.48464F}
                });
        model.rightLeg.xRot = rightLegRot[0] * Mth.DEG_TO_RAD;
        model.rightLeg.yRot = rightLegRot[1] * Mth.DEG_TO_RAD;
        model.rightLeg.zRot = rightLegRot[2] * Mth.DEG_TO_RAD;
        float[] rightLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.5F, 0.6667F, 1.3333F, 1.8333F},
                new float[][]{{0, 0, 0}, {0, -1, -1}, {0, 0, -1}, {0, 0, 0}, {0, 0, 1}});
        model.rightLeg.y = -rightLegPos[1];
        model.rightLeg.z = rightLegPos[2];

        float[] leftLegRot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.6667F, 0.8333F, 1.0F},
                new float[][]{
                        {12.31594F, 2.15393F, -9.76758F},
                        {12.41834F, -7.61516F, -11.90917F},
                        {12.31594F, 2.15393F, -9.76758F},
                        {12.50221F, -10.05629F, -12.45501F}
                });
        model.leftLeg.xRot = leftLegRot[0] * Mth.DEG_TO_RAD;
        model.leftLeg.yRot = leftLegRot[1] * Mth.DEG_TO_RAD;
        model.leftLeg.zRot = leftLegRot[2] * Mth.DEG_TO_RAD;
        float[] leftLegPos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 1.1667F, 1.3333F},
                new float[][]{{0, 0, 0}, {0, -1, 0}, {0, 1, 0}});
        model.leftLeg.y = -leftLegPos[1];

        model.bone4.xRot = 15.0F * Mth.DEG_TO_RAD;
        model.bone4.yRot = 0;
        model.bone4.zRot = 0;
        model.bone4.y = 0;

        float[] bone5Rot = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1667F, 0.3333F, 0.5F, 0.6667F, 0.8333F, 1.6667F, 2.0F},
                new float[][]{
                        {-29.39534F, 16.84816F, 22.95306F},
                        {-22.59547F, 79.7427F, 40.82571F},
                        {-31.52987F, 37.30133F, 70.87666F},
                        {-42.04063F, -17.55267F, 110.83854F},
                        {-354.4764F, -13.44338F, 431.54855F},
                        {-290.26702F, -13.61904F, 368.15163F},
                        {-271.41697F, 51.84905F, 362.19597F},
                        {-296.41697F, 51.84905F, 362.19597F}
                });
        float[] bone5Pos = KeyframeUtils.lerpKeyframes(time,
                new float[]{0.0F, 0.1667F, 0.6667F, 0.8333F, 1.6667F, 2.0F},
                new float[][]{{1, -1, -1}, {9.54F, -10, -7.06F}, {0.54F, -8.04F, -2.65F}, {-2, -6, -1}, {-3, 4, 3}, {-3, 4, 5}});
        model.setBone5Pose(bone5Rot[0], bone5Rot[1], bone5Rot[2], bone5Pos[0], bone5Pos[1], bone5Pos[2]);
    }
}