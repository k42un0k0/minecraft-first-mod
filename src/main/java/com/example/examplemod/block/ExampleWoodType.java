package com.example.examplemod.block;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.WoodType;
import net.minecraft.util.ResourceLocation;

public class ExampleWoodType {
    public static final WoodType REDWOOD =
            WoodType.create(new ResourceLocation(ExampleMod.MOD_ID,"redwood").toString());

}
