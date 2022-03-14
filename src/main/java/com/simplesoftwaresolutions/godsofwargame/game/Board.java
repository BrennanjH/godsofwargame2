package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A location to store the current existing objects for the servers use
 */
@Component
public class Board {

    public GameState gameState;

    private Map map;

    private List<List<Terrain>> terrainBoard;

    @Autowired
    public Board(GameState gameState) {
        this.gameState = gameState;

    }



}
