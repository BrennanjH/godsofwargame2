/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerData;
import com.simplesoftwaresolutions.godsofwargame.player.Team;
import com.simplesoftwaresolutions.godsofwargame.units.AbstractUnitObject;
import java.util.List;

/** The class represents the current game as an unbiased third party 
 * (players, units, Map, Teams)
 *
 * @author brenn
 */
public class GameState{
    //Defines the relationShip players have
    private List<Team> teams;
    
    //List of all players in the game
    private List<PlayerData> players;
    
    //PlayerData has a list of units itself but it is for their units only
    private List<AbstractUnitObject> units;
    
    private Map map;
}
