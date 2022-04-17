package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.game.Board;
import com.simplesoftwaresolutions.godsofwargame.game.BoardManager;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.BrennansFolly;
import com.simplesoftwaresolutions.godsofwargame.game.Maps.Map;
import com.simplesoftwaresolutions.godsofwargame.location.FullPositionalCord;
import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractMovementPlatformTest {

    @Test
    void moveValid() {
        //env
        //Set up map
        Board board = new Board();
        Map map = new BrennansFolly();
        board.setTerrainBoard(map.generateMap());
        BoardManager manager = new BoardManager(board);

        //Create moving platform (the complete unit isn't needed for movement)
        FullPositionalCord startPos = new FullPositionalCord(0,0,0);
        AbstractMovementPlatform movementPlatform = new AbstractMovementPlatform() {

            @Override
            protected boolean validateMove(PositionalCord locationData, PositionalCord moveTo, BoardManager boardManager) {
                return true;
            }

            @Override
            public boolean isBuilt() {
                return false;
            }

            @Override
            public int priceOfSelf() {
                return 0;
            }
        };


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
    void moveInvalid() {
        //env
        //Set up map
        Board board = new Board();
        Map map = new BrennansFolly();
        board.setTerrainBoard(map.generateMap());
        BoardManager manager = new BoardManager(board);

        //Create moving platform (the complete unit isn't needed for movement)
        FullPositionalCord startPos = new FullPositionalCord(0,0,0);
        AbstractMovementPlatform movementPlatform = new AbstractMovementPlatform() {

            @Override
            protected boolean validateMove(PositionalCord locationData, PositionalCord moveTo, BoardManager boardManager) {
                return false;
            }

            @Override
            public boolean isBuilt() {
                return false;
            }

            @Override
            public int priceOfSelf() {
                return 0;
            }
        };


        //Create Route;
        PositionalCord moveToPosition = new PositionalCord(1,0); //THis coordinate is a known PLAINS
        Route route = new Route(); //Not needed but I did it by accident
        route.getStepList().add(moveToPosition);
        movementPlatform.changeRoute(route.getStepList());


        //execute
        movementPlatform.move( startPos, manager);


        //assert
        System.out.println(manager.getTerrain(moveToPosition.getX(),moveToPosition.getY()).getType());
        Assertions.assertFalse(startPos.getX() == moveToPosition.getX() && startPos.getY() == moveToPosition.getY());
    }
}