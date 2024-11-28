package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.LionModel;
import com.tristankechlo.livingthings.client.renderer.state.LionRenderState;
import com.tristankechlo.livingthings.entity.LionEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class LionRenderer extends AgeableMobRenderer<LionEntity, LionRenderState, LionModel<LionRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("lion/lion.png");
    protected static final ResourceLocation TEXTURE_WHITE = LivingThings.getEntityTexture("lion/lion_white.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.6F);

    public LionRenderer(Context context) {
        super(context, LionModel::new, ModelLayer.LION, ModelLayer.LION_BABY, 1F);
    }

    @Override
    public LionRenderState createRenderState() {
        return new LionRenderState();
    }

    @Override
    public void extractRenderState(LionEntity lion, LionRenderState state, float $$2) {
        super.extractRenderState(lion, state, $$2);
        state.fromEntity(lion);
    }

    @Override
    public ResourceLocation getTextureLocation(LionRenderState entity) {
        if (entity.variant != 0) {
            return TEXTURE_WHITE;
        }
        return TEXTURE;
    }

}
