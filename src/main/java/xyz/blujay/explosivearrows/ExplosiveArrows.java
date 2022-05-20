package xyz.blujay.explosivearrows;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.blujay.explosivearrows.commands.ExplosiveArrowsCommand;
import xyz.blujay.explosivearrows.commands.ReloadCommand;
import xyz.blujay.explosivearrows.events.ArrowHitEvent;
import xyz.blujay.explosivearrows.events.ArrowShotEvent;
import xyz.blujay.explosivearrows.events.CraftArrowEvent;
import xyz.blujay.explosivearrows.items.ItemManager;
import xyz.blujay.explosivearrows.utilities.Metrics;

public final class ExplosiveArrows extends JavaPlugin {

    private static ExplosiveArrowsAPI api;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        api = new ExplosiveArrowsAPI(getConfig());

        int pluginId = 15233;
        new Metrics(this, pluginId);

        PluginCommand explosiveArrowsCommand = getCommand("ExplosiveArrows");
        PluginCommand reloadCommand = getCommand("reload");

        if (explosiveArrowsCommand != null && reloadCommand != null){
            explosiveArrowsCommand.setExecutor(new ExplosiveArrowsCommand());
            reloadCommand.setExecutor(new ReloadCommand());
        }
        else{
            getLogger().warning("getCommand returned null, unable to set executors for commands. disabling plugin...");
            getPluginLoader().disablePlugin(this);
        }

        var pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ArrowShotEvent(), this);
        pluginManager.registerEvents(new ArrowHitEvent(), this);
        pluginManager.registerEvents(new CraftArrowEvent(), this);

        ItemManager.init(); //Initialize Crafting Recipes

        getLogger().info("ExplosiveArrows has started!");
    }

    public void reload(){
        reloadConfig();
        api.setConfigOptions(getConfig());
    }

    public static ExplosiveArrows getInstance() {
        return getPlugin(ExplosiveArrows.class);
    }

    public ExplosiveArrowsAPI getAPI(){
        return api;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
