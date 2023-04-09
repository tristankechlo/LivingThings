package com.tristankechlo.livingthings.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;


public class SimpleEntityRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    private final ResourceLocation texture;

    public SimpleEntityRenderer(Context context, float shadowRadius, ResourceLocation texture, ModelLayerLocation mll, ModelSupplier<T> ms) {
        super(context, (M) ms.getModel(context.bakeLayer(mll)), shadowRadius);
        this.texture = texture;
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return texture;
    }

    @FunctionalInterface
    public interface ModelSupplier<T extends Mob> {
        EntityModel<T> getModel(ModelPart root);
    }

}
