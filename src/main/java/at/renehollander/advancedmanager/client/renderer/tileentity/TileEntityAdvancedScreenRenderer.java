package at.renehollander.advancedmanager.client.renderer.tileentity;

import at.renehollander.advancedmanager.client.renderer.tileentity.base.TileEntityAdvancedManagerRenderer;
import at.renehollander.advancedmanager.client.util.FrameBufferObject;
import at.renehollander.advancedmanager.tilentity.TileEntityAdvancedScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class TileEntityAdvancedScreenRenderer extends TileEntityAdvancedManagerRenderer<TileEntityAdvancedScreen> {

    /*
    private BufferedImage bi;
    int textureID;
    public TileEntityAdvancedScreenRenderer() {
        int width = 64;
        int height = 64;
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        int[] nBits = {8, 8, 8};
        int[] bOffs = {0, 1, 2};
        ColorModel cm = new ComponentColorModel(cs, nBits, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        WritableRaster raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, width, height, width * 3, 3, bOffs, null);
        BufferedImage bi = new BufferedImage(cm, raster, false, null);

        Graphics2D graphics = bi.createGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setRenderingHints(rh);
        graphics.setColor(new Color(0, 170, 242));
        graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        graphics.setColor(Color.BLACK);
        Font f = new Font("Arial", Font.PLAIN, 20);
        FontMetrics metrics = graphics.getFontMetrics(f);
        graphics.setFont(f);
        graphics.drawString("Hello World!", 0, metrics.getHeight());

        long start;
        long stop;
        DataBufferByte db = (DataBufferByte) raster.getDataBuffer();
        ByteBuffer buf = ByteBuffer.allocateDirect(db.getData().length);

        textureID = glGenTextures();
        GlStateManager.bindTexture(textureID);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        start = System.nanoTime();
        buf.put(db.getData());
        buf.flip();
        GlStateManager.enableTexture2D();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB8, bi.getWidth(), bi.getHeight(), 0, GL_RGB, GL_UNSIGNED_BYTE, buf);
        stop = System.nanoTime();
        System.out.printf("time to write to texture: %fms", (stop - start) / 1000000D);
    }
    */

    private FrameBufferObject fbo;

    public TileEntityAdvancedScreenRenderer() {
        fbo = new FrameBufferObject(300, 300, true);
        fbo.initialize();
    }

    @Override
    public void render(TileEntityAdvancedScreen te, double x, double y, double z, float partialTicks, int blockDamageProgress) {
        GlStateManager.translate(x, y, z);
        //GlStateManager.disableLighting();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        /*
        worldRenderer.startDrawing(GL11.GL_QUADS);
        {
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(textureID);
            worldRenderer.addVertexWithUV(0, 0, 0, 1, 1); //Bottom left texture
            worldRenderer.addVertexWithUV(0, 1, 0, 1, 0); //Top left
            worldRenderer.addVertexWithUV(1, 1, 0, 0, 0); //Top right
            worldRenderer.addVertexWithUV(1, 0, 0, 0, 1); //Bottom right


        }
        tessellator.draw();
        */
        pixelCount = 300;

        setColor(0, 170, 255);
        fillRect(0, 0, 50, 50);
        setColor(255, 0, 0);
        fillCircle(150, 150, 150);

        Framebuffer frameBuffer = null;
        try {
            GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPushMatrix();
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glPushMatrix();

            // setup modelview matrix
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

            GL11.glOrtho(0.0D, 1.0, 1.0, 0.0, -10.0, 10.0);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);

            frameBuffer = new Framebuffer(300, 300, true);

            frameBuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
            frameBuffer.framebufferClear();
            frameBuffer.bindFramebuffer(true);

            setColor(0, 170, 255);
            fillRect(0, 0, 50, 50);
            setColor(255, 0, 0);
            fillCircle(150, 150, 150);

            frameBuffer.deleteFramebuffer();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (frameBuffer != null) {
                frameBuffer.deleteFramebuffer();
            }
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPopMatrix();
            GL11.glPopAttrib();
        }

        //GlStateManager.enableLighting();
    }

    private int pixelCount = 200;
    private EnumFacing side;

    private void setColor(int r, int g, int b) {
        glColor3f(r / 255f, g / 255f, b / 255f);
    }

    private void fillRect(int x, int y, int w, int h) {
        float fx = (float) x / (float) pixelCount;
        float fy = (float) y / (float) pixelCount;
        float fw = (float) w / (float) pixelCount;
        float fh = (float) h / (float) pixelCount;
        GlStateManager.disableTexture2D();
        glBegin(GL_QUADS);
        glVertex3f(1 - fx, 1 - fy - fw, 0);
        glVertex3f(1 - fx - fw, 1 - fy - fh, 0);
        glVertex3f(1 - fx - fw, 1 - fy, 0);
        glVertex3f(1 - fx, 1 - fy, 0);
        glEnd();
        GlStateManager.enableTexture2D();
    }

    private void fillCircle(int x, int y, int r) {
        float fx = (float) x / (float) pixelCount;
        float fy = (float) y / (float) pixelCount;
        float fr = (float) r / (float) pixelCount;
        int num_segments = 100;
        float theta = 2f * (float) Math.PI / (float) num_segments;
        float tangetial_factor = (float) Math.tan(theta);
        float radial_factor = (float) Math.cos(theta);
        float x2 = fr;
        float y2 = 0;
        GlStateManager.disableTexture2D();
        glBegin(GL_POLYGON);
        for (int ii = 0; ii < num_segments; ii++) {
            glVertex3f(x2 + fx, y2 + fy, 0);
            float tx = -y2;
            float ty = x2;
            x2 = (x2 - tx * tangetial_factor) * radial_factor;
            y2 = (y2 - ty * tangetial_factor) * radial_factor;
        }
        glEnd();
        GlStateManager.enableTexture2D();
    }

}
