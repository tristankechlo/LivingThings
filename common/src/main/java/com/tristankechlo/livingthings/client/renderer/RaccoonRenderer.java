package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.RaccoonModel;
import com.tristankechlo.livingthings.entity.RaccoonEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class RaccoonRenderer extends AgeableMobRenderer<RaccoonEntity, LivingEntityRenderState, RaccoonModel<LivingEntityRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("raccoon/raccoon.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);

    public RaccoonRenderer(EntityRendererProvider.Context context) {
        super(context, RaccoonModel::new, ModelLayer.RACCOON, ModelLayer.RACCOON_BABY, 0.4F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public void extractRenderState(RaccoonEntity entity, LivingEntityRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState entity) {
        return TEXTURE;
    }

}
