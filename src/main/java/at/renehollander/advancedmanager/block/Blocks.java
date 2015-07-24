package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Blocks {

    private static Set<BlockAdvancedManager> blocks;

    public static void preInit() {
        blocks = new HashSet<>();
    }

    public static Set<BlockAdvancedManager> blocks() {
        return Collections.unmodifiableSet(blocks);
    }

    private static void registerBlock(BlockAdvancedManager block) {
        blocks.add(block);
    }
}