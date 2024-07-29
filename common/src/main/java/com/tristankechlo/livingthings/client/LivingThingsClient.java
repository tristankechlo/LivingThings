package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.*;
import com.tristankechlo.livingthings.entity.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public final class LivingThingsClient {

    private static final ResourceLocation ELEPHANT_TEXTURE = getEntityTexture("elephant/elephant.png");
    private static final ResourceLocation FLAMINGO_TEXTURE = getEntityTexture("flamingo/flamingo.png");
    private static final ResourceLocation KOALA_TEXTURE = getEntityTexture("koala/koala.png");
    private static final ResourceLocation OSTRICH_TEXTURE = getEntityTexture("ostrich/ostrich.png");
    private static final ResourceLocation RACCOON_TEXTURE = getEntityTexture("raccoon/raccoon.png");
    private static final ResourceLocation SHARK_TEXTURE = getEntityTexture("shark/shark.png");
    private static final ResourceLocation PEACOCK_TEXTURE = getEntityTexture("peacock/peacock.png");

    public final static EntityRendererProvider<ElephantEntity> ELEPHANT_RENDERER = context -> new SimpleEntityRenderer<>(context, 1.2F, ELEPHANT_TEXTURE, ModelLayer.ELEPHANT, ElephantModel::new);
    public final static EntityRendererProvider<FlamingoEntity> FLAMINGO_RENDERER = context -> new SimpleEntityRenderer<>(context, 0.35F, FLAMINGO_TEXTURE, ModelLayer.FLAMINGO, FlamingoModel::new);
    public final static EntityRendererProvider<KoalaEntity> KOALA_RENDERER = context -> new SimpleEntityRenderer<>(context, 0.4F, KOALA_TEXTURE, ModelLayer.KOALA, KoalaModel::new);
    public final static EntityRendererProvider<OstrichEntity> OSTRICH_RENDERER = context -> new SimpleEntityRenderer<>(context, 0.45F, OSTRICH_TEXTURE, ModelLayer.OSTRICH, OstrichModel::new);
    public final static EntityRendererProvider<RaccoonEntity> RACCOON_RENDERER = context -> new SimpleEntityRenderer<>(context, 0.4F, RACCOON_TEXTURE, ModelLayer.RACCOON, RaccoonModel::new);
    public final static EntityRendererProvider<SharkEntity> SHARK_RENDERER = context -> new SimpleEntityRenderer<>(context, 0.8F, SHARK_TEXTURE, ModelLayer.SHARK, SharkModel::new);
    public final static EntityRendererProvider<PeacockEntity> PEACOCK_RENDERER = context -> new SimpleEntityRenderer<>(context, 0.3F, PEACOCK_TEXTURE, ModelLayer.PEACOCK, PeacockModel::new);

    public static ResourceLocation getEntityTexture(String texture) {
        return ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, "textures/entity/" + texture);
    }

}
