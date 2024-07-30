package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.SeahorseEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SeahorseModel<T extends SeahorseEntity> extends AdvancedEntityModel<T> {

    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart Ear_Right;
    private final ModelPart Ear_Left;
    private final ModelPart tail;
    private final ModelPart tail5;
    private final ModelPart tail7;

    public SeahorseModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = Body.getChild("head");
        this.Ear_Right = Head.getChild("ear_right");
        this.Ear_Left = Head.getChild("ear_left");
        this.tail = Body.getChild("tail");
        ModelPart tail1 = tail.getChild("tail1");
        ModelPart tail2 = tail1.getChild("tail2");
        ModelPart tail3 = tail2.getChild("tail3");
        ModelPart tail4 = tail3.getChild("tail4");
        this.tail5 = tail4.getChild("tail5");
        ModelPart tail6 = tail5.getChild("tail6");
        this.tail7 = tail6.getChild("tail7");
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.defaultHeadMovement(Head, -5, 0, headPitch, netHeadYaw);

        Ear_Right.yRot = -0.2F * Mth.cos(ageInTicks * 0.25F) - 0.55F;
        Ear_Left.yRot = 0.2F * Mth.cos(ageInTicks * 0.25F) + 0.55F;

        tail.xRot = -0.1F * Mth.cos(ageInTicks * 0.1F) - 0.0436F;
        tail5.xRot = -0.2F * Mth.cos(ageInTicks * 0.1F) - 0.5236F;
        tail7.xRot = -0.1F * Mth.cos(ageInTicks * 0.15F) - 0.48F;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(8, 11).addBox(-1.0F, -1.2F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(4, 13).addBox(-0.5F, -2.85F, -0.05F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.025F)).texOffs(4, 13).mirror().addBox(-0.5F, 1.3F, -0.05F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.025F)).mirror(false).texOffs(12, 3).addBox(0.0F, -2.45F, 0.3F, 0.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 18.05F, 0.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition head = Body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -1.8707F, -1.0981F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.8153F, 0.197F, -0.0873F, 0.0F, 0.0F));
        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(4, 6).addBox(-0.5F, -0.825F, -1.825F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.4207F, -0.8981F, 0.1309F, 0.0F, 0.0F));
        PartDefinition mouth_bottom = mouth.addOrReplaceChild("mouth_bottom", CubeListBuilder.create().texOffs(4, 9).addBox(-0.5F, -0.5834F, -1.5624F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.025F)), PartPose.offsetAndRotation(0.0F, 0.15F, -0.1F, 0.1745F, 0.0F, 0.0F));
        PartDefinition ear_right = head.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(9, 4).addBox(0.1F, -0.675F, -0.025F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.3457F, 0.4019F, 0.1309F, -0.4363F, 0.0F));
        PartDefinition ear_left = head.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(9, 4).addBox(-0.1F, -0.675F, -0.025F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.3457F, 0.4019F, 0.1309F, 0.4363F, 0.0F));
        PartDefinition throat_front = Body.addOrReplaceChild("throat_front", CubeListBuilder.create().texOffs(4, 13).mirror().addBox(-0.5F, -0.9925F, -0.5238F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.025F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.875F, -0.2F, -0.3054F, 0.0F, 0.0F));
        PartDefinition tail_front = Body.addOrReplaceChild("tail_front", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, -1.175F, -0.4F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.025F)), PartPose.offsetAndRotation(0.0F, 2.65F, -0.075F, 0.48F, 0.0F, 0.0F));
        PartDefinition tail = Body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.525F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 3.25F, 0.475F, -0.0436F, 0.0F, 0.0F));
        PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, -0.106F, -0.4908F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.875F, -0.025F, -0.3927F, 0.0F, 0.0F));
        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 4).addBox(-0.5F, -0.1799F, -0.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.0F, 0.819F, 0.0342F, -0.5236F, 0.0F, 0.0F));
        PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5F, -0.175F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.03F)), PartPose.offsetAndRotation(0.0F, 0.674F, -0.0366F, -0.5672F, 0.0F, 0.0F));
        PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 8).addBox(-0.5F, -0.2476F, -0.594F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.04F)), PartPose.offsetAndRotation(0.0F, 0.7502F, 0.0533F, -0.5672F, 0.0F, 0.0F));
        PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(4, 0).addBox(-0.5F, -0.2725F, -0.5492F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(0.0F, 0.7024F, -0.119F, -0.5236F, 0.0F, 0.0F));
        PartDefinition tail6 = tail5.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(4, 2).addBox(-0.5F, -0.2747F, -0.5139F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.06F)), PartPose.offsetAndRotation(0.0F, 0.6261F, -0.0859F, -0.5672F, 0.0F, 0.0F));
        PartDefinition tail7 = tail6.addOrReplaceChild("tail7", CubeListBuilder.create().texOffs(4, 4).addBox(-0.5F, -0.2693F, -0.5188F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.07F)), PartPose.offsetAndRotation(0.0F, 0.623F, -0.0488F, -0.48F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 16, 16);
    }

}
