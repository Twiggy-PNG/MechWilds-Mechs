
// Mechwild Ascension v0.7.2 true custom mech mod integration
// Safe to leave installed before the Java mod is built. It only runs if mechwildmechs is loaded.
ServerEvents.recipes(event => {
  if (!Platform.isLoaded('mechwildmechs')) return
  event.shaped('mechwildmechs:mech_deployment_core', [
    'SAS',
    'CRC',
    'SDS'
  ], {
    S: 'kubejs:mech_salvage',
    A: 'kubejs:ancient_servo',
    C: 'kubejs:stable_mech_core',
    R: 'kubejs:raptor_striker_frame',
    D: 'kubejs:dash_core'
  }).id('mechwild:v07_mech_deployment_core')
})
PlayerEvents.loggedIn(event => {
  if (Platform.isLoaded('mechwildmechs')) {
    event.player.tell('§bMechwild Mechs v0.7.2 detected. §fUse a Mech Deployment Core to enter the cockpit, repair with Mech Salvage, and trigger frame abilities while piloting.')
  }
})
