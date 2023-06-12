package net.thanhmgz.commandcooldown.system;


import me.clip.placeholderapi.PlaceholderAPI;
import net.thanhmgz.commandcooldown.CommandCooldown;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CooldownTimer {
    private List<String> actions = new ArrayList<>();

    private List<ZDate> cd; long cd2 = System.currentTimeMillis();

    private String lsc;
    private boolean cs,b;

    public CooldownTimer(ZDate[] dates, boolean consoleSender,boolean forOnlplayer,String... actions) {
        this.actions = Arrays.asList(actions);
        this.cd = List.of(dates);
        this.cs = consoleSender;
        this.b = forOnlplayer;
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(() -> {
            while (true) {
                if (System.currentTimeMillis() > cd2) {
                    if (checkDate()) {
                        action();
                    }
                    cd2 = System.currentTimeMillis() + (5000);
                }
            }
        });
        es.shutdown();
    }

    private boolean checkDate() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int d = calendar.get(Calendar.DAY_OF_WEEK);
        int m = calendar.get(Calendar.MINUTE);
        if (lsc != null && lsc.equals(h + "," + d + "," + m))
            return false;
        for (ZDate zDate : cd) {
            if (zDate.thứ == d) {
                if (zDate.giờ == h) {
                    if (zDate.phút == m) {
                        lsc = h + "," + d + "," + m;
                        return true;
                    }
                }
            }
        }
        return false ;
    }

    private void action() {
        if (b) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (String msg : actions) {
                    Bukkit.dispatchCommand(cs ? Bukkit.getConsoleSender() : player, PlaceholderAPI.setPlaceholders(player,msg));
                }
            }
        } else {
            try {
                for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                    for (String msg : actions) {
                        Bukkit.dispatchCommand(cs ? Bukkit.getConsoleSender() : (CommandSender) player, PlaceholderAPI.setPlaceholders(player, msg));
                    }
                }
            } catch (Exception e) {}
        }
    }

}
