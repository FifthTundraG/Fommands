package io.github.fifthtundrag.fommands.fabric;

import io.github.fifthtundrag.fommands.Fommands;
import net.fabricmc.api.ModInitializer;

public final class FommandsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Fommands.init();
    }
}
