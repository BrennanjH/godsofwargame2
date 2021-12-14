/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

/** Objects that can be removed from the general sphere of gamestate implement this class
 *
 * @author brenn
 */
public interface Destroyable {
    public void addToDestroyingQueue(GameState gameState);
}
