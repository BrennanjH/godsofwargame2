/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

/** A class that represents an instance Owner as well as it's unique Id
 * It doesn't check to make sure it's Unique Id is Unique that is performed 
 * by implementation
 *
 * @author brenn
 */
public class InstanceId {
    
    private String OwnerNickName;
    
    //Although an int would be better here, An objects Id is frequently serialized so String is better
    private String InstanceId;

    public String getOwnerNickName() {
        return OwnerNickName;
    }

    public void setOwnerNickName(String OwnerNickName) {
        this.OwnerNickName = OwnerNickName;
    }

    public String getInstanceId() {
        return InstanceId;
    }

    public void setInstanceId(String InstanceId) {
        this.InstanceId = InstanceId;
    }
    
    
}
