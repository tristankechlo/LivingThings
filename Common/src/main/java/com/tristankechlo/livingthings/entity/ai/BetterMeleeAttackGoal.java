package com.tristankechlo.livingthings.entity.ai;

import com.tristankechlo.livingthings.config.GeneralConfig;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

import java.util.function.Supplier;

public class BetterMeleeAttackGoal extends MeleeAttackGoal {

    private final Supplier<Boolean> canAttack;

    public BetterMeleeAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory, Supplier<Boolean> canAttack) {
        super(creature, speedIn, useLongMemory);
        this.canAttack = canAttack;
    }

    @Override
    public boolean canUse() {
        boolean peaceful = (this.mob.level.getDifficulty() == Difficulty.PEACEFUL);
        boolean ambientMode = GeneralConfig.get().ambientMode.get();
        if (peaceful || ambientMode || !this.canEntityAttack()) {
            return false;
        }
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        boolean peaceful = (this.mob.level.getDifficulty() == Difficulty.PEACEFUL);
        boolean ambientMode = GeneralConfig.get().ambientMode.get();
        if (peaceful || ambientMode || !this.canEntityAttack()) {
            return false;
        }
        return super.canContinueToUse();
    }

    private boolean canEntityAttack() {
        return this.canAttack.get();
    }

}
