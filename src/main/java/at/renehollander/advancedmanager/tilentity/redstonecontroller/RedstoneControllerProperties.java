package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Arrays;

public class RedstoneControllerProperties {

    private boolean dirty;

    private String script;
    private int[] powerlevel;

    public RedstoneControllerProperties() {
        this.script = "var Thread = Java.type(\"java.lang.Thread\");\nwhile (true) {\n\tfor (i = 0; i < 16; i++) {\n\t\tprops.setPowerLevel(2, i);\n\t\tchat.writeMessage(\"Hello!\");\n\t\tThread.sleep(500);\n\t}\n}";
        this.powerlevel = new int[6];
    }

    public boolean setPowerLevel(int side, int level) {
        if (side >= 0 && side <= 5 && level >= 0 && level <= 15) {
            this.powerlevel[side] = level;
            this.markDirty();
            return true;
        } else {
            return false;
        }
    }

    public int getPowerLevel(int side) {
        if (side >= 0 && side <= 5) {
            return this.powerlevel[side];
        } else {
            return -1;
        }
    }

    public void reset() {
        Arrays.fill(this.powerlevel, 0);
        this.markDirty();
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
        this.markDirty();
    }

    public void writeToPacket(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, script);
        for (int i = 0; i < this.powerlevel.length; i++) {
            buf.writeInt(this.powerlevel[i]);
        }
    }

    public void readFromPacket(ByteBuf buf) {
        this.script = ByteBufUtils.readUTF8String(buf);
        for (int i = 0; i < this.powerlevel.length; i++) {
            this.powerlevel[i] = buf.readInt();
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        tag.setIntArray("powerLevels", this.powerlevel);
        tag.setString("script", this.script);
    }

    public void readFromNBT(NBTTagCompound tag) {
        this.powerlevel = tag.getIntArray("powerLevels");
        this.script = tag.getString("script");
        this.markDirty();
    }

    public void markDirty() {
        this.dirty = true;
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public void resetDirty() {
        this.dirty = false;
    }
}
