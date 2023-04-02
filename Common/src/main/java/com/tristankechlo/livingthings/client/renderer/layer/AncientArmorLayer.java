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
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class AncientArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {

    private static final ResourceLocation ANCIENT_ARMOR = new ResourceLocation(LivingThings.MOD_ID, "textures/models/armor/ancient_layer_1.png");
    private final AncientArmorModel model;

    public AncientArmorLayer(RenderLayerParent<T, M> parent, AncientArmorModel model) {
        super(parent);
        this.model = model;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int var3, T entity, float var5, float var6, float var7, float var8, float var9, float var10) {
        renderHelmet(poseStack, buffer, entity, var3);
    }

    private void renderHelmet(PoseStack poseStack, MultiBufferSource buffer, T entity, int var3) {
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (!itemstack.is(ModItems.ANCIENT_HELMET.get())) {
            return;
        }
        this.getParentModel().copyPropertiesTo((HumanoidModel<T>) model);
        this.model.setAllVisible(false);
        this.model.head.visible = true;
        this.model.hat.visible = true;
        this.model.Head.visible = true;
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(buffer, RenderType.armorCutoutNoCull(ANCIENT_ARMOR), false, false);
        this.model.renderToBuffer(poseStack, vertexConsumer, var3, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

    }

}
