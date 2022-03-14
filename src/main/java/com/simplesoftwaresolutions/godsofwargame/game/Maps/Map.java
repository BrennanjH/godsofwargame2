package com.simplesoftwaresolutions.godsofwargame.game.Maps;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;

import java.util.List;

/**
 * An interface whose children each generate a unique map of predefined terrain subclasses
 */
public interface Map {

    public List<List<Terrain>> generateMap();
}
