package juuxel.greenery.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BigFlowerBlock extends FlowerBlock {
    public BigFlowerBlock(StatusEffect effect, int duration, Settings settings) {
        super(effect, duration, settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, EntityContext context) {
        Vec3d offset = state.getOffsetPos(world, pos);
        return VoxelShapes.fullCube().offset(offset.x, offset.y, offset.z);
    }
}
