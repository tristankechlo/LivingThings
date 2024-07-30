package com.tristankechlo.livingthings.client.model.armor;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class AncientArmorModel extends HumanoidModel<LivingEntity> {

    public final ModelPart Head;

    public AncientArmorModel(ModelPart root) {
        super(root);
        this.Head = root.getChild("Head");
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        this.Head.copyFrom(this.head);
        return ImmutableList.of(this.head, this.Head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of();
    }

    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(pose, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(1.0F), 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition Helmet = Head.addOrReplaceChild("Helmet", CubeListBuilder.create().texOffs(0, 32).addBox(3.5F, -8.9F, -4.5F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 32).addBox(-3.5F, -7.9F, -4.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 36).addBox(-2.5F, -8.9F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(38, 36).addBox(0.5F, -8.9F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 38).addBox(-3.5F, -9.9F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(38, 38).addBox(1.5F, -9.9F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 40).addBox(0.5F, -10.9F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(38, 40).addBox(-2.5F, -10.9F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(48, 32).addBox(-1.5F, -11.9F, -4.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(56, 32).addBox(1.5F, -1.9F, -4.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(56, 37).addBox(-3.5F, -1.9F, -4.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(6, 32).addBox(-4.5F, -8.9F, -4.5F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(44, 36).addBox(-2.5F, -8.9F, 3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 38).addBox(-2.5F, -0.9F, 3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 32).addBox(2.5F, -9.9F, 3.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 32).addBox(-3.5F, -9.9F, 3.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 40).addBox(-2.5F, -4.9F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 42).addBox(-0.5F, -7.9F, 3.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(36, 42).addBox(-0.5F, -2.9F, 3.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 44).addBox(-1.5F, -6.9F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 44).addBox(0.5F, -6.9F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(48, 40).addBox(1.5F, -4.9F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 50).addBox(3.5F, -1.9F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(12, 50).addBox(3.5F, -8.9F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(24, 50).addBox(-4.5F, -8.9F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(8, 44).addBox(3.5F, -3.9F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(14, 44).addBox(-4.5F, -3.9F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(12, 32).addBox(3.5F, -9.9F, 2.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(18, 32).addBox(-4.5F, -9.9F, 2.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(52, 40).addBox(-4.5F, -10.9F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 42).addBox(3.5F, -10.9F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(50, 43).addBox(3.5F, -9.9F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(54, 43).addBox(-4.5F, -9.9F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 44).addBox(3.5F, -5.9F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 44).addBox(-4.5F, -5.9F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 50).addBox(3.5F, -5.9F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(20, 50).addBox(-4.5F, -5.9F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(36, 50).addBox(-4.5F, -1.9F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(32, 46).addBox(3.5F, -12.9F, -4.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(36, 46).addBox(-4.5F, -12.9F, -4.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 45).addBox(2.5F, -14.9F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(45, 43).addBox(-3.5F, -14.9F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition Crystal = Head.addOrReplaceChild("Crystal", CubeListBuilder.create().texOffs(50, 54).addBox(-1.5F, -10.9F, -4.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(56, 54).addBox(-3.5F, -8.9F, -4.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(56, 55).addBox(2.5F, -8.9F, -4.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setAllVisible(boolean $$0) {
        super.setAllVisible($$0);
        this.Head.visible = $$0;
    }
}
