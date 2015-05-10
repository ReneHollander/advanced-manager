package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.creative.AwesomeModCreativeTab;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BlockAwesomeModTileEntity extends BlockContainer implements IBlockAwesomeMod {

    private final String name;

    public BlockAwesomeModTileEntity(Material material, String name, Class<? extends TileEntity> teClass) {
        super(material);
        this.name = name;

        setUnlocalizedName(Reference.MODID + "_" + this.getName());
        setCreativeTab(AwesomeModCreativeTab.AWESOME_MOD_CREATIVE_TAB);

        GameRegistry.registerBlock(this, getName());
        GameRegistry.registerTileEntity(teClass, Reference.MODID + ":" + getName());
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getRenderType() {
        return 3;
    }
}
