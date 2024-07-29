package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.entity.BabyEnderDragonEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class BabyEnderDragonCollarLayer extends RenderLayer<BabyEnderDragonEntity, EntityModel<BabyEnderDragonEntity>> {

    private static final ResourceLocation COLLAR = LivingThingsClient.getEntityTexture("baby_ender_dragon/baby_ender_dragon_collar.png");

    public BabyEnderDragonCollarLayer(RenderLayerParent<BabyEnderDragonEntity, EntityModel<BabyEnderDragonEntity>> renderLayerParent) {
        super(renderLayerParent);
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, BabyEnderDragonEntity entity,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isTame() && !entity.isInvisible()) {
            int color = entity.getCollarColor().getTextureDiffuseColor();
            renderColoredCutoutModel(this.getParentModel(), COLLAR, poseStack, buffer, packedLight, entity, color);
        }
    }
}
