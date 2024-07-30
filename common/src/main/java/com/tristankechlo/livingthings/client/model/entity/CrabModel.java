package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.CrabEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CrabModel<T extends CrabEntity> extends AdvancedEntityModel<T> {

    private final ModelPart Body;
    private final ModelPart Leg1;
    private final ModelPart Leg2;
    private final ModelPart Leg3;
    private final ModelPart Leg4;
    private final ModelPart Leg5;
    private final ModelPart Leg6;
    private final ModelPart Leg7;
    private final ModelPart Leg8;
    private final ModelPart Shear1;
    private final ModelPart Shear2;

    public CrabModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Leg1 = Body.getChild("Leg1");
        this.Leg2 = Body.getChild("Leg2");
        this.Leg3 = Body.getChild("Leg3");
        this.Leg4 = Body.getChild("Leg4");
        this.Leg5 = Body.getChild("Leg5");
        this.Leg6 = Body.getChild("Leg6");
        this.Leg7 = Body.getChild("Leg7");
        this.Leg8 = Body.getChild("Leg8");
        this.Shear1 = Body.getChild("Shear1");
        this.Shear2 = Body.getChild("Shear2");
    }

    @Override
    public void setupAnim(CrabEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // set all legs to default {angle * (PI / 180)}
        this.Leg1.yRot = 0.174532925F;
        this.Leg1.zRot = 0.47996554375F;
        this.Leg2.yRot = 0.04363323125F;
        this.Leg2.zRot = 0.47996554375F;
        this.Leg3.yRot = -0.04363323125F;
        this.Leg3.zRot = 0.47996554375F;
        this.Leg4.yRot = -0.174532925F;
        this.Leg4.zRot = 0.47996554375F;
        this.Leg5.yRot = -2.967059725F;
        this.Leg5.zRot = -0.47996554375F;
        this.Leg6.yRot = -3.09795941875F;
        this.Leg6.zRot = -0.47996554375F;
        this.Leg7.yRot = 3.09795941875F;
        this.Leg7.zRot = -0.47996554375F;
        this.Leg8.yRot = 2.967059725F;
        this.Leg8.zRot = -0.47996554375F;

        // walking animation for legs
        float f1 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f2 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f4 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        float f5 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f6 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float) Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        this.Leg1.yRot += f1;
        this.Leg2.yRot += -f1;
        this.Leg3.yRot += f2;
        this.Leg4.yRot += -f2;
        this.Leg5.yRot += f3;
        this.Leg6.yRot += -f3;
        this.Leg7.yRot += f4;
        this.Leg8.yRot += -f4;
        this.Leg1.zRot += f5;
        this.Leg2.zRot += -f5;
        this.Leg3.zRot += f6;
        this.Leg4.zRot += -f6;
        this.Leg5.zRot += f7;
        this.Leg6.zRot += -f7;
        this.Leg7.zRot += f8;
        this.Leg8.zRot += -f8;

        // set shears default angles
        this.Shear1.xRot = -0.08726646259F;
        this.Shear1.yRot = -0.78539816339F;
        this.Shear2.xRot = -0.08726646259F;
        this.Shear2.yRot = 0.78539816339F;

        // shears walking animation
        this.Shear1.xRot += -(Mth.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2;
        this.Shear2.xRot += (Mth.cos(limbSwing * 1.3324F) * 0.75F * limbSwingAmount) / 2;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        if (this.young) {
            matrixStack.scale(0.6F, 0.6F, 0.6F);
            matrixStack.translate(0, 1, 0);
        }
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(24, 13).mirror().addBox(-1.7F, -2.5F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false).texOffs(28, 13).mirror().addBox(0.7F, -2.5F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 20.4F, 0.0F));
        PartDefinition Shear1 = Body.addOrReplaceChild("Shear1", CubeListBuilder.create().texOffs(0, 2).addBox(-0.5F, -0.5F, -2.05F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -0.3F, -2.5F, -0.0873F, -0.7854F, 0.0F));
        PartDefinition Shear1Arm = Shear1.addOrReplaceChild("Shear1Arm", CubeListBuilder.create().texOffs(6, 3).addBox(-0.4805F, -0.5046F, -0.9277F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.9091F, 0.0F, 0.5236F, 0.0F));
        PartDefinition Shear1UpperBottom = Shear1Arm.addOrReplaceChild("Shear1UpperBottom", CubeListBuilder.create(), PartPose.offsetAndRotation(0.102F, -0.0247F, -0.7323F, 0.0F, 0.2618F, 0.0F));
        PartDefinition Shear1Upper = Shear1UpperBottom.addOrReplaceChild("Shear1Upper", CubeListBuilder.create().texOffs(10, 2).addBox(-0.552F, -0.9267F, -2.0836F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 2).addBox(-0.552F, -1.2255F, -2.0836F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0031F, 0.0494F, -0.0074F, -0.0873F, 0.0F, 0.0F));
        PartDefinition Shear1Bottom = Shear1UpperBottom.addOrReplaceChild("Shear1Bottom", CubeListBuilder.create().texOffs(22, 3).addBox(-0.552F, -0.1753F, -1.8686F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 3).addBox(-0.552F, -0.1753F, -1.07F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));
        PartDefinition Shear2 = Body.addOrReplaceChild("Shear2", CubeListBuilder.create().texOffs(0, 5).addBox(-0.5F, -0.5F, -2.05F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -0.3F, -2.5F, -0.0873F, 0.7854F, 0.0F));
        PartDefinition Shear2Arm = Shear2.addOrReplaceChild("Shear2Arm", CubeListBuilder.create().texOffs(6, 6).addBox(-0.975F, -1.0F, -0.95F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3535F, 0.4994F, -1.6338F, 0.0F, -0.5236F, 0.0F));
        PartDefinition Shear2UpperBottom = Shear2Arm.addOrReplaceChild("Shear2UpperBottom", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.4926F, -0.4745F, -0.6809F, 0.0F, -0.2618F, 0.0F));
        PartDefinition Shear2Upper = Shear2UpperBottom.addOrReplaceChild("Shear2Upper", CubeListBuilder.create().texOffs(10, 5).addBox(-0.552F, -1.0017F, -2.2336F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 5).addBox(-0.552F, -1.3005F, -2.2336F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0031F, 0.0537F, -0.0074F, -0.0873F, 0.0F, 0.0F));
        PartDefinition Shear2Bottom = Shear2UpperBottom.addOrReplaceChild("Shear2Bottom", CubeListBuilder.create().texOffs(22, 6).addBox(-0.552F, -0.2503F, -2.0186F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 6).addBox(-0.552F, -0.2503F, -1.22F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0044F, 0.0F, 0.1309F, 0.0F, 0.0F));
        PartDefinition Leg1 = Body.addOrReplaceChild("Leg1", CubeListBuilder.create().texOffs(18, 9).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.15F, 0.6F, -1.825F, 0.0F, 0.1745F, 0.48F));
        PartDefinition Leg11 = Leg1.addOrReplaceChild("Leg11", CubeListBuilder.create().texOffs(26, 9).addBox(-0.2F, -0.45F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.95F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        PartDefinition Leg2 = Body.addOrReplaceChild("Leg2", CubeListBuilder.create().texOffs(18, 9).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 0.6F, -0.55F, 0.0F, 0.0436F, 0.48F));
        PartDefinition Leg22 = Leg2.addOrReplaceChild("Leg22", CubeListBuilder.create().texOffs(26, 9).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0512F, 0.05F, 0.0066F, 0.0F, 0.0F, 0.5236F));
        PartDefinition Leg3 = Body.addOrReplaceChild("Leg3", CubeListBuilder.create().texOffs(18, 9).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 0.6F, 0.725F, 0.0F, -0.0436F, 0.48F));
        PartDefinition Leg33 = Leg3.addOrReplaceChild("Leg33", CubeListBuilder.create().texOffs(26, 9).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0262F, 0.05F, 0.0066F, 0.0F, 0.0F, 0.5236F));
        PartDefinition Leg4 = Body.addOrReplaceChild("Leg4", CubeListBuilder.create().texOffs(18, 9).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.175F, 0.6F, 2.0F, 0.0F, -0.1745F, 0.48F));
        PartDefinition Leg44 = Leg4.addOrReplaceChild("Leg44", CubeListBuilder.create().texOffs(26, 9).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0262F, 0.05F, 0.0066F, 0.0F, 0.0F, 0.5236F));
        PartDefinition Leg5 = Body.addOrReplaceChild("Leg5", CubeListBuilder.create().texOffs(18, 11).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2F, 0.525F, 2.0F, 0.0F, -2.9671F, -0.48F));
        PartDefinition Leg55 = Leg5.addOrReplaceChild("Leg55", CubeListBuilder.create().texOffs(26, 11).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0262F, 0.05F, 0.0066F, 0.0F, 0.0F, 0.5236F));
        PartDefinition Leg6 = Body.addOrReplaceChild("Leg6", CubeListBuilder.create().texOffs(18, 11).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.325F, 0.6F, 0.75F, 0.0F, -3.098F, -0.48F));
        PartDefinition Leg66 = Leg6.addOrReplaceChild("Leg66", CubeListBuilder.create().texOffs(26, 11).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0262F, 0.05F, 0.0066F, 0.0F, 0.0F, 0.5236F));
        PartDefinition Leg7 = Body.addOrReplaceChild("Leg7", CubeListBuilder.create().texOffs(18, 11).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.275F, 0.6F, -0.675F, 0.0F, 3.098F, -0.48F));
        PartDefinition Leg77 = Leg7.addOrReplaceChild("Leg77", CubeListBuilder.create().texOffs(26, 11).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0262F, 0.05F, 0.0066F, 0.0F, 0.0F, 0.5236F));
        PartDefinition Leg8 = Body.addOrReplaceChild("Leg8", CubeListBuilder.create().texOffs(18, 11).addBox(0.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.175F, 0.6F, -2.025F, 0.0F, 2.9671F, -0.48F));
        PartDefinition Leg88 = Leg8.addOrReplaceChild("Leg88", CubeListBuilder.create().texOffs(26, 11).addBox(-0.3012F, -0.45F, -0.5066F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0262F, 0.05F, 0.0066F, 0.0F, 0.0F, 0.5236F));
        return LayerDefinition.create(meshdefinition, 32, 16);
    }

}
