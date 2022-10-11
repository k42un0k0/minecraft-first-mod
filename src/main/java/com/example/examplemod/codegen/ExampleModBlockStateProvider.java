package com.example.examplemod.codegen;

import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
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
        door((DoorBlock) ExampleBlocks.AMETHYST_DOOR.get(),ExampleBlocks.AMETHYST_BLOCK.get());
        trapdoor((TrapDoorBlock) ExampleBlocks.AMETHYST_TRAP_DOOR.get(),ExampleBlocks.AMETHYST_BLOCK.get());
        slab((SlabBlock) ExampleBlocks.AMETHYST_SLAB.get(),ExampleBlocks.AMETHYST_BLOCK.get());
        pane((PaneBlock) ExampleBlocks.AMETHYST_PANE.get(),ExampleBlocks.AMETHYST_BLOCK.get());
    }

    private void stairs(StairsBlock block,Block textureBlock){
        stairsBlock(block,blockTexture(textureBlock));
        ModelFile model = models().getExistingFile(block.getRegistryName());
        simpleBlockItem(block,model);
    }

    private void wall(WallBlock block, Block textureBlock){
        wallBlock(block,blockTexture(textureBlock));
        ModelFile inventory = models().wallInventory(block.getRegistryName().toString()+"_inventory",blockTexture(textureBlock));
        simpleBlockItem(block,inventory);
    }

    private void door(DoorBlock block, Block textureBlock){
        //MEMO: doorのitem modelはitem側で生成
        doorBlock(block,blockTexture(textureBlock),blockTexture(textureBlock));
    }

    private void trapdoor(TrapDoorBlock block, Block textureBlock){
        trapdoorBlock(block,blockTexture(textureBlock),true);
        ResourceLocation location = new ResourceLocation(block.getRegistryName().getNamespace(),block.getRegistryName().getPath() + "_bottom");
        ModelFile bottom = models().getExistingFile(location);
        simpleBlockItem(block,bottom);
    }

    private void pane(PaneBlock block, Block textureBlock){
        paneBlock(block,blockTexture(textureBlock),blockTexture(textureBlock));
    }

    private void slab(SlabBlock block, Block textureBlock){
        slabBlock(block,blockTexture(textureBlock),blockTexture(textureBlock));
        ModelFile model = models().getExistingFile(block.getRegistryName());
        simpleBlockItem(block,model);
    }

    private void simpleBlockWithItem(Block block) {
        ModelFile model = cubeAll(block);
        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }

}
