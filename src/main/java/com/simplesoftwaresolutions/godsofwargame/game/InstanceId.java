/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    public InstanceId(){

    }

    @JsonCreator
    public InstanceId(@JsonProperty("instanceId") String instanceId,
                      @JsonProperty("ownerNickname")String ownerNickname){
        this.instanceId = instanceId;
        this.ownerNickName = new StringBuilder(ownerNickname);
    }


    public StringBuilder getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(StringBuilder ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /** A method that compares an Instance ID to another to verify if they are equal
     * @param comparisonInstance - The instance Id to be compared with
     * @return - true if both values are equivalent to each other
     */
    public boolean compareTo(InstanceId comparisonInstance){

        return ownerNickName.compareTo(comparisonInstance.getOwnerNickName()) == 0 && instanceId.compareTo(comparisonInstance.getInstanceId()) == 0;
    }
}
