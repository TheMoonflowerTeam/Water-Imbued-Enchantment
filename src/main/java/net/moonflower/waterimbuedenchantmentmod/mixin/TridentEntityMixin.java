package net.moonflower.waterimbuedenchantmentmod.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.moonflower.waterimbuedenchantmentmod.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TridentEntity.class)
public class TridentEntityMixin extends PersistentProjectileEntity {
    public TridentEntityMixin(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
        this.tridentStack = new ItemStack(Items.TRIDENT);
    }

    @Shadow
    private ItemStack tridentStack;

    @Shadow
    private boolean dealtDamage;

    @Shadow
    protected ItemStack asItemStack() {
        return this.tridentStack.copy();
    }

    /**
     * @author themoonflowerteam
     * @reason thunder imbued enchantment is supposed to let you use channeling without thunderstorms
     */
    @Overwrite
    public void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity livingEntity) {
            f += EnchantmentHelper.getAttackDamage(this.tridentStack, livingEntity.getGroup());
        }

        Entity entity2 = this.getOwner();
        DamageSource damageSource = DamageSource.trident(this, (Entity)(entity2 == null ? this : entity2));
        this.dealtDamage = true;
        SoundEvent soundEvent = SoundEvents.ITEM_TRIDENT_HIT;
        if (entity.damage(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity2 = (LivingEntity)entity;
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
                    EnchantmentHelper.onTargetDamaged((LivingEntity)entity2, livingEntity2);
                }

                this.onHit(livingEntity2);
            }
        }

        this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
        float g = 1.0F;
        if (this.world instanceof ServerWorld && this.hasChanneling() && (this.world.isThundering() || isThunderImbued())) {
            BlockPos blockPos = entity.getBlockPos();
            if (this.world.isSkyVisible(blockPos)) {
                LightningEntity lightningEntity = (LightningEntity)EntityType.LIGHTNING_BOLT.create(this.world);
                lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity2 : null);
                this.world.spawnEntity(lightningEntity);
                soundEvent = SoundEvents.ITEM_TRIDENT_THUNDER;
                g = 5.0F;
            }
        }

        this.playSound(soundEvent, g, 1.0F);
    }

    @Shadow
    public boolean hasChanneling() {
        return EnchantmentHelper.hasChanneling(this.tridentStack);
    }

    @Unique
    public boolean isThunderImbued() { return EnchantmentHelper.getLevel(ModEnchantments.THUNDER_IMBUED, this.tridentStack) > 0; }
}
