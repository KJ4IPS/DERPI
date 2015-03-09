package com.gamealition.bukkit.derpi.listeners;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import com.gamealition.bukkit.derpi.PunishmentEntry;
import com.gamealition.bukkit.derpi.tasks.ReleaseTask;
import com.gamealition.bukkit.derpi.tasks.ResumePunishmentTask;
import com.gamealition.bukkit.derpi.tasks.StartPunishmentTask;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Collection;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class LoginListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Collection<PunishmentEntry> records = PunishmentEntry.getActiveRecordsForPlayer(event.getPlayer().getUniqueId());
        if(records.size() > 0){
            DERPIPlugin.getDERPI().interredPlayers.add(event.getPlayer());
            PunishmentEntry record = records.iterator().next();
            if(record.isDone()){
                Bukkit.getScheduler().scheduleSyncDelayedTask(DERPIPlugin.getDERPI(),new ReleaseTask(record, event.getPlayer()), 200);
            }else if(record.isHasStarted()){
                new StartPunishmentTask(records.iterator().next(),event.getPlayer()).run();
            }else{
                new ResumePunishmentTask(event.getPlayer()).run();
            }
        }
    }

}
