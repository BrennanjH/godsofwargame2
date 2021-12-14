/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.messages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;

/** An interface that objects that are sent as responses return. Their intended implementation is defined by frontend clients
 *
 * @author brenn
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public abstract class AbstractReturnModel {
    
    protected String className;
    
    protected transient GameState gameState;
    //Builds the implementing subclasses field with the necessary information needed by the type of message
    abstract public void build();
    abstract public void build(GameState gameState);
    
    
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    
}
