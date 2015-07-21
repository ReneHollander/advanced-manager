package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TileEntityHandler {

    private static Set<BlockAdvancedManagerTileEntity> tileentities;

    public static BlockAdvancedManagerTileEntity advancedcable;
    public static BlockAdvancedManagerTileEntity advancedcontroller;
    public static BlockAdvancedManagerTileEntity advancedscreen;

    public static void preInit() {
        tileentities = new HashSet<>();

        registerBlock(advancedcable = new BlockAdvancedCable());
        registerBlock(advancedcontroller = new BlockAdvancedController());
        registerBlock(advancedscreen = new BlockAdvancedScreen());
    }

    public static Set<BlockAdvancedManagerTileEntity> tileEntities() {
        return Collections.unmodifiableSet(tileentities);
    }

    private static void registerBlock(BlockAdvancedManagerTileEntity block) {
        tileentities.add(block);
    }

}
