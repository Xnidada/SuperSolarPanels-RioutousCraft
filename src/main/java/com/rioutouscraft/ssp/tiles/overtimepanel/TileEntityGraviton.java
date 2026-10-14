package com.rioutouscraft.ssp.tiles.overtimepanel;

import com.rioutouscraft.ssp.tiles.TileEntitySolarPanel;

public class TileEntityGraviton extends TileEntitySolarPanel {
    public static SolarConfig settings;

    public TileEntityGraviton() {
        super(settings);
    }
}
