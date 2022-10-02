package com.example.examplemod

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import net.minecraft.block.Block
import net.minecraft.data.DataGenerator
import net.minecraft.data.LootTableProvider
import net.minecraft.data.loot.BlockLootTables
import net.minecraft.loot.LootParameterSet
import net.minecraft.loot.LootParameterSets
import net.minecraft.loot.LootTable
import net.minecraft.loot.ValidationTracker
import net.minecraft.util.ResourceLocation
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier
import java.util.stream.Collectors


class ExampleModLootTableProvider(gen: DataGenerator) : LootTableProvider(gen) {
    override fun getTables(): List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> {
        return ImmutableList.of<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>>(
                Pair.of(Supplier{ExampleModBlockLootTables()}, LootParameterSets.BLOCK)
        )
    }

    override fun validate(map: Map<ResourceLocation, LootTable>, validationtracker: ValidationTracker?) {}

    private class ExampleModBlockLootTables : BlockLootTables() {
        override fun getKnownBlocks(): Iterable<Block> {
            return ExampleBlocks.getEntries().stream().map { it.get() }.collect(Collectors.toList())
        }

        override fun addTables() {
            dropSelf(ExampleBlocks.TITANIUM_BLOCK.get())
        }
    }
}
