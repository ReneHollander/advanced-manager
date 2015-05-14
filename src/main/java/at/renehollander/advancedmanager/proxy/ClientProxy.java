package at.renehollander.advancedmanager.proxy;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.block.base.IBlockAdvancedManager;
import at.renehollander.advancedmanager.client.renderer.tileentity.TileEntityRedstoneScreenRenderer;
import at.renehollander.advancedmanager.init.ModBlocks;
import at.renehollander.advancedmanager.tilentity.TileEntityRedstoneScreen;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        // Blocks
        registerItemRenderer(ModBlocks.redstonecontroller);
        registerItemRenderer(ModBlocks.redstonescreen);
        registerItemRenderer(ModBlocks.networkcable);
        registerItemRenderer(ModBlocks.networkcontroller);

        // TileEntitySpecialRenderers
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRedstoneScreen.class, new TileEntityRedstoneScreenRenderer());
    }

    private void registerItemRenderer(Block block) {
        IBlockAdvancedManager awesomeModBlock = (IBlockAdvancedManager) block;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + awesomeModBlock.getName(), "inventory"));
    }

    @Override
    public void postInit() {

    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public boolean isClient() {
        return true;
    }
}
