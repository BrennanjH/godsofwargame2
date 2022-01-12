package com.simplesoftwaresolutions.godsofwargame.game;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddToQueueAspect {
    @After("")
    public void afterChangeableAction(){

    }
}
