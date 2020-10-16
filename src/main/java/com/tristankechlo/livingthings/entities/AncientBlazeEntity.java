package com.tristankechlo.livingthings.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

public class AncientBlazeEntity extends MonsterEntity {

	public AncientBlazeEntity(EntityType<? extends AncientBlazeEntity> type, World world) {
		super(type, world);
	    this.setPathPriority(PathNodeType.WATER, -1.0F);
	    this.setPathPriority(PathNodeType.LAVA, 8.0F);
	    this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
	    this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
	    this.experienceValue = 20;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
	}

}
