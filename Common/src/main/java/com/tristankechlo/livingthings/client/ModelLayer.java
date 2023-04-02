package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.armor.AncientArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class ModelLayer {

    public static final ModelLayerLocation ELEPHANT = createLayerLocation("elephant");
    public static final ModelLayerLocation GIRAFFE = createLayerLocation("giraffe");
    public static final ModelLayerLocation LION = createLayerLocation("lion");
    public static final ModelLayerLocation SHARK = createLayerLocation("shark");
    public static final ModelLayerLocation PENGUIN = createLayerLocation("penguin");
    public static final ModelLayerLocation OSTRICH = createLayerLocation("ostrich");
    public static final ModelLayerLocation FLAMINGO = createLayerLocation("flamingo");
    public static final ModelLayerLocation CRAB = createLayerLocation("crab");
    public static final ModelLayerLocation MANTARAY = createLayerLocation("mantaray");
    public static final ModelLayerLocation RACCOON = createLayerLocation("raccoon");
    public static final ModelLayerLocation OWL = createLayerLocation("owl");
    public static final ModelLayerLocation ANCIENT_BLAZE = createLayerLocation("ancient_blaze");
    public static final ModelLayerLocation KOALA = createLayerLocation("koala");

    public static final ModelLayerLocation ANCIENT_ARMOR = createLayerLocation("ancientarmormodel");
    public static AncientArmorModel ANCIENT_ARMOR_MODEL = null;

    public static void bakeArmorModel() {
        ANCIENT_ARMOR_MODEL = new AncientArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayer.ANCIENT_ARMOR));
    }

    private static ModelLayerLocation createLayerLocation(final String name) {
        return new ModelLayerLocation(new ResourceLocation(LivingThings.MOD_ID, name), "main");
    }

    public static ResourceLocation getEntityTexture(String texture) {
        return new ResourceLocation(LivingThings.MOD_ID, "textures/entity/" + texture);
    }

}
