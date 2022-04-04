package me.red;

import me.red.listener.onBlockBreakListener;
import me.red.listener.onPlaceBlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new onPlaceBlockListener(this),this);
        getServer().getPluginManager().registerEvents(new onBlockBreakListener(),this);

        saveDefaultConfig();

        loadMessage();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadMessage(){
        getLogger().info("");
        getLogger().info("    ____           ______  __                    ");
        getLogger().info("   / __ )___  ____/ / __ \\/ /___ _________    __ ");
        getLogger().info("  / __  / _ \\/ __  / /_/ / / __ `/ ___/ _ \\__/ /");
        getLogger().info(" / /_/ /  __/ /_/ / ____/ / /_/ / /__/  __/_  __/");
        getLogger().info("/_____/\\___/\\__,_/_/   /_/\\__,_/\\___/\\___/ /_/   ");
        getLogger().info("Plugin made by RedBoy for ThunderLand");
        getLogger().info("");
    }
}
