package juuxel.greenery.block;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.OakTreeFeature;

import java.util.Random;

public class AspenSaplingGenerator extends SaplingGenerator {
    public static final AspenSaplingGenerator INSTANCE = new AspenSaplingGenerator();

    protected AspenSaplingGenerator() {}

    @Override
    protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random random) {
        return createTreeFeature(true);
    }

    public AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(boolean neighborUpdates) {
        return new OakTreeFeature(
                DefaultFeatureConfig::deserialize, neighborUpdates,
                /* height: */ 5,
                GreeneryBlocks.ASPEN_LOG.getDefaultState(),
                GreeneryBlocks.ASPEN_LEAVES.getDefaultState(),
                /* vinesAndCocoa: */ false
        );
    }
}
