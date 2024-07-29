package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.LionEntity;
import com.tristankechlo.livingthings.entity.misc.IGenderedMob;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class LionModel<T extends LionEntity> extends AdvancedEntityModel<T> {

    private final ModelPart Body;
    private final ModelPart Mane;
    private final ModelPart Head;
    private final ModelPart Legs;
    private final ModelPart FrontRightLeg;
    private final ModelPart FrontLeftLeg;
    private final ModelPart BackRightLeg;
    private final ModelPart BackLeftLeg;
    private final ModelPart Tail;

    public LionModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Mane = root.getChild("Mane");
        this.Head = Body.getChild("Head");
        this.Legs = Body.getChild("Legs");
        this.FrontRightLeg = Legs.getChild("FrontRightLeg");
        this.FrontLeftLeg = Legs.getChild("FrontLeftLeg");
        this.BackRightLeg = Legs.getChild("BackRightLeg");
        this.BackLeftLeg = Legs.getChild("BackLeftLeg");
        this.Tail = Body.getChild("Tail");
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, int color) {

        if (this.young) {
            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
            matrixStackIn.translate(0, 1, 0);
        } else {
            Mane.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        }
        Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.Head.xRot = headPitch * 0.0174532925F;
        this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;

        this.walk(FrontRightLeg, FrontLeftLeg, BackRightLeg, BackLeftLeg, limbSwing, limbSwingAmount);

        this.Tail.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
    }

    @Override
    public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        if (entityIn.getGender() == IGenderedMob.Gender.MALE) {
            this.Mane.visible = true;
        } else {
            this.Mane.visible = false;
        }
    }

    @SuppressWarnings("unused")

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 23).addBox(-6.0F, -4.0F, -14.0F, 12.0F, 12.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));
        PartDefinition Legs = Body.addOrReplaceChild("Legs", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));
        PartDefinition FrontLeftLeg = Legs.addOrReplaceChild("FrontLeftLeg", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -10.0F, -12.0F));
        PartDefinition FrontRightLeg = Legs.addOrReplaceChild("FrontRightLeg", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -10.0F, -12.0F));
        PartDefinition BackLeftLeg = Legs.addOrReplaceChild("BackLeftLeg", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -10.0F, 12.0F));
        PartDefinition BackRightLeg = Legs.addOrReplaceChild("BackRightLeg", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -10.0F, 12.0F));
        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(61, 21).addBox(-1.0F, -12.5F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(59, 11).addBox(-2.0F, -15.5F, -1.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 14.5F, -2.7489F, 0.0F, 0.0F));
        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(88, 44).addBox(-6.0F, -7.0F, -8.0F, 12.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(30, 1).addBox(-3.0F, -2.0F, -14.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -14.0F));
        PartDefinition Ears = Head.addOrReplaceChild("Ears", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, -1.0F));
        PartDefinition LeftEar = Ears.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(11, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition RightEar = Ears.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, -1.0F, 0.0F, 0.0F, -0.1745F));
        PartDefinition Mane = partdefinition.addOrReplaceChild("Mane", CubeListBuilder.create().texOffs(82, 0).mirror().addBox(-7.0F, -9.0F, -2.0F, 14.0F, 15.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 6.0F, -14.0F));
        return LayerDefinition.create(meshdefinition, 128, 64);
    }

}
