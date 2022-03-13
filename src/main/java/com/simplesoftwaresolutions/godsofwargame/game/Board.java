package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Mountains;
import com.simplesoftwaresolutions.godsofwargame.Terrain.Plains;
import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;
import com.simplesoftwaresolutions.godsofwargame.Terrain.Water;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        testMap();
    }

    /**
     * Generates a 10x15 terrainBoard that's always the same
     */
    private void testMap(){
        for(int i=0;i<10;i++){
            terrainBoard.add(new ArrayList<>());
            for(int j=0; j< 15; j++){
                switch (i*j % 3) {
                    case 0 :
                        terrainBoard.get(i).add(new Plains());
                        break;
                    case 1 :
                        terrainBoard.get(i).add(new Mountains());
                        break;
                    case 2 :
                        terrainBoard.get(i).add(new Water());
                        break;
                    default :
                        terrainBoard.get(i).add(new Plains());
                        break;
                }

            }
        }
    }

}
