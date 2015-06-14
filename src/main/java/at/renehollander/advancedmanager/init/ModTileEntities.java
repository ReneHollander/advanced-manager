package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.block.BlockAdvancedCable;
import at.renehollander.advancedmanager.block.BlockAdvancedController;
import at.renehollander.advancedmanager.block.BlockAdvancedScreen;
import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;

import java.util.HashSet;
import java.util.Set;

public class ModTileEntities {

    public static Set<BlockAdvancedManagerTileEntity> tileentities;

    public static BlockAdvancedManagerTileEntity advancedcable;
    public static BlockAdvancedManagerTileEntity advancedcontroller;
    public static BlockAdvancedManagerTileEntity advancedscreen;

    public static void init() {
        tileentities = new HashSet<>();

        registerBlock(advancedcable = new BlockAdvancedCable());
        registerBlock(advancedcontroller = new BlockAdvancedController());
        registerBlock(advancedscreen = new BlockAdvancedScreen());
    }

    private static void registerBlock(BlockAdvancedManagerTileEntity block) {
        tileentities.add(block);
    }

}
