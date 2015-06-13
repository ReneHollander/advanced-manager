package at.renehollander.advancedmanager.init;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManager;

import java.util.HashSet;
import java.util.Set;

public class ModBlocks {

    public static Set<BlockAdvancedManager> blocks;

    public static void init() {
        blocks = new HashSet<>();
    }

    private static void registerBlock(BlockAdvancedManager block) {
        blocks.add(block);
    }
}