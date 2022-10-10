package com.example.examplemod.codegen;

import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExampleModBlockStateProvider extends BlockStateProvider {
    public ExampleModBlockStateProvider(DataGenerator gen, String modId, ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ExampleBlocks.TITANIUM_BLOCK.get());
        simpleBlockWithItem(ExampleBlocks.AMETHYST_BLOCK.get());
        simpleBlockWithItem(ExampleBlocks.AMETHYST_ORE.get());
        simpleBlockWithItem(ExampleBlocks.FIRESTONE_BLOCK.get());
    }

    private void simpleBlockWithItem(Block block) {
        ModelFile model = cubeAll(block);
        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }
}
