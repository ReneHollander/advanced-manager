package at.renehollander.advancedmanager.redstonecontroller.scripting.api;

import at.renehollander.advancedmanager.scripting.api.APIInfo;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

import java.util.List;

@APIInfo.Annotation(shortName = "chat", name = "Chat", description = "Write chat messages to other players")
public class Chat extends RedstoneControllerAPI {

    public void broadcastMessage(String string) {
        List<EntityPlayerMP> players = MinecraftServer.getServer().getEntityWorld().playerEntities;
        for (EntityPlayerMP player : players) {
            player.addChatMessage(new ChatComponentText(string));
        }
    }

}
