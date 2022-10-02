package com.example.examplemod

import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

class ExampleModJaJpLanguageProvider(gen: DataGenerator, modId: String) : LanguageProvider(gen, modId, "ja_jp") {
    override fun addTranslations() {
        add(ExampleBlocks.TITANIUM_BLOCK.get(), "チタンブロック")
        add(ExampleItems.TITANIUM_INGOT.get(), "チタンインゴット")
    }
}
