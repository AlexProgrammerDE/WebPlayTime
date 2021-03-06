package me.alexprogrammerde.WebGameStats;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class DataListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!Main.getPlugin(Main.class).exeludedplayers.contains(player.getName())) {
            Main.getPlugin().data.set(player.getName() + ".kills", player.getStatistic(Statistic.PLAYER_KILLS));
            Main.getPlugin().data.set(player.getName() + ".deaths", player.getStatistic(Statistic.DEATHS));
            Main.getPlugin().data.set(player.getName() + ".join", player.getStatistic(Statistic.LEAVE_GAME));
            Main.getPlugin().data.set(player.getName() + ".jumps", player.getStatistic(Statistic.JUMP));

            try {
                Main.getPlugin().data.save(Main.getPlugin().getDataFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Main.getPlugin().reloadData();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!Main.getPlugin(Main.class).exeludedplayers.contains(player.getName())) {
            Main.getPlugin().data.set(player.getName() + ".kills", player.getStatistic(Statistic.PLAYER_KILLS));
            Main.getPlugin().data.set(player.getName() + ".deaths", player.getStatistic(Statistic.DEATHS));
            Main.getPlugin().data.set(player.getName() + ".join", player.getStatistic(Statistic.LEAVE_GAME));
            Main.getPlugin().data.set(player.getName() + ".jumps", player.getStatistic(Statistic.JUMP));

            try {
                Main.getPlugin().data.save(Main.getPlugin().getDataFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Main.getPlugin().reloadData();
        }
    }
}
