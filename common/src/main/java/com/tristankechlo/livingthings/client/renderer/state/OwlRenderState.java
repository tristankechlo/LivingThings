package com.tristankechlo.livingthings.client.renderer.state;

import com.tristankechlo.livingthings.entity.OwlEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;

public class OwlRenderState extends LivingEntityRenderState implements MovingEntityState, StateFromEntity<OwlEntity> {

    public byte variant;
    public float flap;
    public float oFlap;
    public float flapSpeed;
    public float oFlapSpeed;
    public boolean isMoving;
    public Pose pose;
    public float flapAngle;

    @Override
    public void fromEntity(OwlEntity entity) {
        this.variant = entity.getVariant();
        this.flap = entity.flap;
        this.oFlap = entity.oFlap;
        this.flapSpeed = entity.flapSpeed;
        this.oFlapSpeed = entity.oFlapSpeed;
        this.setMoving(entity);
        this.pose = getPose(entity);
    }

    @Override
    public boolean isMoving() {
        return this.isMoving;
    }

    @Override
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    public void flapAngle(OwlEntity entity, float $$2) {
        float $$3 = Mth.lerp($$2, entity.oFlap, entity.flap);
        float $$4 = Mth.lerp($$2, entity.oFlapSpeed, entity.flapSpeed);
        this.flapAngle = (Mth.sin($$3) + 1.0F) * $$4;
    }

    private static Pose getPose(OwlEntity owl) {
        if (owl.isInSittingPose() || owl.isSleeping()) {
            return Pose.SITTING;
        } else {
            return owl.isFlying() ? Pose.FALL_FLYING : Pose.STANDING;
        }
    }
}
