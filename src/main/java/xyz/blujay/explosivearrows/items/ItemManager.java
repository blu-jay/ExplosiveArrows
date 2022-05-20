package xyz.blujay.explosivearrows.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.blujay.explosivearrows.ExplosiveArrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ItemManager {

    public static ItemStack explosiveArrow;

    public static void init() {
        createExplosiveArrow();
    }

    private static void createExplosiveArrow(){
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).getPersistentDataContainer().set(new NamespacedKey(ExplosiveArrows.getInstance(), "ExplosiveArrows"), PersistentDataType.INTEGER, CustomItems.EXPLOSIVEARROW.ordinal());

        meta.setDisplayName("Explosive Arrow");
        List<String> lore = new ArrayList<>();

        lore.add("Explodes when the arrow hits its target!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        explosiveArrow = item;

        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(ExplosiveArrows.getInstance(), "ExplosiveArrow"), explosiveArrow);
        recipe.addIngredient(1, Material.ARROW);
        recipe.addIngredient(1, Material.TNT);

        Bukkit.getServer().addRecipe(recipe);
    }
}
