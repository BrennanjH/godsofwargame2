package com.simplesoftwaresolutions.godsofwargame.units;

import com.simplesoftwaresolutions.godsofwargame.location.PositionalCord;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<PositionalCord> stepList = new ArrayList<>();

    public List<PositionalCord> getStepList() {
        return stepList;
    }

    public void setStepList(List<PositionalCord> stepList) {
        this.stepList = stepList;
    }
}
