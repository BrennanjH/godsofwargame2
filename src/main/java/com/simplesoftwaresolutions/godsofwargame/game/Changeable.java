/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplesoftwaresolutions.godsofwargame.game;

/** Sub-classes store volatile information that needs to be updated on frontends
 * This class is used to find if server message is needed
 *
 * @author brenn
 */
public interface Changeable {
    public boolean hasChanged();
}
