package com.tristankechlo.livingthings.client.layer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.AncientBlazeModel;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientBlazeChargedLayer extends EnergyLayer<AncientBlazeEntity, AncientBlazeModel<AncientBlazeEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/ancient_blaze_charge.png");
   private final AncientBlazeModel<AncientBlazeEntity> model = new AncientBlazeModel<>();

   public AncientBlazeChargedLayer(IEntityRenderer<AncientBlazeEntity, AncientBlazeModel<AncientBlazeEntity>> entityRenderer) {
      super(entityRenderer);
   }

   protected float func_225634_a_(float value) {
      return value * 0.01F;
   }

   protected ResourceLocation func_225633_a_() {
      return TEXTURE;
   }

   protected EntityModel<AncientBlazeEntity> func_225635_b_() {
      return this.model;
   }
}
