package com.gamealition.bukkit.derpi.tasks;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class ResumePunishmentTask implements Runnable {
    private Player target;

    public ResumePunishmentTask(Player target) {
        this.target = target;
    }

    @Override
    public void run() {
        target.teleport(DERPIPlugin.getDERPI().getDerpiTarget(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        target.sendMessage("*** DEMEANOR ERADICATION & REHAB ***");
        target.sendMessage("*** PENITENTIARY INSTITUTE ***");
        target.sendMessage("You have been sent here because you have broken the rules. Consequently, you must earn back your freedom to play on this server.");
        target.sendMessage("This is done by mining enough cobblestone to buy freedom at the shack.");
        target.sendMessage("Use /shack to teleport to the shack and /money to check your balance.");
        target.sendMessage("PvP is enabled and you will be unable to hear or send chat.");
    }
}
