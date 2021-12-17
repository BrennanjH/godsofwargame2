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
    
    private StringBuilder ownerNickName;
    
    //Although an int would be better here, An objects Id is frequently serialized so String is better
    private String instanceId;

    public StringBuilder getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(StringBuilder OwnerNickName) {
        this.ownerNickName = OwnerNickName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String InstanceId) {
        this.instanceId = InstanceId;
    }
    
    
}
