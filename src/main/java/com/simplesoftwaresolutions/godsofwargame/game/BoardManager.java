package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.Terrain.Terrain;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A dedicated object that allows the server safely interact with the Board without leaving artifacts behind
 */
@Component
public class BoardManager {

    private Board gameBoard;

    @Autowired
    public BoardManager(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setMap(Map newMap){
        gameBoard.setTerrainBoard(newMap.generateMap());
    }

    public List<List<Terrain>>   getTerrainLists() {
        return gameBoard.getTerrainBoard();
    }

    public Terrain getTerrain(int x, int y){
        //WARNING - there might be a discrepancy in the lists x,y and the Terrains xy this is not intended and is a bug
        return gameBoard.getTerrainBoard().get(x).get(y);
    }

}
