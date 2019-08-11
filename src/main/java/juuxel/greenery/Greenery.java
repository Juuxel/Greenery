package juuxel.greenery;

import juuxel.greenery.block.GreeneryBlocks;
import juuxel.greenery.feature.GreeneryFeatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

public final class Greenery {
    public static final String ID = "greenery";

    public static Identifier id(String path) {
        return new Identifier(ID, path);
    }

    public static void init() {
        GreeneryBlocks.init();
        GreeneryFeatures.init();
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        GreeneryBlocks.initClient();
    }
}
