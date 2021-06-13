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
	public boolean canUse() {
		return super.canUse() && !this.ostrich.hasEgg();
	}

	@Override
	protected void breed() {
		ServerPlayerEntity serverplayerentity = this.animal.getLoveCause();
		if (serverplayerentity == null && this.partner.getLoveCause() != null) {
			serverplayerentity = this.partner.getLoveCause();
		}
		if (serverplayerentity != null) {
			serverplayerentity.awardStat(Stats.ANIMALS_BRED);
			CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this.animal, this.partner, null);
		}
		this.ostrich.setHasEgg(true);
		this.animal.resetLove();
		this.partner.resetLove();
		this.animal.setAge(6000);
		this.partner.setAge(6000);
		Random random = this.animal.getRandom();
		if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
			this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.animal.getX(), this.animal.getY(),
					this.animal.getZ(), random.nextInt(7) + 1));
		}
	}

}
