package juuxel.greenery.block;

import juuxel.greenery.Greenery;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.*;
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
    public static final Block TANSY = new BigFlowerBlock(StatusEffects.SLOW_FALLING, 10, createFlowerSettings());
    public static final Block POTTED_TANSY = new FlowerPotBlock(TANSY, FabricBlockSettings.of(Material.PART).breakInstantly().build());
    public static final Block ASPEN_LOG = new LogBlock(MaterialColor.SAND, FabricBlockSettings.of(Material.WOOD, MaterialColor.QUARTZ).strength(2.0F, 2.0F).sounds(BlockSoundGroup.WOOD).build());
    public static final Block ASPEN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F, 0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).build());
    public static final Block ASPEN_SAPLING = new GreenerySaplingBlock(new AspenSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
    // TODO: These wood variants (remember to add them to the tag)
//    public static final Block ASPEN_WOOD = new PillarBlock();
//    public static final Block STRIPPED_ASPEN_LOG = new PillarBlock();
//    public static final Block STRIPPED_ASPEN_WOOD = new PillarBlock();
    // TODO: Aspen planks + slabs, stairs, fences, gates, buttons, doors, trapdoors
    // TODO: Potted aspen sapling

    public static void init() {
        register("fireweed", FIREWEED, new TallBlockItem(FIREWEED, new Item.Settings().group(ItemGroup.DECORATIONS)));
        register("tansy", TANSY, ItemGroup.DECORATIONS);
        register("potted_tansy", POTTED_TANSY);
        register("aspen_log", ASPEN_LOG, ItemGroup.BUILDING_BLOCKS);
        register("aspen_leaves", ASPEN_LEAVES, ItemGroup.DECORATIONS);
        register("aspen_sapling", ASPEN_SAPLING, ItemGroup.DECORATIONS);
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

    // Registration with a default item in an item group
    private static void register(String id, Block block, ItemGroup group) {
        register(id, block, new Item.Settings().group(group));
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
