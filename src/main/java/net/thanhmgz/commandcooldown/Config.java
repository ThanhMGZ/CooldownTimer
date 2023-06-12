package net.thanhmgz.commandcooldown;

import net.thanhmgz.commandcooldown.system.CooldownTimer;
import net.thanhmgz.commandcooldown.system.ZDate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Config {

    public Config() {
        new CooldownTimer(new ZDate[]{new ZDate(1,12,30)},true,true,"say abcxyzz","gamemode creative");
        new CooldownTimer(new ZDate[]{new ZDate(2,6,59)
        ,new ZDate(3,7,10),new ZDate(5,5,5)},false,false,"stop");
    }


}
