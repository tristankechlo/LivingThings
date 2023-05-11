package com.tristankechlo.livingthings.entity.projectile;

import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownOstrichEgg extends ThrowableItemProjectile {

    public ThrownOstrichEgg(EntityType<? extends ThrownOstrichEgg> type, Level world) {
        super(type, world);
    }

    public ThrownOstrichEgg(Level world, double x, double y, double z) {
        super(ModEntityTypes.THROWN_OSTRICH_EGG.get(), x, y, z, world);
    }

    public ThrownOstrichEgg(Level world, LivingEntity entity) {
        super(ModEntityTypes.THROWN_OSTRICH_EGG.get(), entity, world);
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        hitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 1.0F);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }

    @Override
    public void handleEntityEvent(byte eventID) {
        if (eventID == 3) {
            double offset = 0.18D;
            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), (this.random.nextFloat() - 0.5D) * offset, (this.random.nextFloat() - 0.5D) * offset, (this.random.nextFloat() - 0.5D) * offset);
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.OSTRICH_EGG.get();
    }

}
