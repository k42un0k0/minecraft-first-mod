package com.example.examplemod

import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.versions.forge.ForgeVersion
import org.apache.logging.log4j.LogManager


// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MOD_ID)
class ExampleMod {
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    fun handler(event: EntityItemPickupEvent?) {
        // do something when the server starts
        LOGGER.info("pick upa")
    }

    companion object {
        const val MOD_ID = "examplemod"

        // Directly reference a log4j logger.
        private val LOGGER = LogManager.getLogger()
    }

    init {
        val modEventBus = FMLJavaModLoadingContext.get().modEventBus
        ExampleItems.register(modEventBus)
        ExampleBlocks.register(modEventBus)
        modEventBus.addListener<GatherDataEvent> {
            val gen = it.generator
            if (it.includeClient()) {
                gen.addProvider(ExampleModBlockStateProvider(gen, MOD_ID, it.existingFileHelper))
            gen.addProvider(ExampleModItemModelProvider(gen, MOD_ID, it.existingFileHelper))
                gen.addProvider(ExampleModEnUsLanguageProvider(gen, MOD_ID))
                gen.addProvider(ExampleModJaJpLanguageProvider(gen, MOD_ID))
            }
            if (it.includeServer()) {
            gen.addProvider(ExampleModRecipeProvider(gen))
            gen.addProvider(ExampleModLootTableProvider(gen))
            }
        }

    }

}