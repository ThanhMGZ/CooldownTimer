package net.thanhmgz.commandcooldown;

import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class CommandCooldown extends JavaPlugin {


    private static CommandCooldown instance;

    private Date date;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.date = new Date();
        new Config();
    }

    public Date getDate() {
        return date;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CommandCooldown getInstance() {
        return instance;
    }
}
