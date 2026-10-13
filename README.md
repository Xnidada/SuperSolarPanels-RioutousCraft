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

## Modular solar panel (phase 1)

This rebuild includes a standalone modular solar panel system. It does not depend on IndustrialUpgrade and does not add recipes. The new items are registered under the `super_solar_panels_rioutouscraft` namespace so pack authors can add recipes later.

- `solar_panel_glass`: day, night, and day/night glass profiles;
- `solar_panel_battery`: basic, advanced, and quantum storage modules;
- `solar_panel_output`: basic, advanced, and quantum output modules;
- `modular_solar_panel`: one glass slot, one battery slot, and one output slot.

The panel stores its configuration and internal EU buffer in NBT. Existing fixed solar panels are unchanged.

The modular component artwork is adapted from the IndustrialUpgrade reference repository. The copied third-party license text is kept under `third_party/industrialupgrade/LICENSE`; the runtime mod does not depend on IndustrialUpgrade.

## Local item catalogue

item-catalog.html is a self-contained local item catalogue. It embeds the current item/block textures, names, categories, and source-derived performance parameters, so it can be opened directly in a browser without a web server or external dependency.

After changing item registrations, language files, models, or configuration defaults, regenerate it with:

    python3 tools/build_item_catalog.py
