package com.example.examplemod.codegen;

import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.state.properties.AttachFace;

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
        stairs((StairsBlock) ExampleBlocks.AMETHYST_STAIRS.get(), ExampleBlocks.AMETHYST_BLOCK.get());
        wall((WallBlock) ExampleBlocks.AMETHYST_WALL.get(), ExampleBlocks.AMETHYST_BLOCK.get());
        door((DoorBlock) ExampleBlocks.AMETHYST_DOOR.get(), ExampleBlocks.AMETHYST_BLOCK.get());
        trapdoor((TrapDoorBlock) ExampleBlocks.AMETHYST_TRAP_DOOR.get(), ExampleBlocks.AMETHYST_BLOCK.get());
        slab((SlabBlock) ExampleBlocks.AMETHYST_SLAB.get(), ExampleBlocks.AMETHYST_BLOCK.get());
        pane((PaneBlock) ExampleBlocks.AMETHYST_PANE.get(), ExampleBlocks.AMETHYST_BLOCK.get());
        button((AbstractButtonBlock) ExampleBlocks.AMETHYST_BUTTON.get(), ExampleBlocks.AMETHYST_BLOCK.get());
    }

    private void stairs(StairsBlock block, Block textureBlock) {
        stairsBlock(block, blockTexture(textureBlock));
        ModelFile model = models().getExistingFile(block.getRegistryName());
        simpleBlockItem(block, model);
    }

    private void wall(WallBlock block, Block textureBlock) {
        wallBlock(block, blockTexture(textureBlock));
        ModelFile inventory = models().wallInventory(block.getRegistryName().toString() + "_inventory", blockTexture(textureBlock));
        simpleBlockItem(block, inventory);
    }

    private void door(DoorBlock block, Block textureBlock) {
        //MEMO: doorのitem modelはitem側で生成
        doorBlock(block, blockTexture(textureBlock), blockTexture(textureBlock));
    }

    private void button(AbstractButtonBlock block, Block textureBlock) {
        String baseName = block.getRegistryName().toString();
        ModelFile inventory =  models().singleTexture(baseName + "_inventory", mcLoc("block/button_inventory"), blockTexture(textureBlock));
        simpleBlockItem(block, inventory);

        ModelFile pressed = models().singleTexture(baseName + "_pressed", mcLoc("block/button_pressed"), blockTexture(textureBlock));
        ModelFile button = models().singleTexture(baseName, mcLoc("block/button"), blockTexture(textureBlock));
        getVariantBuilder(block).forAllStates(state -> {
            int yRot = ((int) state.getValue(AbstractButtonBlock.FACING).toYRot() +180)%360;
            int xRot = 0;
            boolean uvLock = false;
            switch (state.getValue(AbstractButtonBlock.FACE)) {
                case FLOOR:
                    xRot = 0;
                    break;
                case WALL:
                    xRot = 90;
                    uvLock = true;
                    break;
                case CEILING:
                    xRot = 180;
                    yRot = (yRot + 180) % 360;
                    break;
            }

            return ConfiguredModel.builder().modelFile(state.getValue(AbstractButtonBlock.POWERED) ? pressed : button)
                    .rotationY(yRot)
                    .rotationX(xRot)
                    .uvLock(uvLock)
                    .build();
        });
    }

    private void trapdoor(TrapDoorBlock block, Block textureBlock) {
        trapdoorBlock(block, blockTexture(textureBlock), true);
        ResourceLocation location = new ResourceLocation(block.getRegistryName().getNamespace(), block.getRegistryName().getPath() + "_bottom");
        ModelFile bottom = models().getExistingFile(location);
        simpleBlockItem(block, bottom);
    }

    private void pane(PaneBlock block, Block textureBlock) {
        paneBlock(block, blockTexture(textureBlock), blockTexture(textureBlock));
    }

    private void slab(SlabBlock block, Block textureBlock) {
        slabBlock(block, blockTexture(textureBlock), blockTexture(textureBlock));
        ModelFile model = models().getExistingFile(block.getRegistryName());
        simpleBlockItem(block, model);
    }

    private void simpleBlockWithItem(Block block) {
        ModelFile model = cubeAll(block);
        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }


}
