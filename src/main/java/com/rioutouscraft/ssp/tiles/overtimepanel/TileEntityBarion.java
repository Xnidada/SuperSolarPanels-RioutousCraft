package com.rioutouscraft.ssp.tiles.overtimepanel;

import com.rioutouscraft.ssp.tiles.TileEntitySolarPanel;

public class TileEntityBarion extends TileEntitySolarPanel {
    public static SolarConfig settings;

    public TileEntityBarion() {
        super(settings);
    }
}
