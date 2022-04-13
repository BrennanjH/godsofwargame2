package com.simplesoftwaresolutions.godsofwargame;

import com.simplesoftwaresolutions.godsofwargame.game.tasks.TaskStart;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A class that holds a test main method, not used during live production
 */
public class AltMain {

    public static void main(String[] args) {


        Timer timer = new Timer();

        TimerTask task = new TaskStart(null);





            timer.schedule(task, 1000);
            task.cancel();
            task.cancel();

            timer.schedule(new TaskStart(null), 500);

    }

}

