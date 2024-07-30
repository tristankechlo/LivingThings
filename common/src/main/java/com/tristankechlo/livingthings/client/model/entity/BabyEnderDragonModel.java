package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.BabyEnderDragonEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BabyEnderDragonModel extends AdvancedEntityModel<BabyEnderDragonEntity> {

    private final ModelPart Body;
    private final ModelPart FrontLeftLeg;
    private final ModelPart FrontRightLeg;
    private final ModelPart BackLeftLeg;
    private final ModelPart BackRightLeg;
    private final ModelPart LeftWing;
    private final ModelPart RightWing;
    private final ModelPart LeftWing2;
    private final ModelPart RightWing2;
    private final ModelPart Tail;
    private final ModelPart Tail2;
    private final ModelPart Tail3;
    private final ModelPart Tail4;
    private final ModelPart Neck;
    private final ModelPart Head;
    private final ModelPart Bell;

    public BabyEnderDragonModel(ModelPart root) {
        this.Body = root.getChild("Body");
        ModelPart frontBody = this.Body.getChild("FrontBody");
        this.FrontLeftLeg = frontBody.getChild("FrontLeftLeg");
        this.FrontRightLeg = frontBody.getChild("FrontRightLeg");
        this.BackLeftLeg = this.Body.getChild("BackLeftLeg");
        this.BackRightLeg = this.Body.getChild("BackRightLeg");
        this.LeftWing = frontBody.getChild("LeftWing");
        this.LeftWing2 = this.LeftWing.getChild("LeftWing2");
        this.RightWing = frontBody.getChild("RightWing");
        this.RightWing2 = this.RightWing.getChild("RightWing2");
        this.Tail = this.Body.getChild("Tail");
        this.Tail2 = this.Tail.getChild("Tail2");
        this.Tail3 = this.Tail2.getChild("Tail3");
        this.Tail4 = this.Tail3.getChild("Tail4");
        this.Neck = frontBody.getChild("Neck");
        this.Head = this.Neck.getChild("Head");
        this.Bell = this.Neck.getChild("Bell");
    }

    @Override
    public void setupAnim(BabyEnderDragonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.Neck.yRot = (netHeadYaw / 3) * 0.0174532925F;
        this.Neck.xRot = (headPitch / 3) * 0.0174532925F;
        this.Head.yRot = (netHeadYaw / 2) * 0.0174532925F;
        this.Head.xRot = (headPitch / 2) * 0.0174532925F;

        this.FrontLeftLeg.xRot = 0.0F;
        this.FrontRightLeg.xRot = 0.0F;
        this.BackLeftLeg.xRot = 0.0F;
        this.BackRightLeg.xRot = 0.0F;

        final boolean isMoving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D;
        final boolean isFlying = entity.isFlying();

        if (isFlying && isMoving) { // is flying
            this.animateFlying(limbSwing, limbSwingAmount, ageInTicks);
        } else if (isFlying && !isMoving) { // hovering in the air
            this.animateHovering(ageInTicks);
        } else if (!isFlying && !isMoving) { // standing
            this.animateSwingingTail(ageInTicks, 1.0F);
        } else {
            this.animateWalking(limbSwing, limbSwingAmount, ageInTicks);
        }
    }

    private void animateFlying(float limbSwing, float limbSwingAmount, float ageInTicks) {
        final float speed = 0.6662F * 1.75F;
        final float amount = 0.9F;
        this.LeftWing.zRot = (-0.349066F) + Mth.cos(limbSwing * speed) * amount * limbSwingAmount;
        this.LeftWing2.zRot = 0.349066F;
        this.RightWing.zRot = 0.349066F + Mth.cos(limbSwing * speed + (float) Math.PI) * amount * limbSwingAmount;
        this.RightWing2.zRot = (-0.349066F);

        this.Tail.xRot = 1.35263F;
        this.Tail2.xRot = -0.349066F;
        this.Tail3.xRot = 0.174533F;
        this.Tail4.xRot = 0.2181662F;

        this.Tail.yRot = 0F;
        this.Tail2.yRot = 0F;
        this.Tail3.yRot = 0F;
        this.Tail4.yRot = 0F;

        this.Tail.zRot = 0F;
        this.Tail2.zRot = 0F;
        this.Tail3.zRot = 0F;
        this.Tail4.zRot = 0F;

        this.FrontLeftLeg.xRot = 1.0F;
        this.FrontRightLeg.xRot = 1.0F;
        this.BackLeftLeg.xRot = 1.0F;
        this.BackRightLeg.xRot = 1.0F;
    }

    private void animateHovering(float ageInTicks) {
        this.LeftWing.zRot = (-0.349066F) + (Mth.cos(ageInTicks * 0.45F));
        this.LeftWing2.zRot = 0.349066F;
        this.RightWing.zRot = 0.349066F - (Mth.cos(ageInTicks * 0.45F));
        this.RightWing2.zRot = (-0.349066F);

        this.animateSwingingTail(ageInTicks, 1.5F);
    }

    private void animateSwingingTail(float ageInTicks, float speedModifier) {
        this.Tail.xRot = 0.9017533F;
        this.Tail2.xRot = -0.349066F;
        this.Tail3.xRot = 0.523599F;
        this.Tail4.xRot = 0.6544986F;

        this.Tail.zRot = 0.0F;
        this.Tail2.zRot = (Mth.cos(ageInTicks * 0.05F * speedModifier)) * 0.15F;
        this.Tail3.zRot = (Mth.cos(ageInTicks * 0.05F * speedModifier)) * 0.15F;
        this.Tail4.zRot = (Mth.cos(ageInTicks * 0.05F * speedModifier)) * 0.35F;

        this.Tail.yRot = (Mth.cos(ageInTicks * 0.05F * speedModifier)) * -0.15F;
        this.Tail2.yRot = 0.0F;
        this.Tail3.yRot = (Mth.cos(ageInTicks * 0.05F * speedModifier)) * 0.25F;
        this.Tail4.yRot = (Mth.cos(ageInTicks * 0.05F * speedModifier)) * 0.15F;
    }

    private void animateWalking(float limbSwing, float limbSwingAmount, float ageInTicks) {
        this.Tail.xRot = 1.35263F;
        this.Tail2.xRot = -0.349066F;
        this.Tail3.xRot = 0.174533F;
        this.Tail4.xRot = 0.2181662F;

        this.Tail.yRot = 0F;
        this.Tail2.yRot = 0F;
        this.Tail3.yRot = 0F;
        this.Tail4.yRot = 0F;

        this.Tail.zRot = 0F;
        this.Tail2.zRot = 0F;
        this.Tail3.zRot = 0F;
        this.Tail4.zRot = 0F;

        this.walk(FrontRightLeg, FrontLeftLeg, BackRightLeg, BackLeftLeg, limbSwing, limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Body.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void prepareMobModel(BabyEnderDragonEntity entity, float limbSwing, float limbSwingAmount, float partialTick) {
        this.Bell.visible = entity.isTame();
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(14, 17).addBox(-3.0F, -11.625F, 0.0F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 27).addBox(-1.0F, -13.375F, 5.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 27).addBox(-1.0F, -13.375F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition FrontBody = Body.addOrReplaceChild("FrontBody", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, -4.75F, -7.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(19, 3).addBox(-4.0F, -3.0F, -8.0F, 6.0F, 6.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offset(1.0F, -8.625F, 2.0F));
        PartDefinition FrontRightLeg = FrontBody.addOrReplaceChild("FrontRightLeg", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1.25F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 2.0F, -5.0F));
        PartDefinition FrontRightLegLower = FrontRightLeg.addOrReplaceChild("FrontRightLegLower", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-0.75F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));
        PartDefinition FrontRightFoot = FrontRightLegLower.addOrReplaceChild("FrontRightFoot", CubeListBuilder.create().texOffs(11, 16).mirror().addBox(-3.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));
        PartDefinition FrontLeftLeg = FrontBody.addOrReplaceChild("FrontLeftLeg", CubeListBuilder.create().texOffs(46, 0).addBox(-1.75F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.0F, -5.0F));
        PartDefinition FrontLeftLegLower = FrontLeftLeg.addOrReplaceChild("FrontLeftLegLower", CubeListBuilder.create().texOffs(56, 0).addBox(-1.25F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));
        PartDefinition FrontLeftFoot = FrontLeftLegLower.addOrReplaceChild("FrontLeftFoot", CubeListBuilder.create().texOffs(11, 16).addBox(0.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));
        PartDefinition Neck = FrontBody.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.5F, -7.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 19).addBox(-0.5F, -2.5F, -3.7F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 19).addBox(-0.5F, -2.5F, -6.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -0.02F, -6.75F));
        PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.48F, -4.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 0).addBox(1.0F, -5.23F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 0).addBox(-2.0F, -5.23F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 10).addBox(-3.0F, -0.5F, -7.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 1).addBox(0.75F, -0.975F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 1).addBox(-1.75F, -0.975F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.75F));
        PartDefinition Bell = Neck.addOrReplaceChild("Bell", CubeListBuilder.create().texOffs(41, 20).addBox(-1.0F, 1.445F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)).texOffs(35, 16).addBox(-1.5F, 2.875F, -5.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition LeftWing = FrontBody.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(48, 22).addBox(-0.5F, -1.2402F, -1.4429F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(47, 8).addBox(-0.942F, -0.0896F, 0.9F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.0F, -5.5F, 0.0F, 0.0F, -0.3491F));
        PartDefinition LeftWing2 = LeftWing.addOrReplaceChild("LeftWing2", CubeListBuilder.create().texOffs(44, 28).mirror().addBox(0.7886F, -1.5405F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(41, 15).mirror().addBox(0.7816F, -0.7213F, 0.825F, 8.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0514F, 0.3469F, 0.075F, 0.0F, 0.0F, 0.3491F));
        PartDefinition RightWing = FrontBody.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(48, 22).mirror().addBox(-4.5F, -1.2402F, -1.4429F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(47, 8).mirror().addBox(-4.058F, -0.0896F, 0.9F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, -2.0F, -5.5F, 0.0F, 0.0F, 0.3491F));
        PartDefinition RightWing2 = RightWing.addOrReplaceChild("RightWing2", CubeListBuilder.create().texOffs(44, 28).addBox(-8.7886F, -1.5405F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(41, 15).addBox(-8.7816F, -0.7213F, 0.825F, 8.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0514F, 0.3469F, 0.075F, 0.0F, 0.0F, -0.3491F));
        PartDefinition BackLeftLeg = Body.addOrReplaceChild("BackLeftLeg", CubeListBuilder.create().texOffs(46, 0).addBox(-1.75F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -6.625F, 6.5F));
        PartDefinition BackLeftLegLower = BackLeftLeg.addOrReplaceChild("BackLeftLegLower", CubeListBuilder.create().texOffs(56, 0).addBox(-1.25F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));
        PartDefinition BackLeftFoot = BackLeftLegLower.addOrReplaceChild("BackLeftFoot", CubeListBuilder.create().texOffs(11, 16).addBox(0.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));
        PartDefinition BackRightLeg = Body.addOrReplaceChild("BackRightLeg", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1.25F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, -6.625F, 6.5F));
        PartDefinition BackRightLegLower = BackRightLeg.addOrReplaceChild("BackRightLegLower", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-0.75F, 1.475F, -1.1782F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.325F, 1.0F, -0.5236F, 0.0F, 0.0F));
        PartDefinition BackRightFoot = BackRightLegLower.addOrReplaceChild("BackRightFoot", CubeListBuilder.create().texOffs(11, 16).mirror().addBox(-3.25F, 3.3F, 3.775F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 4.0F, -6.9282F, 0.5236F, 0.0F, 0.0F));
        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, 0.0F, -0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0).addBox(-0.5F, 0.5F, 1.25F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.15F, 8.275F, 1.3526F, 0.0F, 0.0F));
        PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0).addBox(-0.5F, 0.5F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, 0.25F, -0.3491F, 0.0F, 0.0F));
        PartDefinition Tail3 = Tail2.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, 0.0F, -1.0216F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0).addBox(-0.5F, 0.5F, 0.9784F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0216F, 0.1745F, 0.0F, 0.0F));
        PartDefinition Tail4 = Tail3.addOrReplaceChild("Tail4", CubeListBuilder.create().texOffs(38, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 0).addBox(-0.5F, 0.5F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, -0.0216F, 0.2182F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

}
