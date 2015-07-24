package at.renehollander.advancedmanager.creative;

import at.renehollander.advancedmanager.block.TileEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AwesomeModCreativeTab extends CreativeTabs {

    public static final AwesomeModCreativeTab AWESOME_MOD_CREATIVE_TAB = new AwesomeModCreativeTab();

    public AwesomeModCreativeTab() {
        super("advancedManager");
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(TileEntities.advancedcontroller);
    }
}
