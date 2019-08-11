package juuxel.greenery.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFlowerFeature;

import java.util.Random;

// Extends DefaultFlowerFeature because generics + abstract methods = heart eyes cat
public class RandomFlowerFeature extends DefaultFlowerFeature {
    private final Block[] flowers;

    public RandomFlowerFeature(Block... flowers) {
        super(DefaultFeatureConfig::deserialize);
        this.flowers = flowers;
    }

    @Override
    public BlockState getFlowerToPlace(Random random, BlockPos pos) {
        int index = random.nextInt(flowers.length);
        return flowers[index].getDefaultState();
    }
}
