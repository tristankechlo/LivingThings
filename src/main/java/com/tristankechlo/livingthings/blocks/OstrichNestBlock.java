package com.tristankechlo.livingthings.blocks;

import java.util.Random;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class OstrichNestBlock extends Block implements ILexiconEntry {

	public static final BooleanProperty EGG = BooleanProperty.create("egg");
	public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
	private static final VoxelShape EGG_SHAPE = Block.box(4, 0, 4, 12, 8, 12);
	private static final VoxelShape NEST_SHAPE = Block.box(2, 0, 2, 14, 4, 14);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"items/ostrich_nest");

	public OstrichNestBlock() {
		super(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_BROWN).strength(0.5F).randomTicks()
				.noCollission());
		registerDefaultState(this.defaultBlockState().setValue(HATCH, 0).setValue(EGG, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(EGG, HATCH);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
			BlockHitResult hit) {
		if (player.getMainHandItem().getItem() == ModItems.LEXICON.get()) {
			// prevent any use when rightclicked with lexicon
			return InteractionResult.PASS;
		}
		if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
			if (player.getMainHandItem().getItem() == Items.AIR) {
				Block block = state.getBlock();
				if (!(block instanceof OstrichNestBlock)) {
					return InteractionResult.PASS;
				}
				if (state.getValue(OstrichNestBlock.EGG)) {
					worldIn.setBlock(pos,
							state.setValue(OstrichNestBlock.EGG, false).setValue(OstrichNestBlock.HATCH, 0), 2);
					worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F);

					ItemEntity entity = new ItemEntity(worldIn, pos.getX(), pos.getY() + 0.5D, pos.getZ(),
							new ItemStack(ModItems.OSTRICH_EGG.get(), 1));
					entity.setDefaultPickUpDelay();
					worldIn.addFreshEntity(entity);
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		if (!state.getValue(EGG)) {
			return;
		}
		if (this.canGrow(worldIn)) {
			int i = state.getValue(HATCH);
			if (i < 2) {
				worldIn.setBlock(pos, state.setValue(HATCH, i + 1), 2);
				worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundSource.BLOCKS, 0.7F, 0.9F);
			} else {
				worldIn.setBlock(pos, state.setValue(EGG, false).setValue(HATCH, 0), 2);
				worldIn.playSound(null, pos, ModSounds.OSTRICH_EGG_CRACKS.get(), SoundSource.BLOCKS, 0.7F, 0.9F);

				OstrichEntity ostrichEntity = ModEntityTypes.OSTRICH.get().create(worldIn);
				ostrichEntity.setAge(-24000);
				ostrichEntity.setPosRaw(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				worldIn.addFreshEntity(ostrichEntity);
			}
		}
	}

	private boolean canGrow(Level worldIn) {
		float f = worldIn.getTimeOfDay(1.0F);
		if (f < 0.7F && f > 0.6F) {
			return true;
		} else {
			return worldIn.random.nextInt(175) == 0;
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		if (state.getValue(EGG)) {
			return Shapes.join(NEST_SHAPE, EGG_SHAPE, BooleanOp.OR);
		}
		return NEST_SHAPE;
	}

	@Override
	public RenderShape getRenderShape(BlockState p_149645_1_) {
		return RenderShape.MODEL;
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
