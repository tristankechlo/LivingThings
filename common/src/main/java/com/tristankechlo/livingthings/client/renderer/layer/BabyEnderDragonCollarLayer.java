package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.renderer.state.BabyEnderDragonRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class BabyEnderDragonCollarLayer extends RenderLayer<BabyEnderDragonRenderState, EntityModel<BabyEnderDragonRenderState>> {

    private static final ResourceLocation COLLAR = LivingThings.getEntityTexture("baby_ender_dragon/baby_ender_dragon_collar.png");

    public BabyEnderDragonCollarLayer(RenderLayerParent<BabyEnderDragonRenderState, EntityModel<BabyEnderDragonRenderState>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, BabyEnderDragonRenderState state, float v, float v1) {
        if (state.isTame && !state.isInvisible) {
            int color = state.collarColor.getTextureDiffuseColor();
            renderColoredCutoutModel(this.getParentModel(), COLLAR, poseStack, buffer, packedLight, state, color);
        }
    }
}
