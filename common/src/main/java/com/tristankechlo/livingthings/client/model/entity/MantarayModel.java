package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.MantarayEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class MantarayModel<T extends MantarayEntity> extends AdvancedEntityModel<T> {

    private final ModelPart Body;
    private final ModelPart Tail;
    private final ModelPart Tail2;
    private final ModelPart LeftFlipper;
    private final ModelPart LeftFlipper2;
    private final ModelPart RightFlipper;
    private final ModelPart RightFlipper2;

    public MantarayModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Tail = Body.getChild("Tail");
        this.Tail2 = Tail.getChild("Tail2");
        this.LeftFlipper = Body.getChild("LeftFlipper");
        this.LeftFlipper2 = LeftFlipper.getChild("LeftFlipper2");
        this.RightFlipper = Body.getChild("RightFlipper");
        this.RightFlipper2 = RightFlipper.getChild("RightFlipper2");
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.Body.xRot = headPitch * 0.0174532925F;
        this.Body.yRot = netHeadYaw * 0.0174532925F;

        this.Tail.xRot = -0.5F * headPitch * 0.0174532925F;
        this.Tail2.xRot = -0.25F * headPitch * 0.0174532925F;

        // needed, so that the patchouli entry is always the same
        this.LeftFlipper.zRot = -0.34906585F;
        this.LeftFlipper2.zRot = -0.34906585F;
        this.RightFlipper.zRot = 0.34906585F;
        this.RightFlipper2.zRot = 0.34906585F;

        if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
            this.Body.xRot += -0.05F + (-0.05F * Mth.cos(ageInTicks * 0.2F));

            // wings flapping
            this.LeftFlipper.zRot = -0.6F * Mth.cos(ageInTicks * 0.15F);
            this.LeftFlipper2.zRot = -0.6F * Mth.cos(ageInTicks * 0.15F);
            this.RightFlipper.zRot = 0.6F * Mth.cos(ageInTicks * 0.15F);
            this.RightFlipper2.zRot = 0.6F * Mth.cos(ageInTicks * 0.15F);
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 38).addBox(-3.0F, -3.9F, -6.0F, 6.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 51).addBox(-3.5F, -3.5F, -6.0F, 7.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(28, 50).addBox(-3.5F, -1.75F, -6.0F, 7.0F, 1.0F, 11.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 23.5F, 0.0F));
        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(46, 14).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 5.0F));
        PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(33, 41).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, 6.0F));
        PartDefinition LeftFlipper = Body.addOrReplaceChild("LeftFlipper", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -1.0F, -4.5F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -2.5F, -0.25F, 0.0F, 0.0F, -0.1745F));
        PartDefinition LeftFlipper2 = LeftFlipper.addOrReplaceChild("LeftFlipper2", CubeListBuilder.create().texOffs(27, 3).addBox(0.0F, -1.0F, -4.25F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 13).addBox(0.0F, -0.5F, -4.25F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 23).addBox(2.0F, -1.0F, -4.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(48, 5).addBox(5.0F, -1.0F, -3.85F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(22, 16).addBox(7.0F, -1.0F, -3.7F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(22, 26).addBox(8.0F, -1.0F, -3.55F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(35, 18).addBox(9.0F, -1.0F, -3.4F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
        PartDefinition RightFlipper = Body.addOrReplaceChild("RightFlipper", CubeListBuilder.create().texOffs(0, 1).mirror().addBox(-4.0F, -1.0F, -4.5F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, -2.5F, -0.25F, 0.0F, 0.0F, 0.1745F));
        PartDefinition RightFlipper2 = RightFlipper.addOrReplaceChild("RightFlipper2", CubeListBuilder.create().texOffs(27, 3).mirror().addBox(-2.0F, -1.0F, -4.25F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 13).mirror().addBox(-2.0F, -0.5F, -4.25F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 23).mirror().addBox(-5.0F, -1.0F, -4.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(48, 5).mirror().addBox(-7.0F, -1.0F, -3.85F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(22, 16).mirror().addBox(-8.0F, -1.0F, -3.7F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(22, 26).mirror().addBox(-9.0F, -1.0F, -3.55F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(35, 18).mirror().addBox(-10.0F, -1.0F, -3.4F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(30, 33).addBox(-3.0F, -1.25F, -1.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)).texOffs(45, 34).addBox(-3.0F, 0.25F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.25F, -5.75F));
        PartDefinition eyes = Head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(22, 33).addBox(-2.6F, -0.25F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(15, 33).addBox(-2.6F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.01F)).texOffs(8, 33).addBox(1.6F, -0.25F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 33).addBox(1.6F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0131F, -0.8491F, 0.1309F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

}
