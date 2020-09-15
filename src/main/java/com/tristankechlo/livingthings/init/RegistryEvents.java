package com.tristankechlo.livingthings.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

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
    
    public void onBiomeLoading(final BiomeLoadingEvent event) {    	
    	/*
    	for (Biome biome : event.getRegistry().getValues()) {
    		
    		Category category = biome.getCategory();    		
    		
    		if(category == Category.SAVANNA) {
    			addSavannaSpawns(biome);
    		} else if(category == Category.OCEAN) {
    			addOceanSpawns(biome);
    		} else if (category == Category.ICY) {
    			addIcySpawns(biome);
    		} else if(category == Category.BEACH) {
    			addBeachSpawns(biome);
    		} else if (category == Category.SWAMP) {
    			addSwampSpawns(biome);
    		}
    	}*/	
    }
    
    private static void addSwampSpawns(Biome biome) {
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map_old = ObfuscationReflectionHelper.getPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), "field_242554_e");
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map = new HashMap<>(spawn_map_old);
    	List<Spawners> spawns_old = spawn_map.get(EntityClassification.CREATURE);
    	List<Spawners> spawns_new = new ArrayList<>(spawns_old);
    	
    	spawns_new.add(new Spawners(ModEntityTypes.FLAMINGO_ENTIY, 15, 3, 6));
    	
    	spawn_map.put(EntityClassification.CREATURE, spawns_new);
    	
    	ObfuscationReflectionHelper.setPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), spawn_map, "field_242554_e");
    }
    
    private static void addBeachSpawns(Biome biome) {
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map_old = ObfuscationReflectionHelper.getPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), "field_242554_e");
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map = new HashMap<>(spawn_map_old);
    	List<Spawners> spawns_old = spawn_map.get(EntityClassification.CREATURE);
    	List<Spawners> spawns_new = new ArrayList<>(spawns_old);
    	
    	spawns_new.add(new Spawners(ModEntityTypes.FLAMINGO_ENTIY, 15, 3, 6));
    	
    	spawn_map.put(EntityClassification.CREATURE, spawns_new);
    	
    	ObfuscationReflectionHelper.setPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), spawn_map, "field_242554_e");
    }
    
    private static void addSavannaSpawns(Biome biome) {
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map_old = ObfuscationReflectionHelper.getPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), "field_242554_e");
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map = new HashMap<>(spawn_map_old);
    	List<Spawners> spawns_old = spawn_map.get(EntityClassification.CREATURE);
    	List<Spawners> spawns_new = new ArrayList<>(spawns_old);
    	
    	spawns_new.add(new Spawners(ModEntityTypes.ELEPHANT_ENTITY, 15, 2, 5));
    	spawns_new.add(new Spawners(ModEntityTypes.GIRAFFE_ENTITY, 15, 3, 5));
    	spawns_new.add(new Spawners(ModEntityTypes.LION_ENTITY, 15, 2, 4));
    	spawns_new.add(new Spawners(ModEntityTypes.OSTRICH_ENTIY, 15, 3, 5));
    	
    	spawn_map.put(EntityClassification.CREATURE, spawns_new);
    	
    	ObfuscationReflectionHelper.setPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), spawn_map, "field_242554_e");
    }
    
    private static void addOceanSpawns(Biome biome) {
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map_old = ObfuscationReflectionHelper.getPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), "field_242554_e");
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map = new HashMap<>(spawn_map_old);
    	List<Spawners> spawns_old = spawn_map.get(EntityClassification.WATER_CREATURE);
    	List<Spawners> spawns_new = new ArrayList<>(spawns_old);
    	
    	spawns_new.add(new Spawners(ModEntityTypes.SHARK_ENTIY, 7, 1, 2));
    	
    	spawn_map.put(EntityClassification.WATER_CREATURE, spawns_new);
    	
    	ObfuscationReflectionHelper.setPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), spawn_map, "field_242554_e");
    }
    
    private static void addIcySpawns(Biome biome) {
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map_old = ObfuscationReflectionHelper.getPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), "field_242554_e");
    	Map<EntityClassification, List<MobSpawnInfo.Spawners>> spawn_map = new HashMap<>(spawn_map_old);
    	List<Spawners> spawns_old = spawn_map.get(EntityClassification.CREATURE);
    	List<Spawners> spawns_new = new ArrayList<>(spawns_old);
    	
    	spawns_new.add(new Spawners(ModEntityTypes.PENGUIN_ENTITY, 15, 3, 6));
    	
    	spawn_map.put(EntityClassification.CREATURE, spawns_new);

    	ObfuscationReflectionHelper.setPrivateValue(MobSpawnInfo.class, biome.func_242433_b(), spawn_map, "field_242554_e");
    }
    
}



/*
  .add(new Spawners(ModEntityTypes.ELEPHANT_ENTITY, 15, 2, 5))
  .add(new Spawners(ModEntityTypes.GIRAFFE_ENTITY, 15, 2, 4))
  .add(new Spawners(ModEntityTypes.LION_ENTITY, 15, 3, 5))
 
private static void registerSharkSpawns() {
	Biomes.OCEAN.getSpawns(EntityClassification.WATER_CREATURE).add(new SpawnListEntry(SHARK_ENTIY, 4, 1, 2));
	Biomes.DEEP_OCEAN.getSpawns(EntityClassification.WATER_CREATURE).add(new SpawnListEntry(SHARK_ENTIY, 5, 1, 2));
	Biomes.WARM_OCEAN.getSpawns(EntityClassification.WATER_CREATURE).add(new SpawnListEntry(SHARK_ENTIY, 6, 1, 2));
	Biomes.DEEP_WARM_OCEAN.getSpawns(EntityClassification.WATER_CREATURE).add(new SpawnListEntry(SHARK_ENTIY, 7, 1, 2));
	Biomes.LUKEWARM_OCEAN.getSpawns(EntityClassification.WATER_CREATURE).add(new SpawnListEntry(SHARK_ENTIY, 6, 1, 2));
	Biomes.DEEP_LUKEWARM_OCEAN.getSpawns(EntityClassification.WATER_CREATURE).add(new SpawnListEntry(SHARK_ENTIY, 7, 1, 2));
}
*/
