package at.renehollander.advancedmanager.client.renderer.tileentity.base;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.tilentity.base.TileEntityAdvancedManager;
import at.renehollander.advancedmanager.util.Log;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * Abstract {@link TileEntitySpecialRenderer} class that takes common code and simplifies it,
 *
 * @param <T> Type of the {@link TileEntityAdvancedManager} that gets rendered
 * @author Rene Hollander
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class TileEntityAdvancedManagerRenderer<T extends TileEntityAdvancedManager> extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntityIn, double x, double y, double z, float partialTicks, int blockDamageProgress) {
        T te = (T) tileEntityIn;
        try {
            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();
            render(te, x, y, z, partialTicks, blockDamageProgress);
        } catch (Exception e) {
            Log.error(String.format("Error rendering %s at pos[x=%f,y=%f,z=%f]", ((BlockAdvancedManagerTileEntity) te.getBlockType()).getName(), x, y, z), e);
        } finally {
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
        }
    }

    /**
     * Render the given {@link TileEntityAdvancedManager} at the given position.
     *
     * @param te                  {@link TileEntityAdvancedManager} to get rendered
     * @param x                   x coordinate of the tileentity
     * @param y                   y coordinate of the tileentity
     * @param z                   z coordinate of the tileentity
     * @param partialTicks        partialTicks coordinate of the tileentity
     * @param blockDamageProgress blockDamageProgress coordinate of the tileentity
     */
    public abstract void render(T te, double x, double y, double z, float partialTicks, int blockDamageProgress);

}
