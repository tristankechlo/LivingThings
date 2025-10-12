package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.init.ModItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class AncientArmorLayer<S extends HumanoidRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M> {

    private static final ResourceLocation ANCIENT_ARMOR = ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, "textures/models/armor/ancient_layer_1.png");
    private final AncientArmorModel model;

    public AncientArmorLayer(RenderLayerParent<S, M> parent, EntityRendererProvider.Context context) {
        super(parent);
        this.model = new AncientArmorModel(context.bakeLayer(ModelLayer.ANCIENT_ARMOR));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, S state, float yRot, float xRot) {
        this.renderHelmet(poseStack, collector, state, packedLight);
    }

    private void renderHelmet(PoseStack poseStack, SubmitNodeCollector collector, HumanoidRenderState state, int packedLight) {
        if (!state.headEquipment.is(ModItems.ANCIENT_HELMET.get())) {
            return;
        }
        collector.submitModel(
                this.model,
                state,
                poseStack,
                RenderType.armorCutoutNoCull(ANCIENT_ARMOR),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                state.outlineColor,
                null
        );
    }

}
