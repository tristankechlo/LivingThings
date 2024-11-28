package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.init.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class AncientArmorLayer<S extends HumanoidRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M> {

    private static final ResourceLocation ANCIENT_ARMOR = ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, "textures/models/armor/ancient_layer_1.png");
    private final AncientArmorModel model;

    public AncientArmorLayer(RenderLayerParent<S, M> parent, AncientArmorModel model) {
        super(parent);
        this.model = model;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int var3, HumanoidRenderState state, float v, float v1) {
        renderHelmet(poseStack, buffer, state, var3);
    }

    private void renderHelmet(PoseStack poseStack, MultiBufferSource buffer, HumanoidRenderState state, int var3) {
        if (!state.headItem.is(ModItems.ANCIENT_HELMET.get())) {
            return;
        }
        this.getParentModel().copyPropertiesTo((HumanoidModel<S>) model);
        this.model.setAllVisible(false);
        this.model.head.visible = true;
        this.model.hat.visible = true;
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(ANCIENT_ARMOR), false);
        this.model.renderToBuffer(poseStack, vertexConsumer, var3, OverlayTexture.NO_OVERLAY);

    }

}
