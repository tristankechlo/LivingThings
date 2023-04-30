package com.tristankechlo.livingthings.entity.misc;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

/**
 * excatly the same like DragonFireball, but the AreaEffectCloud can be
 * customised
 */
public class CustomDragonFireball extends DragonFireball {

    private float radius = 0.5F;
    private int duration = 300;
    private float spreadSpeed = 1.0F;

    public CustomDragonFireball(Level level, LivingEntity owner, double d1, double d2, double d3) {
        super(level, owner, d1, d2, d3);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        HitResult.Type hitresult$type = hitResult.getType();
        if (hitresult$type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult) hitResult);
        } else if (hitresult$type == HitResult.Type.BLOCK) {
            this.onHitBlock((BlockHitResult) hitResult);
        }
        if (hitresult$type != HitResult.Type.MISS) {
            this.gameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
        }
        if (hitResult.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult) hitResult).getEntity())) {
            if (!this.level.isClientSide) {
                List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
                AreaEffectCloud effectcloud = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
                Entity entity = this.getOwner();
                if (entity instanceof LivingEntity) {
                    effectcloud.setOwner((LivingEntity) entity);
                }

                effectcloud.setParticle(ParticleTypes.DRAGON_BREATH);
                effectcloud.setRadius(this.radius);
                effectcloud.setDuration(this.duration);
                effectcloud.setRadiusPerTick((this.spreadSpeed - effectcloud.getRadius()) / (float) effectcloud.getDuration());
                effectcloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));
                if (!list.isEmpty()) {
                    for (LivingEntity livingentity : list) {
                        double d0 = this.distanceToSqr(livingentity);
                        if (d0 < 16.0D) {
                            effectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                            break;
                        }
                    }
                }

                this.level.levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);
                this.level.addFreshEntity(effectcloud);
                this.discard();
            }
        }
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSpreadSpeed(float spreadSpeed) {
        this.spreadSpeed = spreadSpeed;
    }

    public void setAreaEffectCloud(float radius, int duration, float spreadSpeed) {
        this.radius = radius;
        this.duration = duration;
        this.spreadSpeed = spreadSpeed;
    }

}
