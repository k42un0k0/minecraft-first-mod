package com.example.examplemod;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.block.ExampleWoodType;
import com.example.examplemod.codegen.assets.ExampleModBlockStateProvider;
import com.example.examplemod.codegen.assets.ExampleModItemModelProvider;
import com.example.examplemod.codegen.assets.lang.ExampleModEnUsLanguageProvider;
import com.example.examplemod.codegen.assets.lang.ExampleModJaJpLanguageProvider;
import com.example.examplemod.codegen.data.ExampleModLootTableProvider;
import com.example.examplemod.codegen.data.ExampleModRecipeProvider;
import com.example.examplemod.codegen.data.advancements.ExampleModAdvancementProvider;
import com.example.examplemod.codegen.data.tags.ExampleModBlockTagsProvider;
import com.example.examplemod.codegen.data.tags.ExampleModFluidTagsProvider;
import com.example.examplemod.codegen.data.tags.ExampleModItemTagsProvider;
import com.example.examplemod.container.ExampleContainers;
import com.example.examplemod.data.recipes.ExampleRecipeTypes;
import com.example.examplemod.entity.ExampleEntityTypes;
import com.example.examplemod.entity.render.BuffZombieRenderer;
import com.example.examplemod.entity.render.PigeonRenderer;
import com.example.examplemod.fluid.ExampleFluids;
import com.example.examplemod.item.ExampleItems;
import com.example.examplemod.screen.LightningChannelerScreen;
import com.example.examplemod.tileentity.ExampleTileEntities;
import com.example.examplemod.util.ExampleSoundEvents;
import com.example.examplemod.world.structure.ExampleStructures;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

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
        ExampleTileEntities.register(modEventBus);
        ExampleEntityTypes.register(modEventBus);
        ExampleContainers.register(modEventBus);
        ExampleStructures.register(modEventBus);
        ExampleFluids.register(modEventBus);
        ExampleRecipeTypes.register(modEventBus);
        ExampleSoundEvents.register(modEventBus);
        modEventBus.addListener(this::registerProviders);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", net.minecraft.block.Blocks.DIRT.getRegistryName());
        event.enqueueWork(() -> {
            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                    .put(ExampleBlocks.REDWOOD_LOG.get(), ExampleBlocks.STRIPPED_REDWOOD_LOG.get())
                    .put(ExampleBlocks.REDWOOD_WOOD.get(), ExampleBlocks.STRIPPED_REDWOOD_WOOD.get()).build();
            ExampleStructures.setupStructures();

            WoodType.register(ExampleWoodType.REDWOOD);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // do something that can only be done on the client
            LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
            ClientPlayerEntity player = Minecraft.getInstance().player;
            RenderingRegistry.registerEntityRenderingHandler(ExampleEntityTypes.BUFF_ZOMBIE.get(),
                    BuffZombieRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler(ExampleEntityTypes.PIGEON.get(), PigeonRenderer::new);
            RenderTypeLookup.setRenderLayer(ExampleBlocks.AMETHYST_DOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ExampleBlocks.AMETHYST_TRAPDOOR.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ExampleBlocks.OATS.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ExampleBlocks.REDWOOD_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ExampleBlocks.REDWOOD_SAPLING.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ExampleBlocks.HYACINTH.get(), RenderType.cutout());
            ScreenManager.register(ExampleContainers.LIGHTNING_CHANNELER_CONTAINER.get(),
                    LightningChannelerScreen::new);
            ClientRegistry.bindTileEntityRenderer(ExampleTileEntities.SIGN_TILE_ENTITIES.get(),
                    SignTileEntityRenderer::new);
            Atlases.addWoodType(ExampleWoodType.REDWOOD);
            RenderTypeLookup.setRenderLayer(ExampleFluids.OIL_FLUID.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ExampleFluids.OIL_BLOCK.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ExampleFluids.OIL_FLOWING.get(), RenderType.translucent());

        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> SlotTypePreset.CHARM.getMessageBuilder().build());
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

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing
    // to the MOD
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
            gen.addProvider(new ExampleModAdvancementProvider(gen, event.getExistingFileHelper()));
            gen.addProvider(new ExampleModRecipeProvider(gen));
            gen.addProvider(new ExampleModLootTableProvider(gen));
            BlockTagsProvider blockTagsProvider = new ExampleModBlockTagsProvider(gen, MOD_ID,
                    event.getExistingFileHelper());
            gen.addProvider(blockTagsProvider);
            gen.addProvider(new ExampleModItemTagsProvider(gen, blockTagsProvider, MOD_ID,
                    event.getExistingFileHelper()));
            gen.addProvider(new ExampleModFluidTagsProvider(gen, MOD_ID,
                    event.getExistingFileHelper()));

        }
    }
}
