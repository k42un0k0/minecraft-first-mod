package com.example.examplemod.codegen;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleModEnUsLanguageProvider extends LanguageProvider {
    public ExampleModEnUsLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ExampleBlocks.TITANIUM_BLOCK.get(), "Block of Titanium");
        add(ExampleItems.TITANIUM_INGOT.get(), "Titanium Ingot");
    }
}
