package at.renehollander.advancedmanager.block.base;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.creative.AwesomeModCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BlockAdvancedManager extends Block implements IBlockAdvancedManager {

    private final String name;

    public BlockAdvancedManager(Material material, String name) {
        super(material);
        this.name = name;

        setUnlocalizedName(Reference.MODID + "_" + this.getName());
        setCreativeTab(AwesomeModCreativeTab.AWESOME_MOD_CREATIVE_TAB);

        GameRegistry.registerBlock(this, Reference.MODID + ":" + getName());
    }

    public BlockAdvancedManager(String name) {
        this(Material.rock, name);
    }

    public String getName() {
        return this.name;
    }

}
