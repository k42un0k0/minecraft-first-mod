package com.example.examplemod;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.codegen.*;
import com.example.examplemod.entity.ExampleEntityTypes;
import com.example.examplemod.entity.custom.BuffZombieEntity;
import com.example.examplemod.entity.custom.PigeonEntity;
import com.example.examplemod.entity.render.BuffZombieRenderer;
import com.example.examplemod.entity.render.PigeonRenderer;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ExampleItems.register(modEventBus);
        ExampleBlocks.register(modEventBus);
        ExampleEntityTypes.register(modEventBus);
        modEventBus.addListener(this::registerProviders);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", net.minecraft.block.Blocks.DIRT.getRegistryName());

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
        ClientPlayerEntity player = Minecraft.getInstance().player;
        RenderingRegistry.registerEntityRenderingHandler(ExampleEntityTypes.BUFF_ZOMBIE.get(), BuffZombieRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExampleEntityTypes.PIGEON.get(), PigeonRenderer::new);
        RenderTypeLookup.setRenderLayer(ExampleBlocks.AMETHYST_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ExampleBlocks.AMETHYST_TRAP_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ExampleBlocks.OATS.get(), RenderType.cutout());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }


    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    private void registerProviders(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        if (event.includeClient()) {
            gen.addProvider(new ExampleModBlockStateProvider(gen, MOD_ID, event.getExistingFileHelper()));
            gen.addProvider(new ExampleModItemModelProvider(gen, MOD_ID, event.getExistingFileHelper()));
            gen.addProvider(new ExampleModEnUsLanguageProvider(gen, MOD_ID));
            gen.addProvider(new ExampleModJaJpLanguageProvider(gen, MOD_ID));
        }
        if (event.includeServer()) {
            gen.addProvider(new ExampleModRecipeProvider(gen));
            gen.addProvider(new ExampleModLootTableProvider(gen));
            BlockTagsProvider blockTagsProvider = new ExampleModBlockTagsProvider(gen,MOD_ID,event.getExistingFileHelper());
            gen.addProvider(blockTagsProvider);
            gen.addProvider(new ExampleModItemTagsProvider(gen,blockTagsProvider,MOD_ID,event.getExistingFileHelper()));

        }
    }
}
