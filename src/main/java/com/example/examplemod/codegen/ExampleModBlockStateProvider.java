package com.example.examplemod.codegen;

import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

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
        pressurePlate((PressurePlateBlock) ExampleBlocks.AMETHYST_PRESSURE_PLATE.get(), ExampleBlocks.AMETHYST_BLOCK.get());
        crop((CropsBlock) ExampleBlocks.OATS.get());
        simpleBlockWithItem(ExampleBlocks.REDWOOD_PLANKS.get());
        logAndWood((RotatedPillarBlock) ExampleBlocks.REDWOOD_LOG.get(), (RotatedPillarBlock) ExampleBlocks.REDWOOD_WOOD.get());
        logAndWood((RotatedPillarBlock) ExampleBlocks.STRIPPED_REDWOOD_LOG.get(), (RotatedPillarBlock) ExampleBlocks.STRIPPED_REDWOOD_WOOD.get());
        sapling(ExampleBlocks.REDWOOD_SAPLING.get());
        leaves(ExampleBlocks.REDWOOD_LEAVES.get());
    }

    private void stairs(StairsBlock block, Block textureBlock) {
        stairsBlock(block, blockTexture(textureBlock));
        ModelFile model = models().getExistingFile(block.getRegistryName());
        simpleBlockItem(block, model);
    }

    private void wall(WallBlock block, Block textureBlock) {
        wallBlock(block, blockTexture(textureBlock));
        ModelFile inventory = models().wallInventory(name(block) + "_inventory", blockTexture(textureBlock));
        simpleBlockItem(block, inventory);
    }

    private void door(DoorBlock block, Block textureBlock) {
        //MEMO: doorのitem modelはitem側で生成
        doorBlock(block, blockTexture(textureBlock), blockTexture(textureBlock));
    }

    private String name(Block block) {
        return block.getRegistryName().toString();
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

    private ResourceLocation replace(ResourceLocation rl, String target, String replacement) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath().replace(target, replacement));
    }

    private void crop(CropsBlock block) {
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(CropsBlock.AGE);
            ResourceLocation loc = extend(replace(blockTexture(block), "_crop", "_stage"), String.valueOf(age));
            ModelFile model = models().withExistingParent(loc.toString(), mcLoc("block/crop")).texture("crop", loc);
            return ConfiguredModel.builder().modelFile(model).build();
        });
    }

    private void button(AbstractButtonBlock block, Block textureBlock) {
        ModelFile inventory = models().singleTexture(extend(blockTexture(block), "_inventory").toString(), mcLoc("block/button_inventory"), blockTexture(textureBlock));
        simpleBlockItem(block, inventory);

        ModelFile pressed = models().singleTexture(extend(blockTexture(block), "_pressed").toString(), mcLoc("block/button_pressed"), blockTexture(textureBlock));
        ModelFile button = models().singleTexture(name(block), mcLoc("block/button"), blockTexture(textureBlock));
        getVariantBuilder(block).forAllStates(state -> {
            int yRot = ((int) state.getValue(AbstractButtonBlock.FACING).toYRot() + 180) % 360;
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

    private void pressurePlate(PressurePlateBlock block, Block textureBlock) {
        ModelFile plate = models().singleTexture(name(block), mcLoc("block/pressure_plate_up"), blockTexture(textureBlock));
        simpleBlockItem(block, plate);

        ModelFile down = models().singleTexture(extend(blockTexture(block), "_down").toString(), mcLoc("block/pressure_plate_down"), blockTexture(textureBlock));
        getVariantBuilder(block)
                .partialState().with(PressurePlateBlock.POWERED, true)
                .modelForState().modelFile(down).addModel()
                .partialState().with(PressurePlateBlock.POWERED, false)
                .modelForState().modelFile(plate).addModel();
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

    private void logAndWood(RotatedPillarBlock log, RotatedPillarBlock wood) {
        logBlock(log);
        ModelFile model = models().getExistingFile(blockTexture(log));
        simpleBlockItem(log, model);
        ModelFile strippedModel = models().cubeColumn(name(wood), blockTexture(log), blockTexture(log));
        axisBlock(wood, strippedModel, strippedModel);
        simpleBlockItem(wood, strippedModel);
    }

    private void sapling(Block block){
        ModelFile model = models().cross(name(block),blockTexture(block));
        simpleBlock(block,model);
        itemModels().withExistingParent(name(block),"generated").texture("layer0",blockTexture(block));
    }

    private void leaves(Block block){
        ModelFile model = models().withExistingParent(name(block),BLOCK_FOLDER+"/leaves").texture("all",blockTexture(block));
        simpleBlock(block,model);
        simpleBlockItem(block,model);
    }
}
