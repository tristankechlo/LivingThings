package com.tristankechlo.livingthings.entities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SnailEntity extends AnimalEntity implements ILexiconEntry {

	private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(SnailEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SHELL_COLOR_F = EntityDataManager.createKey(SnailEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SHELL_COLOR_B = EntityDataManager.createKey(SnailEntity.class, DataSerializers.VARINT);
	private static final ResourceLocation[] BODY_TEXTURES = new ResourceLocation[] {
			textureLocation("snail_body_1.png") };
	private static final ResourceLocation[] SHELL_TEXTURES_B = new ResourceLocation[] {
			textureLocation("snail_shell_b1.png"), textureLocation("snail_shell_b2.png") };
	private static final ResourceLocation[] SHELL_TEXTURES_F = new ResourceLocation[] {
			textureLocation("snail_shell_f1.png"), textureLocation("snail_shell_f2.png") };
	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.CARROT, Items.APPLE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID, "passive_mobs/snail");

	public SnailEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	private static final ResourceLocation textureLocation(String name) {
		return new ResourceLocation(LivingThings.MOD_ID, "textures/entity/snail/" + name);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
		SnailEntity snailChild = ModEntityTypes.SNAIL_ENTITY.get().create(world);
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
			Collections.shuffle(colors, world.rand);
			snailChild.setShellColor(PatternType.FOREGROUND, colors.get(colors.size() - 1));
			snailChild.setShellColor(PatternType.BACKGROUND, colors.get(colors.size() - 1));

			short bodyVariant = world.rand.nextBoolean() ? this.getBodyVariant() : parentSnail.getBodyVariant();
			short shellVariant = !world.rand.nextBoolean() ? this.getShellVariant() : parentSnail.getShellVariant();
			snailChild.setVariant(bodyVariant, shellVariant);
		} else {
			// use a random preset
			SnailVariants snailPreset = SnailVariants.values()[world.rand.nextInt(SnailVariants.values().length)];
			snailChild.setShellColor(PatternType.FOREGROUND, snailPreset.getForegroundColor());
			snailChild.setShellColor(PatternType.BACKGROUND, snailPreset.getBackgroundColor());
			snailChild.setVariant(snailPreset.getVariant());
		}
		return snailChild;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.SNAIL.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, LivingThingsConfig.SNAIL.speed.get());
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		// select randomly a preset
		SnailVariants data = SnailVariants.values()[world.getRandom().nextInt(SnailVariants.values().length)];
		this.setVariant(data.getVariant());
		this.setShellColor(PatternType.FOREGROUND, data.getForegroundColor());
		this.setShellColor(PatternType.BACKGROUND, data.getBackgroundColor());
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, BREEDING_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(VARIANT, SnailVariants.NORMAL.getVariant());
		this.dataManager.register(SHELL_COLOR_F, SnailVariants.NORMAL.getForegroundColor());
		this.dataManager.register(SHELL_COLOR_B, SnailVariants.NORMAL.getBackgroundColor());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setVariant(compound.getByte("SnailVariant"));
		this.setShellColor(PatternType.FOREGROUND, compound.getInt("ShellColorF"));
		this.setShellColor(PatternType.BACKGROUND, compound.getInt("ShellColorB"));
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("SnailVariant", this.getVariant());
		compound.putInt("ShellColorF", this.getShellColor(PatternType.FOREGROUND));
		compound.putInt("ShellColorB", this.getShellColor(PatternType.BACKGROUND));
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItem() instanceof DyeItem) {
			DyeColor color = DyeColor.getColor(stack);
			if (player.isSneaking()) {
				this.setShellColor(PatternType.FOREGROUND, color.getColorValue());
			} else {
				this.setShellColor(PatternType.BACKGROUND, color.getColorValue());
			}
			this.consumeItemFromStack(player, stack);
			return ActionResultType.func_233537_a_(this.world.isRemote);
		}
		return super.func_230254_b_(player, hand);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isChild() ? 0.3F : 0.65F;
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
		return this.dataManager.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.dataManager.set(VARIANT, variant);
	}

	public void setVariant(short bodyVariant, short shellVariant) {
		this.setVariant((int) ((bodyVariant << 16) | (shellVariant & 0xFFFF)));
	}

	public int getShellColor(PatternType type) {
		if(type == PatternType.FOREGROUND) {
			return this.dataManager.get(SHELL_COLOR_F);
		} else {
			return this.dataManager.get(SHELL_COLOR_B);
		}
	}

	public void setShellColor(PatternType type, int color) {
		if (type == PatternType.FOREGROUND) {
			this.dataManager.set(SHELL_COLOR_F, color);
		} else if (type == PatternType.BACKGROUND) {
			this.dataManager.set(SHELL_COLOR_B, color);
		}
	}

	public void setShellColor(PatternType type, byte red, byte green, byte blue) {
		this.setShellColor(type, (new Color(red, green, blue)).getRGB());
	}

	@Override
	public int getMaxSpawnedInChunk() {
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
		FOREGROUND, BACKGROUND;
	}

}
