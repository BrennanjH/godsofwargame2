package com.simplesoftwaresolutions.godsofwargame.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HealthController {

    @GetMapping("/liveCheck")
    public int health(){
        return 200;
    }

}
