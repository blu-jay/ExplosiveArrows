package xyz.blujay.explosivearrows.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import xyz.blujay.explosivearrows.ExplosiveArrows;
import xyz.blujay.explosivearrows.items.CustomItems;

public class ArrowHitEvent implements Listener {
    @EventHandler
    public void onArrowHit(org.bukkit.event.entity.ProjectileHitEvent e){
        if(e.getEntity() instanceof Arrow arrow && arrow.getShooter() instanceof Player p){

            var key = new NamespacedKey(ExplosiveArrows.getInstance(), "ExplosiveArrows");
            var data = arrow.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
            if(data != null && data == CustomItems.EXPLOSIVEARROW.ordinal()){
                var plugin = ExplosiveArrows.getInstance();
                var api = plugin.getAPI();

                var tnt = (TNTPrimed) arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.PRIMED_TNT);
                tnt.setFuseTicks(api.fuseLength);
                tnt.setSource(p);
                arrow.remove();
            }
        }
    }
}
