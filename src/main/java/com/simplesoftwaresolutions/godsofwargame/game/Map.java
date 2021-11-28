/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

/** A class that represents the current map the game is using
 *
 * @author brenn
 */
public class Map implements Changeable{ //May not need to be Changeable
    public boolean change;
    
    
    
    @Override
    public boolean hasChanged() {
        return change;
        
    }
    
}
