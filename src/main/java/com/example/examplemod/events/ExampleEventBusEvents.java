package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.commands.HungerCommand;
import com.example.examplemod.entity.ExampleEntityTypes;
import com.example.examplemod.entity.custom.BuffZombieEntity;
import com.example.examplemod.entity.custom.PigeonEntity;
import com.example.examplemod.events.loot.FirestoneAdditionModifier;
import com.example.examplemod.events.loot.FirestoneStructureAdditionModifier;
import com.example.examplemod.item.custom.ExampleSpawnEggItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ExampleEntityTypes.BUFF_ZOMBIE.get(), BuffZombieEntity.setCustomAttributes().build());
        event.put(ExampleEntityTypes.PIGEON.get(), PigeonEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ExampleSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void colorEvent(ColorHandlerEvent.Block event) {
        ExampleMod.LOGGER.info("Register Color");
//        event.getBlockColors().register((p_228061_0_, p_228061_1_, p_228061_2_, p_228061_3_) -> {
//            return 0;
//        }, ExampleBlocks.REDWOOD_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new FirestoneAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(ExampleMod.MOD_ID, "firestone_from_magma")),
                new FirestoneStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(ExampleMod.MOD_ID, "firestone_in_igloo"))
        );
    }
}
