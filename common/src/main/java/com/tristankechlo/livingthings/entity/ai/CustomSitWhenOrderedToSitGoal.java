package com.tristankechlo.livingthings.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;

/**
 * similar to SitWhenOrderedToGoal, except the mob can start sitting when in the
 * air
 */
public class CustomSitWhenOrderedToSitGoal extends SitWhenOrderedToGoal {

    private final TamableAnimal mob;

    public CustomSitWhenOrderedToSitGoal(TamableAnimal entity) {
        super(entity);
        this.mob = entity;
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
                return this.mob.distanceToSqr(livingentity) < 144.0D && livingentity.getLastHurtByMob() != null ? false : this.mob.isOrderedToSit();
            }
        }
    }
}