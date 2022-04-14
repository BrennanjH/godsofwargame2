package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<PositionalCord> stepList = new ArrayList<>(); //WARNING - consider replacing this with some type of stack or queue collection

    public List<PositionalCord> getStepList() {
        return stepList;
    }

    /** clears the position returned from movement path and returns it
     * @return - the next location in the list
     */
    public PositionalCord nextLocation(){
        PositionalCord temp = stepList.get(0);
        stepList.remove(temp);
        return temp;
    }

    public void setStepList(List<PositionalCord> stepList) {
        this.stepList = stepList;
    }

    public void clear() {
        stepList.clear();
    }
}
