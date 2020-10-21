package com.tristankechlo.livingthings.events;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RightClickBlockEventHandler {

    @SubscribeEvent
    public void onPlayerRightClickBlock(final BlockEvent.EntityPlaceEvent event) {
        final IWorld world = event.getWorld();
        final Entity entity = event.getEntity();
        final BlockPos pos = event.getPos();
        final BlockState placedBlock = event.getPlacedBlock();
        if(!(entity instanceof PlayerEntity)) {
        	return;
        }
        final PlayerEntity player = (PlayerEntity)entity;
        if (player == null || world == null) {
            return;
        }
        if (world.isRemote() || player.isSpectator()) {
            return;
        }
        if(placedBlock.isIn(Blocks.JACK_O_LANTERN) && world.getBlockState(pos.down()).isIn(Blocks.GLOWSTONE) && world.getBlockState(pos.down(2)).isIn(Blocks.GLOWSTONE)) {
        	        	
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
