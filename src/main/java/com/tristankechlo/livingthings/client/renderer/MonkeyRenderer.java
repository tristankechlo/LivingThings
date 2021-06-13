package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.MonkeyModel;
import com.tristankechlo.livingthings.client.model.entity.MonkeySittingModel;
import com.tristankechlo.livingthings.entities.MonkeyEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonkeyRenderer extends MobRenderer<MonkeyEntity, EntityModel<MonkeyEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/monkey.png");
	private final MonkeyModel<MonkeyEntity> modelNormal = new MonkeyModel<>();
	private final MonkeySittingModel<MonkeyEntity> modelSitting = new MonkeySittingModel<>();
	private byte lastAction;

	public MonkeyRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MonkeyModel<>(), 0.35F);
	}

	@Override
	public ResourceLocation getTextureLocation(MonkeyEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(MonkeyEntity monkey, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {

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