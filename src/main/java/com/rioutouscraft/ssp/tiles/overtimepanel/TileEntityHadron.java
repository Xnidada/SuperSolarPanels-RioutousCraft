package com.rioutouscraft.ssp.tiles.overtimepanel;

import com.rioutouscraft.ssp.tiles.TileEntitySolarPanel;

public class TileEntityHadron extends TileEntitySolarPanel {
    public static SolarConfig settings;

    public TileEntityHadron() {
        super(settings);
    }
}
