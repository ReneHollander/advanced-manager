package at.renehollander.advancedmanager.proxy;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.init.ModBlocks;
import at.renehollander.advancedmanager.init.ModTileEntities;
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
        ModBlocks.blocks.forEach((block) -> {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + block.getName(), "inventory"));
        });

        // TileEntitySpecialRenderers
        ModTileEntities.tileentities.forEach((block) -> {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + block.getName(), "inventory"));
            if (block.hasTileEntitySpecialRenderer()) {
                ClientRegistry.bindTileEntitySpecialRenderer(block.getTileEntityClass(), block.getTileEntitySpecialRenderer());
            }
        });
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
