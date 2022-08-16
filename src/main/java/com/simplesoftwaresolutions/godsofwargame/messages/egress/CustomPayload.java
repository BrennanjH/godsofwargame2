package com.simplesoftwaresolutions.godsofwargame.messages.egress;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.models.Model;
import lombok.Data;

import java.util.List;

@Data
public class CustomPayload extends AbstractReturnModel {
    private List<Model> changedObjects;
    private List<Model> removedObjects;
    private List<Model> newObjects;
}
