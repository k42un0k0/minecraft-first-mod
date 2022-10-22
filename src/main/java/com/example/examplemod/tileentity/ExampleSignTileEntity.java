package com.example.examplemod.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ExampleSignTileEntity extends SignTileEntity {
    public ExampleSignTileEntity(){
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return ExampleTileEntities.SIGN_TILE_ENTITIES.get();
    }
}
