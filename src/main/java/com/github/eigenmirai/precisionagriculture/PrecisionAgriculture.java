package com.github.eigenmirai.precisionagriculture;

import features.overlay.CatOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.Arrays;
import java.util.List;

@Mod(modid = "PrecisionAgriculture", useMetadata = true)
public class ExampleMod {
    public static final String MODID = "PrecisionAgriculture";
    public static final String VERSION = "1.0.0";
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Arrays.asList(
                new CatOverlay()
        ).forEach(MinecraftForge.EVENT_BUS::register);
    }
}
