/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;

/** A Spring configuration Class for gamestate related spring beans
 *
 * @author brenn
 */
@Configuration
public class GameStateConfig {
    @Bean
    public PlayerData playerData(WebSocketSession session){
        //Change 25000 to a value in properties
        return new PlayerData(25000, session);
    }
}
