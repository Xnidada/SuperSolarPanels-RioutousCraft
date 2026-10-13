# SuperSolarPanels-RioutousCraft

A source reconstruction of Super Solar Panels 1.3.1 for Minecraft 1.12.2, rebuilt against IndustrialCraft 2 `2.8.222-ex112`.

The mod id and registry names remain `super_solar_panels` for save and pack compatibility. The display name is `SuperSolarPanels-RioutousCraft`.

## Compatibility change

The original addon calls the pre-2.8.222 `InvSlot` constructor. IC2 2.8.222 uses `IInventorySlotHolder<?>`; `InvSlotMultiCharge` now passes the same holder through that API. Gameplay content, recipes, registry names, and assets are retained from 1.3.1.

## Build

Use Java 8 and run:

```bash
./gradlew build
```

The rebuilt jar is in `build/libs/`.

## Attribution

Original addon: Super Solar Panels 1.3.1 by Denfop and contributors. This repository contains a compatibility-focused source reconstruction for IC2 2.8.222.
