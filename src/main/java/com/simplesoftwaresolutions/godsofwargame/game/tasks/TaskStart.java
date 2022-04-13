package com.simplesoftwaresolutions.godsofwargame.game.tasks;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.SwitchToPreGameHandler;

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

        //Schedule attack timer for game
        TimerTask task = new AttackTimer(gameState);
        gameState.schedule(task, 5000);

        task = new MoveTimer(gameState);
        gameState.schedule(task, 5000);

        //remove self from gameState task list
        gameState.getTasks().remove(this);
    }
}
