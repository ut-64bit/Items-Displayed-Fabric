package net.freedinner.items_displayed.block.custom.stackable;

import net.freedinner.items_displayed.util.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DiamondItemBlock extends AbstractStackableItemBlock {
    public static final VoxelShape NORTH_SOUTH_SHAPE_1 =
            Block.createCuboidShape(6.0, 0.0, 5.0, 10.0, 2.0, 11.0);
    public static final VoxelShape EAST_WEST_SHAPE_1 =
            Block.createCuboidShape(5.0, 0.0, 6.0, 11.0, 2.0, 10.0);
    public static final VoxelShape SHAPE_2 =
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 2.0, 14.0);
    public static final VoxelShape SHAPE_3 =
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 2.0, 14.0);
    public static final VoxelShape SHAPE_4 =
            Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);

    public DiamondItemBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected IntProperty getItemProperty() {
        return ModProperties.GEMSTONES;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int currItemCount = state.get(getItemProperty());

        return switch (currItemCount) {
            case 1 -> switch (state.get(FACING)) {
                case NORTH, SOUTH -> NORTH_SOUTH_SHAPE_1;
                case EAST, WEST -> EAST_WEST_SHAPE_1;
                default -> throw new IllegalStateException("Unexpected value: " + state.get(FACING));
            };
            case 2 -> SHAPE_2;
            case 3 -> SHAPE_3;
            case 4 -> SHAPE_4;
            default -> throw new IllegalStateException("Unexpected value: " + currItemCount);
        };
    }
}
