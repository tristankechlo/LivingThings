package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.FlamingoModel;
import com.tristankechlo.livingthings.entity.FlamingoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FlamingoRenderer extends MobRenderer<FlamingoEntity, FlamingoModel<FlamingoEntity>> {

    protected static final ResourceLocation TEXTURE = ModelLayer.getEntityTexture("flamingo/flamingo.png");

    public FlamingoRenderer(Context context) {
        super(context, new FlamingoModel<>(context.bakeLayer(ModelLayer.FLAMINGO)), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(FlamingoEntity entity) {
        return TEXTURE;
    }

}
