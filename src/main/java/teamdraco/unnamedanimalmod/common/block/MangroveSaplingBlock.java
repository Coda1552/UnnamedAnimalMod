package teamdraco.unnamedanimalmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.ForgeEventFactory;
import teamdraco.unnamedanimalmod.init.UAMFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class MangroveSaplingBlock extends SaplingBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public MangroveSaplingBlock(Properties properties) {
        super(null, properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.getFluidTicks().hasScheduledTick(currentPos, Fluids.WATER);
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(STAGE);
    }

    @Override
    public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, RandomSource rand) {
        if (state.getValue(STAGE) == 0) {
            world.setBlock(pos, state.cycle(STAGE), 4);
        }
        else {
            if (!ForgeEventFactory.saplingGrowTree(world, rand, pos)) {
                return;
            }
            if (state.getValue(WATERLOGGED)) {
                UAMFeatures.WATER_TREE_FEATURE.get().place(NoneFeatureConfiguration.NONE, world, world.getChunkSource().getGenerator(), RandomSource.create(), pos);
            }
            else {
                UAMFeatures.LAND_TREE_FEATURE.get().place(NoneFeatureConfiguration.NONE, world, world.getChunkSource().getGenerator(), RandomSource.create(), pos);
            }
        }
    }
    
}
