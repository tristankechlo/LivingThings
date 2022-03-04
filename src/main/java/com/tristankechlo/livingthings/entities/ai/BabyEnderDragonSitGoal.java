package com.tristankechlo.livingthings.entities.ai;

import java.util.EnumSet;

import com.tristankechlo.livingthings.entities.BabyEnderDragonEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;

/**
 * similar to SitWhenOrderedToGoal, except the mob can start sitting when in the
 * air
 */
public class BabyEnderDragonSitGoal extends Goal {

	private final TamableAnimal mob;

	public BabyEnderDragonSitGoal(BabyEnderDragonEntity entity) {
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		this.mob = entity;
	}

	@Override
	public boolean canContinueToUse() {
		return this.mob.isOrderedToSit();
	}

	@Override
	public boolean canUse() {
		if (!this.mob.isTame()) {
			return false;
		} else if (this.mob.isInWaterOrBubble()) {
			return false;
		} else {
			LivingEntity livingentity = this.mob.getOwner();
			if (livingentity == null) {
				return true;
			} else {
				return this.mob.distanceToSqr(livingentity) < 144.0D && livingentity.getLastHurtByMob() != null ? false
						: this.mob.isOrderedToSit();
			}
		}
	}

	@Override
	public void start() {
		this.mob.getNavigation().stop();
		this.mob.setInSittingPose(true);
	}

	@Override
	public void stop() {
		this.mob.setInSittingPose(false);
	}

}
