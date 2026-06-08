# Mechwild Mechs - Forge 1.20.1

This repository contains the true custom mod prototype for **Mechwild Ascension v0.7.1**.

## What GitHub will do

Every time you upload/push this repository, GitHub Actions will:

1. Install Java 17.
2. Install Gradle 8.10.
3. Build the Forge 1.20.1 mod.
4. Upload the finished `.jar` as a downloadable artifact.

## How to get the jar after upload

1. Open the GitHub repository.
2. Click the **Actions** tab.
3. Click the newest **Build Mechwild Mechs Mod** run.
4. Wait until it shows a green tick.
5. Scroll to **Artifacts**.
6. Download **mechwild-mechs-v0.7.1-jar**.
7. Open the downloaded zip and copy the `.jar` into your CurseForge instance's `mods` folder.

## Pack files

The `pack_integration` folder contains the matching `config` and `kubejs` folders for your modpack instance.
Copy those into your CurseForge `MechMod` folder the same way as earlier updates.

## Current prototype

- Forge 1.20.1
- Custom item: Mech Deployment Core
- Custom rideable entity: Raptor Mech Prototype
- Basic loadout-aware frame naming/stats
