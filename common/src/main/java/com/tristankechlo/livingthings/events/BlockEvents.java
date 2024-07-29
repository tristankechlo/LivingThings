package com.tristankechlo.livingthings.events;

import com.tristankechlo.livingthings.config.GeneralConfig;
import com.tristankechlo.livingthings.config.entity.AncientBlazeConfig;
import com.tristankechlo.livingthings.config.entity.BabyEnderDragonConfig;
import com.tristankechlo.livingthings.entity.AncientBlazeEntity;
import com.tristankechlo.livingthings.entity.BabyEnderDragonEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class BlockEvents {

    public static void onBlockBreak(LevelAccessor world, Player player, BlockPos pos, BlockState state) {
        if (!GeneralConfig.get().doBananaDrops.get()) {
            return;
        }
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
            Registry<Enchantment> registry = world.registryAccess().registry(Registries.ENCHANTMENT).orElseThrow();
            int silktouchLevel = EnchantmentHelper.getItemEnchantmentLevel(registry.getHolderOrThrow(Enchantments.SILK_TOUCH), stack);
            if (silktouchLevel > 0 || stack.getItem() instanceof ShearsItem) {
                return;
            }
        }
        double dropChance = GeneralConfig.get().bananaDropChance.get() / 100.0D;
        if (world.getRandom().nextDouble() < dropChance) {
            ItemStack loot = new ItemStack(ModItems.BANANA.get());
            ItemEntity entity = new ItemEntity((Level) world, pos.getX(), pos.getY(), pos.getZ(), loot);
            world.addFreshEntity(entity);
        }
    }

    public static InteractionResult onBlockPlace(LevelAccessor world, Player player, BlockPos pos, BlockState placedBlock) {
        if (world.isClientSide() || player.isSpectator()) {
            return InteractionResult.PASS;
        }
        if (!placedBlock.is(Blocks.JACK_O_LANTERN) && !placedBlock.is(Blocks.DRAGON_EGG)) {
            return InteractionResult.PASS;
        }
        final BlockState blockBelow = world.getBlockState(pos.below());
        final BlockState blockBelow2 = world.getBlockState(pos.below(2));

        if (placedBlock.is(Blocks.JACK_O_LANTERN) && blockBelow.is(Blocks.GLOWSTONE) && blockBelow2.is(Blocks.GLOWSTONE)) {
            if (!AncientBlazeConfig.canSpawn()) {
                return InteractionResult.PASS;
            }

            // remove blocks
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(pos.below(), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(pos.below(2), Blocks.AIR.defaultBlockState(), 3);

            // spawn ancient blaze
            AncientBlazeEntity blaze = ModEntityTypes.ANCIENT_BLAZE.get().create((Level) world);
            blaze.setInvulnerableTime(AncientBlazeConfig.chargingTime());
            blaze.setPos(pos.getX() + 0.5D, pos.below(2).getY() + 0.2D, pos.getZ() + 0.5D);
            world.addFreshEntity(blaze);
            return InteractionResult.SUCCESS;
        }

        if (placedBlock.is(Blocks.DRAGON_EGG) && blockBelow.is(Blocks.PURPUR_PILLAR) && blockBelow.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y) {
            if (world.dimensionType().hasCeiling()) {
                return InteractionResult.PASS;
            }
            if (!BabyEnderDragonConfig.canSpawn()) {
                return InteractionResult.PASS;
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
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

}
