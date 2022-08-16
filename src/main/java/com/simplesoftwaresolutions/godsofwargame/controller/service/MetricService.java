package com.simplesoftwaresolutions.godsofwargame.controller.service;


import com.simplesoftwaresolutions.godsofwargame.dto.ServerMetrics;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MetricService {

    private GameState gameState;
    public ResponseEntity<ServerMetrics> compileMetrics() {
        //Place data in gameState into ServerMetrics DTO object
        ServerMetrics metrics = new ServerMetrics();
        metrics.setLoadState(gameState.loadState);
        metrics.setPlayerCount(gameState.getNickNames().size());

        return new ResponseEntity<>( metrics, HttpStatus.OK);
    }

    @Autowired
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
