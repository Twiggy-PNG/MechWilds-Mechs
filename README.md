# Mechwild Mechs v0.9 - Scale + Head Entry Pass

This GitHub-ready repo builds the Mechwild Mechs Forge 1.20.1 custom mech mod.

## v0.9 focus
- Re-scales the Raptor Mech toward a small-class/infantry Zoid style: roughly 4-5 Minecraft metres tall.
- Pushes the raptor toward a bipedal dinosaur stance: two dominant rear legs, balancing tail, smaller weaponised forearms.
- Changes deployment so the mech spawns beside you instead of instantly mounting you.
- Adds a climb/entry sequence: right-click the deployed mech to climb into the head cockpit after a short delay.
- Moves the pilot/camera higher and forward into the head cockpit position.
- Updates cockpit HUD wording to make the view feel like a head-mounted camera/screen system.

## Build
Push this repo to GitHub. The workflow in `.github/workflows/build-mod.yml` builds the jar and uploads the artifact `mechwild-mechs-v0.9-jar`.

## Install
1. Remove older `mechwild-mechs-*.jar` files from your CurseForge `mods` folder.
2. Add the new v0.9 jar.
3. Copy `pack_integration/config` and `pack_integration/kubejs` into your CurseForge instance and merge/replace.

## Test checklist
- Deploy the mech with the Mech Deployment Core.
- Confirm it no longer instantly mounts you.
- Right-click the mech from ground level and wait for the climb/entry message.
- Confirm the cockpit camera sits high in/near the head, not near the ground.
- Confirm the silhouette is closer to a bipedal raptor mech, not a four-legged animal.
