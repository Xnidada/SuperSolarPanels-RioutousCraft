package com.rioutouscraft.ssp.tiles.overtimepanel;

import com.rioutouscraft.ssp.tiles.TileEntitySolarPanel;

public class TileEntityQuark extends TileEntitySolarPanel {
    public static SolarConfig settings;

    public TileEntityQuark() {
        super(settings);
    }
}
