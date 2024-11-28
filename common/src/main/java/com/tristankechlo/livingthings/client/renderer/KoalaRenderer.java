package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.KoalaModel;
import com.tristankechlo.livingthings.entity.KoalaEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class KoalaRenderer extends AgeableMobRenderer<KoalaEntity, LivingEntityRenderState, KoalaModel<LivingEntityRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("koala/koala.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);

    public KoalaRenderer(EntityRendererProvider.Context context) {
        super(context, KoalaModel::new, ModelLayer.KOALA, ModelLayer.KOALA_BABY, 0.4F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public void extractRenderState(KoalaEntity entity, LivingEntityRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState entity) {
        return TEXTURE;
    }

}
