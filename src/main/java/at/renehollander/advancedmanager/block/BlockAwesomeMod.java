package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.creative.AwesomeModCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockAwesomeMod extends Block {

    public BlockAwesomeMod(Material material) {
        super(material);
        setCreativeTab(AwesomeModCreativeTab.AWESOME_MOD_CREATIVE_TAB);
    }

    public BlockAwesomeMod() {
        this(Material.rock);
    }
}
