package juuxel.greenery.feature;

import juuxel.greenery.Greenery;
import juuxel.greenery.block.AspenSaplingGenerator;
import juuxel.greenery.block.GreeneryBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;

public final class GreeneryFeatures {
    public static final FlowerFeature SMALL_FLOWERS = new RandomFlowerFeature(GreeneryBlocks.TANSY);
    public static final Feature<DefaultFeatureConfig> ASPEN_TREE = AspenSaplingGenerator.INSTANCE.createTreeFeature(false);

    public static void init() {
        Registry.register(Registry.FEATURE, Greenery.id("small_flowers"), SMALL_FLOWERS);
        Registry.register(Registry.FEATURE, Greenery.id("aspen_tree"), ASPEN_TREE);
    }

    public static void addPlainsFeatures(Biome biome) {
        biome.addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                Biome.configureFeature(
                        Feature.SIMPLE_RANDOM_SELECTOR,
                        new SimpleRandomFeatureConfig(
                                new Feature[] { Feature.DOUBLE_PLANT, SMALL_FLOWERS },
                                new FeatureConfig[] {
                                        new DoublePlantFeatureConfig(GreeneryBlocks.FIREWEED.getDefaultState()),
                                        DefaultFeatureConfig.DEFAULT
                                }
                        ),
                        Decorator.CHANCE_TOP_SOLID_HEIGHTMAP,
                        new ChanceDecoratorConfig(20)
                )
        );

        // TODO: Move to a better place
        biome.addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                Biome.configureFeature(
                        ASPEN_TREE, FeatureConfig.DEFAULT,
                        Decorator.CHANCE_TOP_SOLID_HEIGHTMAP,
                        new ChanceDecoratorConfig(20)
                )
        );
    }
}
