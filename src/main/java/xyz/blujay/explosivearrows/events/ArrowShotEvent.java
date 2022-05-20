package xyz.blujay.explosivearrows.events;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import xyz.blujay.explosivearrows.ExplosiveArrows;
import xyz.blujay.explosivearrows.items.CustomItems;

import java.util.Objects;

public class ArrowShotEvent implements Listener {

    @EventHandler
    public void onArrowShot(org.bukkit.event.entity.EntityShootBowEvent e){
        if(e.getEntity() instanceof Player p){

            var arrow = e.getConsumable();

            if(arrow != null){
                if(arrow.hasItemMeta()){
                    var container = Objects.requireNonNull(arrow.getItemMeta()).getPersistentDataContainer();
                    var key = new NamespacedKey(ExplosiveArrows.getInstance(), "ExplosiveArrows");

                    if(container.has(key, PersistentDataType.INTEGER)){
                        Integer foundValue = container.get(key, PersistentDataType.INTEGER);
                        if(foundValue != null && foundValue == CustomItems.EXPLOSIVEARROW.ordinal()){
                            if(p.hasPermission("explosivearrows.use")){
                                e.setConsumeItem(true);
                                var projectile = e.getProjectile();
                                projectile.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, CustomItems.EXPLOSIVEARROW.ordinal());
                                projectile.getWorld().playSound(projectile.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 5F, 0.7F);
                            }
                            else{
                                var api = ExplosiveArrows.getInstance().getAPI();
                                p.sendMessage(api.prefix + api.usePermissionMissing);
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
