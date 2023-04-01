package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.ElephantModel;
import com.tristankechlo.livingthings.client.model.entity.PenguinModel;
import com.tristankechlo.livingthings.client.renderer.ElephantRenderer;
import com.tristankechlo.livingthings.client.renderer.PenguinRenderer;
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
        event.registerEntityRenderer(ModEntityTypes.PENGUIN.get(), PenguinRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayer.ELEPHANT, ElephantModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
    }

}
