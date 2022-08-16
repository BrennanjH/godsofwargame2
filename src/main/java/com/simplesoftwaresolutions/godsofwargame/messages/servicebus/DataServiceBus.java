package com.simplesoftwaresolutions.godsofwargame.messages.servicebus;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A SINGLETON class that holds data pertaining to state changes in one of three forms, Needs to be thread safe
 */
public class DataServiceBus {

    private static DataServiceBus singletonDSB;

    private DataServiceBus(){
        changeables = new ArrayList<>();
        destroyables = new ArrayList<>();
        creatables = new ArrayList<>();
    }

    public synchronized static DataServiceBus getInstance(){
        if(singletonDSB == null){
            singletonDSB = new DataServiceBus();
        }
        return singletonDSB;
    }

//    private volatile List<Changeable> changeables;
//    private volatile List<Destroyable> destroyables;
//    private volatile List<Creatable> creatables;

    private volatile List<Envelope> changeables;
    private volatile List<Envelope> destroyables;
    private volatile List<Envelope> creatables;


    public synchronized void addToChangeables(Envelope changedObject){
        if(!listContains(changeables, changedObject.getPayload()))
            changeables.add(changedObject);
    }
    public synchronized void addToDestroyables(Envelope removedObject){
        if(!listContains(destroyables,removedObject.getPayload()))
            destroyables.add(removedObject);
    }
    public synchronized void addToCreatables(Envelope newObject){
        if(!listContains(creatables, newObject.getPayload()))
            creatables.add(newObject);
    }

    /** a method to find if passed in value exists within passed in list of envelopes
     * @param envelopes - the list of envelopes where we wish to find its payload
     * @param findPresence - the Business object that we are looking for a copy of
     * @return - true if object is present in the list and false if not
     */
    private boolean listContains(List<Envelope> envelopes, BusinessObject findPresence){
        for (Envelope ev :
                envelopes) {
            if (ev.getPayload() == findPresence) {
                return true;
            }
        }
        return false;
    }

//    public synchronized void addToChangeables(Changeable changedObject){
//        if(!changeables.contains(changedObject))
//            changeables.add(changedObject);
//    }
//    public synchronized void addToDestroyables(Destroyable removedObject){
//        if(!destroyables.contains(removedObject))
//            destroyables.add(removedObject);
//    }
//    public synchronized void addToCreatables(Creatable newObject){
//        if(!creatables.contains(newObject))
//            creatables.add(newObject);
//    }

    public List<Envelope> getChangeables() {
        return Collections.unmodifiableList(changeables);
    }

    public List<Envelope> getDestroyables() {
        return Collections.unmodifiableList(destroyables);
    }

    public List<Envelope> getCreatables() {
        return Collections.unmodifiableList(creatables);
    }
//    public List<Changeable> getChangeables() {
//        return Collections.unmodifiableList(changeables);
//    }
//
//    public List<Destroyable> getDestroyables() {
//        return Collections.unmodifiableList(destroyables);
//    }
//
//    public List<Creatable> getCreatables() {
//        return Collections.unmodifiableList(creatables);
//    }

    public synchronized void clearChangeables(){
        changeables.clear();
    }
    public synchronized void clearDestroyables(){
        destroyables.clear();
    }
    public synchronized void clearCreatables(){
        creatables.clear();
    }

}
