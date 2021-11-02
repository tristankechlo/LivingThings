package com.tristankechlo.livingthings.config.misc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public final class SpawnData {

	public int weight;
	public int minCount;
	public int maxCount;
	public List<String> biomes;

	@SuppressWarnings("unchecked")
	public SpawnData(int weight, int min, int max, ResourceKey<Biome>... biomes) {
		this.weight = weight;
		this.minCount = min;
		this.maxCount = max;
		this.biomes = new ArrayList<>();
		for (int i = 0; i < biomes.length; i++) {
			this.biomes.add(biomes[i].location().toString());
		}
	}

}
