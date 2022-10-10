package com.example.examplemod.codegen;

import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
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
        stairs((StairsBlock) ExampleBlocks.AMETHYST_STAIRS.get(),ExampleBlocks.AMETHYST_BLOCK.get());
        wall((WallBlock) ExampleBlocks.AMETHYST_WALL.get(),ExampleBlocks.AMETHYST_BLOCK.get());
    }

    private void stairs(StairsBlock stairs,Block block){
        stairsBlock(stairs,blockTexture(block));
        ModelFile model = models().getExistingFile(stairs.getRegistryName());
        simpleBlockItem(stairs,model);
    }

    private void wall(WallBlock wall, Block block){
        wallBlock(wall,blockTexture(block));
        ModelFile inventory = models().wallInventory(wall.getRegistryName().toString()+"_inventory",blockTexture(block));
        simpleBlockItem(wall,inventory);
    }


    private void simpleBlockWithItem(Block block) {
        ModelFile model = cubeAll(block);
        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }

}
