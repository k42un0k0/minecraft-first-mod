package com.example.examplemod.codegen;


import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.LootTableIdCondition;
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
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

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
            dropSelf(ExampleBlocks.AMETHYST_BUTTON.get());
            dropSelf(ExampleBlocks.AMETHYST_PRESSURE_PLATE.get());
            ILootCondition.IBuilder builder = BlockStateProperty.hasBlockStateProperties(ExampleBlocks.OATS.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, 7));
            add(ExampleBlocks.OATS.get(),
                    LootTable.lootTable()
                            .withPool(LootPool.lootPool().add(
                                    ItemLootEntry.lootTableItem(ExampleItems.OATS.get())
                                            .when(builder).otherwise(ItemLootEntry.lootTableItem(ExampleItems.OATS.get()))))
                            .withPool(LootPool.lootPool().when(builder).add(
                                    ItemLootEntry.lootTableItem(ExampleItems.OATS.get()).apply(ApplyBonus.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 5))))
                            .apply(ExplosionDecay.explosionDecay())
            );
            add(ExampleBlocks.AMETHYST_ORE.get(), (block) ->
                    createSilkTouchDispatchTable(block, ItemLootEntry.lootTableItem(ExampleItems.AMETHYST.get())
                            .apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F)))
                            .apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                            .apply(ExplosionDecay.explosionDecay())));

            dropSelf(ExampleBlocks.REDWOOD_LOG.get());
            dropSelf(ExampleBlocks.REDWOOD_WOOD.get());
            dropSelf(ExampleBlocks.STRIPPED_REDWOOD_LOG.get());
            dropSelf(ExampleBlocks.STRIPPED_REDWOOD_WOOD.get());
            dropSelf(ExampleBlocks.REDWOOD_PLANKS.get());
            dropSelf(ExampleBlocks.REDWOOD_SAPLING.get());
            dropSelf(ExampleBlocks.REDWOOD_LEAVES.get());
            add(ExampleBlocks.REDWOOD_LEAVES.get(), (block) -> createLeavesDrops(block, ExampleBlocks.REDWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        }
    }

}
