package io.github.fifthtundrag.fommands.neoforge;

import io.github.fifthtundrag.fommands.Fommands;
import net.neoforged.fml.common.Mod;

@Mod(Fommands.MOD_ID)
public final class FommandsNeoForge {
    public FommandsNeoForge() {
        // Run our common setup.
        Fommands.init();
    }
}
