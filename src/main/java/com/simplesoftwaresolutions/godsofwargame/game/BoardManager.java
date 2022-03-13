package com.simplesoftwaresolutions.godsofwargame.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
