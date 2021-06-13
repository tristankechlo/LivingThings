package com.tristankechlo.livingthings.blocks;

import java.util.Random;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

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
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
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

public class OstrichNestBlock extends Block implements ILexiconEntry {

	public static final BooleanProperty EGG = BooleanProperty.create("egg");
	public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
	private static final VoxelShape EGG_SHAPE = Block.box(4, 0, 4, 12, 8, 12);
	private static final VoxelShape NEST_SHAPE = Block.box(2, 0, 2, 14, 4, 14);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"items/ostrich_nest");

	public OstrichNestBlock() {
		super(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_BROWN).strength(0.5F).randomTicks()
				.noCollission());
		registerDefaultState(this.defaultBlockState().setValue(HATCH, 0).setValue(EGG, false));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(EGG, HATCH);
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (player.getMainHandItem().getItem() == ModItems.LEXICON.get()) {
			// prevent any use when rightclicked with lexicon
			return ActionResultType.PASS;
		}
		if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
			if (player.getMainHandItem().getItem() == Items.AIR) {
				Block block = state.getBlock();
				if (!(block instanceof OstrichNestBlock)) {
					return ActionResultType.PASS;
				}
				if (state.getValue(OstrichNestBlock.EGG)) {
					worldIn.setBlock(pos,
							state.setValue(OstrichNestBlock.EGG, false).setValue(OstrichNestBlock.HATCH, 0), 2);
					worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F);

					ItemEntity entity = new ItemEntity(worldIn, pos.getX(), pos.getY() + 0.5D, pos.getZ(),
							new ItemStack(ModItems.OSTRICH_EGG.get(), 1));
					entity.setDefaultPickUpDelay();
					worldIn.addFreshEntity(entity);
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.PASS;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!state.getValue(EGG)) {
			return;
		}
		if (this.canGrow(worldIn)) {
			int i = state.getValue(HATCH);
			if (i < 2) {
				worldIn.setBlock(pos, state.setValue(HATCH, i + 1), 2);
				worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.BLOCKS, 0.7F, 0.9F);
			} else {
				worldIn.setBlock(pos, state.setValue(EGG, false).setValue(HATCH, 0), 2);
				worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundCategory.BLOCKS, 0.7F, 0.9F);

				OstrichEntity ostrichEntity = ModEntityTypes.OSTRICH_ENTITY.get().create(worldIn);
				ostrichEntity.setAge(-24000);
				ostrichEntity.setPosAndOldPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				worldIn.addFreshEntity(ostrichEntity);
			}
		}
	}

	private boolean canGrow(World worldIn) {
		float f = worldIn.getTimeOfDay(1.0F);
		if (f < 0.7F && f > 0.6F) {
			return true;
		} else {
			return worldIn.random.nextInt(175) == 0;
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (state.getValue(EGG)) {
			return VoxelShapes.join(NEST_SHAPE, EGG_SHAPE, IBooleanFunction.OR);
		}
		return NEST_SHAPE;
	}

	@Override
	public BlockRenderType getRenderShape(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState p_149656_1_) {
		return PushReaction.DESTROY;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
