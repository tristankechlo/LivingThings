package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.client.renderer.PenguinRenderer;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;

public class LivingThingsClient {

    // TODO fill with content
    public static <E extends Entity> void registerRenderers(BiConsumer<EntityType<?>, EntityRendererProvider<E>> consumer) {
        consumer.accept(ModEntityTypes.PENGUIN.get(), (c) -> (EntityRenderer<E, ?>) new PenguinRenderer(c));

    }

}
