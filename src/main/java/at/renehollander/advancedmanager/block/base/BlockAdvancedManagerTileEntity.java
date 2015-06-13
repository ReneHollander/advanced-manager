package at.renehollander.advancedmanager.block.base;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.creative.AwesomeModCreativeTab;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.Validate;

/**
 * Abstract TileEntity block class that takes common code and simplifies it,
 *
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class BlockAdvancedManagerTileEntity extends BlockContainer {

    private final String name;
    private final Class<? extends TileEntity> tileEntityClass;
    private final TileEntitySpecialRenderer tileEntitySpecialRenderer;

    private final int renderType;

    /**
     * Creates a new TileEntity block with the given material, name, {@link TileEntity} class,
     * {@link TileEntitySpecialRenderer} and registeres everything. It also determins the rendertype.
     * <p>
     * The {@link net.minecraft.block.ITileEntityProvider#createNewTileEntity(World, int)} gets a
     * default implementation that will create a new instance of the {@link #tileEntityClass} class.
     *
     * @param material                  Material of the new tileentity block
     * @param name                      Name of the new tileentity block
     * @param tileEntityClass           {@link TileEntity} class of the new tileentity block
     * @param tileEntitySpecialRenderer {@link TileEntitySpecialRenderer} class of the new tileentity block, can be
     *                                  null of no renderer is wanted. {@link #renderType} will automatically get set accordingly.
     */
    public BlockAdvancedManagerTileEntity(Material material, String name, Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer tileEntitySpecialRenderer) {
        super(material);
        Validate.notNull(name);
        Validate.notNull(tileEntityClass);
        this.name = name;
        this.tileEntityClass = tileEntityClass;
        this.tileEntitySpecialRenderer = tileEntitySpecialRenderer;
        if (this.getTileEntitySpecialRenderer() == null) {
            renderType = 3;
        } else {
            renderType = -1;
        }

        setUnlocalizedName(Reference.MODID + "_" + this.getName());
        setCreativeTab(AwesomeModCreativeTab.AWESOME_MOD_CREATIVE_TAB);

        GameRegistry.registerBlock(this, getName());
        GameRegistry.registerTileEntity(this.getTileEntityClass(), Reference.MODID + ":" + getName());
    }

    /**
     * Creates a new TileEntity block with the given material, name, {@link TileEntity} class and
     * registeres everything.
     * <p>
     * The {@link net.minecraft.block.ITileEntityProvider#createNewTileEntity(World, int)} gets a
     * default implementation that will create a new instance of the {@link #tileEntityClass} class.
     *
     * @param material        Material of the new tileentity block
     * @param name            Name of the new tileentity block
     * @param tileEntityClass {@link TileEntity} class of the new tileentity block
     * @see BlockAdvancedManagerTileEntity#BlockAdvancedManagerTileEntity(Material, String, Class, TileEntitySpecialRenderer)
     */
    public BlockAdvancedManagerTileEntity(Material material, String name, Class<? extends TileEntity> tileEntityClass) {
        this(material, name, tileEntityClass, null);
    }

    /**
     * Gets the name of the tileentity block
     *
     * @return Name of the tileentity block
     */
    public String getName() {
        return name;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        try {
            return this.getTileEntityClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the {@link TileEntity} class of the tileentity block
     *
     * @return {@link TileEntity} of the tileentity block
     */
    public Class<? extends TileEntity> getTileEntityClass() {
        return tileEntityClass;
    }

    /**
     * Gets the {@link TileEntitySpecialRenderer} of the tileentity block
     *
     * @return {@link TileEntitySpecialRenderer} of the tileentity block,or null if no renderer is set
     */
    public TileEntitySpecialRenderer getTileEntitySpecialRenderer() {
        return tileEntitySpecialRenderer;
    }

    @Override
    public int getRenderType() {
        return renderType;
    }

    /**
     * Checks if this tileentity has a custom renderer
     *
     * @return <tt>true</tt> if a tileentity renderer is set
     */
    public boolean hasTileEntitySpecialRenderer() {
        return this.getTileEntitySpecialRenderer() != null;
    }
}
