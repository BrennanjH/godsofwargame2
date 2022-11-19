package com.simplesoftwaresolutions.godsofwargame.messages.egress;

import lombok.Data;

@Data
public class ErrorMessage {

    public ErrorMessage(String error) {
        this.error = error;
    }

    String error;
}
