package com.example.examplemod.codegen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.util.ExampleTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;


public class ExampleModBlockTagsProvider extends BlockTagsProvider {

    public ExampleModBlockTagsProvider(DataGenerator gen, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ExampleTags.Blocks.FIRESTONE_CLICKABLE_BLOCKS)
                .add(Blocks.OBSIDIAN,Blocks.NETHERRACK, ExampleBlocks.FIRESTONE_BLOCK.get());
    }
}
