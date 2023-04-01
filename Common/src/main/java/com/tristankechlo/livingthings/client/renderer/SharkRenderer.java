package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SharkModel;
import com.tristankechlo.livingthings.entity.SharkEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SharkRenderer extends MobRenderer<SharkEntity, SharkModel<SharkEntity>> {

    protected static final ResourceLocation TEXTURE = ModelLayer.getEntityTexture("shark/shark.png");

    public SharkRenderer(Context context) {
        super(context, new SharkModel<>(context.bakeLayer(ModelLayer.SHARK)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(SharkEntity entity) {
        return TEXTURE;
    }

}
