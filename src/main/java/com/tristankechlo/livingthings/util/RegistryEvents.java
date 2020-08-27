package com.tristankechlo.livingthings.util;

import com.google.common.base.Preconditions;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

	@SubscribeEvent
	public static void onEntityRegistry(RegistryEvent.Register<EntityType<?>> event) {
        for (@SuppressWarnings("rawtypes") EntityType entity : ModEntityTypes.ENTITIES) {
            Preconditions.checkNotNull(entity.getRegistryName(), "registryName");
            event.getRegistry().register(entity);
        }
		ModEntityTypes.registerAttributes();
		ModEntityTypes.registerEntitySpawnPlacements();
	}

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        for (Item spawnEgg : ModEntityTypes.SPAWN_EGGS) {
            Preconditions.checkNotNull(spawnEgg.getRegistryName(), "registryName");
            event.getRegistry().register(spawnEgg);
        }
    }
    
    @SubscribeEvent
    public static void onBiomeRegistry(RegistryEvent.Register<Biome> event) {
    	for (Biome biome : event.getRegistry().getValues()) {
    		if(biome.getCategory() == Category.SAVANNA) {
        		biome.func_242433_b().func_242559_a(EntityClassification.CREATURE);
    		}
    	}
    }
}
/*
 * .add(new Spawners(ModEntityTypes.ELEPHANT_ENTITY, 15, 2, 5))
 * .add(new Spawners(ModEntityTypes.GIRAFFE_ENTITY, 15, 2, 4))
 * .add(new Spawners(ModEntityTypes.LION_ENTITY, 15, 3, 5))
 */
