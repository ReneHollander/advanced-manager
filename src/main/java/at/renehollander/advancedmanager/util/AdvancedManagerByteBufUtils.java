package at.renehollander.advancedmanager.util;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class AdvancedManagerByteBufUtils {

    public static void writeUUID(ByteBuf buf, UUID uuid) {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
    }

    public static UUID readUUID(ByteBuf buf) {
        long mostSig = buf.readLong();
        long leastSig = buf.readLong();
        return new UUID(mostSig, leastSig);
    }

}
