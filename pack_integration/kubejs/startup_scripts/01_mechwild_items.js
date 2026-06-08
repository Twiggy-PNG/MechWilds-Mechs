// Mechwild Ascension v0.5
// Place in: kubejs/startup_scripts/01_mechwild_items.js
// Requires: KubeJS for Minecraft 1.20.1 Forge

StartupEvents.registry('item', event => {
  const basic = (id, name, tooltip, rarity) => {
    let item = event.create(id).displayName(name).tooltip(tooltip)
    if (rarity) item.rarity(rarity)
    return item
  }

  // Core salvage loop materials
  basic('rustforged_scrap', 'Rustforged Scrap', 'Low-grade mech scrap recovered from hostile mobs and ruined machinery.', 'common')
  basic('mech_salvage', 'Mech Salvage', 'Reusable armour fragments, gears and plating for early mech construction.', 'uncommon')
  basic('ancient_servo', 'Ancient Servo', 'An old but powerful actuator used in stronger frame joints.', 'rare')
  basic('damaged_optic', 'Damaged Optic', 'A cracked targeting eye from a rogue machine-beast.', 'uncommon')
  basic('wild_core_fragment', 'Wild Core Fragment', 'A small unstable piece of a feral mech core.', 'rare')
  basic('synthetic_muscle_fibre', 'Synthetic Muscle Fibre', 'Flexible fibre that links animal-like motion into mechanical frames.', 'rare')
  basic('rogue_signal_flare', 'Rogue Signal Flare', 'A prototype tracker used to mark the start of a future rogue mech hunt.', 'rare')

  // v0.1 materials retained for compatibility
  basic('ancient_alloy_scrap', 'Ancient Alloy Scrap', 'Recovered material from a fallen machine-beast.', 'uncommon')
  basic('charged_servo', 'Charged Servo', 'A powered joint used in early mech limb systems.', 'uncommon')
  basic('bio_mech_sinew', 'Bio-Mech Sinew', 'Flexible synthetic fibre used to link frame, pilot and motion.', 'rare')
  basic('mech_diagnostic_chip', 'Mech Diagnostic Chip', 'Stores calibration data for modular upgrades.', 'uncommon')
  basic('wild_core_shard', 'Wild Core Shard', 'A volatile fragment from a rogue mech core.', 'rare')

  basic('proto_mech_core', 'Proto Mech Core', 'The damaged heart of your first machine-beast.', 'rare')
  basic('stable_mech_core', 'Stable Mech Core', 'A reliable core capable of supporting specialised frames.', 'rare')
  basic('apex_mech_core', 'Apex Mech Core', 'A late-game core for ascended mech evolution.', 'epic')

  basic('raptor_frame_blueprint', 'Raptor Frame Blueprint', 'Unlocks an agile melee-focused machine-beast frame.', 'rare')
  basic('beetle_frame_blueprint', 'Beetle Frame Blueprint', 'Unlocks a heavy mining-focused machine-beast frame.', 'rare')
  basic('stag_frame_blueprint', 'Stag Frame Blueprint', 'Unlocks a farming and utility machine-beast frame.', 'rare')
  basic('wolf_frame_blueprint', 'Wolf Frame Blueprint', 'Unlocks a hunter-combat machine-beast frame.', 'rare')
  basic('hawk_frame_blueprint', 'Hawk Frame Blueprint', 'Unlocks a late-game aerial machine-beast frame.', 'epic')
  basic('chimera_frame_blueprint', 'Chimera Frame Blueprint', 'A forbidden hybrid frame that combines multiple lineages.', 'epic')

  basic('proto_raptor_frame', 'Proto Raptor Frame', 'Fast, light and aggressive. The first true combat scout frame.', 'rare')
  basic('proto_beetle_frame', 'Proto Beetle Frame', 'Heavy, stable and built for excavation.', 'rare')
  basic('proto_stag_frame', 'Proto Stag Frame', 'A utility frame designed for farming, forestry and support.', 'rare')
  basic('proto_wolf_frame', 'Proto Wolf Frame', 'A hunting frame tuned for tracking and sustained combat.', 'rare')
  basic('proto_hawk_frame', 'Proto Hawk Frame', 'A fragile aerial frame designed for gliding and speed.', 'epic')
  basic('proto_chimera_frame', 'Proto Chimera Frame', 'A custom hybrid platform for pilots who refuse one path.', 'epic')

  basic('drill_claws_tier_1', 'Drill Claws I', 'Mining module: improves excavation and unlocks early mech-mining quests.', 'uncommon')
  basic('resonance_scanner', 'Resonance Scanner', 'Mining module: tuned to locate ore-rich stone.', 'rare')
  basic('deep_core_excavator', 'Deep Core Excavator', 'Mining module: late-game tunnel-breaking power.', 'epic')

  basic('fang_blade_array', 'Fang Blade Array', 'Combat module: increases melee identity and predator styling.', 'uncommon')
  basic('shock_lance_module', 'Shock Lance Module', 'Combat module: adds electrical strike potential.', 'rare')
  basic('shoulder_cannon_mount', 'Shoulder Cannon Mount', 'Combat module: stabilised ranged weapon hardpoint.', 'epic')
  basic('reactive_plating', 'Reactive Plating', 'Defence module: heavier armour and damage resistance identity.', 'rare')

  basic('harvest_talons', 'Harvest Talons', 'Farming module: crop-handling claws for utility frames.', 'uncommon')
  basic('seeder_module', 'Seeder Module', 'Farming module: automated planting support.', 'rare')
  basic('hydro_sprayer', 'Hydro Sprayer', 'Farming module: growth-support reservoir and sprayer.', 'rare')
  basic('lumber_jaw', 'Lumber Jaw', 'Forestry module: saw-toothed cutting jaws for timber work.', 'rare')

  basic('cargo_link', 'Cargo Link', 'Automation module: storage-routing hardware for mining and building loops.', 'uncommon')
  basic('autosmelt_core', 'Auto-Smelt Core', 'Automation module: converts raw material during processing.', 'rare')
  basic('builder_arm', 'Builder Arm', 'Automation module: block-placement manipulator.', 'rare')
  basic('logistics_uplink', 'Logistics Uplink', 'Automation module: advanced remote-routing hardware.', 'epic')

  basic('hydraulic_leg_servos', 'Hydraulic Leg Servos', 'Movement module: stronger jump and heavier limb silhouette.', 'uncommon')
  basic('sprint_servo_bundle', 'Sprint Servo Bundle', 'Movement module: improves sprint and scout-frame styling.', 'rare')
  basic('wall_claw_kit', 'Wall Claw Kit', 'Movement module: grip-focused climbing upgrade concept.', 'rare')
  basic('dash_core', 'Dash Core', 'Movement module: high-output burst movement system.', 'epic')

  basic('glide_wing_module', 'Glide Wing Module', 'Flight module: unlocks controlled falling and hawk-frame progression.', 'rare')
  basic('jet_booster', 'Jet Booster', 'Flight module: burst propulsion and thruster styling.', 'epic')
  basic('aether_flight_core', 'Aether Flight Core', 'Flight module: endgame aerial power source.', 'epic')

  // v0.4 loadout and socket system
  basic('mech_loadout_calibrator', 'Mech Loadout Calibrator', 'Right-click to display your active frame and installed module sockets.', 'rare')
  basic('frame_reset_key', 'Frame Reset Key', 'Right-click to clear the active frame and all installed module sockets.', 'uncommon')
  basic('module_socket_chip', 'Module Socket Chip', 'Universal interface chip used to install one module into the active mech loadout.', 'uncommon')
  basic('mining_socket_chip', 'Mining Socket Chip', 'Calibrates a mining module socket for Beetle-style excavation paths.', 'uncommon')
  basic('combat_socket_chip', 'Combat Socket Chip', 'Calibrates a combat module socket for Raptor and Wolf combat paths.', 'uncommon')
  basic('utility_socket_chip', 'Utility Socket Chip', 'Calibrates farming and automation modules for Stag-style utility paths.', 'uncommon')
  basic('mobility_socket_chip', 'Mobility Socket Chip', 'Calibrates movement and flight modules for scout and aerial paths.', 'rare')
  basic('active_frame_token', 'Active Frame Token', 'Debug/status item: your mech frame is now stored in the loadout system.', 'rare')

  // v0.5 evolved frame progression
  basic('mech_assembly_rig', 'Mech Assembly Rig', 'Portable schematic station used to evolve Proto Frames into specialised beast-mech variants.', 'rare')
  basic('frame_evolution_circuit', 'Frame Evolution Circuit', 'A calibrated circuit that allows a Proto Frame to evolve into a stronger specialised frame.', 'rare')
  basic('predator_optic_cluster', 'Predator Optic Cluster', 'A linked targeting cluster for raptor and wolf evolution paths.', 'rare')
  basic('heavy_plating_bundle', 'Heavy Plating Bundle', 'Layered armour plates for heavy beetle-frame evolution.', 'rare')
  basic('verdant_utility_matrix', 'Verdant Utility Matrix', 'Growth, harvest and automation logic for stag-frame evolution.', 'rare')
  basic('storm_flight_matrix', 'Storm Flight Matrix', 'High-output aerial stabilisation hardware for hawk-frame evolution.', 'epic')

  basic('raptor_striker_frame', 'Raptor Striker Frame', 'Evolved raptor lineage: faster pursuit, harder strikes and sharper scout identity.', 'epic')
  basic('beetle_bulwark_frame', 'Beetle Bulwark Frame', 'Evolved beetle lineage: heavier plating and stronger excavation support.', 'epic')
  basic('stag_grovekeeper_frame', 'Stag Grovekeeper Frame', 'Evolved stag lineage: farming, forestry and support systems strengthened.', 'epic')
  basic('wolf_nightfang_frame', 'Wolf Nightfang Frame', 'Evolved wolf lineage: night hunting and sustained combat systems strengthened.', 'epic')
  basic('hawk_stormwing_frame', 'Hawk Stormwing Frame', 'Evolved hawk lineage: advanced gliding, speed and aerial identity.', 'epic')

})
