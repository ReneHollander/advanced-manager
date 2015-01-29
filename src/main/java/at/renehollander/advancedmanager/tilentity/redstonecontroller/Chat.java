package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class Chat {

    public void writeMessage(String message) {
        List<EntityPlayerMP> players = MinecraftServer.getServer().getEntityWorld().playerEntities;
        for (EntityPlayerMP player : players) {
            player.addChatMessage(new ChatComponentText(message));
        }
    }

}
