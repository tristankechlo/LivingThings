package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.ElephantModel;
import com.tristankechlo.livingthings.entity.ElephantEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ElephantRenderer extends MobRenderer<ElephantEntity, ElephantModel<ElephantEntity>> {

    protected static final ResourceLocation TEXTURE = ModelLayer.getEntityTexture("elephant/elephant.png");

    public ElephantRenderer(Context context) {
        super(context, new ElephantModel<>(context.bakeLayer(ModelLayer.ELEPHANT)), 1.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(ElephantEntity entity) {
        return TEXTURE;
    }

}
