package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import at.renehollander.advancedmanager.util.AdvancedManagerByteBufUtils;
import at.renehollander.advancedmanager.util.AdvancedManagerNBTUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;

import java.util.EnumMap;

public class RedstoneControllerProperties {

    private boolean dirty;

    private String script;
    private EnumMap<EnumFacing, Integer> powerLevel;

    public RedstoneControllerProperties() {
        this.script = "var Thread = Java.type(\"java.lang.Thread\");\nwhile (true) {\n\tfor (i = 0; i < 16; i++) {\n\t\tprops.setPowerLevel(2, i);\n\t\tchat.writeMessage(\"Hello!\");\n\t\tThread.sleep(500);\n\t}\n}";
        this.powerLevel = new EnumMap<EnumFacing, Integer>(EnumFacing.class);
    }

    public boolean setPowerLevel(EnumFacing side, int level) {
        if (level >= 0 && level <= 15) {
            this.powerLevel.put(side, level);
            this.markDirty();
            return true;
        } else {
            return false;
        }
    }

    public int getPowerLevel(EnumFacing side) {
        return this.powerLevel.get(side);
    }

    public void reset() {
        this.powerLevel.clear();
        this.markDirty();
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
        this.markDirty();
    }

    public void writeToPacket(PacketBuffer buf) {
        buf.writeString(script);
        AdvancedManagerByteBufUtils.writeEnumMap(buf, EnumFacing.class, this.powerLevel);
    }

    public void readFromPacket(PacketBuffer buf) {
        this.script = buf.readStringFromBuffer(Integer.MAX_VALUE);
        AdvancedManagerByteBufUtils.readEnumMap(buf, EnumFacing.class, this.powerLevel);
    }

    public void writeToNBT(NBTTagCompound tag) {
        tag.setString("script", this.script);
        AdvancedManagerNBTUtils.writeEnumMap(tag, "powerlevels", EnumFacing.class, this.powerLevel);
    }

    public void readFromNBT(NBTTagCompound tag) {
        this.script = tag.getString("script");
        AdvancedManagerNBTUtils.readEnumMap(tag, "powerlevels", EnumFacing.class, this.powerLevel);
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
