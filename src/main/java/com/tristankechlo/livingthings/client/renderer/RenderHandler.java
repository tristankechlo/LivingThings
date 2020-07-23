package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.init.ModEntities;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ELEPHANT_ENTITY.get(), ElephantRenderer::new);
	}
}
