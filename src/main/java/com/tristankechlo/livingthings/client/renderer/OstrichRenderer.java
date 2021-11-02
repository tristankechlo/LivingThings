package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.OstrichModel;
import com.tristankechlo.livingthings.entities.OstrichEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OstrichRenderer extends MobRenderer<OstrichEntity, OstrichModel<OstrichEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/ostrich.png");

	public OstrichRenderer(Context context) {
		super(context, new OstrichModel<>(context.bakeLayer(ModelLayer.OSTRICH)), 0.45F);
	}

	@Override
	public ResourceLocation getTextureLocation(OstrichEntity entity) {
		return TEXTURE;
	}

}
