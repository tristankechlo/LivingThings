package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.BabyEnderDragonModel;
import com.tristankechlo.livingthings.client.model.entity.BabyEnderDragonSittingModel;
import com.tristankechlo.livingthings.client.renderer.layer.BabyEnderDragonCollarLayer;
import com.tristankechlo.livingthings.entity.BabyEnderDragonEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BabyEnderDragonRenderer extends MobRenderer<BabyEnderDragonEntity, EntityModel<BabyEnderDragonEntity>> {

    private static final ResourceLocation TEXTURE = LivingThingsClient.getEntityTexture("baby_ender_dragon/baby_ender_dragon.png");
    private final BabyEnderDragonModel modelNormal;
    private final BabyEnderDragonSittingModel modelSitting;

    public BabyEnderDragonRenderer(Context context) {
        super(context, new BabyEnderDragonModel(context.bakeLayer(ModelLayer.BABY_ENDER_DRAGON)), 0.5F);
        this.modelSitting = new BabyEnderDragonSittingModel(context.bakeLayer(ModelLayer.BABY_ENDER_DRAGON_SITTING));
        this.modelNormal = new BabyEnderDragonModel(context.bakeLayer(ModelLayer.BABY_ENDER_DRAGON));
        this.addLayer(new BabyEnderDragonCollarLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(BabyEnderDragonEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BabyEnderDragonEntity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entity.isTame() && entity.isInSittingPose() && !entity.isFlying()) {
            this.model = this.modelSitting;
        } else {
            this.model = this.modelNormal;
        }
        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
