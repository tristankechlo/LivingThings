package com.tristankechlo.livingthings.client.model.entity;

import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.client.renderer.state.PeacockRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class PeacockModel<T extends PeacockRenderState> extends AdvancedEntityModel<T> {

    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart LeftWing;
    private final ModelPart RightWing;
    private final ModelPart Head;
    private final ModelPart[] tails;
    private final float[] xPos = new float[]{1.48353F, 1.481785F, 1.481785F, 1.480039F, 1.480039F, 1.478294F, 1.478294F};
    private final float[] zPos = new float[]{0.0F, 0.436332F, -0.436332F, 0.872665F, -0.872665F, 1.309F, -1.309F};

    public PeacockModel(ModelPart root) {
        super(root);
        ModelPart body = root.getChild("body");
        this.Head = body.getChild("neck");
        this.LeftLeg = body.getChild("legLeft");
        this.RightLeg = body.getChild("legRight");
        this.LeftWing = body.getChild("wingLeft");
        this.RightWing = body.getChild("wingRight");
        ModelPart tail = body.getChild("tail");
        tails = new ModelPart[]{
                tail.getChild("tail1"), tail.getChild("tail2"), tail.getChild("tail3"), tail.getChild("tail4"),
                tail.getChild("tail5"), tail.getChild("tail6"), tail.getChild("tail7")
        };
    }

    @Override
    protected void animate(T state, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1F * limbSwingAmount;
        this.RightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1F * limbSwingAmount;
        this.LeftWing.yRot = 0.0F;
        this.RightWing.yRot = 0.0F;
        for (ModelPart x : tails) {
            setRotationAngle(x, -0.436332F, 0.0F, 0.0F);
        }

        // flap wings
        if (state.inPanic) {
            this.LeftWing.yRot = -0.436332F - Mth.cos(ageInTicks) * 0.5F;
            this.RightWing.yRot = 0.436332F + Mth.cos(ageInTicks) * 0.5F;
        }

        //animate head when eating
        if (state.isDestroyingCrops) {
            this.Head.yRot = 0.0F;
            this.Head.xRot = 1.74533F + Mth.cos(ageInTicks) * 0.5F;
            this.LeftWing.yRot = -0.436332F - Mth.cos(ageInTicks) * 0.25F;
            this.RightWing.yRot = 0.436332F + Mth.cos(ageInTicks) * 0.25F;
        } else {
            this.defaultHeadMovement(Head, 0, 0, headPitch, netHeadYaw);
        }

        // move tail up
        if (state.isTailFluffed) {
            for (int i = 0; i < tails.length; i++) {
                setRotationAngle(tails[i], xPos[i], 0.0F, zPos[i]);
            }
        }

        for (ModelPart x : tails) {
            x.visible = !state.isBaby;
        }
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.5F, -5.0F, 7.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.5F, 0.0F));
        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(20, 17).addBox(-1.5F, -9.0F, -2.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -4.0F));
        PartDefinition crest = neck.addOrReplaceChild("crest", CubeListBuilder.create().texOffs(1, 3).addBox(0.0F, -3.0F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.5F));
        PartDefinition beak = neck.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(1, 1).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -2.0F));
        PartDefinition legLeft = body.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(35, 22).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 3.5F, 2.0F));
        PartDefinition legRight = body.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(35, 22).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 3.5F, 2.0F));
        PartDefinition wingLeft = body.addOrReplaceChild("wingLeft", CubeListBuilder.create().texOffs(1, 18).addBox(-1.0F, -2.5F, 0.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -1.0F, -3.0F));
        PartDefinition wingRight = body.addOrReplaceChild("wingRight", CubeListBuilder.create().texOffs(1, 18).addBox(0.0F, -2.5F, 0.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -1.0F, -3.0F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(12, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 5.0F, 1.4835F, 0.0F, 0.0F));
        PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(12, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 5.0F, 1.4818F, 0.0F, 0.4363F));
        PartDefinition tail3 = tail.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(12, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 5.0F, 1.4818F, 0.0F, -0.4363F));
        PartDefinition tail4 = tail.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(12, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 5.0F, 1.48F, 0.0F, 0.8727F));
        PartDefinition tail5 = tail.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(12, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 5.0F, 1.48F, 0.0F, -0.8727F));
        PartDefinition tail6 = tail.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(12, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 5.0F, 1.4783F, 0.0F, 1.309F));
        PartDefinition tail7 = tail.addOrReplaceChild("tail7", CubeListBuilder.create().texOffs(12, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 5.0F, 1.4783F, 0.0F, -1.309F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }
}
