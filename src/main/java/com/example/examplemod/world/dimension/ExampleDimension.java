package com.example.examplemod.world.dimension;

import com.example.examplemod.ExampleMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ExampleDimension {
    public static RegistryKey<World> KJDim = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(ExampleMod.MOD_ID, "kjdim"));
}
