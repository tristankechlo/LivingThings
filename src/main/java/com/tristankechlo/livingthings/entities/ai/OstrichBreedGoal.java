package com.tristankechlo.livingthings.entities.ai;

import java.util.Random;

import com.tristankechlo.livingthings.entities.OstrichEntity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.GameRules;

public class OstrichBreedGoal extends BreedGoal {

	private final OstrichEntity ostrich;

	public OstrichBreedGoal(OstrichEntity animal, double moveSpeed) {
		super(animal, moveSpeed);
		this.ostrich = animal;
	}

	@Override
	public boolean shouldExecute() {
		return super.shouldExecute() && !this.ostrich.hasEgg();
	}

	@Override
	protected void spawnBaby() {
		ServerPlayerEntity serverplayerentity = this.animal.getLoveCause();
		if (serverplayerentity == null && this.targetMate.getLoveCause() != null) {
			serverplayerentity = this.targetMate.getLoveCause();
		}
		if (serverplayerentity != null) {
			serverplayerentity.addStat(Stats.ANIMALS_BRED);
			CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this.animal, this.targetMate, null);
		}
		this.ostrich.setHasEgg(true);
		this.animal.resetInLove();
		this.targetMate.resetInLove();
		this.animal.setGrowingAge(6000);
		this.targetMate.setGrowingAge(6000);
		Random random = this.animal.getRNG();
		if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
			this.world.addEntity(new ExperienceOrbEntity(this.world, this.animal.getPosX(), this.animal.getPosY(), this.animal.getPosZ(), random.nextInt(7) + 1));
		}
	}

}
