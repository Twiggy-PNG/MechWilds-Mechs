# Mechwild Mechs v0.10 - Full Mech Lineup + Model Polish Pass

This repo is the Forge 1.20.1 custom mech mod source for Mechwild Ascension.

## v0.10 focus

This pass starts the full beast-mech roster rather than only polishing the Raptor.

### Added / changed

- Raptor Striker visual polish: bipedal infantry raptor silhouette, head cockpit, long balance tail, red/black/white armour with green tech strips.
- Beetle Bulwark model: heavy mining/tank class with broad shell, horn/drill nose, thick legs, amber armour.
- Stag Grovekeeper model: utility/farming class with tall stance, antler sensor arrays, green tech/utility armour.
- Wolf Nightfang model: combat hunter class with lower predator posture, blade jaw/snout, grey/navy armour.
- Hawk Stormwing model: light aerial/scout class with wing plates, thrusters, blue/white armour.
- Chimera Titan model: late-game hybrid class with mixed raptor/beetle/wolf/hawk silhouette, larger scale and magenta/black prototype armour.
- Detailed texture sheets for all six variants with panel lines, vents, bolts, armour seams, glow strips and accent plates.
- Variant-aware renderer: active KubeJS frame now controls which mech body and texture appears.
- Updated cockpit positions per mech family.
- Updated GitHub artifact name: `mechwild-mechs-v0.10-jar`.

## How to use

Replace your cloned GitHub repo contents with these files, commit, and push using GitHub Desktop. GitHub Actions will build the jar.

After the build succeeds:

1. Download the `mechwild-mechs-v0.10-jar` artifact from GitHub Actions.
2. Extract the artifact zip.
3. Remove the older `mechwild-mechs-*.jar` from your CurseForge `mods` folder.
4. Add the new v0.10 jar.
5. Copy `pack_integration/config` and `pack_integration/kubejs` into your MechMod instance and merge/replace.

## Testing checklist

Set different active frames using the existing KubeJS frame items, then deploy the mech:

- Proto Raptor Frame / Raptor Striker Frame -> Raptor mech.
- Beetle frame -> Beetle mech.
- Stag frame -> Stag mech.
- Wolf frame -> Wolf mech.
- Hawk frame -> Hawk mech.
- Chimera frame -> Chimera mech.

Main things to judge:

- Is the Raptor now clearly bipedal?
- Are the model proportions closer to the intended class scale?
- Do the textures feel more detailed and less flat?
- Are Beetle, Stag, Wolf, Hawk and Chimera visually distinct enough?
- Does the cockpit camera still feel usable for each class?

