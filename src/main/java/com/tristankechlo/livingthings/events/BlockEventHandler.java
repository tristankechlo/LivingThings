package com.tristankechlo.livingthings.events;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.entities.BabyEnderDragonEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockEventHandler {

	@SubscribeEvent
	/** used to drop bananas at block break event */
	public void onBlockBreak(final BlockEvent.BreakEvent event) {
		if (!LivingThingsConfig.GENERAL.doBananaDrops.get()) {
			return;
		}
		LevelAccessor world = event.getWorld();
		BlockState state = event.getState();
		Player player = event.getPlayer();
		BlockPos pos = event.getPos();
		if (world == null || state == null || player == null || pos == null) {
			return;
		}
		if (world.isClientSide() || player.isSpectator() || player.isCreative()) {
			return;
		}
		if (!state.is(LivingThingsTags.DROPS_BANANAS)) {
			return;
		}
		ItemStack stack = player.getMainHandItem();
		if (!stack.isEmpty()) {
			int silktouchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack);
			if (silktouchLevel > 0 || stack.getItem() instanceof ShearsItem) {
				return;
			}
		}
		double dropChance = LivingThingsConfig.GENERAL.bananaDropChance.get() / 100.0D;
		if (world.getRandom().nextDouble() < dropChance) {
			ItemStack loot = new ItemStack(ModItems.BANANA.get());
			ItemEntity entity = new ItemEntity((Level) world, pos.getX(), pos.getY(), pos.getZ(), loot);
			world.addFreshEntity(entity);
		}
	}

	@SubscribeEvent
	public void onBlockPlaceEvent(final BlockEvent.EntityPlaceEvent event) {
		final LevelAccessor world = event.getWorld();
		if (world == null) {
			return;
		}
		final Entity entity = event.getEntity();
		if (entity == null || !(entity instanceof Player)) {
			return;
		}
		final Player player = (Player) entity;
		if (world.isClientSide() || player.isSpectator()) {
			return;
		}
		final BlockPos pos = event.getPos();
		final BlockState placedBlock = event.getPlacedBlock();
		final BlockState blockBelow = world.getBlockState(pos.below());

		if (placedBlock.is(Blocks.JACK_O_LANTERN) && world.getBlockState(pos.below()).is(Blocks.GLOWSTONE)
				&& world.getBlockState(pos.below(2)).is(Blocks.GLOWSTONE)) {

			if (!LivingThingsConfig.ANCIENT_BLAZE.canSpawn.get()) {
				return;
			}

			// remove blocks
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
			world.setBlock(pos.below(), Blocks.AIR.defaultBlockState(), 3);
			world.setBlock(pos.below(2), Blocks.AIR.defaultBlockState(), 3);

			// spawn ancient blaze
			AncientBlazeEntity blaze = ModEntityTypes.ANCIENT_BLAZE.get().create((Level) world);
			blaze.setInvulnerableTime(LivingThingsConfig.ANCIENT_BLAZE.chargingTime.get());
			blaze.setPos(pos.getX() + 0.5D, pos.below(2).getY() + 0.2D, pos.getZ() + 0.5D);
			world.addFreshEntity(blaze);
			return;

		} else if (placedBlock.is(Blocks.DRAGON_EGG) && blockBelow.is(Blocks.PURPUR_PILLAR)
				&& blockBelow.getValue(RotatedPillarBlock.AXIS) == Axis.Y) {

			if (world.dimensionType().hasCeiling()) {
				return;
			}

			if (!LivingThingsConfig.BABY_ENDER_DRAGON.canSpawn.get()) {
				return;
			}

			// remove dragon egg
			world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

			// summon some lightning bolts
			final int count = 10;
			final double radius = 6.0D;
			for (int i = 0; i < count; i++) {
				LightningBolt bolt = EntityType.LIGHTNING_BOLT.create((Level) world);
				final double x = pos.getX() + (radius * Mth.cos((i * 2 * Mth.PI) / count));
				final double z = pos.getZ() + (radius * Mth.sin((i * 2 * Mth.PI) / count));
				final double y = pos.below().getY();
				bolt.setPos(x, y, z);
				world.addFreshEntity(bolt);
			}

			// spawn baby ender dragon
			BabyEnderDragonEntity dragon = ModEntityTypes.BABY_ENDER_DRAGON.get().create((Level) world);
			dragon.setPos(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D);
			world.addFreshEntity(dragon);
			return;
		}
	}

}
