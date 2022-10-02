package com.example.examplemod

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.material.MaterialColor
import net.minecraftforge.common.ToolType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries


object ExampleBlocks {
    private val BLOCKS: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID)
    val TITANIUM_BLOCK: RegistryObject<Block> = BLOCKS.register("titanium_block"
    ) {
        Block(
            AbstractBlock.Properties
                .of(Material.METAL, MaterialColor.METAL)
                .requiresCorrectToolForDrops()
                .strength(5.0f, 6.0f)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1)
        )
    }

    fun register(eventBus: IEventBus?) {
        BLOCKS.register(eventBus)
    }

    fun getEntries(): Collection<RegistryObject<Block>> {
        return BLOCKS.entries
    }
}