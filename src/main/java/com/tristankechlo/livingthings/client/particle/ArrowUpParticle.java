package com.tristankechlo.livingthings.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArrowUpParticle extends SpriteTexturedParticle {

	private ArrowUpParticle(ClientWorld world, double x, double y, double z) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.01F;
		this.motionY *= 0.01F;
		this.motionZ *= 0.01F;
		this.motionY += 0.1D;
		this.particleScale *= 1.5F;
		this.maxAge = 16;
		this.canCollide = false;
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public float getScale(float scaleFactor) {
		return this.particleScale * MathHelper.clamp(((float) this.age + scaleFactor) / (float) this.maxAge * 32.0F, 0.0F, 1.0F);
	}

	@Override
	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.age++ >= this.maxAge) {
			this.setExpired();
		} else {
			this.move(this.motionX, this.motionY, this.motionZ);
			if (this.posY == this.prevPosY) {
				this.motionX *= 1.1D;
				this.motionZ *= 1.1D;
			}

			this.motionX *= 0.86F;
			this.motionY *= 0.86F;
			this.motionZ *= 0.86F;
			if (this.onGround) {
				this.motionX *= 0.7F;
				this.motionZ *= 0.7F;
			}

		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Green implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Green(IAnimatedSprite sprite) {
			this.spriteSet = sprite;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ArrowUpParticle particle = new ArrowUpParticle(worldIn, x, y, z);
			particle.selectSpriteRandomly(this.spriteSet);
			particle.setColor(0F, 1F, 0F);
			return particle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Red implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Red(IAnimatedSprite sprite) {
			this.spriteSet = sprite;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ArrowUpParticle particle = new ArrowUpParticle(worldIn, x, y, z);
			particle.selectSpriteRandomly(this.spriteSet);
			particle.setColor(1F, 0F, 0F);
			return particle;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Blue implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Blue(IAnimatedSprite sprite) {
			this.spriteSet = sprite;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ArrowUpParticle particle = new ArrowUpParticle(worldIn, x, y, z);
			particle.selectSpriteRandomly(this.spriteSet);
			particle.setColor(0F, 0F, 1F);
			return particle;
		}
	}
}
