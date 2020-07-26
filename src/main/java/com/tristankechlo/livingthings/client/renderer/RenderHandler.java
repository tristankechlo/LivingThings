package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.renderer.entity.ElephantRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.GiraffeRenderer;
import com.tristankechlo.livingthings.client.renderer.entity.LionRenderer;
import com.tristankechlo.livingthings.init.ModEntities;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ELEPHANT_ENTITY.get(), ElephantRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.GIRAFFE_ENTITY.get(), GiraffeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.LION_ENTITY.get(), LionRenderer::new);
	}
}
