package com.tristankechlo.livingthings.client.model.entity;

import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class RaccoonModel<T extends LivingEntityRenderState> extends AdvancedEntityModel<T> {

    private final ModelPart Head;
    private final ModelPart Tail;
    private final ModelPart LegFrontRight;
    private final ModelPart LegFrontLeft;
    private final ModelPart LegBackRight;
    private final ModelPart LegBackLeft;

    public RaccoonModel(ModelPart root) {
        super(root);
        ModelPart body = root.getChild("Body");
        this.Head = body.getChild("Head");
        this.Tail = body.getChild("Tail");
        this.LegFrontRight = body.getChild("LegFrontRight");
        this.LegFrontLeft = body.getChild("LegFrontLeft");
        this.LegBackRight = body.getChild("LegBackRight");
        this.LegBackLeft = body.getChild("LegBackLeft");
    }

    @Override
    protected void animate(T state, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.Head.xRot = headPitch * 0.0174532925F;
        this.Head.yRot = (netHeadYaw / 3.75F) * 0.0174532925F;
        this.walk(LegFrontRight, LegFrontLeft, LegBackRight, LegBackLeft, limbSwing, limbSwingAmount);
        this.Tail.yRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;

    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 1).addBox(-3.0F, -11.0F, -5.5F, 6.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition LegFrontLeft = Body.addOrReplaceChild("LegFrontLeft", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -5.0F, -3.5F));
        PartDefinition LegBackLeft = Body.addOrReplaceChild("LegBackLeft", CubeListBuilder.create().texOffs(9, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -5.0F, 3.5F));
        PartDefinition LegFrontRight = Body.addOrReplaceChild("LegFrontRight", CubeListBuilder.create().texOffs(18, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, -5.0F, -3.5F));
        PartDefinition LegBackRight = Body.addOrReplaceChild("LegBackRight", CubeListBuilder.create().texOffs(27, 25).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, -5.0F, 3.5F));
        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(36, 20).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.95F, 4.65F, -0.4363F, 0.0F, 0.0F));
        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(36, 7).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -5.5F));
        PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -1.75F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -3.0F, -2.0F));
        PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(7, 20).addBox(-1.0F, -1.75F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -3.0F, -1.75F));
        PartDefinition Mouth = Head.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(25, 4).addBox(-2.0F, -1.5F, -3.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.25F, -5.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

}
