package com.example.examplemod.codegen;

import com.example.examplemod.ExampleBlocks;
import com.example.examplemod.ExampleItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleModJaJpLanguageProvider  extends LanguageProvider {
    public ExampleModJaJpLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        add(ExampleBlocks.TITANIUM_BLOCK.get(), "チタンブロック");
        add(ExampleItems.TITANIUM_INGOT.get(), "チタンインゴット");
    }
}
