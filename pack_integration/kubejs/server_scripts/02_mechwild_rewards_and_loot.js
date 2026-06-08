// Mechwild Ascension v0.5 reward hooks and salvage drops
// Place in: kubejs/server_scripts/02_mechwild_rewards_and_loot.js

ServerEvents.tags('item', event => {
  event.add('mechwild:salvage_materials', [
    'kubejs:rustforged_scrap',
    'kubejs:mech_salvage',
    'kubejs:ancient_servo',
    'kubejs:damaged_optic',
    'kubejs:wild_core_fragment',
    'kubejs:synthetic_muscle_fibre',
    'kubejs:ancient_alloy_scrap',
    'kubejs:charged_servo',
    'kubejs:bio_mech_sinew',
    'kubejs:mech_diagnostic_chip',
    'kubejs:wild_core_shard',
    'kubejs:frame_evolution_circuit',
    'kubejs:predator_optic_cluster',
    'kubejs:heavy_plating_bundle',
    'kubejs:verdant_utility_matrix',
    'kubejs:storm_flight_matrix'
  ])

  event.add('mechwild:frame_blueprints', [
    'kubejs:raptor_frame_blueprint',
    'kubejs:beetle_frame_blueprint',
    'kubejs:stag_frame_blueprint',
    'kubejs:wolf_frame_blueprint',
    'kubejs:hawk_frame_blueprint',
    'kubejs:chimera_frame_blueprint'
  ])

  event.add('mechwild:frames', [
    'kubejs:proto_raptor_frame',
    'kubejs:proto_beetle_frame',
    'kubejs:proto_stag_frame',
    'kubejs:proto_wolf_frame',
    'kubejs:proto_hawk_frame',
    'kubejs:proto_chimera_frame',
    'kubejs:raptor_striker_frame',
    'kubejs:beetle_bulwark_frame',
    'kubejs:stag_grovekeeper_frame',
    'kubejs:wolf_nightfang_frame',
    'kubejs:hawk_stormwing_frame'
  ])

  event.add('mechwild:mining_modules', ['kubejs:drill_claws_tier_1', 'kubejs:resonance_scanner', 'kubejs:deep_core_excavator'])
  event.add('mechwild:combat_modules', ['kubejs:fang_blade_array', 'kubejs:shock_lance_module', 'kubejs:shoulder_cannon_mount', 'kubejs:reactive_plating'])
  event.add('mechwild:farming_modules', ['kubejs:harvest_talons', 'kubejs:seeder_module', 'kubejs:hydro_sprayer', 'kubejs:lumber_jaw'])
  event.add('mechwild:automation_modules', ['kubejs:cargo_link', 'kubejs:autosmelt_core', 'kubejs:builder_arm', 'kubejs:logistics_uplink'])
  event.add('mechwild:movement_modules', ['kubejs:hydraulic_leg_servos', 'kubejs:sprint_servo_bundle', 'kubejs:wall_claw_kit', 'kubejs:dash_core', 'kubejs:glide_wing_module', 'kubejs:jet_booster', 'kubejs:aether_flight_core'])
})

// Simple salvage drops from vanilla enemies. This makes combat feed directly into mech progression.
EntityEvents.death(event => {
  const entity = event.entity
  const source = event.source
  const player = source.player
  if (!player) return

  const id = String(entity.type)
  const drop = item => entity.block.popItem(item)

  if (['minecraft:zombie', 'minecraft:husk', 'minecraft:drowned', 'minecraft:skeleton', 'minecraft:stray', 'minecraft:spider', 'minecraft:cave_spider'].includes(id)) {
    if (Math.random() < 0.35) drop('kubejs:rustforged_scrap')
    if (Math.random() < 0.08) drop('kubejs:mech_salvage')
  }

  if (['minecraft:creeper', 'minecraft:pillager', 'minecraft:vindicator', 'minecraft:witch'].includes(id)) {
    if (Math.random() < 0.25) drop('kubejs:mech_salvage')
    if (Math.random() < 0.08) drop('kubejs:damaged_optic')
  }

  if (['minecraft:enderman', 'minecraft:blaze', 'minecraft:piglin_brute', 'minecraft:guardian'].includes(id)) {
    if (Math.random() < 0.20) drop('kubejs:damaged_optic')
    if (Math.random() < 0.12) drop('kubejs:wild_core_fragment')
  }

  if (['minecraft:slime', 'minecraft:magma_cube', 'minecraft:phantom'].includes(id)) {
    if (Math.random() < 0.25) drop('kubejs:synthetic_muscle_fibre')
  }

  if (['minecraft:ravager', 'minecraft:warden', 'minecraft:elder_guardian', 'minecraft:evoker', 'minecraft:wither'].includes(id)) {
    drop('kubejs:wild_core_shard')
    drop('kubejs:ancient_servo')
    player.tell('§bMechwild: §fRare rogue mech components were recovered from the fallen threat.')
  }

  // v0.3: extra reward for the first Wild Hunt prototype boss.
  const name = String(entity.displayName.string)
  if (name.includes('Rustjaw Raptor')) {
    drop('kubejs:apex_mech_core')
    drop('kubejs:wild_core_shard')
    drop('kubejs:ancient_servo')
    player.give('kubejs:dash_core')
    player.tell('§6Rustjaw Raptor defeated. §fA Dash Core was recovered from the rogue frame.')
  }
})

// v0.3 Wild Hunt trigger. Right-clicking a Rogue Signal Flare starts the first miniboss hunt.
ItemEvents.rightClicked('kubejs:rogue_signal_flare', event => {
  const player = event.player

  // Consume one flare using a vanilla command so the script stays compatible across KubeJS builds.
  player.runCommandSilent('clear @s kubejs:rogue_signal_flare 1')

  player.tell('§4Wild Hunt initiated: §cRustjaw Raptor signal locked.')
  player.tell('§7A rogue machine-beast is breaching nearby. Defeat it for advanced mech parts.')
  player.potionEffects.add('minecraft:glowing', 160, 0, true, false)

  // A Ravager is used as the first safe prototype boss because it works in vanilla Forge without adding new mods.
  player.runCommandSilent(`execute at @s run summon minecraft:ravager ~ ~1 ~ {CustomName:'{"text":"Rustjaw Raptor","color":"red","bold":true}',CustomNameVisible:1b,PersistenceRequired:1b,Health:90f,Attributes:[{Name:"minecraft:generic.max_health",Base:90.0},{Name:"minecraft:generic.attack_damage",Base:11.0},{Name:"minecraft:generic.movement_speed",Base:0.32},{Name:"minecraft:generic.knockback_resistance",Base:0.65}],Tags:["mechwild_rogue_boss","rustjaw_raptor"]}`)
})
