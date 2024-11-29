package com.tristankechlo.livingthings.client.model.entity;

import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.client.renderer.state.OwlRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class OwlModel<T extends OwlRenderState> extends AdvancedEntityModel<T> {

    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart Tail;
    private final ModelPart LeftWing;
    private final ModelPart RightWing;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;

    public OwlModel(ModelPart root) {
        super(root);
        this.Body = root.getChild("Body");
        this.Head = Body.getChild("Head");
        this.Tail = Body.getChild("Tail");
        this.LeftWing = Body.getChild("LeftWing");
        this.RightWing = Body.getChild("RightWing");
        this.LeftLeg = Body.getChild("LeftLeg");
        this.RightLeg = Body.getChild("RightLeg");
    }

    @Override
    public void animate(T state, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.setRotationAngles(state, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    public void setRotationAngles(OwlRenderState state, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.Head.xRot = -0.174532F + headPitch * ((float) Math.PI / 180F);
        this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.Head.zRot = 0.0F;
        this.Head.x = 0.0F;
        this.Body.x = 0.0F;
        this.Tail.x = 0.0F;
        this.RightWing.x = -3.5F;
        this.LeftWing.x = 3.5F;
        switch (state.pose) {
            case SITTING:
                break;
            case STANDING:
                this.LeftLeg.xRot += Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
                this.RightLeg.xRot += Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
                break;
            case FALL_FLYING:
            default:
                // TODO animate flapping wings
                this.Tail.xRot = -0.959931F + Mth.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
                this.LeftWing.zRot = -0.1309F - state.flapAngle;
                this.RightWing.zRot = 0.1309F + state.flapAngle;
                break;
        }
    }

    public void setLivingAnimations(OwlRenderState state) {
        this.Body.xRot = 0.174532F;

        this.LeftWing.xRot = 0F;
        this.LeftWing.yRot = 0F;
        this.LeftWing.zRot = -0.130899F;

        this.RightWing.xRot = 0F;
        this.RightWing.yRot = 0F;
        this.RightWing.zRot = 0.130899F;

        this.LeftLeg.xRot = -0.261799F;
        this.LeftLeg.y = -1.9107F;
        this.LeftLeg.zRot = 0F;

        this.RightLeg.xRot = -0.261799F;
        this.RightLeg.y = -1.9107F;
        this.RightLeg.zRot = 0F;

        switch (state.pose) {
            case SITTING:
                this.Body.xRot = 0F;
                this.LeftLeg.xRot = 0F;
                this.RightLeg.xRot = 0F;
                break;
            case FALL_FLYING:
                if (state.isMoving) {
                    this.LeftLeg.xRot += 0.6981317F;
                    this.RightLeg.xRot += 0.6981317F;
                }
                break;
            case STANDING:
            default:
                break;
        }
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 17).addBox(-3.5F, -9.025F, -3.5F, 7.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.5F, 1.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -8.5792F, -0.4056F, 0.0F, 0.0F, -0.1309F));
        PartDefinition RightWing = Body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(15, 1).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -8.5792F, -0.4056F, 0.0F, 0.0F, 0.1309F));
        PartDefinition LeftLeg = Body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(31, 1).addBox(-1.0F, 0.1224F, -0.5671F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.9107F, -1.5699F, -0.2618F, 0.0F, 0.0F));
        PartDefinition LeftFoot = LeftLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create().texOffs(40, 3).addBox(-1.0667F, -0.0681F, -1.058F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0667F, 3.0978F, 0.5107F, 0.0873F, 0.0F, 0.0F));
        PartDefinition RightToeLeftFoot = LeftFoot.addOrReplaceChild("RightToeLeftFoot", CubeListBuilder.create().texOffs(49, 4).addBox(-0.5F, -0.475F, -0.925F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5667F, 0.4082F, -1.0494F, 0.0F, 0.1745F, 0.0F));
        PartDefinition LeftToeLeftFoot = LeftFoot.addOrReplaceChild("LeftToeLeftFoot", CubeListBuilder.create().texOffs(54, 4).addBox(-0.5F, -0.5F, -0.925F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4333F, 0.4332F, -1.0494F, 0.0F, -0.1745F, 0.0F));
        PartDefinition RightLeg = Body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(31, 7).addBox(-1.0F, 0.1224F, -0.5671F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.9107F, -1.5699F, -0.2618F, 0.0F, 0.0F));
        PartDefinition RightFoot = RightLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create().texOffs(40, 9).addBox(-1.0667F, -0.0681F, -1.058F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0667F, 3.0978F, 0.5107F, 0.0873F, 0.0F, 0.0F));
        PartDefinition RightToeRightFoot = RightFoot.addOrReplaceChild("RightToeRightFoot", CubeListBuilder.create().texOffs(49, 10).addBox(-0.5F, -0.475F, -0.925F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5667F, 0.4082F, -1.0494F, 0.0F, 0.1745F, 0.0F));
        PartDefinition LeftToeRightFoot = RightFoot.addOrReplaceChild("LeftToeRightFoot", CubeListBuilder.create().texOffs(54, 10).addBox(-0.5F, -0.5F, -0.925F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4333F, 0.4332F, -1.0494F, 0.0F, -0.1745F, 0.0F));
        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(31, 13).addBox(-1.0F, -0.5186F, -0.2595F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.7652F, 1.3264F, -0.9599F, 0.0F, 0.0F));
        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(27, 20).addBox(-3.5F, -6.0F, -3.25F, 7.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5045F, -0.1394F, -0.1745F, 0.0F, 0.0F));
        PartDefinition Beak = Head.addOrReplaceChild("Beak", CubeListBuilder.create().texOffs(42, 14).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.25F, -2.7F, 0.4363F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

}
