package com.mechwild.mechs.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.UUID;

public class RaptorMechEntity extends Mob {
    private UUID ownerUuid;
    private UUID cockpitPilotUuid;
    private String frameType = "raptor";
    private int abilityCooldownTicks = 0;

    public RaptorMechEntity(EntityType<? extends Mob> type, Level level) {
        super(type, level);
        this.setNoAi(false);
        this.setCustomName(Component.literal("Raptor Mech Prototype"));
        this.setCustomNameVisible(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ARMOR, 6.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.35D);
    }

    public void configureFromPilot(Player player) {
        this.ownerUuid = player.getUUID();
        CompoundTag data = player.getPersistentData();
        String savedFrame = data.getString("mw_frame");
        if (!savedFrame.isEmpty()) {
            this.frameType = savedFrame;
        }
        applyFrameStats();
    }

    private void applyFrameStats() {
        double speed = 0.32D;
        double health = 60.0D;
        double armour = 6.0D;
        double damage = 6.0D;
        double knockback = 0.35D;

        if (frameType.contains("raptor")) {
            speed = 0.42D;
            damage = 8.0D;
            knockback = 0.45D;
        }
        if (frameType.contains("beetle")) {
            speed = 0.24D;
            health = 105.0D;
            armour = 14.0D;
            damage = 7.0D;
            knockback = 0.85D;
        }
        if (frameType.contains("stag")) {
            health = 78.0D;
            armour = 9.0D;
            damage = 6.5D;
        }
        if (frameType.contains("wolf")) {
            speed = 0.37D;
            damage = 10.0D;
            knockback = 0.50D;
        }
        if (frameType.contains("hawk")) {
            speed = 0.45D;
            health = 52.0D;
            armour = 5.0D;
            knockback = 0.25D;
        }

        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
        this.getAttribute(Attributes.ARMOR).setBaseValue(armour);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(damage);
        this.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(knockback);
        this.setHealth(this.getMaxHealth());
        this.setCustomName(Component.literal(displayNameForFrame() + " Prototype"));
    }

    private String displayNameForFrame() {
        if (frameType.contains("beetle")) return "Beetle Bulwark Mech";
        if (frameType.contains("stag")) return "Stag Grovekeeper Mech";
        if (frameType.contains("wolf")) return "Wolf Nightfang Mech";
        if (frameType.contains("hawk")) return "Hawk Stormwing Mech";
        return "Raptor Striker Mech";
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.level().isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (ownerUuid == null) {
            ownerUuid = player.getUUID();
        }

        if (!player.getUUID().equals(ownerUuid)) {
            player.displayClientMessage(Component.literal("This mech is bonded to another pilot."), true);
            return InteractionResult.CONSUME;
        }

        ItemStack held = player.getItemInHand(hand);
        if (isKubeJsItem(held, "mech_salvage")) {
            if (this.getHealth() >= this.getMaxHealth()) {
                player.displayClientMessage(Component.literal("Mech hull is already fully repaired."), true);
            } else {
                this.heal(14.0F);
                if (!player.getAbilities().instabuild) {
                    held.shrink(1);
                }
                player.displayClientMessage(Component.literal("Mech Salvage fused into the hull. Integrity restored."), true);
            }
            return InteractionResult.CONSUME;
        }

        player.displayClientMessage(Component.literal("Entering " + displayNameForFrame() + " cockpit..."), true);
        player.startRiding(this, true);
        cockpitPilotUuid = player.getUUID();
        player.setInvisible(true);
        player.getPersistentData().putBoolean("mw_piloting_mech", true);
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 240, 0, false, false, false));
        return InteractionResult.CONSUME;
    }

    private boolean isKubeJsItem(ItemStack stack, String path) {
        if (stack.isEmpty()) return false;
        ResourceLocation key = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return key != null && key.equals(new ResourceLocation("kubejs", path));
    }

    @Override
    public void tick() {
        super.tick();

        if (abilityCooldownTicks > 0) {
            abilityCooldownTicks--;
        }

        if (frameType.contains("hawk") && this.isVehicle() && !this.onGround() && this.getDeltaMovement().y < -0.12D) {
            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(motion.x, motion.y * 0.60D, motion.z);
            this.fallDistance = 0.0F;
        }

        if (!this.level().isClientSide) {
            if (this.getControllingPassenger() instanceof Player pilot) {
                cockpitPilotUuid = pilot.getUUID();
                pilot.setInvisible(true);
                pilot.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 240, 0, false, false, false));
                pilot.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 240, 0, false, false, true));
                pilot.getPersistentData().putBoolean("mw_piloting_mech", true);
            } else if (cockpitPilotUuid != null) {
                Player formerPilot = this.level().getPlayerByUUID(cockpitPilotUuid);
                if (formerPilot != null) {
                    formerPilot.setInvisible(false);
                    formerPilot.getPersistentData().putBoolean("mw_piloting_mech", false);
                    formerPilot.displayClientMessage(Component.literal("Exited mech cockpit."), true);
                }
                cockpitPilotUuid = null;
            }
        }
    }

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction moveFunction) {
        // Raise the rider/camera into the upper cockpit shell. The pilot is hidden and the HUD overlay sells the interior view.
        if (this.hasPassenger(passenger)) {
            moveFunction.accept(passenger, this.getX(), this.getY() + 1.45D, this.getZ());
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isAlive() && this.isVehicle() && this.getControllingPassenger() instanceof Player player) {
            this.setYRot(player.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(player.getXRot() * 0.35F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;

            float strafe = player.xxa * 0.45F;
            float forward = player.zza;
            if (forward <= 0.0F) {
                forward *= 0.22F;
            }

            float frameSpeed = (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
            if (player.isSprinting() && !frameType.contains("beetle")) {
                frameSpeed *= 1.18F;
            }

            this.setSpeed(frameSpeed);
            super.travel(new Vec3(strafe, travelVector.y, forward));
            return;
        }
        super.travel(travelVector);
    }

    public void activatePrimaryAbility(Player player) {
        if (this.level().isClientSide) return;

        if (abilityCooldownTicks > 0) {
            player.displayClientMessage(Component.literal("Mech ability recharging: " + Math.ceil(abilityCooldownTicks / 20.0D) + "s"), true);
            return;
        }

        Vec3 look = player.getLookAngle();
        Vec3 horizontal = new Vec3(look.x, 0.0D, look.z);
        if (horizontal.lengthSqr() < 0.01D) {
            horizontal = Vec3.directionFromRotation(0.0F, player.getYRot());
        }
        horizontal = horizontal.normalize();

        if (frameType.contains("beetle")) {
            this.setDeltaMovement(horizontal.scale(1.15D).add(0.0D, 0.10D, 0.0D));
            AABB hitBox = this.getBoundingBox().inflate(2.25D, 0.75D, 2.25D);
            List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, hitBox, target -> target != this && target != player && target.isAlive());
            for (LivingEntity target : targets) {
                target.hurt(this.damageSources().mobAttack(this), 7.0F);
                target.knockback(1.2D, this.getX() - target.getX(), this.getZ() - target.getZ());
            }
            player.displayClientMessage(Component.literal("Beetle Charge engaged."), true);
            abilityCooldownTicks = 120;
            return;
        }

        if (frameType.contains("stag")) {
            this.heal(10.0F);
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120, 0));
            }
            player.displayClientMessage(Component.literal("Stag Utility Pulse restored mech integrity."), true);
            abilityCooldownTicks = 140;
            return;
        }

        double dashStrength = frameType.contains("hawk") ? 2.05D : frameType.contains("wolf") ? 1.85D : 1.70D;
        double lift = frameType.contains("hawk") ? 0.42D : 0.18D;
        this.setDeltaMovement(horizontal.scale(dashStrength).add(0.0D, lift, 0.0D));
        this.hurtMarked = true;
        player.displayClientMessage(Component.literal(frameType.contains("wolf") ? "Wolf Strike dash engaged." : frameType.contains("hawk") ? "Hawk Burst glide engaged." : "Raptor Dash engaged."), true);
        abilityCooldownTicks = 90;
    }


    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity living ? living : null;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return super.hurt(source, amount * 0.78F);
    }

    @Override
    public void die(DamageSource source) {
        if (!this.level().isClientSide && cockpitPilotUuid != null) {
            Player pilot = this.level().getPlayerByUUID(cockpitPilotUuid);
            if (pilot != null) {
                pilot.setInvisible(false);
                pilot.getPersistentData().putBoolean("mw_piloting_mech", false);
                pilot.displayClientMessage(Component.literal("Mech destroyed. Emergency cockpit release triggered."), true);
            }
        }
        super.die(source);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (ownerUuid != null) tag.putUUID("Owner", ownerUuid);
        if (cockpitPilotUuid != null) tag.putUUID("CockpitPilot", cockpitPilotUuid);
        tag.putString("FrameType", frameType);
        tag.putInt("AbilityCooldown", abilityCooldownTicks);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("Owner")) ownerUuid = tag.getUUID("Owner");
        if (tag.hasUUID("CockpitPilot")) cockpitPilotUuid = tag.getUUID("CockpitPilot");
        if (tag.contains("FrameType")) frameType = tag.getString("FrameType");
        if (tag.contains("AbilityCooldown")) abilityCooldownTicks = tag.getInt("AbilityCooldown");
        applyFrameStats();
    }
}
