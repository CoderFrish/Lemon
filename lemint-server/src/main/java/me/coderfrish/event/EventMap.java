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
                put("Bukkit_BellResonateEvent", BellResonateEvent.class);
                put("Bukkit_BellRingEvent", BellRingEvent.class);
                put("Bukkit_BlockBreakEvent", BlockBreakEvent.class);
                put("Bukkit_BlockBurnEvent", BlockBurnEvent.class);
                put("Bukkit_BlockCanBuildEvent", BlockCanBuildEvent.class);
                put("Bukkit_BlockCookEvent", BlockCookEvent.class);
                put("Bukkit_BlockDamageAbortEvent", BlockDamageAbortEvent.class);
                put("Bukkit_BlockDamageEvent", BlockDamageEvent.class);
                put("Bukkit_BlockDispenseArmorEvent", BlockDispenseArmorEvent.class);
                put("Bukkit_BlockDispenseEvent", BlockDispenseEvent.class);
                put("Bukkit_BlockDispenseLootEvent", BlockDispenseLootEvent.class);
                put("Bukkit_BlockDropItemEvent", BlockDropItemEvent.class);
                put("Bukkit_BlockExpEvent", BlockExpEvent.class);
                put("Bukkit_BlockExplodeEvent", BlockExplodeEvent.class);
                put("Bukkit_BlockFadeEvent", BlockFadeEvent.class);
                put("Bukkit_BlockFertilizeEvent", BlockFertilizeEvent.class);
                put("Bukkit_BlockFormEvent", BlockFormEvent.class);
                put("Bukkit_BlockFromToEvent", BlockFromToEvent.class);
                put("Bukkit_BlockGrowEvent", BlockGrowEvent.class);
                put("Bukkit_BlockIgniteEvent", BlockIgniteEvent.class);
                put("Bukkit_BlockMultiPlaceEvent", BlockMultiPlaceEvent.class);
                put("Bukkit_BlockPhysicsEvent", BlockPhysicsEvent.class);
                put("Bukkit_BlockPistonExtendEvent", BlockPistonExtendEvent.class);
                put("Bukkit_BlockPistonRetractEvent", BlockPistonRetractEvent.class);
                put("Bukkit_BlockPlaceEvent", BlockPlaceEvent.class);
                put("Bukkit_BlockReceiveGameEvent", BlockReceiveGameEvent.class);
                put("Bukkit_BlockRedstoneEvent", BlockRedstoneEvent.class);
                put("Bukkit_BlockShearEntityEvent", BlockShearEntityEvent.class);
                put("Bukkit_BlockSpreadEvent", BlockSpreadEvent.class);
                put("Bukkit_BrewingStartEvent", BrewingStartEvent.class);
                put("Bukkit_CampfireStartEvent", CampfireStartEvent.class);
                put("Bukkit_CauldronLevelChangeEvent", CauldronLevelChangeEvent.class);
                put("Bukkit_CrafterCraftEvent", CrafterCraftEvent.class);
                put("Bukkit_EntityBlockFormEvent", EntityBlockFormEvent.class);
                put("Bukkit_FluidLevelChangeEvent", FluidLevelChangeEvent.class);
                put("Bukkit_InventoryBlockStartEvent", InventoryBlockStartEvent.class);
                put("Bukkit_LeavesDecayEvent", LeavesDecayEvent.class);
                put("Bukkit_MoistureChangeEvent", MoistureChangeEvent.class);
                put("Bukkit_NotePlayEvent", NotePlayEvent.class);
                put("Bukkit_SculkBloomEvent", SculkBloomEvent.class);
                put("Bukkit_SignChangeEvent", SignChangeEvent.class);
                put("Bukkit_SpongeAbsorbEvent", SpongeAbsorbEvent.class);
                put("Bukkit_TNTPrimeEvent", TNTPrimeEvent.class);
                put("Bukkit_VaultDisplayItemEvent", VaultDisplayItemEvent.class);

                // Command Event
                put("Bukkit_UnknownCommandEvent", UnknownCommandEvent.class);

                // Enchantment
                put("Bukkit_EnchantItemEvent", EnchantItemEvent.class);
                put("Bukkit_PrepareItemEnchantEvent", PrepareItemEnchantEvent.class);

                // Entity Event
                put("Bukkit_EntityKnockbackEvent", org.bukkit.event.entity.EntityKnockbackEvent.class);
                put("Bukkit_AreaEffectCloudApplyEvent", AreaEffectCloudApplyEvent.class);
                put("Bukkit_ArrowBodyCountChangeEvent", ArrowBodyCountChangeEvent.class);
                put("Bukkit_BatToggleSleepEvent", BatToggleSleepEvent.class);
                put("Bukkit_CreatureSpawnEvent", CreatureSpawnEvent.class);
                put("Bukkit_CreeperPowerEvent", CreeperPowerEvent.class);
                put("Bukkit_EnderDragonChangePhaseEvent", EnderDragonChangePhaseEvent.class);
                put("Bukkit_EntityAirChangeEvent", EntityAirChangeEvent.class);
                put("Bukkit_EntityBreakDoorEvent", EntityBreakDoorEvent.class);
                put("Bukkit_EntityBreedEvent", EntityBreedEvent.class);
                put("Bukkit_EntityChangeBlockEvent", EntityChangeBlockEvent.class);
                put("Bukkit_EntityCombustByBlockEvent", EntityCombustByBlockEvent.class);
                put("Bukkit_EntityCombustEvent", EntityCombustEvent.class);
                put("Bukkit_EntityDamageByBlockEvent", EntityDamageByBlockEvent.class);
                put("Bukkit_EntityDamageByEntityEvent", EntityDamageByEntityEvent.class);
                put("Bukkit_EntityDeathEvent", EntityDeathEvent.class);
                put("Bukkit_EntityDismountEvent", EntityDismountEvent.class);
                put("Bukkit_EntityDropItemEvent", EntityDropItemEvent.class);
                put("Bukkit_EntityEnterBlockEvent", EntityEnterBlockEvent.class);
                put("Bukkit_EntityEnterLoveModeEvent", EntityEnterLoveModeEvent.class);
                put("Bukkit_EntityExhaustionEvent", EntityExhaustionEvent.class);
                put("Bukkit_EntityExplodeEvent", EntityExplodeEvent.class);
                put("Bukkit_EntityInteractEvent", EntityInteractEvent.class);
                put("Bukkit_EntityMountEvent", EntityMountEvent.class);
                put("Bukkit_EntityPickupItemEvent", EntityPickupItemEvent.class);
                put("Bukkit_EntityPlaceEvent", EntityPlaceEvent.class);
                put("Bukkit_EntityPortalEnterEvent", EntityPortalEnterEvent.class);
                put("Bukkit_EntityPortalEvent", EntityPortalEvent.class);
                put("Bukkit_EntityPortalExitEvent", EntityPortalExitEvent.class);
                put("Bukkit_EntityPoseChangeEvent", EntityPoseChangeEvent.class);
                put("Bukkit_EntityPotionEffectEvent", EntityPotionEffectEvent.class);
                put("Bukkit_EntityRegainHealthEvent", EntityRegainHealthEvent.class);
                put("Bukkit_EntityRemoveEvent", EntityRemoveEvent.class);
                put("Bukkit_EntityResurrectEvent", EntityResurrectEvent.class);
                put("Bukkit_EntityShootBowEvent", EntityShootBowEvent.class);
                put("Bukkit_EntitySpawnEvent", EntitySpawnEvent.class);
                put("Bukkit_EntitySpellCastEvent", EntitySpellCastEvent.class);
                put("Bukkit_EntityTameEvent", EntityTameEvent.class);
                put("Bukkit_EntityTargetEvent", EntityTargetEvent.class);
                put("Bukkit_EntityTargetLivingEntityEvent", EntityTargetLivingEntityEvent.class);
                put("Bukkit_EntityTeleportEvent", EntityTeleportEvent.class);
                put("Bukkit_EntityToggleGlideEvent", EntityToggleGlideEvent.class);
                put("Bukkit_EntityToggleSwimEvent", EntityToggleSwimEvent.class);
                put("Bukkit_EntityTransformEvent", EntityTransformEvent.class);
                put("Bukkit_EntityUnleashEvent", EntityUnleashEvent.class);
                put("Bukkit_ExpBottleEvent", ExpBottleEvent.class);
                put("Bukkit_ExplosionPrimeEvent", ExplosionPrimeEvent.class);
                put("Bukkit_FireworkExplodeEvent", FireworkExplodeEvent.class);
                put("Bukkit_FoodLevelChangeEvent", FoodLevelChangeEvent.class);
                put("Bukkit_HorseJumpEvent", HorseJumpEvent.class);
                put("Bukkit_ItemDespawnEvent", ItemDespawnEvent.class);
                put("Bukkit_ItemMergeEvent", ItemMergeEvent.class);
                put("Bukkit_ItemSpawnEvent", ItemSpawnEvent.class);
                put("Bukkit_LingeringPotionSplashEvent", LingeringPotionSplashEvent.class);
                put("Bukkit_PiglinBarterEvent", PiglinBarterEvent.class);
                put("Bukkit_PigZapEvent", PigZapEvent.class);
                put("Bukkit_PigZombieAngerEvent", PigZombieAngerEvent.class);
                put("Bukkit_PlayerDeathEvent", PlayerDeathEvent.class);
                put("Bukkit_PlayerLeashEntityEvent", PlayerLeashEntityEvent.class);
                put("Bukkit_PotionSplashEvent", PotionSplashEvent.class);
                put("Bukkit_ProjectileHitEvent", ProjectileHitEvent.class);
                put("Bukkit_ProjectileLaunchEvent", ProjectileLaunchEvent.class);
                put("Bukkit_SheepDyeWoolEvent", SheepDyeWoolEvent.class);
                put("Bukkit_SheepRegrowWoolEvent", SheepRegrowWoolEvent.class);
                put("Bukkit_SlimeSplitEvent", SlimeSplitEvent.class);
                put("Bukkit_SpawnerSpawnEvent", SpawnerSpawnEvent.class);
                put("Bukkit_StriderTemperatureChangeEvent", StriderTemperatureChangeEvent.class);
                put("Bukkit_TrialSpawnerSpawnEvent", TrialSpawnerSpawnEvent.class);
                put("Bukkit_VillagerAcquireTradeEvent", VillagerAcquireTradeEvent.class);
                put("Bukkit_VillagerCareerChangeEvent", VillagerCareerChangeEvent.class);
                put("Bukkit_VillagerReplenishTradeEvent", VillagerReplenishTradeEvent.class);

                // Hanging Event
                put("Bukkit_HangingBreakByEntityEvent", HangingBreakByEntityEvent.class);
                put("Bukkit_HangingBreakEvent", HangingBreakEvent.class);
                put("Bukkit_HangingPlaceEvent", HangingPlaceEvent.class);

                // Inventory Event
                put("Bukkit_BrewEvent", BrewEvent.class);
                put("Bukkit_BrewingStandFuelEvent", BrewingStandFuelEvent.class);
                put("Bukkit_CraftItemEvent", CraftItemEvent.class);
                put("Bukkit_FurnaceBurnEvent", FurnaceBurnEvent.class);
                put("Bukkit_FurnaceExtractEvent", FurnaceExtractEvent.class);
                put("Bukkit_FurnaceSmeltEvent", FurnaceSmeltEvent.class);
                put("Bukkit_FurnaceStartSmeltEvent", FurnaceStartSmeltEvent.class);
                put("Bukkit_HopperInventorySearchEvent", HopperInventorySearchEvent.class);
                put("Bukkit_InventoryClickEvent", InventoryClickEvent.class);
                put("Bukkit_InventoryCloseEvent", InventoryCloseEvent.class);
                put("Bukkit_InventoryCreativeEvent", InventoryCreativeEvent.class);
                put("Bukkit_InventoryDragEvent", InventoryDragEvent.class);
                put("Bukkit_InventoryEvent", InventoryEvent.class);
                put("Bukkit_InventoryMoveItemEvent", InventoryMoveItemEvent.class);
                put("Bukkit_InventoryOpenEvent", InventoryOpenEvent.class);
                put("Bukkit_InventoryPickupItemEvent", InventoryPickupItemEvent.class);
                put("Bukkit_PrepareAnvilEvent", PrepareAnvilEvent.class);
                put("Bukkit_PrepareGrindstoneEvent", PrepareGrindstoneEvent.class);
                put("Bukkit_PrepareItemCraftEvent", PrepareItemCraftEvent.class);
                put("Bukkit_PrepareSmithingEvent", PrepareSmithingEvent.class);
                put("Bukkit_SmithItemEvent", SmithItemEvent.class);
                put("Bukkit_TradeSelectEvent", TradeSelectEvent.class);
                put("Bukkit_PrepareInventoryResultEvent", PrepareInventoryResultEvent.class);

                // Player Event
                put("Bukkit_AsyncPlayerChatEvent", AsyncPlayerChatEvent.class);
                put("Bukkit_AsyncPlayerChatPreviewEvent", AsyncPlayerChatPreviewEvent.class);
                put("Bukkit_PlayerJoinEvent", PlayerJoinEvent.class);
                put("Bukkit_PlayerQuitEvent", PlayerQuitEvent.class);
                put("Bukkit_AsyncPlayerPreLoginEvent", AsyncPlayerPreLoginEvent.class);
                put("Bukkit_PlayerAdvancementDoneEvent", PlayerAdvancementDoneEvent.class);
                put("Bukkit_PlayerAnimationEvent", PlayerAnimationEvent.class);
                put("Bukkit_PlayerArmorStandManipulateEvent", PlayerArmorStandManipulateEvent.class);
                put("Bukkit_PlayerAttemptPickupItemEvent", PlayerAttemptPickupItemEvent.class);
                put("Bukkit_PlayerBedEnterEvent", PlayerBedEnterEvent.class);
                put("Bukkit_PlayerBedLeaveEvent", PlayerBedLeaveEvent.class);
                put("Bukkit_PlayerBucketEmptyEvent", PlayerBucketEmptyEvent.class);
                put("Bukkit_PlayerBucketEntityEvent", PlayerBucketEntityEvent.class);
                put("Bukkit_PlayerBucketFillEvent", PlayerBucketFillEvent.class);
                put("Bukkit_PlayerBucketFishEvent", PlayerBucketFishEvent.class);
                put("Bukkit_PlayerChangedMainHandEvent", PlayerChangedMainHandEvent.class);
                put("Bukkit_PlayerChangedWorldEvent", PlayerChangedWorldEvent.class);
                put("Bukkit_PlayerChatEvent", PlayerChatEvent.class);
                put("Bukkit_PlayerChatTabCompleteEvent", PlayerChatTabCompleteEvent.class);
                put("Bukkit_PlayerCommandPreprocessEvent", PlayerCommandPreprocessEvent.class);
                put("Bukkit_PlayerCommandSendEvent", PlayerCommandSendEvent.class);
                put("Bukkit_PlayerDropItemEvent", PlayerDropItemEvent.class);
                put("Bukkit_PlayerEditBookEvent", PlayerEditBookEvent.class);
                put("Bukkit_PlayerEggThrowEvent", PlayerEggThrowEvent.class);
                put("Bukkit_PlayerExpChangeEvent", PlayerExpChangeEvent.class);
                put("Bukkit_PlayerExpCooldownChangeEvent", PlayerExpCooldownChangeEvent.class);
                put("Bukkit_PlayerFishEvent", PlayerFishEvent.class);
                put("Bukkit_PlayerGameModeChangeEvent", PlayerGameModeChangeEvent.class);
                put("Bukkit_PlayerHarvestBlockEvent", PlayerHarvestBlockEvent.class);
                put("Bukkit_PlayerHideEntityEvent", PlayerHideEntityEvent.class);
                put("Bukkit_PlayerInputEvent", PlayerInputEvent.class);
                put("Bukkit_PlayerInteractAtEntityEvent", PlayerInteractAtEntityEvent.class);
                put("Bukkit_PlayerInteractEntityEvent", PlayerInteractEntityEvent.class);
                put("Bukkit_PlayerInteractEvent", PlayerInteractEvent.class);
                put("Bukkit_PlayerItemBreakEvent", PlayerItemBreakEvent.class);
                put("Bukkit_PlayerItemConsumeEvent", PlayerItemConsumeEvent.class);
                put("Bukkit_PlayerItemDamageEvent", PlayerItemDamageEvent.class);
                put("Bukkit_PlayerItemHeldEvent", PlayerItemHeldEvent.class);
                put("Bukkit_PlayerItemMendEvent", PlayerItemMendEvent.class);
                put("Bukkit_PlayerKickEvent", PlayerKickEvent.class);
                put("Bukkit_PlayerLevelChangeEvent", PlayerLevelChangeEvent.class);
                put("Bukkit_PlayerLinksSendEvent", PlayerLinksSendEvent.class);
                put("Bukkit_PlayerLocaleChangeEvent", PlayerLocaleChangeEvent.class);
                put("Bukkit_PlayerLoginEvent", PlayerLoginEvent.class);
                put("Bukkit_PlayerMoveEvent", PlayerMoveEvent.class);
                put("Bukkit_PlayerPickupArrowEvent", PlayerPickupArrowEvent.class);
                put("Bukkit_PlayerPickupItemEvent", PlayerPickupItemEvent.class);
                put("Bukkit_PlayerPortalEvent", PlayerPortalEvent.class);
                put("Bukkit_PlayerPreLoginEvent", PlayerPreLoginEvent.class);
                put("Bukkit_PlayerRecipeBookClickEvent", PlayerRecipeBookClickEvent.class);
                put("Bukkit_PlayerRecipeBookSettingsChangeEvent", PlayerRecipeBookSettingsChangeEvent.class);
                put("Bukkit_PlayerRecipeDiscoverEvent", PlayerRecipeDiscoverEvent.class);
                put("Bukkit_PlayerRegisterChannelEvent", PlayerRegisterChannelEvent.class);
                put("Bukkit_PlayerResourcePackStatusEvent", PlayerResourcePackStatusEvent.class);
                put("Bukkit_PlayerRespawnEvent", PlayerRespawnEvent.class);
                put("Bukkit_PlayerRiptideEvent", PlayerRiptideEvent.class);
                put("Bukkit_PlayerShearEntityEvent", PlayerShearEntityEvent.class);
                put("Bukkit_PlayerShowEntityEvent", PlayerShowEntityEvent.class);
                put("Bukkit_PlayerSignOpenEvent", PlayerSignOpenEvent.class);
                put("Bukkit_PlayerSpawnChangeEvent", PlayerSpawnChangeEvent.class);
                put("Bukkit_PlayerStatisticIncrementEvent", PlayerStatisticIncrementEvent.class);
                put("Bukkit_PlayerSwapHandItemsEvent", PlayerSwapHandItemsEvent.class);
                put("Bukkit_PlayerTakeLecternBookEvent", PlayerTakeLecternBookEvent.class);
                put("Bukkit_PlayerTeleportEvent", PlayerTeleportEvent.class);
                put("Bukkit_PlayerToggleFlightEvent", PlayerToggleFlightEvent.class);
                put("Bukkit_PlayerToggleSneakEvent", PlayerToggleSneakEvent.class);
                put("Bukkit_PlayerToggleSprintEvent", PlayerToggleSprintEvent.class);
                put("Bukkit_PlayerUnleashEntityEvent", PlayerUnleashEntityEvent.class);
                put("Bukkit_PlayerUnregisterChannelEvent", PlayerUnregisterChannelEvent.class);
                put("Bukkit_PlayerVelocityEvent", PlayerVelocityEvent.class);

                // Raid Event
                put("Bukkit_RaidFinishEvent", RaidFinishEvent.class);
                put("Bukkit_RaidSpawnWaveEvent", RaidSpawnWaveEvent.class);
                put("Bukkit_RaidStopEvent", RaidStopEvent.class);
                put("Bukkit_RaidTriggerEvent", RaidTriggerEvent.class);

                // Server Event
                put("Bukkit_BroadcastMessageEvent", BroadcastMessageEvent.class);
                put("Bukkit_MapInitializeEvent", MapInitializeEvent.class);
                put("Bukkit_PluginDisableEvent", PluginDisableEvent.class);
                put("Bukkit_PluginEnableEvent", PluginEnableEvent.class);
                put("Bukkit_ServerLoadEvent", ServerLoadEvent.class);
                put("Bukkit_ServerListPingEvent", ServerListPingEvent.class);
                put("Bukkit_RemoteServerCommandEvent", RemoteServerCommandEvent.class);
                put("Bukkit_ServerCommandEvent", ServerCommandEvent.class);
                put("Bukkit_ServiceRegisterEvent", ServiceRegisterEvent.class);
                put("Bukkit_ServiceUnregisterEvent", ServiceUnregisterEvent.class);
                put("Bukkit_TabCompleteEvent", TabCompleteEvent.class);

                // Vehicle Event
                put("Bukkit_VehicleBlockCollisionEvent", VehicleBlockCollisionEvent.class);
                put("Bukkit_VehicleCreateEvent", VehicleCreateEvent.class);
                put("Bukkit_VehicleDamageEvent", VehicleDamageEvent.class);
                put("Bukkit_VehicleDestroyEvent", VehicleDestroyEvent.class);
                put("Bukkit_VehicleEnterEvent", VehicleEnterEvent.class);
                put("Bukkit_VehicleEntityCollisionEvent", VehicleEntityCollisionEvent.class);
                put("Bukkit_VehicleExitEvent", VehicleExitEvent.class);
                put("Bukkit_VehicleMoveEvent", VehicleMoveEvent.class);
                put("Bukkit_VehicleUpdateEvent", VehicleUpdateEvent.class);

                // Wather Event
                put("Bukkit_LightningStrikeEvent", LightningStrikeEvent.class);
                put("Bukkit_ThunderChangeEvent", ThunderChangeEvent.class);
                put("Bukkit_WeatherChangeEvent", WeatherChangeEvent.class);

                // World Event
                put("Bukkit_AsyncStructureGenerateEvent", AsyncStructureGenerateEvent.class);
                put("Bukkit_AsyncStructureSpawnEvent", AsyncStructureSpawnEvent.class);
                put("Bukkit_ChunkLoadEvent", ChunkLoadEvent.class);
                put("Bukkit_ChunkPopulateEvent", ChunkPopulateEvent.class);
                put("Bukkit_ChunkUnloadEvent", ChunkUnloadEvent.class);
                put("Bukkit_EntitiesLoadEvent", EntitiesLoadEvent.class);
                put("Bukkit_EntitiesUnloadEvent", EntitiesUnloadEvent.class);
                put("Bukkit_GenericGameEvent", GenericGameEvent.class);
                put("Bukkit_LootGenerateEvent", LootGenerateEvent.class);
                put("Bukkit_PortalCreateEvent", PortalCreateEvent.class);
                put("Bukkit_SpawnChangeEvent", SpawnChangeEvent.class);
                put("Bukkit_StructureGrowEvent", StructureGrowEvent.class);
                put("Bukkit_TimeSkipEvent", TimeSkipEvent.class);
                put("Bukkit_WorldInitEvent", WorldInitEvent.class);
                put("Bukkit_WorldLoadEvent", WorldLoadEvent.class);
                put("Bukkit_WorldSaveEvent", WorldSaveEvent.class);
                put("Bukkit_WorldUnloadEvent", WorldUnloadEvent.class);

                // Paper Event
                // Block Event
                put("Paper_BeaconActivatedEvent", BeaconActivatedEvent.class);
                put("Paper_BeaconDeactivatedEvent", BeaconDeactivatedEvent.class);
                put("Paper_BellRevealRaiderEvent", BellRevealRaiderEvent.class);
                put("Paper_BellRingEvent", BellRingEvent.class);
                put("Paper_BlockBreakBlockEvent", BlockBreakBlockEvent.class);
                put("Paper_BlockBreakProgressUpdateEvent", BlockBreakProgressUpdateEvent.class);
                put("Paper_BlockFailedDispenseEvent", BlockFailedDispenseEvent.class);
                put("Paper_BlockLockCheckEvent", BlockLockCheckEvent.class);
                put("Paper_BlockPreDispenseEvent", BlockPreDispenseEvent.class);
                put("Paper_CompostItemEvent", CompostItemEvent.class);
                put("Paper_DragonEggFormEvent", DragonEggFormEvent.class);
                put("Paper_PlayerShearBlockEvent", PlayerShearBlockEvent.class);
                put("Paper_TargetHitEvent", TargetHitEvent.class);
                put("Paper_VaultChangeStateEvent", VaultChangeStateEvent.class);
                put("Paper_AnvilDamagedEvent", AnvilDamagedEvent.class);
                put("Paper_BeaconEffectEvent", BeaconEffectEvent.class);
                put("Paper_BlockDestroyEvent", BlockDestroyEvent.class);
                put("Paper_TNTPrimeEvent", com.destroystokyo.paper.event.block.TNTPrimeEvent.class);

                // Inventory Event
                put("Paper_PrepareGrindstoneEvent", com.destroystokyo.paper.event.inventory.PrepareGrindstoneEvent.class);
                put("Paper_PrepareResultEvent", PrepareResultEvent.class);

                // Brigadier
                put("Paper_AsyncPlayerSendCommandsEvent", AsyncPlayerSendCommandsEvent.class);
                put("Paper_AsyncPlayerSendSuggestionsEvent", AsyncPlayerSendSuggestionsEvent.class);
                put("Paper_CommandRegisteredEvent", CommandRegisteredEvent.class);

                // Connection Event
                put("Paper_PlayerConnectionValidateLoginEvent", PlayerConnectionValidateLoginEvent.class);

                // Entity Event
                put("Paper_CreeperIgniteEvent", CreeperIgniteEvent.class);
                put("Paper_EnderDragonFireballHitEvent", EnderDragonFireballHitEvent.class);
                put("Paper_EnderDragonFlameEvent", EnderDragonFlameEvent.class);
                put("Paper_EnderDragonShootFireballEvent", EnderDragonShootFireballEvent.class);
                put("Paper_EndermanAttackPlayerEvent", EndermanAttackPlayerEvent.class);
                put("Paper_EndermanEscapeEvent", EndermanEscapeEvent.class);
                put("Paper_EntityAddToWorldEvent", EntityAddToWorldEvent.class);
                put("Paper_EntityJumpEvent", EntityJumpEvent.class);
                put("Paper_EntityPathfindEvent", EntityPathfindEvent.class);
                put("Paper_EntityRemoveFromWorldEvent", EntityRemoveFromWorldEvent.class);
                put("Paper_EntityTeleportEndGatewayEvent", EntityTeleportEndGatewayEvent.class);
                put("Paper_EntityZapEvent", EntityZapEvent.class);
                put("Paper_ExperienceOrbMergeEvent", ExperienceOrbMergeEvent.class);
                put("Paper_PhantomPreSpawnEvent", PhantomPreSpawnEvent.class);
                put("Paper_PlayerNaturallySpawnCreaturesEvent", PlayerNaturallySpawnCreaturesEvent.class);
                put("Paper_PreCreatureSpawnEvent", PreCreatureSpawnEvent.class);
                put("Paper_PreSpawnerSpawnEvent", PreSpawnerSpawnEvent.class);
                put("Paper_ProjectileCollideEvent", ProjectileCollideEvent.class);
                put("Paper_SkeletonHorseTrapEvent", SkeletonHorseTrapEvent.class);
                put("Paper_SlimeChangeDirectionEvent", SlimeChangeDirectionEvent.class);
                put("Paper_SlimePathfindEvent", SlimePathfindEvent.class);
                put("Paper_SlimeSwimEvent", SlimeSwimEvent.class);
                put("Paper_SlimeTargetLivingEntityEvent", SlimeTargetLivingEntityEvent.class);
                put("Paper_SlimeWanderEvent", SlimeWanderEvent.class);
                put("Paper_ThrownEggHatchEvent", ThrownEggHatchEvent.class);
                put("Paper_TurtleGoHomeEvent", TurtleGoHomeEvent.class);
                put("Paper_TurtleLayEggEvent", TurtleLayEggEvent.class);
                put("Paper_TurtleStartDiggingEvent", TurtleStartDiggingEvent.class);
                put("Paper_WitchConsumePotionEvent", WitchConsumePotionEvent.class);
                put("Paper_WitchReadyPotionEvent", WitchReadyPotionEvent.class);
                put("Paper_WitchThrowPotionEvent", WitchThrowPotionEvent.class);
                put("Paper_EntityKnockbackByEntityEvent", EntityKnockbackByEntityEvent.class);
                put("Paper_ElderGuardianAppearanceEvent", ElderGuardianAppearanceEvent.class);
                put("Paper_EntityAttemptSmashAttackEvent", EntityAttemptSmashAttackEvent.class);
                put("Paper_EntityCompostItemEvent", EntityCompostItemEvent.class);
                put("Paper_EntityDamageItemEvent", EntityDamageItemEvent.class);
                put("Paper_EntityDyeEvent", EntityDyeEvent.class);
                put("Paper_EntityEffectTickEvent", EntityEffectTickEvent.class);
                put("Paper_EntityEquipmentChangedEvent", EntityEquipmentChangedEvent.class);
                put("Paper_EntityFertilizeEggEvent", EntityFertilizeEggEvent.class);
                put("Paper_EntityInsideBlockEvent", EntityInsideBlockEvent.class);
                put("Paper_Paper_EntityKnockbackEvent", EntityKnockbackEvent.class);
                put("Paper_EntityLoadCrossbowEvent", EntityLoadCrossbowEvent.class);
                put("Paper_EntityMoveEvent", EntityMoveEvent.class);
                put("Paper_EntityPortalReadyEvent", EntityPortalReadyEvent.class);
                put("Paper_EntityPushedByEntityAttackEvent", EntityPushedByEntityAttackEvent.class);
                put("Paper_EntityToggleSitEvent", EntityToggleSitEvent.class);
                put("Paper_FishHookStateChangeEvent", FishHookStateChangeEvent.class);
                put("Paper_PufferFishStateChangeEvent", PufferFishStateChangeEvent.class);
                put("Paper_ShulkerDuplicateEvent", ShulkerDuplicateEvent.class);
                put("Paper_TameableDeathMessageEvent", TameableDeathMessageEvent.class);
                put("Paper_WardenAngerChangeEvent", WardenAngerChangeEvent.class);
                put("Paper_WaterBottleSplashEvent", WaterBottleSplashEvent.class);

                // Packet Event
                put("Paper_ClientTickEndEvent", ClientTickEndEvent.class);

                // Player Event
                put("Paper_IllegalPacketEvent", IllegalPacketEvent.class);
                put("Paper_PlayerAdvancementCriterionGrantEvent", PlayerAdvancementCriterionGrantEvent.class);
                put("Paper_PlayerArmorChangeEvent", PlayerArmorChangeEvent.class);
                put("Paper_PlayerAttackEntityCooldownResetEvent", PlayerAttackEntityCooldownResetEvent.class);
                put("Paper_PlayerClientOptionsChangeEvent", PlayerClientOptionsChangeEvent.class);
                put("Paper_PlayerConnectionCloseEvent", PlayerConnectionCloseEvent.class);
                put("Paper_PlayerElytraBoostEvent", PlayerElytraBoostEvent.class);
                put("Paper_PlayerHandshakeEvent", PlayerHandshakeEvent.class);
                put("Paper_PlayerJumpEvent", PlayerJumpEvent.class);
                put("Paper_PlayerLaunchProjectileEvent", PlayerLaunchProjectileEvent.class);
                put("Paper_PlayerPickupExperienceEvent", PlayerPickupExperienceEvent.class);
                put("Paper_PlayerPostRespawnEvent", PlayerPostRespawnEvent.class);
                put("Paper_PlayerReadyArrowEvent", PlayerReadyArrowEvent.class);
                put("Paper_Paper_PlayerRecipeBookClickEvent", com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent.class);
                put("Paper_PlayerSetSpawnEvent", PlayerSetSpawnEvent.class);
                put("Paper_PlayerStopSpectatingEntityEvent", PlayerStopSpectatingEntityEvent.class);
                put("Paper_PlayerStartSpectatingEntityEvent", PlayerStartSpectatingEntityEvent.class);
                put("Paper_PlayerTeleportEndGatewayEvent", PlayerTeleportEndGatewayEvent.class);
                put("Paper_PlayerUseUnknownEntityEvent", PlayerUseUnknownEntityEvent.class);
                put("Paper_PlayerChunkLoadEvent", PlayerChunkLoadEvent.class);
                put("Paper_PlayerChunkUnloadEvent", PlayerChunkUnloadEvent.class);
                put("Paper_UncheckedSignChangeEvent", UncheckedSignChangeEvent.class);
                put("Paper_AsyncChatCommandDecorateEvent", AsyncChatCommandDecorateEvent.class);
                put("Paper_AsyncChatDecorateEvent", AsyncChatDecorateEvent.class);
                put("Paper_AsyncChatEvent", AsyncChatEvent.class);
                put("Paper_CartographyItemEvent", CartographyItemEvent.class);
                put("Paper_ChatEvent", ChatEvent.class);
                put("Paper_PlayerArmSwingEvent", PlayerArmSwingEvent.class);
                put("Paper_PlayerBedFailEnterEvent", PlayerBedFailEnterEvent.class);
                put("Paper_PlayerChangeBeaconEffectEvent", PlayerChangeBeaconEffectEvent.class);
                put("Paper_PlayerClientLoadedWorldEvent", PlayerClientLoadedWorldEvent.class);
                put("Paper_PlayerCustomClickEvent", PlayerCustomClickEvent.class);
                put("Paper_PlayerDeepSleepEvent", PlayerDeepSleepEvent.class);
                put("Paper_PlayerFailMoveEvent", PlayerFailMoveEvent.class);
                put("Paper_PlayerFlowerPotManipulateEvent", PlayerFlowerPotManipulateEvent.class);
                put("Paper_PlayerInsertLecternBookEvent", PlayerInsertLecternBookEvent.class);
                put("Paper_PlayerInventorySlotChangeEvent", PlayerInventorySlotChangeEvent.class);
                put("Paper_PlayerItemCooldownEvent", PlayerItemCooldownEvent.class);
                put("Paper_PlayerItemFrameChangeEvent", PlayerItemFrameChangeEvent.class);
                put("Paper_PlayerItemGroupCooldownEvent", PlayerItemGroupCooldownEvent.class);
                put("Paper_PlayerLecternPageChangeEvent", PlayerLecternPageChangeEvent.class);
                put("Paper_PlayerLoomPatternSelectEvent", PlayerLoomPatternSelectEvent.class);
                put("Paper_PlayerMapFilledEvent", PlayerMapFilledEvent.class);
                put("Paper_PlayerNameEntityEvent", PlayerNameEntityEvent.class);
                put("Paper_PlayerOpenSignEvent", PlayerOpenSignEvent.class);
                put("Paper_PlayerPickBlockEvent", PlayerPickBlockEvent.class);
                put("Paper_PlayerPickEntityEvent", PlayerPickEntityEvent.class);
                put("Paper_PlayerPurchaseEvent", PlayerPurchaseEvent.class);
                put("Paper_PlayerServerFullCheckEvent", PlayerServerFullCheckEvent.class);
                put("Paper_PlayerShieldDisableEvent", PlayerShieldDisableEvent.class);
                put("Paper_PlayerSignCommandPreprocessEvent", PlayerSignCommandPreprocessEvent.class);
                put("Paper_PlayerStonecutterRecipeSelectEvent", PlayerStonecutterRecipeSelectEvent.class);
                put("Paper_PlayerStopUsingItemEvent", PlayerStopUsingItemEvent.class);
                put("Paper_PlayerTrackEntityEvent", PlayerTrackEntityEvent.class);
                put("Paper_PlayerTradeEvent", PlayerTradeEvent.class);
                put("Paper_PlayerUntrackEntityEvent", PlayerUntrackEntityEvent.class);
                put("Paper_PrePlayerAttackEntityEvent", PrePlayerAttackEntityEvent.class);

                // Server Event
                put("Paper_ServerResourcesReloadedEvent", ServerResourcesReloadedEvent.class);
                put("Paper_WhitelistStateUpdateEvent", WhitelistStateUpdateEvent.class);
                put("Paper_AsyncTabCompleteEvent", AsyncTabCompleteEvent.class);
                put("Paper_GS4QueryEvent", GS4QueryEvent.class);
                put("Paper_PaperServerListPingEvent", PaperServerListPingEvent.class);
                put("Paper_ServerExceptionEvent", ServerExceptionEvent.class);
                put("Paper_ServerTickEndEvent", ServerTickEndEvent.class);
                put("Paper_ServerTickStartEvent", ServerTickStartEvent.class);
                put("Paper_WhitelistToggleEvent", WhitelistToggleEvent.class);

                // World Event
                put("Paper_StructuresLocateEvent", StructuresLocateEvent.class);
                put("Paper_WorldGameRuleChangeEvent", WorldGameRuleChangeEvent.class);
                put("Paper_WorldBorderBoundsChangeEvent", WorldBorderBoundsChangeEvent.class);
                put("Paper_WorldBorderBoundsChangeFinishEvent", WorldBorderBoundsChangeFinishEvent.class);
                put("Paper_WorldBorderCenterChangeEvent", WorldBorderCenterChangeEvent.class);

                // Profile Event
                put("Paper_FillProfileEvent", FillProfileEvent.class);
                put("Paper_LookupProfileEvent", LookupProfileEvent.class);
                put("Paper_PreFillProfileEvent", PreFillProfileEvent.class);
                put("Paper_PreLookupProfileEvent", PreLookupProfileEvent.class);
                put("Paper_ProfileWhitelistVerifyEvent", ProfileWhitelistVerifyEvent.class);

                // Folia
                // Server Event
                put("Folia_RegionizedServerInitEvent", RegionizedServerInitEvent.class);
            }
        };
    }

    public static Map<String, Class<? extends Event>> getEvents() {
        return events;
    }
}
