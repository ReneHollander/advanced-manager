package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.block.BlockNetworkCable;
import at.renehollander.advancedmanager.block.BlockNetworkController;
import at.renehollander.advancedmanager.block.BlockRedstoneController;
import at.renehollander.advancedmanager.block.BlockRedstoneScreen;
import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;

import java.util.HashSet;
import java.util.Set;

public class ModTileEntities {

    public static Set<BlockAdvancedManagerTileEntity> tileentities;

    public static BlockAdvancedManagerTileEntity redstonecontroller;
    public static BlockAdvancedManagerTileEntity redstonescreen;
    public static BlockAdvancedManagerTileEntity networkcable;
    public static BlockAdvancedManagerTileEntity networkcontroller;

    public static void init() {
        tileentities = new HashSet<>();

        registerBlock(redstonecontroller = new BlockRedstoneController());
        registerBlock(redstonescreen = new BlockRedstoneScreen());
        registerBlock(networkcable = new BlockNetworkCable());
        registerBlock(networkcontroller = new BlockNetworkController());
    }

    private static void registerBlock(BlockAdvancedManagerTileEntity block) {
        tileentities.add((BlockAdvancedManagerTileEntity) block);
    }

}
