package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.NetherKnightEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class NetherKnightModel<T extends NetherKnightEntity> extends AdvancedEntityModel<T> implements ArmedModel {

    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;

    public NetherKnightModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = Body.getChild("Head");
        this.RightArm = Body.getChild("RightArm");
        this.LeftArm = Body.getChild("LeftArm");
        this.RightLeg = Body.getChild("RightLeg");
        this.LeftLeg = Body.getChild("LeftLeg");
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.defaultHeadMovement(Head, 0, 0, headPitch, netHeadYaw);
        this.LeftArm.xRot = 0;
        this.LeftArm.zRot = 0;
        this.RightArm.xRot = 0;
        this.RightArm.zRot = 0;
        this.walking1(LeftLeg, limbSwing, limbSwingAmount);
        this.walking2(RightLeg, limbSwing, limbSwingAmount);
        this.walking2(LeftArm, limbSwing, limbSwingAmount);
        this.walking1(RightArm, limbSwing, limbSwingAmount);
        this.setupAttackAnimation(entity, ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void translateToHand(HumanoidArm handSide, PoseStack stack) {
        this.getArm(handSide).translateAndRotate(stack);
    }

    private ModelPart getArm(HumanoidArm handSide) {
        return handSide == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    private void setupAttackAnimation(T entity, float ageInTicks) {
        if (this.attackTime > 0.0F) {
            float f = Mth.sin(attackTime * (float) Math.PI);
            float f1 = Mth.sin((1.0F - (1.0F - attackTime) * (1.0F - attackTime)) * (float) Math.PI);
            LeftArm.zRot = 0.0F;
            RightArm.zRot = 0.0F;
            LeftArm.yRot = 0.15707964F;
            RightArm.yRot = -0.15707964F;
            if (entity.getMainArm() == HumanoidArm.RIGHT) {
                RightArm.xRot = -Mth.cos(ageInTicks * 0.1F) * 0.5F;
                RightArm.xRot -= f * 1.2F + f1 * 0.4F;
            } else {
                LeftArm.xRot = -Mth.cos(ageInTicks * 0.1F) * 0.5F;
                LeftArm.xRot -= f * 1.2F + f1 * 0.4F;
            }
        }
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 53).addBox(-6.0F, -3.8F, -2.0F, 12.0F, 7.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(0, 41).addBox(-4.5F, 3.0F, -2.0F, 9.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(20, 39).addBox(-3.0F, -5.75F, -3.975F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.0F, -3.5F, -5.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));
        PartDefinition Horn1 = Head.addOrReplaceChild("Horn1", CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, -14.0F, 0.0F, 14.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
        PartDefinition Horn2 = Head.addOrReplaceChild("Horn2", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-7.0F, -14.0F, 0.0F, 14.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
        PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(48, 46).mirror().addBox(-2.15F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.25F)).mirror(false).texOffs(48, 11).addBox(-2.15F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.5F)).texOffs(48, 37).mirror().addBox(-2.15F, 8.9F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -2.0F, 0.0F));
        PartDefinition RightShoulder = RightArm.addOrReplaceChild("RightShoulder", CubeListBuilder.create().texOffs(48, 29).mirror().addBox(-1.9F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
        PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 46).addBox(-1.85F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.25F)).texOffs(48, 11).addBox(-1.85F, -1.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(-0.5F)).texOffs(48, 37).addBox(-1.85F, 8.9F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -2.0F, 0.0F));
        PartDefinition LeftShoulder = LeftArm.addOrReplaceChild("LeftShoulder", CubeListBuilder.create().texOffs(48, 29).addBox(-1.6F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition RightLeg = Body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(32, 47).addBox(-2.3F, -2.1F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(-0.1F)).texOffs(32, 30).addBox(-2.3F, -2.1F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(-0.2F)).texOffs(0, 33).addBox(-2.3F, 8.9F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.9F, 11.0F, 0.0F));
        PartDefinition LeftLeg = Body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(32, 47).mirror().addBox(-1.7F, -3.1F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(-0.1F)).mirror(false).texOffs(32, 30).mirror().addBox(-1.7F, -3.1F, -2.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(-0.2F)).mirror(false).texOffs(0, 33).mirror().addBox(-1.7F, 7.9F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

}