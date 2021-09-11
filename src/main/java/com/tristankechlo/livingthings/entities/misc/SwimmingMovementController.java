package com.tristankechlo.livingthings.entities.misc;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.MathHelper;

public class SwimmingMovementController extends MovementController {

	private final CreatureEntity entity;

	public SwimmingMovementController(CreatureEntity sharkIn) {
		super(sharkIn);
		this.entity = sharkIn;
	}

	@Override
	public void tick() {
		if (this.entity.isInWater()) {
			this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
		}

		if (this.operation == MovementController.Action.MOVE_TO && !this.entity.getNavigation().isDone()) {
			double d0 = this.wantedX - this.entity.getX();
			double d1 = this.wantedY - this.entity.getY();
			double d2 = this.wantedZ - this.entity.getZ();
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			if (d3 < (double) 2.5000003E-7F) {
				this.mob.setZza(0.0F);
			} else {
				float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.entity.yRot = this.rotlerp(this.entity.yRot, f, 10.0F);
				this.entity.yBodyRot = this.entity.yRot;
				this.entity.yHeadRot = this.entity.yRot;
				float f1 = (float) (this.speedModifier * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if (this.entity.isInWater()) {
					this.entity.setSpeed(f1 * 0.02F);
					float f2 = -((float) (MathHelper.atan2(d1, MathHelper.sqrt(d0 * d0 + d2 * d2))
							* (double) (180F / (float) Math.PI)));
					f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
					this.entity.xRot = this.rotlerp(this.entity.xRot, f2, 5.0F);
					float f3 = MathHelper.cos(this.entity.xRot * ((float) Math.PI / 180F));
					float f4 = MathHelper.sin(this.entity.xRot * ((float) Math.PI / 180F));
					this.entity.zza = f3 * f1;
					this.entity.yya = -f4 * f1;
				} else {
					this.entity.setSpeed(f1 * 0.1F);
				}

			}
		} else {
			this.entity.setSpeed(0.0F);
			this.entity.setXxa(0.0F);
			this.entity.setYya(0.0F);
			this.entity.setZza(0.0F);
		}
	}
}