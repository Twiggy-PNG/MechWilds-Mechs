// Mechwild Ascension v0.5 evolved frame loadout + module socket prototype
// Right-click a frame to activate it. Right-click a module to install it into a socket.
// Effects are based on the saved loadout first, with v0.3 inventory fallback retained for safety.

const MW_FRAMES = {
  'kubejs:proto_raptor_frame': 'raptor',
  'kubejs:proto_beetle_frame': 'beetle',
  'kubejs:proto_stag_frame': 'stag',
  'kubejs:proto_wolf_frame': 'wolf',
  'kubejs:proto_hawk_frame': 'hawk',
  'kubejs:proto_chimera_frame': 'chimera',
  'kubejs:raptor_striker_frame': 'raptor_striker',
  'kubejs:beetle_bulwark_frame': 'beetle_bulwark',
  'kubejs:stag_grovekeeper_frame': 'stag_grovekeeper',
  'kubejs:wolf_nightfang_frame': 'wolf_nightfang',
  'kubejs:hawk_stormwing_frame': 'hawk_stormwing'
}

const MW_MODULES = {
  'kubejs:drill_claws_tier_1': ['mining', 'Drill Claws I'],
  'kubejs:resonance_scanner': ['mining', 'Resonance Scanner'],
  'kubejs:deep_core_excavator': ['mining', 'Deep Core Excavator'],
  'kubejs:fang_blade_array': ['combat', 'Fang Blade Array'],
  'kubejs:shock_lance_module': ['combat', 'Shock Lance Module'],
  'kubejs:shoulder_cannon_mount': ['combat', 'Shoulder Cannon Mount'],
  'kubejs:reactive_plating': ['defence', 'Reactive Plating'],
  'kubejs:harvest_talons': ['utility', 'Harvest Talons'],
  'kubejs:seeder_module': ['utility', 'Seeder Module'],
  'kubejs:hydro_sprayer': ['utility', 'Hydro Sprayer'],
  'kubejs:lumber_jaw': ['utility', 'Lumber Jaw'],
  'kubejs:cargo_link': ['automation', 'Cargo Link'],
  'kubejs:autosmelt_core': ['automation', 'Auto-Smelt Core'],
  'kubejs:builder_arm': ['automation', 'Builder Arm'],
  'kubejs:logistics_uplink': ['automation', 'Logistics Uplink'],
  'kubejs:hydraulic_leg_servos': ['mobility', 'Hydraulic Leg Servos'],
  'kubejs:sprint_servo_bundle': ['mobility', 'Sprint Servo Bundle'],
  'kubejs:wall_claw_kit': ['mobility', 'Wall Claw Kit'],
  'kubejs:dash_core': ['mobility', 'Dash Core'],
  'kubejs:glide_wing_module': ['flight', 'Glide Wing Module'],
  'kubejs:jet_booster': ['flight', 'Jet Booster'],
  'kubejs:aether_flight_core': ['flight', 'Aether Flight Core']
}

function mwGet(player, key) {
  let v = String(player.persistentData[key] || '')
  if (v == 'undefined' || v == 'null') return ''
  return v
}
function mwSet(player, key, value) { player.persistentData[key] = value }
function mwClear(player) {
  mwSet(player, 'mw_frame', '')
  ;['mining','combat','defence','utility','automation','mobility','flight'].forEach(s => mwSet(player, 'mw_' + s, ''))
}
function mwHasInventory(player, id) { return player.inventory.count(Item.of(id)) > 0 }
function mwHasModule(player, moduleId) {
  for (let slot of ['mining','combat','defence','utility','automation','mobility','flight']) {
    if (mwGet(player, 'mw_' + slot) == moduleId) return true
  }
  return mwHasInventory(player, moduleId)
}
function mwFrameName(frame) {
  const names = {
    raptor: 'Raptor', beetle: 'Beetle', stag: 'Stag', wolf: 'Wolf', hawk: 'Hawk', chimera: 'Chimera',
    raptor_striker: 'Raptor Striker', beetle_bulwark: 'Beetle Bulwark', stag_grovekeeper: 'Stag Grovekeeper',
    wolf_nightfang: 'Wolf Nightfang', hawk_stormwing: 'Hawk Stormwing'
  }
  if (!frame) return 'None'
  return names[frame] || frame
}
function mwSlotLabel(player, slot) {
  let id = mwGet(player, 'mw_' + slot)
  if (!id) return 'empty'
  return MW_MODULES[id] ? MW_MODULES[id][1] : id
}

Object.keys(MW_FRAMES).forEach(id => {
  ItemEvents.rightClicked(id, event => {
    const player = event.player
    const frame = MW_FRAMES[id]
    mwSet(player, 'mw_frame', frame)
    player.tell('§bMech frame calibrated: §f' + mwFrameName(frame) + ' Frame')
    player.tell('§7Right-click modules to install them into your active loadout. Use a Frame Reset Key to clear it.')
  })
})

Object.keys(MW_MODULES).forEach(id => {
  ItemEvents.rightClicked(id, event => {
    const player = event.player
    const frame = mwGet(player, 'mw_frame')
    if (!frame) {
      player.tell('§cNo active frame. §fRight-click a Proto Frame first, then install modules.')
      return
    }
    const data = MW_MODULES[id]
    const slot = data[0]
    const name = data[1]
    mwSet(player, 'mw_' + slot, id)
    player.tell('§aInstalled ' + name + ' §finto §e' + slot + '§f socket on your §b' + mwFrameName(frame) + ' Frame§f.')
    if (slot == 'flight' && frame != 'hawk' && frame != 'hawk_stormwing' && frame != 'chimera') {
      player.tell('§7Note: flight modules work best on Hawk or Chimera frames. Other frames receive weaker movement benefits for now.')
    }
  })
})

ItemEvents.rightClicked('kubejs:mech_loadout_calibrator', event => {
  const p = event.player
  p.tell('§8==== §bMechwild Loadout §8====')
  p.tell('§fFrame: §b' + mwFrameName(mwGet(p, 'mw_frame')))
  ;['mining','combat','defence','utility','automation','mobility','flight'].forEach(slot => {
    p.tell('§7' + slot + ': §f' + mwSlotLabel(p, slot))
  })
})

ItemEvents.rightClicked('kubejs:frame_reset_key', event => {
  mwClear(event.player)
  event.player.tell('§eMech loadout cleared. §fRight-click a frame to calibrate a new build.')
})

PlayerEvents.tick(event => {
  const player = event.player
  if (player.age % 40 != 0) return

  const activeFrame = mwGet(player, 'mw_frame')
  const invHas = id => mwHasInventory(player, id)
  const frame = activeFrame || (
    invHas('kubejs:raptor_striker_frame') ? 'raptor_striker' :
    invHas('kubejs:beetle_bulwark_frame') ? 'beetle_bulwark' :
    invHas('kubejs:stag_grovekeeper_frame') ? 'stag_grovekeeper' :
    invHas('kubejs:wolf_nightfang_frame') ? 'wolf_nightfang' :
    invHas('kubejs:hawk_stormwing_frame') ? 'hawk_stormwing' :
    invHas('kubejs:proto_raptor_frame') ? 'raptor' : invHas('kubejs:proto_beetle_frame') ? 'beetle' : invHas('kubejs:proto_stag_frame') ? 'stag' : invHas('kubejs:proto_wolf_frame') ? 'wolf' : invHas('kubejs:proto_hawk_frame') ? 'hawk' : invHas('kubejs:proto_chimera_frame') ? 'chimera' : '')

  // Frame identities
  if (frame == 'raptor') {
    player.potionEffects.add('minecraft:speed', 60, 0, true, false)
    player.potionEffects.add('minecraft:jump_boost', 60, 0, true, false)
  }
  if (frame == 'beetle') {
    player.potionEffects.add('minecraft:haste', 60, 0, true, false)
    player.potionEffects.add('minecraft:resistance', 60, 0, true, false)
  }
  if (frame == 'stag') player.potionEffects.add('minecraft:regeneration', 60, 0, true, false)
  if (frame == 'wolf') {
    player.potionEffects.add('minecraft:strength', 60, 0, true, false)
    player.potionEffects.add('minecraft:night_vision', 260, 0, true, false)
  }
  if (frame == 'hawk') {
    player.potionEffects.add('minecraft:slow_falling', 60, 0, true, false)
    player.potionEffects.add('minecraft:speed', 60, 0, true, false)
  }
  if (frame == 'chimera') {
    player.potionEffects.add('minecraft:speed', 60, 0, true, false)
    player.potionEffects.add('minecraft:haste', 60, 0, true, false)
    player.potionEffects.add('minecraft:resistance', 60, 0, true, false)
  }

  // v0.5 evolved frame identities
  if (frame == 'raptor_striker') {
    player.potionEffects.add('minecraft:speed', 60, 1, true, false)
    player.potionEffects.add('minecraft:jump_boost', 60, 1, true, false)
    player.potionEffects.add('minecraft:strength', 60, 0, true, false)
  }
  if (frame == 'beetle_bulwark') {
    player.potionEffects.add('minecraft:haste', 60, 1, true, false)
    player.potionEffects.add('minecraft:resistance', 60, 1, true, false)
  }
  if (frame == 'stag_grovekeeper') {
    player.potionEffects.add('minecraft:regeneration', 60, 1, true, false)
    player.potionEffects.add('minecraft:haste', 60, 0, true, false)
  }
  if (frame == 'wolf_nightfang') {
    player.potionEffects.add('minecraft:strength', 60, 1, true, false)
    player.potionEffects.add('minecraft:night_vision', 260, 0, true, false)
    player.potionEffects.add('minecraft:speed', 60, 0, true, false)
  }
  if (frame == 'hawk_stormwing') {
    player.potionEffects.add('minecraft:slow_falling', 60, 0, true, false)
    player.potionEffects.add('minecraft:speed', 60, 2, true, false)
    player.potionEffects.add('minecraft:jump_boost', 60, 1, true, false)
  }

  // Installed/inventory module identities
  if (mwHasModule(player, 'kubejs:hydraulic_leg_servos')) player.potionEffects.add('minecraft:jump_boost', 60, 0, true, false)
  if (mwHasModule(player, 'kubejs:sprint_servo_bundle')) player.potionEffects.add('minecraft:speed', 60, 1, true, false)
  if (mwHasModule(player, 'kubejs:reactive_plating')) player.potionEffects.add('minecraft:resistance', 60, 0, true, false)
  if (mwHasModule(player, 'kubejs:glide_wing_module')) player.potionEffects.add('minecraft:slow_falling', 60, 0, true, false)
  if (mwHasModule(player, 'kubejs:resonance_scanner')) player.potionEffects.add('minecraft:night_vision', 260, 0, true, false)
  if (mwHasModule(player, 'kubejs:fang_blade_array')) player.potionEffects.add('minecraft:strength', 60, 0, true, false)
  if (mwHasModule(player, 'kubejs:shock_lance_module')) player.potionEffects.add('minecraft:strength', 60, 1, true, false)
  if (mwHasModule(player, 'kubejs:shoulder_cannon_mount')) player.potionEffects.add('minecraft:resistance', 60, 0, true, false)
  if (mwHasModule(player, 'kubejs:autosmelt_core')) player.potionEffects.add('minecraft:fire_resistance', 60, 0, true, false)
  if (mwHasModule(player, 'kubejs:jet_booster')) {
    player.potionEffects.add('minecraft:speed', 60, frame == 'hawk' || frame == 'hawk_stormwing' || frame == 'chimera' ? 2 : 1, true, false)
    player.potionEffects.add('minecraft:slow_falling', 60, 0, true, false)
  }
})

BlockEvents.broken(event => {
  const player = event.player
  if (!player) return
  const held = player.mainHandItem.id
  const blockId = String(event.block.id)

  if (mwHasModule(player, 'kubejs:drill_claws_tier_1') && held.includes('pickaxe') && Math.random() < 0.08) player.give('minecraft:raw_iron')
  if (mwGet(player, 'mw_frame') == 'beetle' && held.includes('pickaxe') && Math.random() < 0.07) player.give('kubejs:rustforged_scrap')
  if (!mwGet(player, 'mw_frame') && mwHasInventory(player, 'kubejs:proto_beetle_frame') && held.includes('pickaxe') && Math.random() < 0.04) player.give('kubejs:rustforged_scrap')
  if (mwHasModule(player, 'kubejs:resonance_scanner') && event.block.hasTag('minecraft:mineable/pickaxe') && Math.random() < 0.015) player.tell('§bScanner ping: §fThis rock layer contains useful mineral traces.')
  if (mwHasModule(player, 'kubejs:harvest_talons') && (blockId.includes('wheat') || blockId.includes('carrots') || blockId.includes('potatoes') || blockId.includes('beetroots')) && Math.random() < 0.20) player.give('minecraft:bone_meal')
  if (mwHasModule(player, 'kubejs:lumber_jaw') && event.block.hasTag('minecraft:logs') && Math.random() < 0.12) player.give('minecraft:stick')
})


ItemEvents.rightClicked('kubejs:mech_assembly_rig', event => {
  const p = event.player
  p.tell('§bMech Assembly Rig: §fcombine a Proto Frame, Stable Mech Core, Frame Evolution Circuit and lineage parts to craft an evolved frame.')
  p.tell('§7Try Raptor Striker, Beetle Bulwark, Stag Grovekeeper, Wolf Nightfang or Hawk Stormwing in JEI.')
})

ItemEvents.rightClicked('kubejs:hydro_sprayer', event => {
  const player = event.player
  player.potionEffects.add('minecraft:saturation', 100, 0, true, false)
  player.give('minecraft:bone_meal')
  player.tell('§aHydro Sprayer activated. §fRecovered 1 bone meal from the nutrient reservoir.')
})
ItemEvents.rightClicked('kubejs:dash_core', event => {
  const player = event.player
  player.potionEffects.add('minecraft:speed', 120, 3, true, false)
  player.potionEffects.add('minecraft:jump_boost', 120, 1, true, false)
  player.tell('§bDash Core engaged.')
})
ItemEvents.rightClicked('kubejs:resonance_scanner', event => {
  const player = event.player
  player.tell('§bResonance Scanner: §fmining with this module active may reveal mineral traces.')
  player.potionEffects.add('minecraft:night_vision', 260, 0, true, false)
})
