package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.BabyEnderDragonEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BabyEnderDragonCollarLayer extends RenderLayer<BabyEnderDragonEntity, EntityModel<BabyEnderDragonEntity>> {

	private static final ResourceLocation COLLAR = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/baby_ender_dragon/baby_ender_dragon_collar.png");

	public BabyEnderDragonCollarLayer(
			RenderLayerParent<BabyEnderDragonEntity, EntityModel<BabyEnderDragonEntity>> renderLayerParent) {
		super(renderLayerParent);
	}

	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, BabyEnderDragonEntity entity,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {
		if (entity.isTame() && !entity.isInvisible()) {
			float[] afloat = entity.getCollarColor().getTextureDiffuseColors();
			renderColoredCutoutModel(this.getParentModel(), COLLAR, poseStack, buffer, packedLight, entity, afloat[0],
					afloat[1], afloat[2]);
		}
	}
}
