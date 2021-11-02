package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import com.tristankechlo.livingthings.client.model.entity.CrabModel;
import com.tristankechlo.livingthings.client.model.entity.ElephantModel;
import com.tristankechlo.livingthings.client.model.entity.FlamingoModel;
import com.tristankechlo.livingthings.client.model.entity.GiraffeModel;
import com.tristankechlo.livingthings.client.model.entity.KoalaModel;
import com.tristankechlo.livingthings.client.model.entity.LionModel;
import com.tristankechlo.livingthings.client.model.entity.MantarayModel;
import com.tristankechlo.livingthings.client.model.entity.MonkeyModel;
import com.tristankechlo.livingthings.client.model.entity.MonkeySittingModel;
import com.tristankechlo.livingthings.client.model.entity.NetherKnightModel;
import com.tristankechlo.livingthings.client.model.entity.OstrichModel;
import com.tristankechlo.livingthings.client.model.entity.OwlModel;
import com.tristankechlo.livingthings.client.model.entity.PenguinModel;
import com.tristankechlo.livingthings.client.model.entity.RaccoonModel;
import com.tristankechlo.livingthings.client.model.entity.SeahorseModel;
import com.tristankechlo.livingthings.client.model.entity.SharkModel;
import com.tristankechlo.livingthings.client.model.entity.ShroomieModel;
import com.tristankechlo.livingthings.client.model.entity.SnailModel;
import com.tristankechlo.livingthings.client.renderer.AncientBlazeRenderer;
import com.tristankechlo.livingthings.client.renderer.CrabRenderer;
import com.tristankechlo.livingthings.client.renderer.ElephantRenderer;
import com.tristankechlo.livingthings.client.renderer.FlamingoRenderer;
import com.tristankechlo.livingthings.client.renderer.GiraffeRenderer;
import com.tristankechlo.livingthings.client.renderer.KoalaRenderer;
import com.tristankechlo.livingthings.client.renderer.LionRenderer;
import com.tristankechlo.livingthings.client.renderer.MantarayRenderer;
import com.tristankechlo.livingthings.client.renderer.MonkeyRenderer;
import com.tristankechlo.livingthings.client.renderer.NetherKnightRenderer;
import com.tristankechlo.livingthings.client.renderer.OstrichRenderer;
import com.tristankechlo.livingthings.client.renderer.OwlRenderer;
import com.tristankechlo.livingthings.client.renderer.PenguinRenderer;
import com.tristankechlo.livingthings.client.renderer.RaccoonRenderer;
import com.tristankechlo.livingthings.client.renderer.SeahorseRenderer;
import com.tristankechlo.livingthings.client.renderer.SharkRenderer;
import com.tristankechlo.livingthings.client.renderer.ShroomieRenderer;
import com.tristankechlo.livingthings.client.renderer.SnailRenderer;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ClientEvents {
	
	public static AncientArmorModel ANCIENT_ARMOR_MODEL = null;

	@SubscribeEvent
	private void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.GIRAFFE, GiraffeModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.LION, LionModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.SHARK, SharkModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.OSTRICH, OstrichModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.FLAMINGO, FlamingoModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.CRAB, CrabModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.MANTARAY, MantarayModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.RACCOON, RaccoonModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.OWL, OwlModel::createBodyLayer);
		// ancient blaze
		event.registerLayerDefinition(ModelLayer.KOALA, KoalaModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.SNAIL, SnailModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.MONKEY, MonkeyModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.MONKEY_SITTING, MonkeySittingModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.NETHER_KNIGHT, NetherKnightModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.SHROOMIE, ShroomieModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.SEAHORSE, SeahorseModel::createBodyLayer);
		event.registerLayerDefinition(ModelLayer.ANCIENT_ARMOR, AncientArmorModel::createBodyLayer);
	}

	@SubscribeEvent
	private void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntityTypes.ELEPHANT.get(), ElephantRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.GIRAFFE.get(), GiraffeRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.LION.get(), LionRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SHARK.get(), SharkRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.OSTRICH.get(), OstrichRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.FLAMINGO.get(), FlamingoRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.CRAB.get(), CrabRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.MANTARAY.get(), MantarayRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.RACCOON.get(), RaccoonRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.OWL.get(), OwlRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.ANCIENT_BLAZE.get(), AncientBlazeRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.KOALA.get(), KoalaRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SNAIL.get(), SnailRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.MONKEY.get(), MonkeyRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.NETHER_KNIGHT.get(), NetherKnightRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SHROOMIE.get(), ShroomieRenderer::new);
		event.registerEntityRenderer(ModEntityTypes.SEAHORSE.get(), SeahorseRenderer::new);
	}
	
	@SubscribeEvent
	private void onAddLayers(EntityRenderersEvent.AddLayers event) {
		ANCIENT_ARMOR_MODEL = new AncientArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayer.ANCIENT_ARMOR));
	}

}
