package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.ExampleEntityTypes;
import com.example.examplemod.entity.custom.BuffZombieEntity;
import com.example.examplemod.entity.custom.PigeonEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExampleEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ExampleEntityTypes.BUFF_ZOMBIE.get(), BuffZombieEntity.setCustomAttributes().build());
        event.put(ExampleEntityTypes.PIGEON.get(), PigeonEntity.setCustomAttributes().build());
    }
}
