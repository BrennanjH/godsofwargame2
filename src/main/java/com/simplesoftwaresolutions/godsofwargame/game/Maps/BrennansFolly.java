package com.simplesoftwaresolutions.godsofwargame.game.Maps;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Mountains;
import com.simplesoftwaresolutions.godsofwargame.Terrain.Plains;
import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;
import com.simplesoftwaresolutions.godsofwargame.Terrain.Water;

import java.util.ArrayList;
import java.util.List;

public class BrennansFolly implements Map {

    public static List<List<Terrain>> generateMap() {
        List<List<Terrain>> terrainBoard = new ArrayList<>();
        for(int i=0;i<10;i++){
            terrainBoard.add(new ArrayList<>());
            for(int j=0; j< 15; j++){
                switch (i * j % 3) {
                    case 0 -> terrainBoard.get(i).add(new Plains());
                    case 1 -> terrainBoard.get(i).add(new Mountains());
                    case 2 -> terrainBoard.get(i).add(new Water());
                    default -> terrainBoard.get(i).add(new Plains());
                }

            }
        }
        return terrainBoard;
    }

}
