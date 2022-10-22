package com.example.examplemod.tileentity;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExampleTileEntities {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ExampleMod.MOD_ID);

    public static RegistryObject<TileEntityType<LightningChannelerTile>> LIGHTNING_CHANNELER_TILE =
            TILE_ENTITIES.register("lightning_canneler_tile",
                    () -> TileEntityType.Builder.of(LightningChannelerTile::new,
                            ExampleBlocks.LIGHTNING_CHANNELER.get()).build(null));

    public static final RegistryObject<TileEntityType<ExampleSignTileEntity>> SIGN_TILE_ENTITIES =
            TILE_ENTITIES.register("sign",
                    () -> TileEntityType.Builder.of(ExampleSignTileEntity::new,
                            ExampleBlocks.REDWOOD_SIGN.get(),
                            ExampleBlocks.REDWOOD_WALL_SIGN.get()
                    ).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
