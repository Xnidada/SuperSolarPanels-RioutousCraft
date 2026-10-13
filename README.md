# SuperSolarPanels-RioutousCraft

A source reconstruction of Super Solar Panels 1.3.1 for Minecraft 1.12.2, rebuilt against IndustrialCraft 2 `2.8.222-ex112`.

The standalone mod id is `super_solar_panels_rioutouscraft` so it can coexist with the original addon. Its display name is `SuperSolarPanels-RioutousCraft`; registry names and resource namespaces use the new id.

## Compatibility change

The original addon calls the pre-2.8.222 `InvSlot` constructor. This standalone rebuild targets IC2 2.8.222 directly and uses `IInventorySlotHolder<?>`. Gameplay content, recipes, machine behavior, power values, and assets are retained from 1.3.1.

## Build

Use Java 8 and run:

```bash
./gradlew build
```

The rebuilt jar is in `build/libs/`.

## Attribution

Original addon: Super Solar Panels 1.3.1 by Denfop and contributors. This repository contains a compatibility-focused source reconstruction for IC2 2.8.222.
