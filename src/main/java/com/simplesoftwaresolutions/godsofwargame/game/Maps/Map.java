package com.simplesoftwaresolutions.godsofwargame.game.Maps;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;

import java.util.List;

/**
 * An interface whose children each generate a unique map of predefined terrain subclasses, These maps may have new features
 * attached as such this interface is required
 */
public interface Map {


    public List<List<Terrain>> generateMap();
}
