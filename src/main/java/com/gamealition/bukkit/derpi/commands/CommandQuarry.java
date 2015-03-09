package com.gamealition.bukkit.derpi.commands;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import com.gamealition.bukkit.derpi.PunishmentEntry;
import com.gamealition.bukkit.derpi.tasks.StartPunishmentTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class CommandQuarry extends DERPICommand {
    @Override
    protected boolean isArgumentsValid(String[] args) {
        return true;
    }

    @Override
    protected boolean processCommand(CommandSender sender, String alias, String[] args) {
        PunishmentEntry punishmentEntry = new PunishmentEntry(Bukkit.getOfflinePlayer(args[0]).getUniqueId(), sender instanceof Player ?
        ((Player)sender).getUniqueId(): null);

        UUID targetUUID = Bukkit.getPlayer(args[0]).getUniqueId();

        DERPIPlugin.getDERPI().getDatabase().save(punishmentEntry);

        //if the player is online, punish them now
        if(Bukkit.getPlayer(targetUUID) != null) {
            new StartPunishmentTask(punishmentEntry, Bukkit.getPlayer(targetUUID)).run();
        }
        return true;
    }
}
