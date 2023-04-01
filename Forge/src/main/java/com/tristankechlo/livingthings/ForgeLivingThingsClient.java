package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.*;
import com.tristankechlo.livingthings.client.renderer.*;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ForgeLivingThingsClient {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.ELEPHANT.get(), ElephantRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.GIRAFFE.get(), GiraffeRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.LION.get(), LionRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SHARK.get(), SharkRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.OSTRICH.get(), OstrichRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FLAMINGO.get(), FlamingoRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.CRAB.get(), CrabRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.GIRAFFE, GiraffeModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.LION, LionModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.SHARK, SharkModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.OSTRICH, OstrichModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.FLAMINGO, FlamingoModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.CRAB, CrabModel::createBodyLayer);
    }

}
