package me.coderfrish.event;

import com.destroystokyo.paper.event.block.*;
import com.destroystokyo.paper.event.entity.EntityKnockbackByEntityEvent;
import com.destroystokyo.paper.event.player.*;
import com.destroystokyo.paper.event.entity.*;
import com.destroystokyo.paper.event.inventory.*;
import com.destroystokyo.paper.event.brigadier.*;
import com.destroystokyo.paper.event.profile.*;
import com.destroystokyo.paper.event.server.*;
import io.papermc.paper.event.block.*;
import io.papermc.paper.event.block.BellRingEvent;
import io.papermc.paper.event.connection.*;
import io.papermc.paper.event.entity.*;
import io.papermc.paper.event.entity.EntityKnockbackEvent;
import io.papermc.paper.event.player.*;
import io.papermc.paper.event.packet.*;
import io.papermc.paper.event.server.*;
import io.papermc.paper.event.world.*;
import io.papermc.paper.event.world.border.*;
import io.papermc.paper.threadedregions.RegionizedServerInitEvent;
import org.bukkit.event.Event;
import org.bukkit.event.block.*;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.command.*;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerRecipeBookClickEvent;
import org.bukkit.event.raid.*;
import org.bukkit.event.server.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;
import org.bukkit.event.world.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventMap {
    private static final Map<String, Class<? extends Event>> events;

    static {
        events = new ConcurrentHashMap<>() {
            {
                // Bukkit Event
                // Block Event
                put("BellResonateEvent", BellResonateEvent.class);
                put("BellRingEvent", BellRingEvent.class);
                put("BlockBreakEvent", BlockBreakEvent.class);
                put("BlockBurnEvent", BlockBurnEvent.class);
                put("BlockCanBuildEvent", BlockCanBuildEvent.class);
                put("BlockCookEvent", BlockCookEvent.class);
                put("BlockDamageAbortEvent", BlockDamageAbortEvent.class);
                put("BlockDamageEvent", BlockDamageEvent.class);
                put("BlockDispenseArmorEvent", BlockDispenseArmorEvent.class);
                put("BlockDispenseEvent", BlockDispenseEvent.class);
                put("BlockDispenseLootEvent", BlockDispenseLootEvent.class);
                put("BlockDropItemEvent", BlockDropItemEvent.class);
                put("BlockExpEvent", BlockExpEvent.class);
                put("BlockExplodeEvent", BlockExplodeEvent.class);
                put("BlockFadeEvent", BlockFadeEvent.class);
                put("BlockFertilizeEvent", BlockFertilizeEvent.class);
                put("BlockFormEvent", BlockFormEvent.class);
                put("BlockFromToEvent", BlockFromToEvent.class);
                put("BlockGrowEvent", BlockGrowEvent.class);
                put("BlockIgniteEvent", BlockIgniteEvent.class);
                put("BlockMultiPlaceEvent", BlockMultiPlaceEvent.class);
                put("BlockPhysicsEvent", BlockPhysicsEvent.class);
                put("BlockPistonExtendEvent", BlockPistonExtendEvent.class);
                put("BlockPistonRetractEvent", BlockPistonRetractEvent.class);
                put("BlockPlaceEvent", BlockPlaceEvent.class);
                put("BlockReceiveGameEvent", BlockReceiveGameEvent.class);
                put("BlockRedstoneEvent", BlockRedstoneEvent.class);
                put("BlockShearEntityEvent", BlockShearEntityEvent.class);
                put("BlockSpreadEvent", BlockSpreadEvent.class);
                put("BrewingStartEvent", BrewingStartEvent.class);
                put("CampfireStartEvent", CampfireStartEvent.class);
                put("CauldronLevelChangeEvent", CauldronLevelChangeEvent.class);
                put("CrafterCraftEvent", CrafterCraftEvent.class);
                put("EntityBlockFormEvent", EntityBlockFormEvent.class);
                put("FluidLevelChangeEvent", FluidLevelChangeEvent.class);
                put("InventoryBlockStartEvent", InventoryBlockStartEvent.class);
                put("LeavesDecayEvent", LeavesDecayEvent.class);
                put("MoistureChangeEvent", MoistureChangeEvent.class);
                put("NotePlayEvent", NotePlayEvent.class);
                put("SculkBloomEvent", SculkBloomEvent.class);
                put("SignChangeEvent", SignChangeEvent.class);
                put("SpongeAbsorbEvent", SpongeAbsorbEvent.class);
                put("TNTPrimeEvent", TNTPrimeEvent.class);
                put("VaultDisplayItemEvent", VaultDisplayItemEvent.class);

                // Command Event
                put("UnknownCommandEvent", UnknownCommandEvent.class);

                // Enchantment
                put("EnchantItemEvent", EnchantItemEvent.class);
                put("PrepareItemEnchantEvent", PrepareItemEnchantEvent.class);

                // Entity Event
                put("EntityKnockbackEvent", org.bukkit.event.entity.EntityKnockbackEvent.class);
                put("AreaEffectCloudApplyEvent", AreaEffectCloudApplyEvent.class);
                put("ArrowBodyCountChangeEvent", ArrowBodyCountChangeEvent.class);
                put("BatToggleSleepEvent", BatToggleSleepEvent.class);
                put("CreatureSpawnEvent", CreatureSpawnEvent.class);
                put("CreeperPowerEvent", CreeperPowerEvent.class);
                put("EnderDragonChangePhaseEvent", EnderDragonChangePhaseEvent.class);
                put("EntityAirChangeEvent", EntityAirChangeEvent.class);
                put("EntityBreakDoorEvent", EntityBreakDoorEvent.class);
                put("EntityBreedEvent", EntityBreedEvent.class);
                put("EntityChangeBlockEvent", EntityChangeBlockEvent.class);
                put("EntityCombustByBlockEvent", EntityCombustByBlockEvent.class);
                put("EntityCombustEvent", EntityCombustEvent.class);
                put("EntityDamageByBlockEvent", EntityDamageByBlockEvent.class);
                put("EntityDamageByEntityEvent", EntityDamageByEntityEvent.class);
                put("EntityDeathEvent", EntityDeathEvent.class);
                put("EntityDismountEvent", EntityDismountEvent.class);
                put("EntityDropItemEvent", EntityDropItemEvent.class);
                put("EntityEnterBlockEvent", EntityEnterBlockEvent.class);
                put("EntityEnterLoveModeEvent", EntityEnterLoveModeEvent.class);
                put("EntityExhaustionEvent", EntityExhaustionEvent.class);
                put("EntityExplodeEvent", EntityExplodeEvent.class);
                put("EntityInteractEvent", EntityInteractEvent.class);
                put("EntityMountEvent", EntityMountEvent.class);
                put("EntityPickupItemEvent", EntityPickupItemEvent.class);
                put("EntityPlaceEvent", EntityPlaceEvent.class);
                put("EntityPortalEnterEvent", EntityPortalEnterEvent.class);
                put("EntityPortalEvent", EntityPortalEvent.class);
                put("EntityPortalExitEvent", EntityPortalExitEvent.class);
                put("EntityPoseChangeEvent", EntityPoseChangeEvent.class);
                put("EntityPotionEffectEvent", EntityPotionEffectEvent.class);
                put("EntityRegainHealthEvent", EntityRegainHealthEvent.class);
                put("EntityRemoveEvent", EntityRemoveEvent.class);
                put("EntityResurrectEvent", EntityResurrectEvent.class);
                put("EntityShootBowEvent", EntityShootBowEvent.class);
                put("EntitySpawnEvent", EntitySpawnEvent.class);
                put("EntitySpellCastEvent", EntitySpellCastEvent.class);
                put("EntityTameEvent", EntityTameEvent.class);
                put("EntityTargetEvent", EntityTargetEvent.class);
                put("EntityTargetLivingEntityEvent", EntityTargetLivingEntityEvent.class);
                put("EntityTeleportEvent", EntityTeleportEvent.class);
                put("EntityToggleGlideEvent", EntityToggleGlideEvent.class);
                put("EntityToggleSwimEvent", EntityToggleSwimEvent.class);
                put("EntityTransformEvent", EntityTransformEvent.class);
                put("EntityUnleashEvent", EntityUnleashEvent.class);
                put("ExpBottleEvent", ExpBottleEvent.class);
                put("ExplosionPrimeEvent", ExplosionPrimeEvent.class);
                put("FireworkExplodeEvent", FireworkExplodeEvent.class);
                put("FoodLevelChangeEvent", FoodLevelChangeEvent.class);
                put("HorseJumpEvent", HorseJumpEvent.class);
                put("ItemDespawnEvent", ItemDespawnEvent.class);
                put("ItemMergeEvent", ItemMergeEvent.class);
                put("ItemSpawnEvent", ItemSpawnEvent.class);
                put("LingeringPotionSplashEvent", LingeringPotionSplashEvent.class);
                put("PiglinBarterEvent", PiglinBarterEvent.class);
                put("PigZapEvent", PigZapEvent.class);
                put("PigZombieAngerEvent", PigZombieAngerEvent.class);
                put("PlayerDeathEvent", PlayerDeathEvent.class);
                put("PlayerLeashEntityEvent", PlayerLeashEntityEvent.class);
                put("PotionSplashEvent", PotionSplashEvent.class);
                put("ProjectileHitEvent", ProjectileHitEvent.class);
                put("ProjectileLaunchEvent", ProjectileLaunchEvent.class);
                put("SheepDyeWoolEvent", SheepDyeWoolEvent.class);
                put("SheepRegrowWoolEvent", SheepRegrowWoolEvent.class);
                put("SlimeSplitEvent", SlimeSplitEvent.class);
                put("SpawnerSpawnEvent", SpawnerSpawnEvent.class);
                put("StriderTemperatureChangeEvent", StriderTemperatureChangeEvent.class);
                put("TrialSpawnerSpawnEvent", TrialSpawnerSpawnEvent.class);
                put("VillagerAcquireTradeEvent", VillagerAcquireTradeEvent.class);
                put("VillagerCareerChangeEvent", VillagerCareerChangeEvent.class);
                put("VillagerReplenishTradeEvent", VillagerReplenishTradeEvent.class);

                // Hanging Event
                put("HangingBreakByEntityEvent", HangingBreakByEntityEvent.class);
                put("HangingBreakEvent", HangingBreakEvent.class);
                put("HangingPlaceEvent", HangingPlaceEvent.class);

                // Inventory Event
                put("BrewEvent", BrewEvent.class);
                put("BrewingStandFuelEvent", BrewingStandFuelEvent.class);
                put("CraftItemEvent", CraftItemEvent.class);
                put("FurnaceBurnEvent", FurnaceBurnEvent.class);
                put("FurnaceExtractEvent", FurnaceExtractEvent.class);
                put("FurnaceSmeltEvent", FurnaceSmeltEvent.class);
                put("FurnaceStartSmeltEvent", FurnaceStartSmeltEvent.class);
                put("HopperInventorySearchEvent", HopperInventorySearchEvent.class);
                put("InventoryClickEvent", InventoryClickEvent.class);
                put("InventoryCloseEvent", InventoryCloseEvent.class);
                put("InventoryCreativeEvent", InventoryCreativeEvent.class);
                put("InventoryDragEvent", InventoryDragEvent.class);
                put("InventoryEvent", InventoryEvent.class);
                put("InventoryMoveItemEvent", InventoryMoveItemEvent.class);
                put("InventoryOpenEvent", InventoryOpenEvent.class);
                put("InventoryPickupItemEvent", InventoryPickupItemEvent.class);
                put("PrepareAnvilEvent", PrepareAnvilEvent.class);
                put("PrepareGrindstoneEvent", PrepareGrindstoneEvent.class);
                put("PrepareItemCraftEvent", PrepareItemCraftEvent.class);
                put("PrepareSmithingEvent", PrepareSmithingEvent.class);
                put("SmithItemEvent", SmithItemEvent.class);
                put("TradeSelectEvent", TradeSelectEvent.class);
                put("PrepareInventoryResultEvent", PrepareInventoryResultEvent.class);

                // Player Event
                put("AsyncPlayerChatEvent", AsyncPlayerChatEvent.class);
                put("AsyncPlayerChatPreviewEvent", AsyncPlayerChatPreviewEvent.class);
                put("PlayerJoinEvent", PlayerJoinEvent.class);
                put("PlayerQuitEvent", PlayerQuitEvent.class);
                put("AsyncPlayerPreLoginEvent", AsyncPlayerPreLoginEvent.class);
                put("PlayerAdvancementDoneEvent", PlayerAdvancementDoneEvent.class);
                put("PlayerAnimationEvent", PlayerAnimationEvent.class);
                put("PlayerArmorStandManipulateEvent", PlayerArmorStandManipulateEvent.class);
                put("PlayerAttemptPickupItemEvent", PlayerAttemptPickupItemEvent.class);
                put("PlayerBedEnterEvent", PlayerBedEnterEvent.class);
                put("PlayerBedLeaveEvent", PlayerBedLeaveEvent.class);
                put("PlayerBucketEmptyEvent", PlayerBucketEmptyEvent.class);
                put("PlayerBucketEntityEvent", PlayerBucketEntityEvent.class);
                put("PlayerBucketFillEvent", PlayerBucketFillEvent.class);
                put("PlayerBucketFishEvent", PlayerBucketFishEvent.class);
                put("PlayerChangedMainHandEvent", PlayerChangedMainHandEvent.class);
                put("PlayerChangedWorldEvent", PlayerChangedWorldEvent.class);
                put("PlayerChatEvent", PlayerChatEvent.class);
                put("PlayerChatTabCompleteEvent", PlayerChatTabCompleteEvent.class);
                put("PlayerCommandPreprocessEvent", PlayerCommandPreprocessEvent.class);
                put("PlayerCommandSendEvent", PlayerCommandSendEvent.class);
                put("PlayerDropItemEvent", PlayerDropItemEvent.class);
                put("PlayerEditBookEvent", PlayerEditBookEvent.class);
                put("PlayerEggThrowEvent", PlayerEggThrowEvent.class);
                put("PlayerExpChangeEvent", PlayerExpChangeEvent.class);
                put("PlayerExpCooldownChangeEvent", PlayerExpCooldownChangeEvent.class);
                put("PlayerFishEvent", PlayerFishEvent.class);
                put("PlayerGameModeChangeEvent", PlayerGameModeChangeEvent.class);
                put("PlayerHarvestBlockEvent", PlayerHarvestBlockEvent.class);
                put("PlayerHideEntityEvent", PlayerHideEntityEvent.class);
                put("PlayerInputEvent", PlayerInputEvent.class);
                put("PlayerInteractAtEntityEvent", PlayerInteractAtEntityEvent.class);
                put("PlayerInteractEntityEvent", PlayerInteractEntityEvent.class);
                put("PlayerInteractEvent", PlayerInteractEvent.class);
                put("PlayerItemBreakEvent", PlayerItemBreakEvent.class);
                put("PlayerItemConsumeEvent", PlayerItemConsumeEvent.class);
                put("PlayerItemDamageEvent", PlayerItemDamageEvent.class);
                put("PlayerItemHeldEvent", PlayerItemHeldEvent.class);
                put("PlayerItemMendEvent", PlayerItemMendEvent.class);
                put("PlayerKickEvent", PlayerKickEvent.class);
                put("PlayerLevelChangeEvent", PlayerLevelChangeEvent.class);
                put("PlayerLinksSendEvent", PlayerLinksSendEvent.class);
                put("PlayerLocaleChangeEvent", PlayerLocaleChangeEvent.class);
                put("PlayerLoginEvent", PlayerLoginEvent.class);
                put("PlayerMoveEvent", PlayerMoveEvent.class);
                put("PlayerPickupArrowEvent", PlayerPickupArrowEvent.class);
                put("PlayerPickupItemEvent", PlayerPickupItemEvent.class);
                put("PlayerPortalEvent", PlayerPortalEvent.class);
                put("PlayerPreLoginEvent", PlayerPreLoginEvent.class);
                put("PlayerRecipeBookClickEvent", PlayerRecipeBookClickEvent.class);
                put("PlayerRecipeBookSettingsChangeEvent", PlayerRecipeBookSettingsChangeEvent.class);
                put("PlayerRecipeDiscoverEvent", PlayerRecipeDiscoverEvent.class);
                put("PlayerRegisterChannelEvent", PlayerRegisterChannelEvent.class);
                put("PlayerResourcePackStatusEvent", PlayerResourcePackStatusEvent.class);
                put("PlayerRespawnEvent", PlayerRespawnEvent.class);
                put("PlayerRiptideEvent", PlayerRiptideEvent.class);
                put("PlayerShearEntityEvent", PlayerShearEntityEvent.class);
                put("PlayerShowEntityEvent", PlayerShowEntityEvent.class);
                put("PlayerSignOpenEvent", PlayerSignOpenEvent.class);
                put("PlayerSpawnChangeEvent", PlayerSpawnChangeEvent.class);
                put("PlayerStatisticIncrementEvent", PlayerStatisticIncrementEvent.class);
                put("PlayerSwapHandItemsEvent", PlayerSwapHandItemsEvent.class);
                put("PlayerTakeLecternBookEvent", PlayerTakeLecternBookEvent.class);
                put("PlayerTeleportEvent", PlayerTeleportEvent.class);
                put("PlayerToggleFlightEvent", PlayerToggleFlightEvent.class);
                put("PlayerToggleSneakEvent", PlayerToggleSneakEvent.class);
                put("PlayerToggleSprintEvent", PlayerToggleSprintEvent.class);
                put("PlayerUnleashEntityEvent", PlayerUnleashEntityEvent.class);
                put("PlayerUnregisterChannelEvent", PlayerUnregisterChannelEvent.class);
                put("PlayerVelocityEvent", PlayerVelocityEvent.class);

                // Raid Event
                put("RaidFinishEvent", RaidFinishEvent.class);
                put("RaidSpawnWaveEvent", RaidSpawnWaveEvent.class);
                put("RaidStopEvent", RaidStopEvent.class);
                put("RaidTriggerEvent", RaidTriggerEvent.class);

                // Server Event
                put("BroadcastMessageEvent", BroadcastMessageEvent.class);
                put("MapInitializeEvent", MapInitializeEvent.class);
                put("PluginDisableEvent", PluginDisableEvent.class);
                put("PluginEnableEvent", PluginEnableEvent.class);
                put("ServerLoadEvent", ServerLoadEvent.class);
                put("ServerListPingEvent", ServerListPingEvent.class);
                put("RemoteServerCommandEvent", RemoteServerCommandEvent.class);
                put("ServerCommandEvent", ServerCommandEvent.class);
                put("ServiceRegisterEvent", ServiceRegisterEvent.class);
                put("ServiceUnregisterEvent", ServiceUnregisterEvent.class);
                put("TabCompleteEvent", TabCompleteEvent.class);

                // Vehicle Event
                put("VehicleBlockCollisionEvent", VehicleBlockCollisionEvent.class);
                put("VehicleCreateEvent", VehicleCreateEvent.class);
                put("VehicleDamageEvent", VehicleDamageEvent.class);
                put("VehicleDestroyEvent", VehicleDestroyEvent.class);
                put("VehicleEnterEvent", VehicleEnterEvent.class);
                put("VehicleEntityCollisionEvent", VehicleEntityCollisionEvent.class);
                put("VehicleExitEvent", VehicleExitEvent.class);
                put("VehicleMoveEvent", VehicleMoveEvent.class);
                put("VehicleUpdateEvent", VehicleUpdateEvent.class);

                // Wather Event
                put("LightningStrikeEvent", LightningStrikeEvent.class);
                put("ThunderChangeEvent", ThunderChangeEvent.class);
                put("WeatherChangeEvent", WeatherChangeEvent.class);

                // World Event
                put("AsyncStructureGenerateEvent", AsyncStructureGenerateEvent.class);
                put("AsyncStructureSpawnEvent", AsyncStructureSpawnEvent.class);
                put("ChunkLoadEvent", ChunkLoadEvent.class);
                put("ChunkPopulateEvent", ChunkPopulateEvent.class);
                put("ChunkUnloadEvent", ChunkUnloadEvent.class);
                put("EntitiesLoadEvent", EntitiesLoadEvent.class);
                put("EntitiesUnloadEvent", EntitiesUnloadEvent.class);
                put("GenericGameEvent", GenericGameEvent.class);
                put("LootGenerateEvent", LootGenerateEvent.class);
                put("PortalCreateEvent", PortalCreateEvent.class);
                put("SpawnChangeEvent", SpawnChangeEvent.class);
                put("StructureGrowEvent", StructureGrowEvent.class);
                put("TimeSkipEvent", TimeSkipEvent.class);
                put("WorldInitEvent", WorldInitEvent.class);
                put("WorldLoadEvent", WorldLoadEvent.class);
                put("WorldSaveEvent", WorldSaveEvent.class);
                put("WorldUnloadEvent", WorldUnloadEvent.class);

                // Paper Event
                // Block Event
                put("BeaconActivatedEvent", BeaconActivatedEvent.class);
                put("BeaconDeactivatedEvent", BeaconDeactivatedEvent.class);
                put("BellRevealRaiderEvent", BellRevealRaiderEvent.class);
                put("Paper_BellRingEvent", BellRingEvent.class);
                put("BlockBreakBlockEvent", BlockBreakBlockEvent.class);
                put("BlockBreakProgressUpdateEvent", BlockBreakProgressUpdateEvent.class);
                put("BlockFailedDispenseEvent", BlockFailedDispenseEvent.class);
                put("BlockLockCheckEvent", BlockLockCheckEvent.class);
                put("BlockPreDispenseEvent", BlockPreDispenseEvent.class);
                put("CompostItemEvent", CompostItemEvent.class);
                put("DragonEggFormEvent", DragonEggFormEvent.class);
                put("PlayerShearBlockEvent", PlayerShearBlockEvent.class);
                put("TargetHitEvent", TargetHitEvent.class);
                put("VaultChangeStateEvent", VaultChangeStateEvent.class);
                put("AnvilDamagedEvent", AnvilDamagedEvent.class);
                put("BeaconEffectEvent", BeaconEffectEvent.class);
                put("BlockDestroyEvent", BlockDestroyEvent.class);
                put("Paper_TNTPrimeEvent", com.destroystokyo.paper.event.block.TNTPrimeEvent.class);

                // Inventory Event
                put("Paper_PrepareGrindstoneEvent", com.destroystokyo.paper.event.inventory.PrepareGrindstoneEvent.class);
                put("PrepareResultEvent", PrepareResultEvent.class);

                // Brigadier
                put("AsyncPlayerSendCommandsEvent", AsyncPlayerSendCommandsEvent.class);
                put("AsyncPlayerSendSuggestionsEvent", AsyncPlayerSendSuggestionsEvent.class);
                put("CommandRegisteredEvent", CommandRegisteredEvent.class);

                // Connection Event
                put("PlayerConnectionValidateLoginEvent", PlayerConnectionValidateLoginEvent.class);

                // Entity Event
                put("CreeperIgniteEvent", CreeperIgniteEvent.class);
                put("EnderDragonFireballHitEvent", EnderDragonFireballHitEvent.class);
                put("EnderDragonFlameEvent", EnderDragonFlameEvent.class);
                put("EnderDragonShootFireballEvent", EnderDragonShootFireballEvent.class);
                put("EndermanAttackPlayerEvent", EndermanAttackPlayerEvent.class);
                put("EndermanEscapeEvent", EndermanEscapeEvent.class);
                put("EntityAddToWorldEvent", EntityAddToWorldEvent.class);
                put("EntityJumpEvent", EntityJumpEvent.class);
                put("EntityPathfindEvent", EntityPathfindEvent.class);
                put("EntityRemoveFromWorldEvent", EntityRemoveFromWorldEvent.class);
                put("EntityTeleportEndGatewayEvent", EntityTeleportEndGatewayEvent.class);
                put("EntityZapEvent", EntityZapEvent.class);
                put("ExperienceOrbMergeEvent", ExperienceOrbMergeEvent.class);
                put("PhantomPreSpawnEvent", PhantomPreSpawnEvent.class);
                put("PlayerNaturallySpawnCreaturesEvent", PlayerNaturallySpawnCreaturesEvent.class);
                put("PreCreatureSpawnEvent", PreCreatureSpawnEvent.class);
                put("PreSpawnerSpawnEvent", PreSpawnerSpawnEvent.class);
                put("ProjectileCollideEvent", ProjectileCollideEvent.class);
                put("SkeletonHorseTrapEvent", SkeletonHorseTrapEvent.class);
                put("SlimeChangeDirectionEvent", SlimeChangeDirectionEvent.class);
                put("SlimePathfindEvent", SlimePathfindEvent.class);
                put("SlimeSwimEvent", SlimeSwimEvent.class);
                put("SlimeTargetLivingEntityEvent", SlimeTargetLivingEntityEvent.class);
                put("SlimeWanderEvent", SlimeWanderEvent.class);
                put("ThrownEggHatchEvent", ThrownEggHatchEvent.class);
                put("TurtleGoHomeEvent", TurtleGoHomeEvent.class);
                put("TurtleLayEggEvent", TurtleLayEggEvent.class);
                put("TurtleStartDiggingEvent", TurtleStartDiggingEvent.class);
                put("WitchConsumePotionEvent", WitchConsumePotionEvent.class);
                put("WitchReadyPotionEvent", WitchReadyPotionEvent.class);
                put("WitchThrowPotionEvent", WitchThrowPotionEvent.class);
                put("EntityKnockbackByEntityEvent", EntityKnockbackByEntityEvent.class);
                put("ElderGuardianAppearanceEvent", ElderGuardianAppearanceEvent.class);
                put("EntityAttemptSmashAttackEvent", EntityAttemptSmashAttackEvent.class);
                put("EntityCompostItemEvent", EntityCompostItemEvent.class);
                put("EntityDamageItemEvent", EntityDamageItemEvent.class);
                put("EntityDyeEvent", EntityDyeEvent.class);
                put("EntityEffectTickEvent", EntityEffectTickEvent.class);
                put("EntityEquipmentChangedEvent", EntityEquipmentChangedEvent.class);
                put("EntityFertilizeEggEvent", EntityFertilizeEggEvent.class);
                put("EntityInsideBlockEvent", EntityInsideBlockEvent.class);
                put("Paper_EntityKnockbackEvent", EntityKnockbackEvent.class);
                put("EntityLoadCrossbowEvent", EntityLoadCrossbowEvent.class);
                put("EntityMoveEvent", EntityMoveEvent.class);
                put("EntityPortalReadyEvent", EntityPortalReadyEvent.class);
                put("EntityPushedByEntityAttackEvent", EntityPushedByEntityAttackEvent.class);
                put("EntityToggleSitEvent", EntityToggleSitEvent.class);
                put("FishHookStateChangeEvent", FishHookStateChangeEvent.class);
                put("PufferFishStateChangeEvent", PufferFishStateChangeEvent.class);
                put("ShulkerDuplicateEvent", ShulkerDuplicateEvent.class);
                put("TameableDeathMessageEvent", TameableDeathMessageEvent.class);
                put("WardenAngerChangeEvent", WardenAngerChangeEvent.class);
                put("WaterBottleSplashEvent", WaterBottleSplashEvent.class);

                // Packet Event
                put("ClientTickEndEvent", ClientTickEndEvent.class);

                // Player Event
                put("IllegalPacketEvent", IllegalPacketEvent.class);
                put("PlayerAdvancementCriterionGrantEvent", PlayerAdvancementCriterionGrantEvent.class);
                put("PlayerArmorChangeEvent", PlayerArmorChangeEvent.class);
                put("PlayerAttackEntityCooldownResetEvent", PlayerAttackEntityCooldownResetEvent.class);
                put("PlayerClientOptionsChangeEvent", PlayerClientOptionsChangeEvent.class);
                put("PlayerConnectionCloseEvent", PlayerConnectionCloseEvent.class);
                put("PlayerElytraBoostEvent", PlayerElytraBoostEvent.class);
                put("PlayerHandshakeEvent", PlayerHandshakeEvent.class);
                put("PlayerJumpEvent", PlayerJumpEvent.class);
                put("PlayerLaunchProjectileEvent", PlayerLaunchProjectileEvent.class);
                put("PlayerPickupExperienceEvent", PlayerPickupExperienceEvent.class);
                put("PlayerPostRespawnEvent", PlayerPostRespawnEvent.class);
                put("PlayerReadyArrowEvent", PlayerReadyArrowEvent.class);
                put("Paper_PlayerRecipeBookClickEvent", com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent.class);
                put("PlayerSetSpawnEvent", PlayerSetSpawnEvent.class);
                put("PlayerStopSpectatingEntityEvent", PlayerStopSpectatingEntityEvent.class);
                put("PlayerStartSpectatingEntityEvent", PlayerStartSpectatingEntityEvent.class);
                put("PlayerTeleportEndGatewayEvent", PlayerTeleportEndGatewayEvent.class);
                put("PlayerUseUnknownEntityEvent", PlayerUseUnknownEntityEvent.class);
                put("PlayerChunkLoadEvent", PlayerChunkLoadEvent.class);
                put("PlayerChunkUnloadEvent", PlayerChunkUnloadEvent.class);
                put("UncheckedSignChangeEvent", UncheckedSignChangeEvent.class);
                put("AsyncChatCommandDecorateEvent", AsyncChatCommandDecorateEvent.class);
                put("AsyncChatDecorateEvent", AsyncChatDecorateEvent.class);
                put("AsyncChatEvent", AsyncChatEvent.class);
                put("CartographyItemEvent", CartographyItemEvent.class);
                put("ChatEvent", ChatEvent.class);
                put("PlayerArmSwingEvent", PlayerArmSwingEvent.class);
                put("PlayerBedFailEnterEvent", PlayerBedFailEnterEvent.class);
                put("PlayerChangeBeaconEffectEvent", PlayerChangeBeaconEffectEvent.class);
                put("PlayerClientLoadedWorldEvent", PlayerClientLoadedWorldEvent.class);
                put("PlayerCustomClickEvent", PlayerCustomClickEvent.class);
                put("PlayerDeepSleepEvent", PlayerDeepSleepEvent.class);
                put("PlayerFailMoveEvent", PlayerFailMoveEvent.class);
                put("PlayerFlowerPotManipulateEvent", PlayerFlowerPotManipulateEvent.class);
                put("PlayerInsertLecternBookEvent", PlayerInsertLecternBookEvent.class);
                put("PlayerInventorySlotChangeEvent", PlayerInventorySlotChangeEvent.class);
                put("PlayerItemCooldownEvent", PlayerItemCooldownEvent.class);
                put("PlayerItemFrameChangeEvent", PlayerItemFrameChangeEvent.class);
                put("PlayerItemGroupCooldownEvent", PlayerItemGroupCooldownEvent.class);
                put("PlayerLecternPageChangeEvent", PlayerLecternPageChangeEvent.class);
                put("PlayerLoomPatternSelectEvent", PlayerLoomPatternSelectEvent.class);
                put("PlayerMapFilledEvent", PlayerMapFilledEvent.class);
                put("PlayerNameEntityEvent", PlayerNameEntityEvent.class);
                put("PlayerOpenSignEvent", PlayerOpenSignEvent.class);
                put("PlayerPickBlockEvent", PlayerPickBlockEvent.class);
                put("PlayerPickEntityEvent", PlayerPickEntityEvent.class);
                put("PlayerPurchaseEvent", PlayerPurchaseEvent.class);
                put("PlayerServerFullCheckEvent", PlayerServerFullCheckEvent.class);
                put("PlayerShieldDisableEvent", PlayerShieldDisableEvent.class);
                put("PlayerSignCommandPreprocessEvent", PlayerSignCommandPreprocessEvent.class);
                put("PlayerStonecutterRecipeSelectEvent", PlayerStonecutterRecipeSelectEvent.class);
                put("PlayerStopUsingItemEvent", PlayerStopUsingItemEvent.class);
                put("PlayerTrackEntityEvent", PlayerTrackEntityEvent.class);
                put("PlayerTradeEvent", PlayerTradeEvent.class);
                put("PlayerUntrackEntityEvent", PlayerUntrackEntityEvent.class);
                put("PrePlayerAttackEntityEvent", PrePlayerAttackEntityEvent.class);

                // Server Event
                put("ServerResourcesReloadedEvent", ServerResourcesReloadedEvent.class);
                put("WhitelistStateUpdateEvent", WhitelistStateUpdateEvent.class);
                put("AsyncTabCompleteEvent", AsyncTabCompleteEvent.class);
                put("GS4QueryEvent", GS4QueryEvent.class);
                put("PaperServerListPingEvent", PaperServerListPingEvent.class);
                put("ServerExceptionEvent", ServerExceptionEvent.class);
                put("ServerTickEndEvent", ServerTickEndEvent.class);
                put("ServerTickStartEvent", ServerTickStartEvent.class);
                put("WhitelistToggleEvent", WhitelistToggleEvent.class);

                // World Event
                put("StructuresLocateEvent", StructuresLocateEvent.class);
                put("WorldGameRuleChangeEvent", WorldGameRuleChangeEvent.class);
                put("WorldBorderBoundsChangeEvent", WorldBorderBoundsChangeEvent.class);
                put("WorldBorderBoundsChangeFinishEvent", WorldBorderBoundsChangeFinishEvent.class);
                put("WorldBorderCenterChangeEvent", WorldBorderCenterChangeEvent.class);

                // Profile Event
                put("FillProfileEvent", FillProfileEvent.class);
                put("LookupProfileEvent", LookupProfileEvent.class);
                put("PreFillProfileEvent", PreFillProfileEvent.class);
                put("PreLookupProfileEvent", PreLookupProfileEvent.class);
                put("ProfileWhitelistVerifyEvent", ProfileWhitelistVerifyEvent.class);

                // Folia
                // Server Event
                put("RegionizedServerInitEvent", RegionizedServerInitEvent.class);
            }
        };
    }

    public static Map<String, Class<? extends Event>> getEvents() {
        return events;
    }
}
