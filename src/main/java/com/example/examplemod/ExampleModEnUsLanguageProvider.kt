package com.example.examplemod

import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

class ExampleModEnUsLanguageProvider(gen: DataGenerator, modId: String) : LanguageProvider(gen,modId,"en_us") {
    override fun addTranslations() {
        add(ExampleBlocks.TITANIUM_BLOCK.get(), "Titanium Block")
        add(ExampleItems.TITANIUM_INGOT.get(), "Titanium Ingot")
    }
}
