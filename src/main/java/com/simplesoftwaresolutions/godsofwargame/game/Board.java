package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A location to store the current existing objects for the servers use
 */
@Component
public class Board {

    private List<List<Terrain>> terrainBoard;

    public Board() {

    }



}
