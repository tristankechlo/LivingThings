package com.tristankechlo.livingthings.client;

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
import com.tristankechlo.livingthings.client.renderer.SharkRenderer;
import com.tristankechlo.livingthings.client.renderer.ShroomieRenderer;
import com.tristankechlo.livingthings.client.renderer.SnailRenderer;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ELEPHANT_ENTITY.get(), ElephantRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GIRAFFE_ENTITY.get(), GiraffeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.LION_ENTITY.get(), LionRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SHARK_ENTITY.get(), SharkRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.PENGUIN_ENTITY.get(), PenguinRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.OSTRICH_ENTITY.get(), OstrichRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.FLAMINGO_ENTITY.get(), FlamingoRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CRAB_ENTITY.get(), CrabRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MANTARAY_ENTITY.get(), MantarayRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RACCOON_ENTITY.get(), RaccoonRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.OWL_ENTITY.get(), OwlRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ANCIENT_BLAZE_ENTITY.get(), AncientBlazeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.KOALA_ENTITY.get(), KoalaRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SNAIL_ENTITY.get(), SnailRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MONKEY_ENTITY.get(), MonkeyRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.NETHER_KNIGHT_ENTITY.get(), NetherKnightRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SHROOMIE_ENTITY.get(), ShroomieRenderer::new);
	}
}
