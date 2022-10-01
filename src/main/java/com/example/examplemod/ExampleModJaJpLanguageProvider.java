package com.example.examplemod;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;
import net.minecraftforge.common.data.LanguageProvider;

public class ExampleModJaJpLanguageProvider  extends LanguageProvider {
    public ExampleModJaJpLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        add(Blocks.TITANIUM_BLOCK.get(), "チタンブロック");
        add(Items.TITANIUM_INGOT.get(), "チタンインゴット");
    }
}
