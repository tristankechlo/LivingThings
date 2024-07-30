package com.tristankechlo.livingthings.entity.ai;

import com.tristankechlo.livingthings.config.entity.AncientBlazeConfig;
import com.tristankechlo.livingthings.entity.AncientBlazeEntity;
import com.tristankechlo.livingthings.init.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;

import java.util.EnumSet;

public class AncientBlazeChargeUpGoal extends Goal {

    private final AncientBlazeEntity blaze;

    public AncientBlazeChargeUpGoal(AncientBlazeEntity entity) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        this.blaze = entity;
    }

    @Override
    public boolean canUse() {
        return this.blaze.getInvulnerableTime() > 0;
    }

    @Override
    public void tick() {
        int chargedtime = this.blaze.getInvulnerableTime();
        int targetShoots = AncientBlazeConfig.largeFireballAmount();
        if (chargedtime > 0) {
            chargedtime--;
            int divider = AncientBlazeConfig.chargingTime() / targetShoots;
            if (chargedtime % divider < 1 && this.blaze.getShoots() < targetShoots) {
                this.blaze.setShoots((byte) (this.blaze.getShoots() + 1));
                if (!this.blaze.level.isClientSide()) {
                    this.blaze.level.playSound(null, this.blaze.blockPosition(), ModSounds.ANCIENT_BLAZE_CHARGE_UP.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
                }
            }
        }
        if (chargedtime == 0) {
            this.blaze.setHealth(this.blaze.getMaxHealth());
            this.blaze.setShoots((byte) targetShoots);

            if (!this.blaze.level.isClientSide()) {
                this.blaze.level.playSound(null, this.blaze.blockPosition(), ModSounds.ANCIENT_BLAZE_SPAWN.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
            }

            for (int i = 0; i < 4; i++) {
                double accelX = Math.pow(-1, i) * 90;
                double accelZ = (i < 2) ? 90 : -90;
                SmallFireball smallfireballentity = new SmallFireball(this.blaze.level, this.blaze, accelX, -15D, accelZ);
                smallfireballentity.setPos(smallfireballentity.getX(), this.blaze.getY(0.5D), smallfireballentity.getZ());
                this.blaze.level.addFreshEntity(smallfireballentity);
            }

            for (int i = 0; i < 4; i++) {
                double accelX = (i > 1) ? Math.pow(-1, i) * 90 : 0;
                double accelZ = (i < 2) ? Math.pow(-1, i) * 90 : 0;
                LargeFireball smallfireballentity = new LargeFireball(this.blaze.level, this.blaze, accelX, -15D, accelZ, 1);
                smallfireballentity.setPos(smallfireballentity.getX(), this.blaze.getY(0.5D) + 0.5D, smallfireballentity.getZ());
                this.blaze.level.addFreshEntity(smallfireballentity);
            }
        }
        this.blaze.setInvulnerableTime(chargedtime);
    }

}