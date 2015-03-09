package com.gamealition.bukkit.derpi.tasks;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import com.gamealition.bukkit.derpi.PunishmentEntry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class ReleaseTask implements Runnable {
    private PunishmentEntry record;
    private Player target;

    public ReleaseTask(PunishmentEntry record, Player target) {
        this.record = record;
        this.target = target;
    }

    @Override
    public void run() {
        DERPIPlugin.getDERPI().interredPlayers.remove(target);
        System.out.println("Releaseing a target");
        Location tpTarget = target.getBedSpawnLocation();
        if(tpTarget == null){
            tpTarget = Bukkit.getWorlds().get(0).getSpawnLocation();
        }
        target.getInventory().clear();
        target.teleport(tpTarget, PlayerTeleportEvent.TeleportCause.PLUGIN);
        record.setHasReleased(true);
        DERPIPlugin.getDERPI().getDatabase().update(record);
    }
}
