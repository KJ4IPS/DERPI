package com.gamealition.bukkit.derpi.commands;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import com.gamealition.bukkit.derpi.PunishmentEntry;
import com.gamealition.bukkit.derpi.tasks.ReleaseTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class CommandUnquarry extends DERPICommand {
    @Override
    protected boolean isArgumentsValid(String[] args) {
        return true;
    }

    @Override
    protected boolean processCommand(CommandSender sender, String alias, String[] args) {
        UUID uuid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        Collection<PunishmentEntry> entries = PunishmentEntry.getActiveRecordsForPlayer(uuid);
        if(entries.size() < 1){
            sender.sendMessage("That player is not in DERPI");
            return true;
        }
        PunishmentEntry entry = entries.iterator().next();
            entry.setDone(true);
            entry.setReleaseReason("Released by command");
            DERPIPlugin.getDERPI().getDatabase().update(entry);


        if(entry.isHasStarted()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) return true;
            new ReleaseTask(entry, player).run();
            return true;
        }else{
            //This case is for dealing with people who were not ever interred
            entry.setHasStarted(true);
            entry.setHasReleased(true);
            DERPIPlugin.getDERPI().getDatabase().update(entry);
        }
        return true;
    }
}
