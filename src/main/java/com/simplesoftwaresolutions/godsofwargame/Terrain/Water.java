package com.simplesoftwaresolutions.godsofwargame.Terrain;

import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

public class Water implements Terrain{

    public final static transient TerrainTypes terrainTypes = TerrainTypes.WATER;

    public WaterVisual waterVisual = WaterVisual.WATER_FULL;

    private PositionalCord locationData;

    @Override
    public PositionalCord getLocationData() {
        return locationData;
    }

    @Override
    public TerrainTypes getType() {
        return terrainTypes;
    }


}
