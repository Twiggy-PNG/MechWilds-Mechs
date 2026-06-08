# Mechwild Mechs v0.8 - Visual Cockpit Pass

This GitHub-ready repo builds the Mechwild Mechs Forge 1.20.1 custom mech mod.

## v0.8 focus
- Larger Raptor Mech entity scale
- More angular red/black mechanical raptor model
- Green cockpit/HUD glass texture direction
- First-person cockpit overlay while piloting
- Player hand/item rendering hidden while piloting
- Pilot camera/position raised into the cockpit shell

## Build
Push this repo to GitHub. The workflow in `.github/workflows/build-mod.yml` builds the jar and uploads the artifact `mechwild-mechs-v0.8-jar`.

## Install
1. Remove older `mechwild-mechs-*.jar` files from your CurseForge `mods` folder.
2. Add the new v0.8 jar.
3. Copy `pack_integration/config` and `pack_integration/kubejs` into your CurseForge instance and merge/replace.
