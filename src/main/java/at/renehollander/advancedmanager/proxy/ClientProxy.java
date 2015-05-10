package at.renehollander.advancedmanager.proxy;

import at.renehollander.advancedmanager.Reference;
import at.renehollander.advancedmanager.block.IBlockAwesomeMod;
import at.renehollander.advancedmanager.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        // Blocks
        registerItemRenderer(ModBlocks.redstoneController);
    }

    private void registerItemRenderer(Block block) {
        IBlockAwesomeMod awesomeModBlock = (IBlockAwesomeMod) block;
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
