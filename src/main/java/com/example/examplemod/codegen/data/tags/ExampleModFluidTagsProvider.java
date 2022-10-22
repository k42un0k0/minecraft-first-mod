package com.example.examplemod.codegen.data.tags;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.fluid.ExampleFluids;
import com.example.examplemod.util.ExampleTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;


public class ExampleModFluidTagsProvider extends FluidTagsProvider {

    public ExampleModFluidTagsProvider(DataGenerator gen, String modId,
                                       @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(FluidTags.WATER)
                .add(ExampleFluids.OIL_FLUID.get(), ExampleFluids.OIL_FLOWING.get());
    }
}
