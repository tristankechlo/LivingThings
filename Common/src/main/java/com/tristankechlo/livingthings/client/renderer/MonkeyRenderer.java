package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.MonkeyModel;
import com.tristankechlo.livingthings.client.model.entity.MonkeySittingModel;
import com.tristankechlo.livingthings.entity.MonkeyEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MonkeyRenderer extends MobRenderer<MonkeyEntity, EntityModel<MonkeyEntity>> {

    private static final ResourceLocation TEXTURE = LivingThingsClient.getEntityTexture("monkey/monkey.png");
    private final MonkeyModel<MonkeyEntity> modelNormal;
    private final MonkeySittingModel<MonkeyEntity> modelSitting;
    private byte lastAction;

    public MonkeyRenderer(Context context) {
        super(context, new MonkeyModel<>(context.bakeLayer(ModelLayer.MONKEY)), 0.35F);
        this.modelNormal = new MonkeyModel<>(context.bakeLayer(ModelLayer.MONKEY));
        this.modelSitting = new MonkeySittingModel<>(context.bakeLayer(ModelLayer.MONKEY_SITTING));
    }

    @Override
    public ResourceLocation getTextureLocation(MonkeyEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(MonkeyEntity monkey, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        byte monkeyAction = (byte) ((monkey.isCrouching()) ? 1 : 0);
        if (monkeyAction != this.lastAction) {
            if (monkeyAction == 1) {
                this.model = this.modelSitting;
            } else {
                this.model = modelNormal;
            }
        }
        this.lastAction = monkeyAction;
        super.render(monkey, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}