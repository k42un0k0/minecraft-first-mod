package com.example.examplemod.entity;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.custom.BuffZombieEntity;
import com.example.examplemod.entity.custom.PigeonEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExampleEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, ExampleMod.MOD_ID);

    public static final RegistryObject<EntityType<BuffZombieEntity>> BUFF_ZOMBIE = ENTITY_TYPES.register("buff_zombie",
            ()->EntityType.Builder.<BuffZombieEntity>of(BuffZombieEntity::new, EntityClassification.MONSTER).sized(1f,3f)
                    .build(new ResourceLocation(ExampleMod.MOD_ID,"buff_zombie").toString()));
    public static final RegistryObject<EntityType<PigeonEntity>> PIGEON = ENTITY_TYPES.register("pigeon",
            ()->EntityType.Builder.<PigeonEntity>of(PigeonEntity::new, EntityClassification.MONSTER).sized(1f,3f)
                    .build(new ResourceLocation(ExampleMod.MOD_ID,"pigeon").toString()));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
