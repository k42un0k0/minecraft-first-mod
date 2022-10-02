package com.example.examplemod

import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries


object ExampleItems {
    private val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID)
    val TITANIUM_INGOT: RegistryObject<Item> = ITEMS.register("titanium_ingot"
    ) {
        Item(
            Item.Properties()
                .tab(ItemGroup.TAB_MATERIALS)
        )
    }
    val TITANIUM_BLOCK = ITEMS.register<Item>(
        "titanium_block"
    ) {
        BlockItem(
            ExampleBlocks.TITANIUM_BLOCK.get(), Item.Properties()
                .tab(ItemGroup.TAB_BUILDING_BLOCKS)
        )
    }

    fun register(eventBus: IEventBus?) {
        ITEMS.register(eventBus)
    }
}