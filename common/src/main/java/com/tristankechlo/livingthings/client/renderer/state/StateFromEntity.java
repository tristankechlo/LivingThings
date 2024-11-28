package com.tristankechlo.livingthings.client.renderer.state;

import net.minecraft.world.entity.LivingEntity;

public interface StateFromEntity<T extends LivingEntity> {

    public void fromEntity(T entity);

}
