package com.tristankechlo.livingthings.client.renderer.entity;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.GiraffeModel;
import com.tristankechlo.livingthings.entities.GiraffeEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiraffeRenderer  extends MobRenderer<GiraffeEntity, GiraffeModel<GiraffeEntity>>{
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/giraffe/giraffe_entity.png");

	public GiraffeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GiraffeModel<>(), 0.8F);
	}

	@Override
	public ResourceLocation getEntityTexture(GiraffeEntity entity) {
		return TEXTURE;
	}

}
