/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.player;

import java.util.List;

/**
 *
 * @author brenn
 */
public interface Team {
    //Teams have a many to many relationship with PlayerData
    public List<PlayerValues> getPlayersOnTeam();
}
