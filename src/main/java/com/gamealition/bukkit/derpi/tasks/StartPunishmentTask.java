package com.gamealition.bukkit.derpi.tasks;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import com.gamealition.bukkit.derpi.PunishmentEntry;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class StartPunishmentTask implements Runnable {
    private PunishmentEntry record;
    private Player target;

    public StartPunishmentTask(PunishmentEntry record, Player target) {
        this.record = record;
        this.target = target;
    }

    @Override
    public void run() {
        //TODO: set the player's vault balance to the startingOffset

        //send the player to DERPI
        this.target.teleport(DERPIPlugin.getDERPI().getDerpiTarget(), PlayerTeleportEvent.TeleportCause.PLUGIN);

        //enable all the DERPI-related handlers
        DERPIPlugin.getDERPI().interredPlayers.add(this.target);

        this.target.sendMessage("*** DEMEANOR ERADICATION & REHAB ***");
        this.target.sendMessage("*** PENITENTIARY INSTITUTE ***");
        this.target.sendMessage("You have been sent here because you have broken the rules. Consequently, you must earn back your freedom to play on this server.");
        this.target.sendMessage("This is done by mining enough cobblestone to buy freedom at the shack.");
        this.target.sendMessage("Use /shack to teleport to the shack and /money to check your balance.");
        this.target.sendMessage("PvP is enabled and you will be unable to hear or send chat.");

        this.target.setHealth(this.target.getMaxHealth());
        this.target.getInventory().clear();
        this.target.getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE,1));
        record.setHasStarted(true);
        DERPIPlugin.getDERPI().getDatabase().update(record);
    }
}
