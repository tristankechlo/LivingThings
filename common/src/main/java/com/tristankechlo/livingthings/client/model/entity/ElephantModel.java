package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.ElephantEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ElephantModel<T extends ElephantEntity> extends AdvancedEntityModel<T> {

    private final ModelPart Tusks;
    private final ModelPart Body;
    private final ModelPart Chests;
    private final ModelPart Saddle;
    private final ModelPart Head;
    private final ModelPart TrunkTop;
    private final ModelPart TrunkMiddle;
    private final ModelPart TrunkBottom;
    private final ModelPart RightFrontLeg;
    private final ModelPart LeftFrontLeg;
    private final ModelPart RightBackLeg;
    private final ModelPart LeftBackLeg;
    private final ModelPart LeftEar;
    private final ModelPart RightEar;
    private float headAngle;

    public ElephantModel(ModelPart root) {
        this.Body = root.getChild("Body");

        this.Chests = Body.getChild("Chests");
        this.Saddle = Body.getChild("Saddle");
        this.Head = Body.getChild("Head");

        this.LeftEar = Head.getChild("LeftEar");
        this.RightEar = Head.getChild("RightEar");

        this.Tusks = Head.getChild("Tusks");
        this.TrunkTop = Head.getChild("TrunkTop");
        this.TrunkMiddle = TrunkTop.getChild("TrunkMiddle");
        this.TrunkBottom = TrunkMiddle.getChild("TrunkBottom");

        this.RightFrontLeg = Body.getChild("RightFrontLeg");
        this.LeftFrontLeg = Body.getChild("LeftFrontLeg");
        this.RightBackLeg = Body.getChild("RightBackLeg");
        this.LeftBackLeg = Body.getChild("LeftBackLeg");
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (this.young) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
            poseStack.translate(0, 1, 0);
        }
        Body.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(ElephantEntity elephant, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (elephant.getAttackTimer() > 0) {
            this.Head.xRot = this.headAngle;
        } else {
            this.Head.xRot = -0.0436332F + (headPitch * 0.0174532925F);
        }
        this.Head.yRot = netHeadYaw * 0.0174532925F;

        this.walk(RightFrontLeg, LeftFrontLeg, RightBackLeg, LeftBackLeg, limbSwing, limbSwingAmount);

        if (elephant.isAngry()) { // ears spread out when angry at player
            this.LeftEar.yRot = 1.13446F;
            this.LeftEar.zRot = 0.1309F;
            this.RightEar.yRot = -1.13446F;
            this.RightEar.zRot = -0.1309F;
        } else {
            this.LeftEar.yRot = 0.4799655F + (0.3F * Mth.sin(0.1F * ageInTicks + 1F + (float) Math.PI));
            this.LeftEar.zRot = 0F;
            this.RightEar.yRot = -0.4799655F + (0.3F * Mth.sin(0.1F * ageInTicks + 1F));
            this.RightEar.zRot = 0F;
        }

        this.TrunkTop.xRot = 0.0436332F + (0.1F * Mth.sin(0.1F * ageInTicks + 1F));
        this.TrunkTop.zRot = (0.1F * Mth.sin(0.1F * ageInTicks + 1F));

        this.TrunkMiddle.xRot = 0.0436332F + (0.15F * Mth.cos(0.1F * ageInTicks + 2F));
        this.TrunkMiddle.zRot = (0.15F * Mth.cos(0.1F * ageInTicks + 2F));

        this.TrunkBottom.xRot = 0.0872665F + (0.2F * Mth.sin(0.1F * ageInTicks + 2F));
        this.TrunkBottom.zRot = (0.2F * Mth.sin(0.1F * ageInTicks + 2F));
    }

    @Override
    public void prepareMobModel(ElephantEntity elephant, float limbSwing, float limbSwingAmount, float partialTick) {
        float i = elephant.getAttackTimer(); // counting down from 400 ticks
        if (i > 0) {
            //calculate angle, linear between -10 and 55 degrees
            float progress = (1.0F - ((i - partialTick) / (float) ElephantEntity.ANGER_TIME));
            this.headAngle = Mth.lerp(progress, 0.174533F, -0.959931F);
        }

        this.Chests.visible = elephant.hasChest();
        this.Saddle.visible = elephant.isSaddled();
        this.Tusks.visible = !this.young;

    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 65).addBox(-11.0F, -41.0F, -20.0F, 22.0F, 23.0F, 40.0F, new CubeDeformation(0.0F)).texOffs(156, 93).addBox(-8.0F, -42.0F, -17.0F, 16.0F, 1.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, 0.0F));
        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(123, 45).addBox(-9.0F, -9.0F, -12.0F, 18.0F, 16.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, -20.0F, -0.0436F, 0.0F, 0.0F));
        PartDefinition TrunkTop = Head.addOrReplaceChild("TrunkTop", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -0.1F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, -8.0F, 0.0436F, 0.0F, 0.0F));
        PartDefinition TrunkMiddle = TrunkTop.addOrReplaceChild("TrunkMiddle", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -0.2F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0436F, 0.0F, 0.0F));
        PartDefinition TrunkBottom = TrunkMiddle.addOrReplaceChild("TrunkBottom", CubeListBuilder.create().texOffs(0, 44).addBox(-2.0F, 0.7F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0873F, 0.0F, 0.0F));
        PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(220, 37).mirror().addBox(0.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.0F, -1.0F, -6.0F, 0.1309F, 0.48F, 0.0F));
        PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(200, 37).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -1.0F, -6.0F, 0.1309F, -0.48F, 0.0F));
        PartDefinition Tusks = Head.addOrReplaceChild("Tusks", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition RightTusk = Tusks.addOrReplaceChild("RightTusk", CubeListBuilder.create(), PartPose.offset(-7.0F, 6.0F, -9.0F));
        PartDefinition RightTuskTop = RightTusk.addOrReplaceChild("RightTuskTop", CubeListBuilder.create().texOffs(192, 0).addBox(-15.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 0.0F, 0.0F));
        PartDefinition RightTuskMiddle = RightTuskTop.addOrReplaceChild("RightTuskMiddle", CubeListBuilder.create().texOffs(204, 0).mirror().addBox(-15.0F, -0.35F, -1.05F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, -0.3054F, 0.0F, 0.0F));
        PartDefinition RightTuskBottom = RightTuskMiddle.addOrReplaceChild("RightTuskBottom", CubeListBuilder.create().texOffs(215, 0).addBox(-15.0F, -0.85F, -1.475F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.002F)), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.6545F, 0.0F, 0.0F));
        PartDefinition LeftTusk = Tusks.addOrReplaceChild("LeftTusk", CubeListBuilder.create(), PartPose.offset(7.0F, 6.0F, -9.0F));
        PartDefinition LeftTuskTop = LeftTusk.addOrReplaceChild("LeftTuskTop", CubeListBuilder.create().texOffs(192, 0).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition LeftTuskMiddle = LeftTuskTop.addOrReplaceChild("LeftTuskMiddle", CubeListBuilder.create().texOffs(204, 0).addBox(-1.0F, -0.35F, -1.05F, 2.0F, 6.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, -0.3054F, 0.0F, 0.0F));
        PartDefinition LeftTuskBottom = LeftTuskMiddle.addOrReplaceChild("LeftTuskBottom", CubeListBuilder.create().texOffs(215, 0).mirror().addBox(-1.0F, -0.85F, -1.475F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.002F)).mirror(false), PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.6545F, 0.0F, 0.0F));
        PartDefinition RightFrontLeg = Body.addOrReplaceChild("RightFrontLeg", CubeListBuilder.create().texOffs(95, 0).addBox(-2.75F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -19.0F, -16.0F));
        PartDefinition LeftFrontLeg = Body.addOrReplaceChild("LeftFrontLeg", CubeListBuilder.create().texOffs(68, 0).addBox(-3.25F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -19.0F, -16.0F));
        PartDefinition LeftBackLeg = Body.addOrReplaceChild("LeftBackLeg", CubeListBuilder.create().texOffs(122, 0).addBox(-3.25F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -19.0F, 16.0F));
        PartDefinition RightBackLeg = Body.addOrReplaceChild("RightBackLeg", CubeListBuilder.create().texOffs(149, 0).addBox(-2.75F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -19.0F, 16.0F));
        PartDefinition Chests = Body.addOrReplaceChild("Chests", CubeListBuilder.create().texOffs(54, 38).addBox(11.0F, -36.0F, 9.5F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(54, 38).mirror().addBox(-13.0F, -36.0F, 9.5F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(127, 112).mirror().addBox(10.2F, -41.475F, 11.5F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(127, 112).addBox(-11.2F, -41.475F, 11.5F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(127, 105).addBox(-8.5F, -42.475F, 11.5F, 17.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition HolderLeft = Chests.addOrReplaceChild("HolderLeft", CubeListBuilder.create().texOffs(173, 104).addBox(-1.158F, 0.4397F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(9.6F, -42.5F, 13.5F, 0.0F, 0.0F, 0.3491F));
        PartDefinition HolderRight = Chests.addOrReplaceChild("HolderRight", CubeListBuilder.create().texOffs(173, 104).addBox(-1.842F, 0.4397F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-9.6F, -42.5F, 13.5F, 0.0F, 0.0F, -0.3491F));
        PartDefinition Saddle = Body.addOrReplaceChild("Saddle", CubeListBuilder.create().texOffs(127, 112).addBox(-6.5F, -43.0F, -7.0F, 13.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(128, 100).addBox(-6.0F, -44.0F, 3.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(181, 113).addBox(-11.2F, -41.475F, -2.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(181, 113).mirror().addBox(10.2F, -41.475F, -2.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(168, 117).addBox(-1.5F, -45.0F, -7.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(245, 43).addBox(10.4F, -31.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(245, 43).addBox(-11.4F, -31.5F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(245, 43).addBox(10.375F, -31.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(245, 43).addBox(-11.375F, -31.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition SaddleRight = Saddle.addOrReplaceChild("SaddleRight", CubeListBuilder.create().texOffs(158, 100).addBox(-2.8499F, 0.8811F, -0.5F, 5.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-8.625F, -43.175F, -1.5F, 0.0F, 0.0F, -0.3054F));
        PartDefinition SaddleLeft = Saddle.addOrReplaceChild("SaddleLeft", CubeListBuilder.create().texOffs(158, 100).mirror().addBox(-2.2485F, 0.8811F, -0.5F, 5.0F, 1.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offsetAndRotation(8.725F, -43.15F, -1.5F, 0.0F, 0.0F, 0.3054F));
        return LayerDefinition.create(meshdefinition, 256, 128);
    }

}
