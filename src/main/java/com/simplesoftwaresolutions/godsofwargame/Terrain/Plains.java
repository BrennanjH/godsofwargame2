package com.simplesoftwaresolutions.godsofwargame.Terrain;

import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

public class Plains implements Terrain{

    public final static transient TerrainTypes terrainTypes = TerrainTypes.PLAINS;

    public PlainVisual plainVisual = PlainVisual.PLAIN_FULL;

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
