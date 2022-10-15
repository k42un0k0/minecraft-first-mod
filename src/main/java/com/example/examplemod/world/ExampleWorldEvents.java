package com.example.examplemod.world;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.world.gen.ExampleOreGeneration;
import com.example.examplemod.world.gen.ExampleTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ExampleWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ExampleOreGeneration.generateOres(event);
        ExampleTreeGeneration.generateTrees(event);
    }
}
