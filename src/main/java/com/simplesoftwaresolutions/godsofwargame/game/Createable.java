/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

/** When used marks an object as something that could be created from scratch
 * Stored by GameState in a List<NewObjects> newObjects, 
 *
 * @author brenn
 */
public interface Createable {
    
    public void addToNewObjectsQueue(GameState gameState);
    
}
