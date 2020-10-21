package com.tristankechlo.livingthings.client.renderer;

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
	
	protected static final ResourceLocation NORMAL = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/giraffe/giraffe_entity.png");
	protected static final ResourceLocation NORMAL_2 = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/giraffe/giraffe_entity_2.png");
	protected static final ResourceLocation ALBINO = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/giraffe/giraffe_entity_albino.png");

	public GiraffeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GiraffeModel<>(), 0.8F);
	}

	@Override
	public ResourceLocation getEntityTexture(GiraffeEntity entity) {
		byte variant = entity.getVariant();
		if(variant == 15) {
			return ALBINO;
		} else if (variant == 1) {
			return NORMAL_2;
		}
		return NORMAL;
	}

}
