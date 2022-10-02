package com.example.examplemod

import net.minecraft.data.DataGenerator
import net.minecraft.item.Item
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraft.util.ResourceLocation
import java.util.*


class ExampleModItemModelProvider(gen: DataGenerator, modId: String, existingFileHelper: ExistingFileHelper?) :
    ItemModelProvider (gen,modId,existingFileHelper){
    override fun registerModels() {
        simpleItem(ExampleItems.TITANIUM_INGOT.get())
    }

    private fun simpleItem(item: Item) {
        val name: ResourceLocation = item.registryName!!
        singleTexture(
            name.path,
            mcLoc("$folder/generated"),
            "layer0",
            ResourceLocation(name.namespace, folder + "/" + name.path)
        )
    }
}
