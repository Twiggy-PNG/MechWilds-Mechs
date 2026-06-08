package com.mechwild.mechs.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.UUID;

public class RaptorMechEntity extends Mob {
    private UUID ownerUuid;
    private String frameType = "raptor";
    public RaptorMechEntity(EntityType<? extends Mob> type, Level level) { super(type, level); this.setNoAi(false); this.setCustomName(Component.literal("Raptor Mech Prototype")); this.setCustomNameVisible(true); }
    public static AttributeSupplier.Builder createAttributes() { return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 60.0D).add(Attributes.MOVEMENT_SPEED, 0.32D).add(Attributes.ATTACK_DAMAGE, 6.0D).add(Attributes.ARMOR, 6.0D).add(Attributes.KNOCKBACK_RESISTANCE, 0.35D); }
    public void configureFromPilot(Player player) { this.ownerUuid = player.getUUID(); CompoundTag data = player.getPersistentData(); String savedFrame = data.getString("mw_frame"); if (!savedFrame.isEmpty()) this.frameType = savedFrame; applyFrameStats(); }
    private void applyFrameStats() {
        double speed = 0.32D, health = 60.0D, armour = 6.0D, damage = 6.0D;
        if (frameType.contains("raptor")) { speed = 0.40D; damage = 8.0D; }
        if (frameType.contains("beetle")) { speed = 0.24D; health = 95.0D; armour = 13.0D; }
        if (frameType.contains("stag")) { health = 72.0D; armour = 8.0D; }
        if (frameType.contains("wolf")) { speed = 0.36D; damage = 9.0D; }
        if (frameType.contains("hawk")) { speed = 0.44D; health = 48.0D; armour = 4.0D; }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed); this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health); this.getAttribute(Attributes.ARMOR).setBaseValue(armour); this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(damage); this.setHealth(this.getMaxHealth()); this.setCustomName(Component.literal(displayNameForFrame() + " Prototype"));
    }
    private String displayNameForFrame() { if (frameType.contains("beetle")) return "Beetle Bulwark Mech"; if (frameType.contains("stag")) return "Stag Grovekeeper Mech"; if (frameType.contains("wolf")) return "Wolf Nightfang Mech"; if (frameType.contains("hawk")) return "Hawk Stormwing Mech"; return "Raptor Striker Mech"; }
    @Override protected InteractionResult mobInteract(Player player, InteractionHand hand) { if (!this.level().isClientSide) { if (ownerUuid == null) ownerUuid = player.getUUID(); if (player.getUUID().equals(ownerUuid)) { player.startRiding(this); return InteractionResult.CONSUME; } player.displayClientMessage(Component.literal("This mech is bonded to another pilot."), true); } return InteractionResult.SUCCESS; }
    @Override public void travel(Vec3 travelVector) { if (this.isAlive() && this.isVehicle() && this.getControllingPassenger() instanceof Player player) { this.setYRot(player.getYRot()); this.yRotO = this.getYRot(); this.setXRot(player.getXRot() * 0.5F); this.setRot(this.getYRot(), this.getXRot()); this.yBodyRot = this.getYRot(); this.yHeadRot = this.yBodyRot; float strafe = player.xxa * 0.5F; float forward = player.zza; if (forward <= 0.0F) forward *= 0.25F; this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED)); super.travel(new Vec3(strafe, travelVector.y, forward)); return; } super.travel(travelVector); }
    @Override public LivingEntity getControllingPassenger() { return this.getFirstPassenger() instanceof LivingEntity living ? living : null; }
    @Override protected boolean canAddPassenger(net.minecraft.world.entity.Entity passenger) { return this.getPassengers().isEmpty(); }
    @Override public boolean hurt(DamageSource source, float amount) { return super.hurt(source, amount * 0.8F); }
    @Override public boolean removeWhenFarAway(double distanceToClosestPlayer) { return false; }
    @Override public void addAdditionalSaveData(CompoundTag tag) { super.addAdditionalSaveData(tag); if (ownerUuid != null) tag.putUUID("Owner", ownerUuid); tag.putString("FrameType", frameType); }
    @Override public void readAdditionalSaveData(CompoundTag tag) { super.readAdditionalSaveData(tag); if (tag.hasUUID("Owner")) ownerUuid = tag.getUUID("Owner"); if (tag.contains("FrameType")) frameType = tag.getString("FrameType"); applyFrameStats(); }
}
