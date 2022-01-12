package com.simplesoftwaresolutions.godsofwargame.game;

import java.util.TimerTask;

public class TaskStart extends TimerTask {
    SwitchToPreGameHandler handler = new SwitchToPreGameHandler();
    GameState gameState;
    public TaskStart(GameState gameState){
        this.gameState = gameState;
    }
    @Override
    public void run() {

        handler.SwitchToPreGame(gameState);
        //remove self from gameState task list
        gameState.getTasks().remove(this);
    }
}
