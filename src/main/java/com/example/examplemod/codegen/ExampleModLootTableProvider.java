package com.example.examplemod.codegen;


import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ExampleModLootTableProvider extends LootTableProvider {
    public ExampleModLootTableProvider(DataGenerator gen) {
        super(gen);
    }
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(ExampleModLootTables::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
    }

    private static class ExampleModLootTables extends BlockLootTables {
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ExampleBlocks.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }

        @Override
        protected void addTables() {
            dropSelf(ExampleBlocks.TITANIUM_BLOCK.get());
            dropSelf(ExampleBlocks.AMETHYST_BLOCK.get());
            dropSelf(ExampleBlocks.FIRESTONE_BLOCK.get());
            dropSelf(ExampleBlocks.AMETHYST_STAIRS.get());
            dropSelf(ExampleBlocks.AMETHYST_WALL.get());
            dropSelf(ExampleBlocks.AMETHYST_DOOR.get());
            dropSelf(ExampleBlocks.AMETHYST_TRAP_DOOR.get());
            dropSelf(ExampleBlocks.AMETHYST_SLAB.get());
            dropSelf(ExampleBlocks.AMETHYST_PANE.get());
            dropOther(ExampleBlocks.AMETHYST_ORE.get(), ExampleItems.AMETHYST.get());
            add(ExampleBlocks.AMETHYST_ORE.get(), (block) ->
                    createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(ExampleItems.AMETHYST.get())
                            .apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
                            .apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
        }
    }

}
