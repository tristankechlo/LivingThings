package com.tristankechlo.livingthings.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.client.model.AdvancedEntityModel;
import com.tristankechlo.livingthings.entity.AncientBlazeEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import java.util.Arrays;

public class AncientBlazeModel<T extends AncientBlazeEntity> extends AdvancedEntityModel<T> {

    private static final int MAX_SHIELD_COUNT = 4;
    private static final int MAX_STICK_COUNT = 10;
    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart Shields;
    private final ModelPart Sticks;
    private final ModelPart[] shields; // 4
    private final ModelPart[] sticks; // 10

    public AncientBlazeModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = Body.getChild("Head");
        this.Shields = Body.getChild("Shields");
        this.shields = new ModelPart[MAX_SHIELD_COUNT];
        Arrays.setAll(this.shields, (number) -> {
            return Shields.getChild("shield_" + number);
        });
        this.Sticks = Body.getChild("Sticks");
        this.sticks = new ModelPart[MAX_STICK_COUNT];
        Arrays.setAll(this.sticks, (number) -> {
            return Sticks.getChild("stick_" + number);
        });
    }

    @Override
    public void setupAnim(AncientBlazeEntity blaze, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.Head.yRot = netHeadYaw * 0.017453F;
        this.Head.xRot = headPitch * 0.017453F;

        float f = 0.785398F + ageInTicks * -0.094247F;
        for (int i = 0; i < this.sticks.length; i++) {
            this.sticks[i].visible = (blaze.getShoots() > i);
            this.sticks[i].y = Mth.cos((i * 3F + ageInTicks) * 0.3F) - 1.0F;
            this.sticks[i].x = Mth.cos(f) * 9.0F;
            this.sticks[i].z = Mth.sin(f) * 9.0F;
            f++;
        }

        if (blaze.isPowered()) {
            this.Shields.yRot = 0.785398F;
            this.Head.y = -22.5F;

            for (int i = 0; i < this.shields.length; i++) {
                this.shields[i].x = Mth.cos(i * 1.570796F) * 6.0F;
                this.shields[i].z = Mth.sin(i * 1.570796F) * 6.0F;
                this.shields[i].xRot = 0F;
            }

        } else {
            this.Shields.yRot = -ageInTicks / 50;
            this.Head.y = -24.5F;

            for (int i = 0; i < this.shields.length; i++) {
                this.shields[i].x = Mth.cos(i * 1.570796F) * 7.5F;
                this.shields[i].z = Mth.sin(i * 1.570796F) * 7.5F;
                this.shields[i].xRot = -0.349065F;
            }

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
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(25, 24).addBox(-2.5F, -22.0F, -2.5F, 5.0F, 35.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));
        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.5F, 0.0F));
        PartDefinition Helmet = Head.addOrReplaceChild("Helmet", CubeListBuilder.create().texOffs(33, 0).addBox(3.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(9, 17).addBox(-3.5F, -8.5F, -4.5F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(9, 22).addBox(-2.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 22).addBox(0.5F, -9.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 22).addBox(-3.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(9, 25).addBox(1.5F, -10.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 25).addBox(0.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 25).addBox(-2.5F, -11.5F, -4.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(9, 28).addBox(-1.5F, -12.5F, -4.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(33, 13).addBox(1.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 13).addBox(-3.5F, -3.5F, -4.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 0).addBox(-4.5F, -9.5F, -4.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 33).addBox(-2.5F, -8.5F, 3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 36).addBox(-2.5F, -0.5F, 3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 13).addBox(2.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 25).addBox(-3.5F, -9.5F, 3.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(18, 28).addBox(-2.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(33, 19).addBox(-0.5F, -7.5F, 3.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(38, 19).addBox(-0.5F, -2.5F, 3.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 37).addBox(-1.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(47, 44).addBox(0.5F, -6.5F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(41, 24).addBox(1.5F, -4.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(52, 29).addBox(3.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(52, 21).addBox(3.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(52, 13).addBox(-4.5F, -8.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(46, 51).addBox(3.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 39).addBox(-4.5F, -3.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(47, 0).addBox(3.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(54, 0).addBox(-4.5F, -9.5F, 2.5F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(7, 40).addBox(-4.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 31).addBox(3.5F, -10.5F, 3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 34).addBox(3.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(18, 34).addBox(-4.5F, -5.5F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(46, 55).addBox(3.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(46, 60).addBox(-4.5F, -5.5F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(52, 37).addBox(-4.5F, -1.5F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(52, 45).addBox(3.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(57, 45).addBox(-4.5F, -13.5F, -4.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(53, 51).addBox(2.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(58, 51).addBox(-3.5F, -15.5F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition Crystal = Head.addOrReplaceChild("Crystal", CubeListBuilder.create().texOffs(25, 2).addBox(-1.5F, -11.5F, -4.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(27, 6).addBox(-3.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(27, 6).addBox(2.5F, -9.5F, -4.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition Shields = Body.addOrReplaceChild("Shields", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        CubeListBuilder shieldForm = CubeListBuilder.create().texOffs(0, 43).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 19.0F, 2.0F, new CubeDeformation(0.0F));
        for (int i = 0; i < MAX_SHIELD_COUNT; i++) {
            Shields.addOrReplaceChild("shield_" + i, shieldForm, PartPose.offsetAndRotation(i * 7.5F, 0F, i * 7.5F, -0.3491F, ((-1) ^ i) * 1.570796F, 0.0F));
        }
        PartDefinition Sticks = Body.addOrReplaceChild("Sticks", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));
        CubeListBuilder stickForm = CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F));
        for (int i = 0; i < MAX_STICK_COUNT; i++) {
            Sticks.addOrReplaceChild("stick_" + i, stickForm, PartPose.offset(i * 4.0F, 0F, i * 4.0F));
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

}
