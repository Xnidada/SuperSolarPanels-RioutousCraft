# SuperSolarPanels-RioutousCraft

A source reconstruction of Super Solar Panels 1.3.1 for Minecraft 1.12.2, rebuilt against IndustrialCraft 2 `2.8.222-ex112`.

The standalone mod id is `super_solar_panels_rioutouscraft` so it can coexist with the original addon. Its display name is `SuperSolarPanels-RioutousCraft`; registry names and resource namespaces use the new id. Because the id and registry namespace are new, existing blocks from the original mod are not automatically migrated in old saves.

## Compatibility change

The original addon calls the pre-2.8.222 `InvSlot` constructor. This standalone rebuild targets IC2 2.8.222 directly and uses `IInventorySlotHolder<?>`. Gameplay content, recipes, machine behavior, power values, and assets are retained from 1.3.1.

## Build

Use Java 8 and run:

```bash
./gradlew build
```

The rebuilt jar is in `build/libs/`. ForgeGradle 2.3 and Java 8 are required for the 1.12.2 toolchain.

## Attribution

Original addon: Super Solar Panels 1.3.1 by Denfop and contributors. This repository contains a compatibility-focused source reconstruction for IC2 2.8.222.

## Existing panel module slots

Version 1.3.2 adds the modular component slots directly to every existing solar, daylight, moonlight, and rain panel; version 1.3.3 extends the normal solar series to 14 IndustrialUpgrade-aligned tiers. It includes the same 14 tiers as IndustrialUpgrade, including Barion, Hadron, Graviton, and Quark, with matching default generation, output, storage, and tier values. The standalone modular solar panel block has been removed. The module items remain registered under the `super_solar_panels_rioutouscraft` namespace, and no recipes are added so pack authors can define them later.

- `solar_panel_glass`: day, night, and day/night generation bonuses;
- `solar_panel_battery`: basic, advanced, and quantum storage capacity;
- `solar_panel_output`: basic, advanced, and quantum output limits.

Each existing panel GUI now exposes three dedicated module slots beside its original charge slots. Modules are saved with the panel, and the original weather/day/night generation rules remain active.

The modular component and high-tier solar panel artwork is adapted from the IndustrialUpgrade reference repository. The copied third-party license text is kept under `third_party/industrialupgrade/LICENSE`; the runtime mod does not depend on IndustrialUpgrade.

## Local item catalogue

item-catalog.html is a self-contained local item catalogue. It embeds the current item/block textures, names, categories, and source-derived performance parameters, so it can be opened directly in a browser without a web server or external dependency.

After changing item registrations, language files, models, or configuration defaults, regenerate it with:

    python3 tools/build_item_catalog.py
