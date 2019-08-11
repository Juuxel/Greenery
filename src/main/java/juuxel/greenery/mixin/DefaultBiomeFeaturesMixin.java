package juuxel.greenery.mixin;

import juuxel.greenery.feature.GreeneryFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(method = "addPlainsFeatures", at = @At("RETURN"))
    private static void onAddPlainsFeatures(Biome biome, CallbackInfo info) {
        GreeneryFeatures.addPlainsFeatures(biome);
    }
}
