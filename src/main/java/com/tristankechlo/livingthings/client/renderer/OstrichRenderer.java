package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.OstrichModel;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OstrichRenderer extends MobRenderer<OstrichEntity, OstrichModel<OstrichEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/ostrich.png");

	public OstrichRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new OstrichModel<>(), 0.45F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(OstrichEntity entity) {
		return TEXTURE;
	}

}
