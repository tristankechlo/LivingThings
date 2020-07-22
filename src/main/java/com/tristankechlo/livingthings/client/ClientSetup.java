package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.renderer.ElephantRenderer;
import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ELEPHANT_ENTITY.get(), ElephantRenderer::new);
	}
/*
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(new EntityType[] { ModEntities.ELEPHANT_ENTITY.get() });
	}*/

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void imstuff(RegistryEvent.Register<EntityType<?>> event) {
		GlobalEntityTypeAttributes.put(ModEntities.ELEPHANT_ENTITY.get(), ElephantEntity.func_234200_m_().func_233813_a_());
	}
}
