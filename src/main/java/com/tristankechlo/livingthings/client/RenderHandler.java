package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.client.renderer.CrabRenderer;
import com.tristankechlo.livingthings.client.renderer.ElephantRenderer;
import com.tristankechlo.livingthings.client.renderer.FlamingoRenderer;
import com.tristankechlo.livingthings.client.renderer.GiraffeRenderer;
import com.tristankechlo.livingthings.client.renderer.LionRenderer;
import com.tristankechlo.livingthings.client.renderer.MantarayRenderer;
import com.tristankechlo.livingthings.client.renderer.OstrichRenderer;
import com.tristankechlo.livingthings.client.renderer.PenguinRenderer;
import com.tristankechlo.livingthings.client.renderer.RaccoonRenderer;
import com.tristankechlo.livingthings.client.renderer.SharkRenderer;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ELEPHANT_ENTITY, ElephantRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GIRAFFE_ENTITY, GiraffeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LION_ENTITY, LionRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SHARK_ENTIY, SharkRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PENGUIN_ENTITY, PenguinRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.OSTRICH_ENTIY, OstrichRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FLAMINGO_ENTIY, FlamingoRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CRAB_ENTITY, CrabRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MANTARAY_ENTITY, MantarayRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RACCOON_ENTITY, RaccoonRenderer::new);
	}
}
