package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.game.Board;
import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.BrennansFolly;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.Map;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LightTrackMovementPlatformTest {

    @Test
    void moveToValidLocation() {
        //env
            //Set up map
        Board board = new Board();
        Map map = new BrennansFolly();
        board.setTerrainBoard(map.generateMap());
        BoardManager manager = new BoardManager(board);

            //Create moving platform (the complete unit isn't needed for movement)
        FullPositionalCord startPos = new FullPositionalCord(0,0,0);
        LightTrackMovementPlatform movementPlatform = new LightTrackMovementPlatform();

            //Create Route;
        PositionalCord moveToPosition = new PositionalCord(1,0); //THis coordinate is a known PLAINS
        Route route = new Route(); //Not needed but I did it by accident
        route.getStepList().add(moveToPosition);
        movementPlatform.changeRoute(route.getStepList());

        //execute
        movementPlatform.move( startPos, manager);


        //assert
        System.out.println(manager.getTerrain(moveToPosition.getX(),moveToPosition.getY()).getType());
        Assertions.assertTrue(startPos.getX() == moveToPosition.getX() && startPos.getY() == moveToPosition.getY());
    }

    @Test
    void moveToInvalidTerrain() {
        //env

        //execute

        //assert
    }
    @Test
    void moveToInvalidDistance() {
        //env

        //execute

        //assert
    }
}