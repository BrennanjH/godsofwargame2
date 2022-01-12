package com.simplesoftwaresolutions.godsofwargame;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
    public static void main(String[] args){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer task Testing");
            }
        };
        timer.schedule(task, 1000);
        task.cancel();

        task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer task Testing2");
            }
        };
        timer.schedule(task, 1000);
        //timer.cancel();
//        timer.schedule(task, 1000);

    }
}
