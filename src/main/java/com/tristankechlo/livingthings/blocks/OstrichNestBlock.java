package com.tristankechlo.livingthings.blocks;

import java.util.Random;

import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OstrichNestBlock extends Block {

	public static final BooleanProperty EGG = BooleanProperty.create("egg");
	public static final IntegerProperty HATCH = BlockStateProperties.HATCH_0_2;
	private static final VoxelShape EGG_SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 8, 12);
	private static final VoxelShape NEST_SHAPE = Block.makeCuboidShape(2, 0, 2, 14, 4, 14);

	public OstrichNestBlock() {
		super(AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.BROWN)
				.hardnessAndResistance(0.5F)
				.notSolid().tickRandomly());
		this.setDefaultState(this.getDefaultState().with(HATCH, 0).with(EGG, false));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(EGG, HATCH);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote && handIn == Hand.MAIN_HAND && player.getHeldItemMainhand().getItem() == Items.AIR) {
			Block block = state.getBlock();
			if (!(block instanceof OstrichNestBlock)) {
				return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
			}
			if (state.get(OstrichNestBlock.EGG)) {
				worldIn.setBlockState(pos, state.with(OstrichNestBlock.EGG, false).with(OstrichNestBlock.HATCH, 0), 2);
				worldIn.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F);

				ItemEntity entity = new ItemEntity(worldIn, pos.getX(), pos.getY() + 0.5D, pos.getZ(), new ItemStack(ModItems.OSTRICH_EGG.get(), 1));
				entity.setDefaultPickupDelay();
				worldIn.addEntity(entity);
				return ActionResultType.SUCCESS;
			}
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!state.get(EGG)) {
			return;
		}
		if (this.canGrow(worldIn)) {
			int i = state.get(HATCH);
			if (i < 2) {
				worldIn.setBlockState(pos, state.with(HATCH, Integer.valueOf(i + 1)), 2);
	            worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.BLOCKS, 0.7F, 0.9F);
			} else {
				worldIn.setBlockState(pos, state.with(EGG, false).with(HATCH, 0), 2);
	            worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.BLOCKS, 0.7F, 0.9F);

				OstrichEntity turtleentity = ModEntityTypes.OSTRICH_ENTIY.create(worldIn);
				turtleentity.setGrowingAge(-24000);
				turtleentity.setLocationAndAngles(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0.0F, 0.0F);
				worldIn.addEntity(turtleentity);
			}
		}
	}

	private boolean canGrow(World worldIn) {
		float f = worldIn.func_242415_f(1.0F);
		if (f < 0.6F && f > 0.6F) {
			return true;
		} else {
			return worldIn.rand.nextInt(175) == 0;
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (state.get(EGG)) {
			return VoxelShapes.combineAndSimplify(NEST_SHAPE, EGG_SHAPE, IBooleanFunction.OR);
		}
		return NEST_SHAPE;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

}
