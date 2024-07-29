package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.PenguinEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class PenguinModel extends AdvancedEntityModel<PenguinEntity> {

    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart Beak;
    private final ModelPart LeftWing;
    private final ModelPart RightWing;
    private final ModelPart LeftFeet;
    private final ModelPart RightFeet;

    public PenguinModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = root.getChild("Head");
        this.Beak = Head.getChild("Beak");
        this.LeftWing = Body.getChild("LeftWing");
        this.RightWing = Body.getChild("RightWing");
        this.LeftFeet = Body.getChild("LeftFeet");
        this.RightFeet = Body.getChild("RightFeet");
    }

    @Override
    public void setupAnim(PenguinEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.defaultHeadMovement(Head, 0, 0, headPitch, netHeadYaw);
        // wobbling effect while walking
        this.Body.zRot = (Mth.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 7;
        // animate penguin swinging wings
        this.RightWing.zRot = (0.1308996938F) + ((0.7872664625F + Mth.cos(limbSwing * 0.6662F) * 1.0F) * limbSwingAmount);
        this.LeftWing.zRot = (-0.1308996938F) + ((-0.7872664625F + Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F) * limbSwingAmount);
        this.RightFeet.xRot = -((Mth.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2);
        this.LeftFeet.xRot = (Mth.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, int color) {

        if (this.young) {
            matrixStackIn.pushPose();
            this.Beak.setPos(0.0F, -2.25F, -3.75F);
            float f = 0.55F;
            matrixStackIn.scale(f, f, f);
            matrixStackIn.translate(0.0D, 1.32D, 0.02D);
            this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            matrixStackIn.popPose();

            matrixStackIn.pushPose();
            float f1 = 0.5F;
            matrixStackIn.scale(f1, f1, f1);
            matrixStackIn.translate(0.0D, 1.5D, 0.0D);
            this.Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            matrixStackIn.popPose();
        } else {
            this.Beak.setPos(0.0F, -1.75F, -3.75F);
            this.Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -16.0F, -3.9167F, 10.0F, 16.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -1.0833F));
        PartDefinition RightFeet = Body.addOrReplaceChild("RightFeet", CubeListBuilder.create().texOffs(54, 10).addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -0.5F, -2.9167F, 0.0F, 0.1745F, 0.0F));
        PartDefinition LeftFeet = Body.addOrReplaceChild("LeftFeet", CubeListBuilder.create().texOffs(54, 10).mirror().addBox(-1.0F, -0.5F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -0.5F, -2.9167F, 0.0F, -0.1745F, 0.0F));
        PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(46, 23).addBox(0.0F, 0.0F, -4.5F, 1.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -14.0F, 1.0833F, 0.0F, 0.0F, -0.0873F));
        PartDefinition RightWing = Body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(46, 23).mirror().addBox(-1.0F, 0.0F, -4.5F, 1.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -14.0F, 1.0833F, 0.0F, 0.0F, 0.0873F));
        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 27).addBox(-4.0F, -7.0F, -3.75F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -1.0F));
        PartDefinition Beak = Head.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(54, 0).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.75F, -3.75F));
        return LayerDefinition.create(meshdefinition, 64, 42);
    }

}
