package juuxel.greenery.feature;

import juuxel.greenery.block.GreeneryBlocks;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.OakTreeFeature;

public class AspenTreeFeature extends OakTreeFeature {
    public AspenTreeFeature(boolean neighborUpdates) {
        super(DefaultFeatureConfig::deserialize, neighborUpdates, 5, GreeneryBlocks.ASPEN_LOG.getDefaultState(), GreeneryBlocks.ASPEN_LEAVES.getDefaultState(), false);
    }
}
