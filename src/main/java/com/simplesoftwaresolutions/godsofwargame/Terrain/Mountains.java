package com.simplesoftwaresolutions.godsofwargame.Terrain;

import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

public class Mountains implements Terrain{

    public final static transient TerrainTypes terrainTypes = TerrainTypes.MOUNTAINS;

    public MountainVisual mountainVisual = MountainVisual.MOUNTAIN_FULL;

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
