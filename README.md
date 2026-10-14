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

Version 1.3.2 added module slots to the original panel families; version 1.3.3 added the IndustrialUpgrade-aligned 14-tier normal series. Version 1.4.0 removes the separate daylight, moonlight, and rain blocks. Version 1.4.2 removes the unused daylight and day/night glass variants, leaving moonlight glass as the night-generation upgrade and rain glass as the rain multiplier upgrade. Version 1.4.3 fixes IC2 2.8.222 runtime verification for all three module item classes while preserving their display names. Version 1.4.4 normalizes texture filenames and all model, GUI, and armor references for strict resource loaders. Version 1.4.5 moves all registered content into the standalone Super Solar Panels creative tab. The 14 normal panels use one shared weather-aware implementation with storage and output modules in their GUIs. No recipes are added for the modules, so pack authors can define them later.

- `solar_panel_glass`: moonlight night-generation bonus and rain multiplier upgrade;
- All registered items and block items use the standalone `Super Solar Panels` creative tab.
- `solar_panel_battery`: basic, advanced, and quantum storage capacity;
- `solar_panel_output`: basic, advanced, and quantum output limits.

Each normal panel GUI exposes three dedicated module slots beside its original charge slots. Modules are saved with the panel; rain produces 65% of the normal generation by default, and rain glass raises that weather coefficient to 100%.

The modular component and high-tier solar panel artwork is adapted from the IndustrialUpgrade reference repository. The copied third-party license text is kept under `third_party/industrialupgrade/LICENSE`; the runtime mod does not depend on IndustrialUpgrade.

## Local item catalogue

For a detailed comparison with the original Super Solar Panels 1.3.1, see [RIOUTOUSCRAFT_CHANGES.md](RIOUTOUSCRAFT_CHANGES.md).

item-catalog.html is a self-contained local item catalogue. It embeds the current item/block textures, names, categories, and source-derived performance parameters, so it can be opened directly in a browser without a web server or external dependency.

After changing item registrations, language files, models, or configuration defaults, regenerate it with:

    python3 tools/build_item_catalog.py
