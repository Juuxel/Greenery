package juuxel.greenery.mixin;

import juuxel.greenery.block.GreeneryBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.ForestFlowerFeature;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ForestFlowerFeature.class)
public class ForestFlowerFeatureMixin {
    @Shadow
    @Final
    @Mutable
    private static Block[] FLOWERS;

    static {
        FLOWERS = ArrayUtils.add(FLOWERS, GreeneryBlocks.TANSY);
    }
}
