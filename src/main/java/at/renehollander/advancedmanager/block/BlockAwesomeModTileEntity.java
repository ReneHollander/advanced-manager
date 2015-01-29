package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.creative.AwesomeModCreativeTab;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockAwesomeModTileEntity extends BlockContainer {

    public BlockAwesomeModTileEntity(Material material) {
        super(material);
        setCreativeTab(AwesomeModCreativeTab.AWESOME_MOD_CREATIVE_TAB);
    }

    public BlockAwesomeModTileEntity() {
        this(Material.rock);
    }

}
