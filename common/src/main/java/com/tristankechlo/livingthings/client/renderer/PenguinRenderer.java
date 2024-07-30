package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.PenguinModel;
import com.tristankechlo.livingthings.entity.PenguinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PenguinRenderer extends MobRenderer<PenguinEntity, PenguinModel> {

    protected static final ResourceLocation TEXTURE = LivingThingsClient.getEntityTexture("penguin/penguin.png");
    protected static final ResourceLocation TEXTURE_CHILD = LivingThingsClient.getEntityTexture("penguin/penguin_baby.png");

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(ModelLayer.PENGUIN)), 0.45F);
    }

    @Override
    public ResourceLocation getTextureLocation(PenguinEntity entity) {
        return (entity.isBaby()) ? TEXTURE_CHILD : TEXTURE;
    }

}
