package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.RaccoonModel;
import com.tristankechlo.livingthings.entities.RaccoonEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaccoonRenderer extends MobRenderer<RaccoonEntity, RaccoonModel<RaccoonEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/raccoon.png");

	public RaccoonRenderer(Context context) {
		super(context, new RaccoonModel<>(context.bakeLayer(ModelLayer.RACCOON)), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(RaccoonEntity entity) {
		return TEXTURE;
	}

}
