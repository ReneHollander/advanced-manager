package at.renehollander.advancedmanager.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class AdvancedManagerNBTUtils {

    public static void setUUID(NBTTagCompound nbt, String name, UUID uuid) {
        ByteBuf buf = Unpooled.buffer();
        AdvancedManagerByteBufUtils.writeUUID(buf, uuid);
        nbt.setByteArray(name, buf.array());
        buf.release();
    }

    public static UUID getUUID(NBTTagCompound nbt, String name) {
        ByteBuf buf = Unpooled.wrappedBuffer(nbt.getByteArray(name));
        UUID uuid = AdvancedManagerByteBufUtils.readUUID(buf);
        buf.release();
        return uuid;
    }

}
