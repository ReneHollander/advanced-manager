package at.renehollander.advancedmanager.block;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.creative.AwesomeModCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BlockAwesomeMod extends Block implements IBlockAwesomeMod {

    private final String name;

    public BlockAwesomeMod(Material material, String name) {
        super(material);
        this.name = name;

        setUnlocalizedName(Reference.MODID + "_" + this.getName());
        setCreativeTab(AwesomeModCreativeTab.AWESOME_MOD_CREATIVE_TAB);

        GameRegistry.registerBlock(this, Reference.MODID + ":" + getName());
    }

    public BlockAwesomeMod(String name) {
        this(Material.rock, name);
    }

    public String getName() {
        return this.name;
    }

}
