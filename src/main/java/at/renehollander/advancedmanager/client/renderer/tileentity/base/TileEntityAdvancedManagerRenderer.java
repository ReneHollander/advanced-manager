package at.renehollander.advancedmanager.client.renderer.tileentity.base;

import at.renehollander.advancedmanager.block.base.BlockAdvancedManagerTileEntity;
import at.renehollander.advancedmanager.tilentity.TileEntityRedstoneScreen;
import at.renehollander.advancedmanager.tilentity.base.TileEntityAdvancedManager;
import at.renehollander.advancedmanager.util.Log;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

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

    public abstract void render(T te, double x, double y, double z, float partialTicks, int blockDamageProgress);

}
