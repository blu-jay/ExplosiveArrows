package xyz.blujay.explosivearrows.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.blujay.explosivearrows.ExplosiveArrows;
import xyz.blujay.explosivearrows.items.ItemManager;

public class CraftArrowEvent implements Listener {
    @EventHandler
    public void onArrowCrafted(org.bukkit.event.inventory.CraftItemEvent e) {
        var api = ExplosiveArrows.getInstance().getAPI();
        if(api.isItemExplosiveArrow(e.getRecipe().getResult())){
            var p = e.getWhoClicked();
            if(!p.hasPermission("explosivearrows.craft")){
                p.sendMessage(api.prefix + api.craftPermissionMissing);
                e.setCancelled(true);
                p.closeInventory();
            }
        }
    }
}
