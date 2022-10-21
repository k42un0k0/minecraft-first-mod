package com.example.examplemod.codegen;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

public class ExampleModAdvancementProvider extends AdvancementProvider {
    private final List<Consumer<Consumer<Advancement>>> tabs = ImmutableList.of(new ExampleAdvancements());

    public ExampleModAdvancementProvider(DataGenerator p_i244821_1_, ExistingFileHelper p_i244821_2_) {
        super(p_i244821_1_, p_i244821_2_);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer,
                                        ExistingFileHelper fileHelper) {
        for (Consumer<Consumer<Advancement>> consumer1 : this.tabs) {
            consumer1.accept(consumer);
        }
    }
}
