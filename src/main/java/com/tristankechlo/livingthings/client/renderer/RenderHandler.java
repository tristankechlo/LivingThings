package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.renderer.entity.ElephantRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.GiraffeRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.LionRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.SharkRenderer;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ELEPHANT_ENTITY, ElephantRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GIRAFFE_ENTITY, GiraffeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LION_ENTITY, LionRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SHARK_ENTIY, SharkRenderer::new);
	}
}
