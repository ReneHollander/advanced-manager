package at.renehollander.advancedmanager.block.base;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.creative.AwesomeModCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.Validate;

/**
 * Abstract Block class that takes common code and simplifies it,
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class BlockAdvancedManager extends Block {

    private final String name;

    /**
     * Creates a new block with the given material and name and registeres everything.
     *
     * @param material Material of the new block
     * @param name     Name of the new block
     */
    public BlockAdvancedManager(Material material, String name) {
        super(material);
        Validate.notNull(name);
        this.name = name;

        setUnlocalizedName(Reference.MODID + "_" + this.getName());
        setCreativeTab(AwesomeModCreativeTab.AWESOME_MOD_CREATIVE_TAB);

        GameRegistry.registerBlock(this, Reference.MODID + ":" + getName());
    }

    /**
     * Creates a new block with the given name and a default material (rock) and registeres everything.
     *
     * @param name Name of the block
     * @see BlockAdvancedManager#BlockAdvancedManager(Material, String)
     */
    public BlockAdvancedManager(String name) {
        this(Material.rock, name);
    }

    /**
     * Gets the name of the block
     *
     * @return Name of the block
     */
    public String getName() {
        return this.name;
    }

}
