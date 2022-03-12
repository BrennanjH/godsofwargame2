package com.simplesoftwaresolutions.godsofwargame.Terrain;

import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

public interface Terrain {
    public PositionalCord getLocationData();
    public TerrainTypes getType();
}
