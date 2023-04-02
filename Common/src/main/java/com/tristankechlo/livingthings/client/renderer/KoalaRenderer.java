package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.KoalaModel;
import com.tristankechlo.livingthings.entity.KoalaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KoalaRenderer extends MobRenderer<KoalaEntity, KoalaModel<KoalaEntity>> {

    protected static final ResourceLocation TEXTURE = ModelLayer.getEntityTexture("koala/koala.png");

    public KoalaRenderer(Context context) {
        super(context, new KoalaModel<>(context.bakeLayer(ModelLayer.KOALA)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(KoalaEntity entity) {
        return TEXTURE;
    }

}