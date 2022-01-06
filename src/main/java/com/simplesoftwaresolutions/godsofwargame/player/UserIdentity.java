/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.player;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import org.springframework.web.socket.WebSocketSession;

/** A class that links a session's Id to a nickname that the player uses;
 *
 * @author brenn
 */
public class UserIdentity {
    
    private StringBuilder nickname;
    
    private String id;

    public UserIdentity(GameState gameState, WebSocketSession newUser, StringBuilder nickName) {
        this.id = newUser.getId();
        this.nickname = nickName;
    }

    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    public StringBuilder getNickname() {
        return nickname;
    }

    

}
