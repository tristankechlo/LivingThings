package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.renderer.entity.ElephantRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.FlamingoRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.GiraffeRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.LionRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.OstrichRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.PenguinRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.SharkRenderer;
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
	}
}
