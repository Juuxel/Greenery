package juuxel.greenery.block;

import juuxel.greenery.Greenery;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.Material;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public final class GreeneryBlocks {
    public static final Block FIREWEED = new TallFlowerBlock(createFlowerSettings());
    public static final Block TANSY = new BigFlowerBlock(StatusEffects.SLOW_FALLING, 3, createFlowerSettings());
    public static final Block POTTED_TANSY = new FlowerPotBlock(TANSY, FabricBlockSettings.of(Material.PART).breakInstantly().build());

    public static void init() {
        register("fireweed", FIREWEED, new TallBlockItem(FIREWEED, new Item.Settings().group(ItemGroup.DECORATIONS)));
        register("tansy", TANSY, new Item.Settings().group(ItemGroup.DECORATIONS));
        register("potted_tansy", POTTED_TANSY);
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ColorProviderRegistry.BLOCK.register(
                (state, world, pos, i) ->
                        world != null && pos != null
                                ? BiomeColors.getFoliageColor(world, pos)
                                : FoliageColors.getColor(0.5D, 1.0D),
                FIREWEED, TANSY, POTTED_TANSY
        );

        ColorProviderRegistry.ITEM.register(
                (stack, i) -> {
                    if (i > 0) return -1;

                    BlockColors blockColors = MinecraftClient.getInstance().getBlockColorMap();
                    return blockColors.getColorMultiplier(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, i);
                },
                FIREWEED, TANSY
        );
    }

    // Registration with a default item
    private static void register(String id, Block block, Item.Settings itemSettings) {
        register(id, block, new BlockItem(block, itemSettings));
    }

    // Registration with a custom item
    private static void register(String id, Block block, Item item) {
        register(id, block);
        Registry.register(Registry.ITEM, Greenery.id(id), item);
    }

    // Registration with no items
    private static void register(String id, Block block) {
        Registry.register(Registry.BLOCK, Greenery.id(id), block);
    }

    private static Block.Settings createFlowerSettings() {
        return FabricBlockSettings
                .of(Material.REPLACEABLE_PLANT)
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.GRASS)
                .build();
    }
}
