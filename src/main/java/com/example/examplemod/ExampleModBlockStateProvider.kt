package com.example.examplemod

import net.minecraft.block.Block
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper


class ExampleModBlockStateProvider(gen: DataGenerator, modId: String, existingFileHelper: ExistingFileHelper) : BlockStateProvider(gen,modId,existingFileHelper){
    override fun registerStatesAndModels() {
        simpleBlockWithItem(ExampleBlocks.TITANIUM_BLOCK.get())
    }

    private fun simpleBlockWithItem(block: Block) {
        val model = cubeAll(block)
        simpleBlock(block, model)
        simpleBlockItem(block, model)
    }
}
