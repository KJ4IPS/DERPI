package com.gamealition.bukkit.derpi;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
@Entity()
@Table(name = "derpi_entries")
public class PunishmentEntry {

    @Id
    private int dbId;

    @NotNull
    private UUID targetPlayerUUID;

    private UUID actingPlayerUUID;

    @NotNull
    private int releaseTarget;

    @NotNull
    private int startingOffset;

    @NotNull
    private boolean hasStarted;

    @NotNull
    private boolean done;

    @NotNull
    private boolean hasReleased;

    @NotEmpty
    private String punishReasion;
    private String releaseReason;

    private Date lastFreePickaxe;

    public static List<PunishmentEntry> getActiveRecordsForPlayer(UUID playerUUID){
        return DERPIPlugin.getDERPI().getDatabase()
                .find(PunishmentEntry.class).where()
                .eq("target_player_uuid", playerUUID)
                .eq("has_released", false).findList();
    }

    public static List<PunishmentEntry> getAllRecordsForPlayer(UUID playerUUID){
        return DERPIPlugin.getDERPI().getDatabase()
                .find(PunishmentEntry.class).where()
                .eq("target_player_uuid", playerUUID).findList();
    }

    public PunishmentEntry(UUID target, UUID acting, int releaseTarget, int startingOffset, String reason){
        this.targetPlayerUUID = target;
        this.actingPlayerUUID = acting;
        this.releaseTarget = releaseTarget;
        this.startingOffset = startingOffset;
        this.punishReasion = reason;

        this.releaseReason = "Not Yet Released";

    }

    public PunishmentEntry(UUID target, UUID acting, String reason){
        this(target,acting, 270, 0, reason);
    }

    public PunishmentEntry(UUID target, UUID acting){
        this(target, acting, 270, 0, "No Reason Specified");
    }

    public PunishmentEntry(){

    }

    /*Ebean getters/setters*/

    public UUID getActingPlayerUUID() {
        return actingPlayerUUID;
    }

    public void setActingPlayerUUID(UUID actingPlayerUUID) {
        this.actingPlayerUUID = actingPlayerUUID;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public boolean isHasReleased() {
        return hasReleased;
    }

    public void setHasReleased(boolean hasReleased) {
        this.hasReleased = hasReleased;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public String getPunishReasion() {
        return punishReasion;
    }

    public void setPunishReasion(String punishReasion) {
        this.punishReasion = punishReasion;
    }

    public String getReleaseReason() {
        return releaseReason;
    }

    public void setReleaseReason(String releaseReason) {
        this.releaseReason = releaseReason;
    }

    public int getReleaseTarget() {
        return releaseTarget;
    }

    public void setReleaseTarget(int releaseTarget) {
        this.releaseTarget = releaseTarget;
    }

    public int getStartingOffset() {
        return startingOffset;
    }

    public void setStartingOffset(int startingOffset) {
        this.startingOffset = startingOffset;
    }

    public UUID getTargetPlayerUUID() {
        return targetPlayerUUID;
    }

    public void setTargetPlayerUUID(UUID targetPlayerUUID) {
        this.targetPlayerUUID = targetPlayerUUID;
    }

    public Date getLastFreePickaxe() {
        return lastFreePickaxe;
    }

    public void setLastFreePickaxe(Date lastFreePickaxe) {
        this.lastFreePickaxe = lastFreePickaxe;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean isDone) {
        this.done = isDone;
    }
}
