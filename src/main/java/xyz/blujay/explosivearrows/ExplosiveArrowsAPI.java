package xyz.blujay.explosivearrows;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import xyz.blujay.explosivearrows.items.CustomItems;
import xyz.blujay.explosivearrows.utilities.ChatUtility;

public class ExplosiveArrowsAPI {
    public int fuseLength;
    public String prefix;
    public String usePermissionMissing;
    public String craftPermissionMissing;

    ExplosiveArrowsAPI(FileConfiguration config){
        setConfigOptions(config);
    }

    public void setConfigOptions(FileConfiguration config){
        this.fuseLength = config.getInt("fuseLength");
        this.prefix = ChatUtility.colorize(config.getString("prefix"));
        this.usePermissionMissing = ChatUtility.colorize(config.getString("usePermissionMissing"));
        this.craftPermissionMissing = ChatUtility.colorize(config.getString("craftPermissionMissing"));
    }

    public boolean isItemExplosiveArrow(ItemStack item){
        var meta = item.getItemMeta();
        if (meta != null){
            var data = meta.getPersistentDataContainer().get(new NamespacedKey(ExplosiveArrows.getInstance(), "ExplosiveArrows"), PersistentDataType.INTEGER);
            return (data != null && data == CustomItems.EXPLOSIVEARROW.ordinal());
        }
        return false;
    }
}
