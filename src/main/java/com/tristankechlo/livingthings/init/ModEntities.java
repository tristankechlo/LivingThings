package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

	public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, LivingThings.MOD_ID);


	public static final RegistryObject<EntityType<ElephantEntity>> ELEPHANT_ENTITY = ENTITIES.register("elephant",
			() -> EntityType.Builder.create(ElephantEntity::new, EntityClassification.AMBIENT)
					.size(2.2F, 2.7F).build((new ResourceLocation(LivingThings.MOD_ID, "elephant")).toString()));

}
