// Mechwild Ascension v0.5 recipes
// Place in: kubejs/server_scripts/01_mechwild_recipes.js

ServerEvents.recipes(event => {
  // Salvage conversion loop: common drops become structured mech parts.
  event.shapeless('kubejs:ancient_alloy_scrap', [
    'kubejs:rustforged_scrap',
    'minecraft:copper_ingot',
    'minecraft:redstone'
  ])

  event.shapeless('kubejs:mech_salvage', [
    'kubejs:rustforged_scrap',
    'kubejs:rustforged_scrap',
    'minecraft:iron_nugget',
    'minecraft:copper_ingot'
  ])

  event.shapeless('kubejs:bio_mech_sinew', [
    'kubejs:synthetic_muscle_fibre',
    'minecraft:string',
    'minecraft:slime_ball'
  ])

  event.shaped('kubejs:charged_servo', [
    'RMR',
    'CIC',
    'RMR'
  ], {
    R: 'minecraft:redstone',
    M: 'kubejs:mech_salvage',
    C: 'minecraft:copper_ingot',
    I: 'minecraft:iron_ingot'
  })

  event.shaped('kubejs:ancient_servo', [
    'SMS',
    'MCM',
    'SMS'
  ], {
    S: 'kubejs:charged_servo',
    M: 'kubejs:mech_salvage',
    C: 'minecraft:gold_ingot'
  })

  event.shaped('kubejs:mech_diagnostic_chip', [
    'RGR',
    'ODO',
    'RGR'
  ], {
    R: 'minecraft:redstone',
    G: 'minecraft:gold_nugget',
    O: 'kubejs:damaged_optic',
    D: 'minecraft:quartz'
  })

  event.shapeless('kubejs:wild_core_shard', [
    'kubejs:wild_core_fragment',
    'kubejs:wild_core_fragment',
    'kubejs:wild_core_fragment',
    'minecraft:amethyst_shard'
  ])

  event.shaped('kubejs:proto_mech_core', [
    'ASA',
    'SDS',
    'ASA'
  ], {
    A: 'kubejs:ancient_alloy_scrap',
    S: 'kubejs:charged_servo',
    D: 'kubejs:mech_diagnostic_chip'
  })

  event.shaped('kubejs:stable_mech_core', [
    'SRS',
    'RCR',
    'SRS'
  ], {
    S: 'kubejs:ancient_servo',
    R: 'minecraft:redstone_block',
    C: 'kubejs:proto_mech_core'
  })

  event.shaped('kubejs:apex_mech_core', [
    'WGW',
    'GCG',
    'WGW'
  ], {
    W: 'kubejs:wild_core_shard',
    G: 'minecraft:gold_block',
    C: 'kubejs:stable_mech_core'
  })

  // Starter blueprints: cheap enough for testing, but now tied to theme materials.
  event.shaped('kubejs:raptor_frame_blueprint', [' P ', 'DMD', ' F '], {
    P: 'minecraft:paper', D: 'kubejs:damaged_optic', M: 'kubejs:mech_salvage', F: 'minecraft:feather'
  })
  event.shaped('kubejs:beetle_frame_blueprint', [' P ', 'DMD', ' I '], {
    P: 'minecraft:paper', D: 'kubejs:damaged_optic', M: 'kubejs:mech_salvage', I: 'minecraft:iron_pickaxe'
  })
  event.shaped('kubejs:stag_frame_blueprint', [' P ', 'DMD', ' W '], {
    P: 'minecraft:paper', D: 'kubejs:damaged_optic', M: 'kubejs:mech_salvage', W: 'minecraft:wheat_seeds'
  })
  event.shaped('kubejs:wolf_frame_blueprint', [' P ', 'DMD', ' B '], {
    P: 'minecraft:paper', D: 'kubejs:damaged_optic', M: 'kubejs:mech_salvage', B: 'minecraft:bone'
  })
  event.shaped('kubejs:hawk_frame_blueprint', [' P ', 'DMD', ' M '], {
    P: 'minecraft:paper', D: 'kubejs:damaged_optic', M: 'minecraft:phantom_membrane'
  })

  const frameRecipe = (output, blueprint, symbolMaterial) => {
    event.shaped(output, [
      'ABA',
      'SCS',
      'FMA'
    ], {
      A: 'kubejs:ancient_alloy_scrap',
      B: blueprint,
      S: 'kubejs:charged_servo',
      C: 'kubejs:proto_mech_core',
      F: 'kubejs:synthetic_muscle_fibre',
      M: symbolMaterial
    })
  }

  frameRecipe('kubejs:proto_raptor_frame', 'kubejs:raptor_frame_blueprint', 'minecraft:feather')
  frameRecipe('kubejs:proto_beetle_frame', 'kubejs:beetle_frame_blueprint', 'minecraft:iron_pickaxe')
  frameRecipe('kubejs:proto_stag_frame', 'kubejs:stag_frame_blueprint', 'minecraft:wheat_seeds')
  frameRecipe('kubejs:proto_wolf_frame', 'kubejs:wolf_frame_blueprint', 'minecraft:bone')
  frameRecipe('kubejs:proto_hawk_frame', 'kubejs:hawk_frame_blueprint', 'minecraft:phantom_membrane')

  event.shaped('kubejs:proto_chimera_frame', [
    'RWH',
    'BCS',
    'AAA'
  ], {
    R: 'kubejs:raptor_frame_blueprint',
    W: 'kubejs:wolf_frame_blueprint',
    H: 'kubejs:hawk_frame_blueprint',
    B: 'kubejs:beetle_frame_blueprint',
    S: 'kubejs:stag_frame_blueprint',
    C: 'kubejs:apex_mech_core',
    A: 'kubejs:ancient_alloy_scrap'
  })

  event.shaped('kubejs:drill_claws_tier_1', [' I ', 'SCS', ' I '], {
    I: 'minecraft:iron_ingot',
    S: 'kubejs:mech_salvage',
    C: 'kubejs:charged_servo'
  })

  event.shaped('kubejs:resonance_scanner', ['ARA', 'OCO', 'ARA'], {
    A: 'minecraft:amethyst_shard',
    R: 'minecraft:redstone',
    O: 'kubejs:damaged_optic',
    C: 'kubejs:mech_diagnostic_chip'
  })

  event.shaped('kubejs:deep_core_excavator', ['NIN', 'DCD', 'NIN'], {
    N: 'minecraft:netherite_scrap',
    I: 'minecraft:iron_block',
    D: 'kubejs:drill_claws_tier_1',
    C: 'kubejs:stable_mech_core'
  })

  event.shaped('kubejs:fang_blade_array', [' I ', 'SCS', ' F '], {
    I: 'minecraft:iron_sword',
    S: 'kubejs:mech_salvage',
    C: 'kubejs:charged_servo',
    F: 'kubejs:synthetic_muscle_fibre'
  })

  event.shaped('kubejs:shock_lance_module', [' R ', 'ACA', ' O '], {
    R: 'minecraft:redstone_block',
    A: 'kubejs:ancient_alloy_scrap',
    C: 'kubejs:stable_mech_core',
    O: 'kubejs:damaged_optic'
  })

  event.shaped('kubejs:reactive_plating', ['SIS', 'ICI', 'SIS'], {
    S: 'kubejs:mech_salvage',
    I: 'minecraft:iron_ingot',
    C: 'kubejs:charged_servo'
  })

  event.shaped('kubejs:shoulder_cannon_mount', [' D ', 'ACA', ' R '], {
    D: 'minecraft:dispenser',
    A: 'kubejs:ancient_alloy_scrap',
    C: 'kubejs:stable_mech_core',
    R: 'minecraft:redstone_block'
  })

  event.shaped('kubejs:harvest_talons', [' I ', 'SCS', ' H '], {
    I: 'minecraft:iron_hoe',
    S: 'kubejs:mech_salvage',
    C: 'kubejs:charged_servo',
    H: 'minecraft:hay_block'
  })

  event.shaped('kubejs:seeder_module', ['WWW', 'SCS', 'BBB'], {
    W: 'minecraft:wheat_seeds',
    S: 'kubejs:charged_servo',
    C: 'kubejs:mech_diagnostic_chip',
    B: 'minecraft:bone_meal'
  })

  event.shaped('kubejs:hydro_sprayer', [' B ', 'WCW', ' B '], {
    B: 'minecraft:bucket',
    W: 'minecraft:water_bucket',
    C: 'kubejs:mech_diagnostic_chip'
  })

  event.shaped('kubejs:lumber_jaw', [' A ', 'SCS', ' F '], {
    A: 'minecraft:iron_axe',
    S: 'kubejs:mech_salvage',
    C: 'kubejs:charged_servo',
    F: 'kubejs:synthetic_muscle_fibre'
  })

  event.shaped('kubejs:cargo_link', [' C ', 'HSH', ' M '], {
    C: 'minecraft:chest',
    H: 'minecraft:hopper',
    S: 'kubejs:charged_servo',
    M: 'kubejs:mech_salvage'
  })

  event.shaped('kubejs:autosmelt_core', [' F ', 'ACA', ' F '], {
    F: 'minecraft:furnace',
    A: 'kubejs:ancient_alloy_scrap',
    C: 'kubejs:stable_mech_core'
  })

  event.shaped('kubejs:builder_arm', [' P ', 'SCS', ' M '], {
    P: 'minecraft:piston',
    S: 'kubejs:charged_servo',
    C: 'kubejs:mech_diagnostic_chip',
    M: 'kubejs:mech_salvage'
  })

  event.shaped('kubejs:logistics_uplink', ['EGE', 'GCG', 'EGE'], {
    E: 'minecraft:ender_pearl',
    G: 'minecraft:gold_ingot',
    C: 'kubejs:stable_mech_core'
  })

  event.shaped('kubejs:hydraulic_leg_servos', ['RIR', 'SCS', 'RIR'], {
    R: 'minecraft:redstone',
    I: 'minecraft:iron_ingot',
    S: 'kubejs:charged_servo',
    C: 'minecraft:slime_ball'
  })

  event.shaped('kubejs:sprint_servo_bundle', ['SRS', 'RCR', 'SRS'], {
    S: 'kubejs:charged_servo',
    R: 'minecraft:redstone',
    C: 'minecraft:sugar'
  })

  event.shaped('kubejs:wall_claw_kit', [' I ', 'SCS', ' V '], {
    I: 'minecraft:iron_ingot',
    S: 'kubejs:charged_servo',
    C: 'minecraft:vine',
    V: 'minecraft:slime_ball'
  })

  event.shaped('kubejs:dash_core', [' R ', 'SCS', ' R '], {
    R: 'minecraft:redstone_block',
    S: 'kubejs:sprint_servo_bundle',
    C: 'kubejs:stable_mech_core'
  })

  event.shaped('kubejs:glide_wing_module', ['M M', ' C ', 'M M'], {
    M: 'minecraft:phantom_membrane',
    C: 'kubejs:stable_mech_core'
  })

  event.shaped('kubejs:jet_booster', ['BFB', 'SCS', 'BFB'], {
    B: 'minecraft:blast_furnace',
    F: 'minecraft:fire_charge',
    S: 'kubejs:ancient_servo',
    C: 'kubejs:stable_mech_core'
  })

  event.shaped('kubejs:aether_flight_core', ['EGE', 'GCG', 'EGE'], {
    E: 'minecraft:elytra',
    G: 'kubejs:jet_booster',
    C: 'kubejs:apex_mech_core'
  })

  event.shaped('kubejs:rogue_signal_flare', [' R ', 'OCO', ' F '], {
    R: 'minecraft:redstone_torch',
    O: 'kubejs:damaged_optic',
    C: 'kubejs:proto_mech_core',
    F: 'minecraft:fire_charge'
  })

  // v0.4 loadout and socket progression
  event.shaped('kubejs:mech_loadout_calibrator', ['ODO', 'RCR', 'OCO'], {
    O: 'kubejs:damaged_optic',
    D: 'kubejs:mech_diagnostic_chip',
    R: 'minecraft:redstone',
    C: 'kubejs:charged_servo'
  })

  event.shaped('kubejs:frame_reset_key', [' R ', ' D ', ' S '], {
    R: 'minecraft:redstone_torch',
    D: 'kubejs:mech_diagnostic_chip',
    S: 'kubejs:rustforged_scrap'
  })

  event.shaped('kubejs:module_socket_chip', ['RGR', 'GCG', 'RGR'], {
    R: 'minecraft:redstone',
    G: 'minecraft:gold_nugget',
    C: 'kubejs:mech_diagnostic_chip'
  })

  event.shapeless('kubejs:mining_socket_chip', ['kubejs:module_socket_chip', 'minecraft:iron_pickaxe', 'kubejs:mech_salvage'])
  event.shapeless('kubejs:combat_socket_chip', ['kubejs:module_socket_chip', 'minecraft:iron_sword', 'kubejs:mech_salvage'])
  event.shapeless('kubejs:utility_socket_chip', ['kubejs:module_socket_chip', 'minecraft:iron_hoe', 'kubejs:mech_salvage'])
  event.shapeless('kubejs:mobility_socket_chip', ['kubejs:module_socket_chip', 'minecraft:feather', 'kubejs:charged_servo'])


  // v0.5 evolved frame progression
  event.shaped('kubejs:mech_assembly_rig', ['IPI', 'SCS', 'IRI'], {
    I: 'minecraft:iron_ingot',
    P: 'minecraft:piston',
    S: 'kubejs:charged_servo',
    C: 'kubejs:mech_diagnostic_chip',
    R: 'minecraft:redstone_block'
  })

  event.shaped('kubejs:frame_evolution_circuit', ['OAO', 'SCS', 'OAO'], {
    O: 'kubejs:damaged_optic',
    A: 'kubejs:ancient_alloy_scrap',
    S: 'kubejs:ancient_servo',
    C: 'kubejs:stable_mech_core'
  })

  event.shaped('kubejs:predator_optic_cluster', ['OOO', 'OCO', 'FFF'], {
    O: 'kubejs:damaged_optic',
    C: 'kubejs:frame_evolution_circuit',
    F: 'kubejs:synthetic_muscle_fibre'
  })

  event.shaped('kubejs:heavy_plating_bundle', ['MMM', 'MCM', 'III'], {
    M: 'kubejs:mech_salvage',
    C: 'kubejs:frame_evolution_circuit',
    I: 'minecraft:iron_ingot'
  })

  event.shaped('kubejs:verdant_utility_matrix', ['BHB', 'SCS', 'LWL'], {
    B: 'minecraft:bone_meal',
    H: 'kubejs:harvest_talons',
    S: 'kubejs:seeder_module',
    C: 'kubejs:frame_evolution_circuit',
    L: 'minecraft:oak_log',
    W: 'minecraft:wheat'
  })

  event.shaped('kubejs:storm_flight_matrix', ['GJG', 'SCS', 'GJG'], {
    G: 'kubejs:glide_wing_module',
    J: 'kubejs:jet_booster',
    S: 'kubejs:ancient_servo',
    C: 'kubejs:frame_evolution_circuit'
  })

  event.shaped('kubejs:raptor_striker_frame', ['PFP', 'RCS', 'PMP'], {
    P: 'kubejs:predator_optic_cluster',
    F: 'kubejs:fang_blade_array',
    R: 'kubejs:proto_raptor_frame',
    C: 'kubejs:stable_mech_core',
    S: 'kubejs:sprint_servo_bundle',
    M: 'kubejs:mech_assembly_rig'
  })

  event.shaped('kubejs:beetle_bulwark_frame', ['HPH', 'BCP', 'HMH'], {
    H: 'kubejs:heavy_plating_bundle',
    P: 'kubejs:reactive_plating',
    B: 'kubejs:proto_beetle_frame',
    C: 'kubejs:stable_mech_core',
    M: 'kubejs:mech_assembly_rig'
  })

  event.shaped('kubejs:stag_grovekeeper_frame', ['VHV', 'SCU', 'VMV'], {
    V: 'kubejs:verdant_utility_matrix',
    H: 'kubejs:hydro_sprayer',
    S: 'kubejs:proto_stag_frame',
    C: 'kubejs:stable_mech_core',
    U: 'kubejs:cargo_link',
    M: 'kubejs:mech_assembly_rig'
  })

  event.shaped('kubejs:wolf_nightfang_frame', ['PFP', 'WCS', 'PMP'], {
    P: 'kubejs:predator_optic_cluster',
    F: 'kubejs:fang_blade_array',
    W: 'kubejs:proto_wolf_frame',
    C: 'kubejs:stable_mech_core',
    S: 'kubejs:shock_lance_module',
    M: 'kubejs:mech_assembly_rig'
  })

  event.shaped('kubejs:hawk_stormwing_frame', ['SJS', 'HCG', 'SMS'], {
    S: 'kubejs:storm_flight_matrix',
    J: 'kubejs:jet_booster',
    H: 'kubejs:proto_hawk_frame',
    C: 'kubejs:stable_mech_core',
    G: 'kubejs:glide_wing_module',
    M: 'kubejs:mech_assembly_rig'
  })

})
