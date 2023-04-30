package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.MonkeyEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MonkeySittingModel<T extends MonkeyEntity> extends AdvancedEntityModel<T> {

    private final ModelPart Body;
    private final ModelPart BodyTop;
    private final ModelPart Head;

    public MonkeySittingModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.BodyTop = Body.getChild("BodyTop");
        this.Head = BodyTop.getChild("Head");
    }

    @Override
    public void setupAnim(MonkeyEntity monkey, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (monkey.isPartying()) {
            Head.xRot = 1.4835F + Mth.cos(ageInTicks * 0.4F) * 0.3F;
            Head.zRot = 0.0F;
            BodyTop.yRot = Mth.cos(ageInTicks * 0.2F) * 0.175F;
        } else {
            Head.xRot = 1.4835F + deg2rad(headPitch);
            Head.zRot = deg2rad(netHeadYaw);
            BodyTop.yRot = 0.0F;
        }

    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young) {
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0, 1.5D, 0);
        }
        Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -2.1468F, -0.0005F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 19.117F, 0.9453F, -1.5272F, 0.0F, 0.0F));
        PartDefinition BackRightLegTop = Body.addOrReplaceChild("BackRightLegTop", CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, -1.4F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 1.0209F, 4.0362F, -0.0436F, 0.0436F, 0.5672F));
        PartDefinition BackRightLegBottom = BackRightLegTop.addOrReplaceChild("BackRightLegBottom", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -0.45F, -0.925F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 30).addBox(-1.0F, 3.55F, -1.925F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5388F, 2.1947F, -0.0614F, 0.0F, 0.0F, -1.4835F));
        PartDefinition BackLeftLegTop = Body.addOrReplaceChild("BackLeftLegTop", CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, -1.475F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.95F, 0.9675F, 3.9196F, -0.0697F, -0.008F, -0.6557F));
        PartDefinition BackLeftLegBottom = BackLeftLegTop.addOrReplaceChild("BackLeftLegBottom", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -0.525F, -0.9F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 30).addBox(-1.0F, 3.475F, -1.9F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2656F, 1.7932F, 0.0215F, 1.309F, -1.5708F, 0.0F));
        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(18, 20).addBox(-1.0F, -1.1F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0106F, 4.1315F, 1.4835F, 0.0F, -0.1745F));
        PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(22, 14).addBox(-0.4898F, -0.4184F, 0.0203F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0102F, -0.1211F, 4.3435F, 0.0436F, -0.6981F, 0.0F));
        PartDefinition Tail3 = Tail2.addOrReplaceChild("Tail3", CubeListBuilder.create().texOffs(18, 13).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0268F, 0.0709F, 4.0296F, 0.0F, -0.6109F, 0.0F));
        PartDefinition BodyTop = Body.addOrReplaceChild("BodyTop", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -2.0076F, -5.0365F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1619F, 0.3208F, 0.0873F, 0.0F, 0.0F));
        PartDefinition Head = BodyTop.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 5).addBox(-2.5F, -5.0F, -1.9965F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.3827F, -4.8802F, 1.4835F, 0.0F, 0.0F));
        PartDefinition MouthTop = Head.addOrReplaceChild("MouthTop", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.125F, -2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.9009F, -1.2519F, 0.0873F, 0.0F, 0.0F));
        PartDefinition MouthBottom = Head.addOrReplaceChild("MouthBottom", CubeListBuilder.create().texOffs(19, 8).addBox(-1.5F, -0.625F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.6598F, -1.0705F, 0.3927F, 0.0F, 0.0F));
        PartDefinition EarRight = Head.addOrReplaceChild("EarRight", CubeListBuilder.create().texOffs(28, 21).addBox(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -3.0378F, 0.037F));
        PartDefinition EarLeft = Head.addOrReplaceChild("EarLeft", CubeListBuilder.create().texOffs(28, 21).addBox(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -3.0378F, 0.037F));
        PartDefinition FrontRightLegTop = BodyTop.addOrReplaceChild("FrontRightLegTop", CubeListBuilder.create().texOffs(15, 0).addBox(-1.3215F, -1.4728F, -1.059F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.675F, 0.2566F, -2.8141F, 0.4038F, -0.8178F, 0.7357F));
        PartDefinition FrontRightLegBottom = FrontRightLegTop.addOrReplaceChild("FrontRightLegBottom", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5958F, -1.3258F, -0.934F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 30).addBox(-0.5958F, 2.6742F, -1.934F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.136F, 2.4769F, -0.0749F, 0.0F, 0.0F, -1.1781F));
        PartDefinition FrontLeftLegTop = BodyTop.addOrReplaceChild("FrontLeftLegTop", CubeListBuilder.create().texOffs(15, 0).addBox(-1.0F, -0.875F, -1.2F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.55F, 0.3021F, -2.7148F, 0.4911F, -1.1232F, -1.3466F));
        PartDefinition FrontLeftLegBottom = FrontLeftLegTop.addOrReplaceChild("FrontLeftLegBottom", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0588F, -0.0608F, -1.0545F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 30).addBox(-1.0588F, 3.9392F, -2.0545F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0641F, 2.2424F, -0.8784F, 1.5708F, 0.2618F, 1.5708F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

}
