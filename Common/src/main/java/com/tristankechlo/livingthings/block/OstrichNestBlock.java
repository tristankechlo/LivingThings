package com.tristankechlo.livingthings.block;

import com.tristankechlo.livingthings.entity.OstrichEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.LexiconEntries;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
    private static final VoxelShape EMPTY_NEST_SHAPE = Block.box(2, 0, 2, 14, 4, 14);
    private static final VoxelShape FULL_NEST_SHAPE = Shapes.join(EMPTY_NEST_SHAPE, Block.box(4, 0, 4, 12, 8, 12), BooleanOp.OR);

    public OstrichNestBlock() {
        super(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_BROWN).strength(0.5F).randomTicks());
        registerDefaultState(this.defaultBlockState().setValue(HATCH, 0).setValue(EGG, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EGG, HATCH);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack stack = player.getMainHandItem();
        boolean hasEgg = state.getValue(EGG);

        // prevent any use when rightclicked with lexicon
        if (player.getMainHandItem().getItem() == ModItems.LEXICON.get()) {
            return InteractionResult.PASS;
        }

        if (hasEgg && stack.isEmpty()) {
            //drop egg when present
            world.setBlock(pos, state.setValue(EGG, false).setValue(HATCH, 0), 2);
            world.playSound(player, pos, ModSounds.OSTRICH_EGG_REMOVED.get(), SoundSource.BLOCKS, 0.7F, 0.9F);
            if (!world.isClientSide) {
                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.OSTRICH_EGG.get()));
                itemEntity.setDefaultPickUpDelay();
                world.addFreshEntity(itemEntity);
            }
            return InteractionResult.SUCCESS;
        }
        if (!hasEgg && stack.is(ModItems.OSTRICH_EGG.get())) {
            //place egg when empty
            state = state.setValue(EGG, true).setValue(HATCH, 0);
            world.setBlock(pos, state, 2);
            world.playSound(player, pos, SoundEvents.SWEET_BERRY_BUSH_PLACE, SoundSource.BLOCKS, 0.7F, 0.9F);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return super.use(state, world, pos, player, handIn, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
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

    private boolean canGrow(Level world) {
        float f = world.getTimeOfDay(1.0F);
        if (f < 0.7F && f > 0.6F) {
            return true;
        } else {
            return world.random.nextInt(175) == 0;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(EGG) ? FULL_NEST_SHAPE : EMPTY_NEST_SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.OSTRICH_NEST_BLOCK;
    }

}
