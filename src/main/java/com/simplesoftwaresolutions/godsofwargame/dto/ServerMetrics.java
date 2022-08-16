package com.simplesoftwaresolutions.godsofwargame.dto;

import com.simplesoftwaresolutions.godsofwargame.game.LoadState;

/**
 * A DTO that holds data about the general server gamestate
 */
//todo decide between lombock and records
public class ServerMetrics{

    private LoadState loadState;
    private int playerCount;

    public LoadState getLoadState() {
        return loadState;
    }

    public void setLoadState(LoadState loadState) {
        this.loadState = loadState;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
