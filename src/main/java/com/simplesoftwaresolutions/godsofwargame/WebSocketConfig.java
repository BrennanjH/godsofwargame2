/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/** I have no clue how this works 11-26-21
 * I have a vague clue how this works 11-27-21
 * I don't know how this works 12-26-21
 *
 * @author brenn
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer  {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandling(), "/socket").setAllowedOrigins("*");
    }
    
    
}
