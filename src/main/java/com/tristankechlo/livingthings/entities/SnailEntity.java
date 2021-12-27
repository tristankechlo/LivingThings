package com.tristankechlo.livingthings.entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SnailEntity extends Animal implements ILexiconEntry {

	private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(SnailEntity.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> SHELL_COLOR_F = SynchedEntityData.defineId(SnailEntity.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> SHELL_COLOR_B = SynchedEntityData.defineId(SnailEntity.class,
			EntityDataSerializers.INT);
	private static final ResourceLocation[] BODY_TEXTURES = new ResourceLocation[] {
			textureLocation("snail_body_1.png") };
	private static final ResourceLocation[] SHELL_TEXTURES_B = new ResourceLocation[] {
			textureLocation("snail_shell_b1.png"), textureLocation("snail_shell_b2.png") };
	private static final ResourceLocation[] SHELL_TEXTURES_F = new ResourceLocation[] {
			textureLocation("snail_shell_f1.png"), textureLocation("snail_shell_f2.png") };
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.CARROT, Items.APPLE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"passive_mobs/snail");
	private static Tag<Block> spawnableOn = null;

	public SnailEntity(EntityType<? extends Animal> type, Level worldIn) {
		super(type, worldIn);
	}

	public static boolean checkSnailSpawnRules(EntityType<SnailEntity> animal, LevelAccessor world,
			MobSpawnType reason, BlockPos pos, Random random) {
		if (spawnableOn == null) {
			spawnableOn = BlockTags.getAllTags().getTagOrEmpty(LivingThingsTags.SNAIL_SPAWNABLE_ON);
		}
		return spawnableOn.contains(world.getBlockState(pos.below()).getBlock()) && isBrightEnoughToSpawn(world, pos);
	}

	private static final ResourceLocation textureLocation(String name) {
		return new ResourceLocation(LivingThings.MOD_ID, "textures/entity/snail/" + name);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		SnailEntity snailChild = ModEntityTypes.SNAIL.get().create(world);
		if (entity == this) {
			// make copy of current snail
			snailChild.setShellColor(PatternType.FOREGROUND, this.getShellColor(PatternType.FOREGROUND));
			snailChild.setShellColor(PatternType.BACKGROUND, this.getShellColor(PatternType.BACKGROUND));
			snailChild.setVariant(this.getVariant());
			return snailChild;
		}
		if (entity instanceof SnailEntity) {
			// select randomly colors from parents
			SnailEntity parentSnail = (SnailEntity) entity;
			List<Integer> colors = new ArrayList<Integer>();
			colors.add(this.getShellColor(PatternType.FOREGROUND));
			colors.add(this.getShellColor(PatternType.BACKGROUND));
			colors.add(parentSnail.getShellColor(PatternType.FOREGROUND));
			colors.add(parentSnail.getShellColor(PatternType.BACKGROUND));
			Collections.shuffle(colors, world.random);
			snailChild.setShellColor(PatternType.FOREGROUND, colors.get(colors.size() - 1));
			snailChild.setShellColor(PatternType.BACKGROUND, colors.get(colors.size() - 1));

			short bodyVariant = world.random.nextBoolean() ? this.getBodyVariant() : parentSnail.getBodyVariant();
			short shellVariant = !world.random.nextBoolean() ? this.getShellVariant() : parentSnail.getShellVariant();
			snailChild.setVariant(bodyVariant, shellVariant);
		} else {
			// use a random preset
			SnailVariants snailPreset = SnailVariants.values()[world.random.nextInt(SnailVariants.values().length)];
			snailChild.setShellColor(PatternType.FOREGROUND, snailPreset.getForegroundColor());
			snailChild.setShellColor(PatternType.BACKGROUND, snailPreset.getBackgroundColor());
			snailChild.setVariant(snailPreset.getVariant());
		}
		return snailChild;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.SNAIL.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.SNAIL.speed.get());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
			MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
		// select randomly a preset
		SnailVariants data = SnailVariants.values()[level.getRandom().nextInt(SnailVariants.values().length)];
		this.setVariant(data.getVariant());
		this.setShellColor(PatternType.FOREGROUND, data.getForegroundColor());
		this.setShellColor(PatternType.BACKGROUND, data.getBackgroundColor());
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, BREEDING_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, SnailVariants.NORMAL.getVariant());
		this.entityData.define(SHELL_COLOR_F, SnailVariants.NORMAL.getForegroundColor());
		this.entityData.define(SHELL_COLOR_B, SnailVariants.NORMAL.getBackgroundColor());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getByte("SnailVariant"));
		this.setShellColor(PatternType.FOREGROUND, compound.getInt("ShellColorF"));
		this.setShellColor(PatternType.BACKGROUND, compound.getInt("ShellColorB"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("SnailVariant", this.getVariant());
		compound.putInt("ShellColorF", this.getShellColor(PatternType.FOREGROUND));
		compound.putInt("ShellColorB", this.getShellColor(PatternType.BACKGROUND));
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() instanceof DyeItem) {
			DyeColor color = DyeColor.getColor(stack);
			if (player.isCrouching()) {
				this.setShellColor(PatternType.FOREGROUND, color.getTextColor());
			} else {
				this.setShellColor(PatternType.BACKGROUND, color.getTextColor());
			}
			if (!player.getAbilities().instabuild) {
				stack.shrink(1);
			}
			return InteractionResult.sidedSuccess(this.level.isClientSide);
		}
		return super.mobInteract(player, hand);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.3F : 0.65F;
	}

	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getBodyTexture() {
		return BODY_TEXTURES[this.getBodyVariant()];
	}

	private short getBodyVariant() {
		return (short) (this.getVariant() >> 16);
	}

	@OnlyIn(Dist.CLIENT)
	public ResourceLocation getShellPatternTexture(PatternType type) {
		return (type == PatternType.FOREGROUND) ? SHELL_TEXTURES_F[this.getShellVariant()]
				: SHELL_TEXTURES_B[this.getShellVariant()];
	}

	private short getShellVariant() {
		return (short) this.getVariant();
	}

	@OnlyIn(Dist.CLIENT)
	public float[] getShellColorScheme(PatternType type) {
		int colorValue = this.getShellColor(type);
		int i = (colorValue & 16711680) >> 16;
		int j = (colorValue & '\uff00') >> 8;
		int k = (colorValue & 255) >> 0;
		return new float[] { (float) i / 255.0F, (float) j / 255.0F, (float) k / 255.0F };
	}

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	public void setVariant(short bodyVariant, short shellVariant) {
		this.setVariant((int) ((bodyVariant << 16) | (shellVariant & 0xFFFF)));
	}

	public int getShellColor(PatternType type) {
		if (type == PatternType.FOREGROUND) {
			return this.entityData.get(SHELL_COLOR_F);
		} else {
			return this.entityData.get(SHELL_COLOR_B);
		}
	}

	public void setShellColor(PatternType type, int color) {
		if (type == PatternType.FOREGROUND) {
			this.entityData.set(SHELL_COLOR_F, color);
		} else if (type == PatternType.BACKGROUND) {
			this.entityData.set(SHELL_COLOR_B, color);
		}
	}

	public void setShellColor(PatternType type, byte red, byte green, byte blue) {
		this.setShellColor(type, (new Color(red, green, blue)).getRGB());
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.SNAIL.maxSpawnedInChunk.get();
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	static enum SnailVariants {

		NORMAL(0, 0, 11693105, 8209952),
		GREEN(0, 0, 412975, 2129982),
		PURPLE(0, 0, 6488099, 10238043),
		BLUE(0, 0, 4857561, 6447075),
		RED(0, 0, 10367513, 13586001),
		NORMAL_2(0, 1, 9847813, 7352576);

		private int variant;
		private int colorForeground;
		private int colorBackground;

		private SnailVariants(int bodyVariant, int shellVariant, int colorb, int colorf) {
			this.variant = (bodyVariant << 16) | (shellVariant & 0xFFFF);
			this.colorBackground = colorb;
			this.colorForeground = colorf;
		}

		public int getVariant() {
			return this.variant;
		}

		public int getForegroundColor() {
			return this.colorForeground;
		}

		public int getBackgroundColor() {
			return this.colorBackground;
		}

	}

	public static enum PatternType {
		FOREGROUND,
		BACKGROUND;
	}

}
