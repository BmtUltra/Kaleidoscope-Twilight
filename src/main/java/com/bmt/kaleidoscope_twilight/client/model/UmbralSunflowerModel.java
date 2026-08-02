package com.bmt.kaleidoscope_twilight.client.model;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.client.animation.*;
import com.bmt.kaleidoscope_twilight.entity.UmbralSunflower;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class UmbralSunflowerModel extends HierarchicalModel<UmbralSunflower> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            KaleidoscopeTwilight.id("umbral_sunflower"), "main");

    public final ModelPart bone2;
    public final ModelPart head;
    public final ModelPart body;
    public final ModelPart bone3;
    public final ModelPart rightLeg;
    public final ModelPart leftLeg;
    public final ModelPart rightArm;
    public final ModelPart leftArm;
    public final ModelPart petalPlate;
    public final ModelPart bone4;
    public final ModelPart bone5;

    public UmbralSunflowerModel(ModelPart root) {
        this.bone2 = root.getChild("bone2");
        this.head = this.bone2.getChild("Head");
        this.body = this.bone2.getChild("Body");
        ModelPart bone3 = this.bone2.getChild("bone3");
        this.bone3 = bone3;
        this.rightLeg = bone3.getChild("RightLeg");
        this.leftLeg = bone3.getChild("LeftLeg");
        ModelPart bone4Parent = this.bone2.getChild("bone4");
        this.rightArm = bone4Parent.getChild("RightArm");
        this.leftArm = bone4Parent.getChild("LeftArm");
        this.bone4 = bone4Parent;
        this.bone5 = this.rightArm.getChild("bone5");
        this.petalPlate = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = bone2.addOrReplaceChild("Head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = bone2.addOrReplaceChild("Body", CubeListBuilder.create()
                        .texOffs(16, 16).addBox(-4.0F, -11.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(16, 32).addBox(-4.0F, -11.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition rightLeg = bone3.addOrReplaceChild("RightLeg", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition leftLeg = bone3.addOrReplaceChild("LeftLeg", CubeListBuilder.create()
                        .texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                PartPose.offsetAndRotation(3.8F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition bone4 = bone2.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition rightArm = bone4.addOrReplaceChild("RightArm", CubeListBuilder.create()
                        .texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                PartPose.offset(-5.0F, 0.0F, 0.0F));

        PartDefinition bone5 = rightArm.addOrReplaceChild("bone5", CubeListBuilder.create()
                        .texOffs(51, 72).addBox(-18.0F, -2.0F, 0.0F, 20.0F, 20.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(51, 72).addBox(-18.0F, -2.0F, 0.25F, 20.0F, 20.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(51, 72).addBox(-18.0F, -2.0F, 0.5F, 20.0F, 20.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(51, 72).addBox(-18.0F, -2.0F, 0.75F, 20.0F, 20.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 9.0F, 0.0F, 1.5708F, -0.829F, -1.5708F));

        PartDefinition leftArm = bone4.addOrReplaceChild("LeftArm", CubeListBuilder.create()
                        .texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition petalPlate = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create()
                        .texOffs(65, 35).addBox(-2.0F, -16.0F, 1.0F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-19.0F, 0.0F, -15.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public @NotNull ModelPart root() {
        return this.bone2;
    }

    public void applyDefaultPositions() {
        this.bone2.x = 0;
        this.bone2.y = -1;
        this.bone2.z = 1;
        this.bone2.xRot = 0;
        this.bone2.yRot = 0;
        this.bone2.zRot = 0;

        this.head.x = 0;
        this.head.y = 0;
        this.head.z = 3;

        this.body.x = 0;
        this.body.y = 11.0F;
        this.body.z = 0;

        this.bone4.x = 0;
        this.bone4.y = 0;
        this.bone4.z = 3;

        this.bone3.xRot = 0;
        this.bone3.yRot = 0;
        this.bone3.zRot = 0;
        this.bone3.x = -1.9F;
        this.bone3.y = 12.0F;
        this.bone3.z = 0;

        this.rightArm.x = -5.0F;
        this.rightArm.y = 0;
        this.rightArm.z = 0;
        this.leftArm.x = 5.0F;
        this.leftArm.y = 0;
        this.leftArm.z = 0;
        this.rightLeg.x = 0;
        this.rightLeg.y = 0;
        this.rightLeg.z = 0;
        this.leftLeg.x = 3.8F;
        this.leftLeg.y = 0;
        this.leftLeg.z = 0;
    }

    public void setBone5Pose(float rotXDeg, float rotYDeg, float rotZDeg, float posX, float posY, float posZ) {
        this.bone5.xRot = 1.5708F + rotXDeg * Mth.DEG_TO_RAD;
        this.bone5.yRot = -0.829F + rotYDeg * Mth.DEG_TO_RAD;
        this.bone5.zRot = -1.5708F + rotZDeg * Mth.DEG_TO_RAD;
        this.bone5.x = -1.0F + posX;
        this.bone5.y = 9.0F - posY;
        this.bone5.z = posZ;
    }

    @Override
    public void setupAnim(@NotNull UmbralSunflower entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        applyDefaultPositions();
        UmbralSunflowerAnimations.animate(this, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay, int color) {
        bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        petalPlate.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}