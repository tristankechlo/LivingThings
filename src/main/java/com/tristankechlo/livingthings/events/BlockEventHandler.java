package com.tristankechlo.livingthings.events;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockEventHandler {

	private static ITag<Block> droppingBananaBlocks;

	@SubscribeEvent
	public void onBlockBreak(final BlockEvent.BreakEvent event) {
		if (!LivingThingsConfig.GENERAL.doBananaDrops.get()) {
			return;
		}
		IWorld world = event.getWorld();
		BlockState state = event.getState();
		PlayerEntity player = event.getPlayer();
		BlockPos pos = event.getPos();
		if (world == null || state == null || player == null || pos == null) {
			return;
		}
		if (world.isRemote() || player.isSpectator() || player.isCreative()) {
			return;
		}
		if (droppingBananaBlocks == null) {
			droppingBananaBlocks = BlockTags.getCollection().get(LivingThingsTags.DROPS_BANANAS);
		}
		if (!droppingBananaBlocks.contains(state.getBlock())) {
			return;
		}
		ItemStack stack = player.getHeldItemMainhand();
		if (!stack.isEmpty()) {
			int silktouchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack);
			if (silktouchLevel > 0 || stack.getItem() instanceof ShearsItem) {
				return;
			}
		}
		double dropChance = LivingThingsConfig.GENERAL.bananaDropChance.get() / 100.0D;
		if (world.getRandom().nextDouble() < dropChance) {
			ItemStack loot = new ItemStack(ModItems.BANANA.get());
			ItemEntity entity = new ItemEntity((World) world, pos.getX(), pos.getY(), pos.getZ(), loot);
			world.addEntity(entity);
		}
	}

	@SubscribeEvent
	public void onPlayerRightClickBlock(final BlockEvent.EntityPlaceEvent event) {
		if (!LivingThingsConfig.ANCIENT_BLAZE.canSpawn.get()) {
			return;
		}
		final IWorld world = event.getWorld();
		if (world == null) {
			return;
		}
		final Entity entity = event.getEntity();
		if (entity == null || !(entity instanceof PlayerEntity)) {
			return;
		}
		final PlayerEntity player = (PlayerEntity) entity;
		if (world.isRemote() || player.isSpectator()) {
			return;
		}
		final BlockPos pos = event.getPos();
		final BlockState placedBlock = event.getPlacedBlock();
		if (placedBlock.isIn(Blocks.JACK_O_LANTERN) && world.getBlockState(pos.down()).isIn(Blocks.GLOWSTONE)
				&& world.getBlockState(pos.down(2)).isIn(Blocks.GLOWSTONE)) {

			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
			world.setBlockState(pos.down(), Blocks.AIR.getDefaultState(), 3);
			world.setBlockState(pos.down(2), Blocks.AIR.getDefaultState(), 3);

			AncientBlazeEntity blaze = ModEntityTypes.ANCIENT_BLAZE_ENTITY.get().create((World) world);
			blaze.setInvulnerableTime(LivingThingsConfig.ANCIENT_BLAZE.chargingTime.get());
			blaze.setPosition(pos.getX() + 0.5D, pos.down(2).getY() + 0.2D, pos.getZ() + 0.5D);
			world.addEntity(blaze);
			return;

		}
	}
}
