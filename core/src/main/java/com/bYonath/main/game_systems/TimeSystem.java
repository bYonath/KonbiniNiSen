package com.bYonath.main.game_systems;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.utils.Timer;

public class TimeSystem
{
    private Timer timer;

    public TimeSystem(Timer timer)
    {
        this.timer = timer;
    }

    public void start()
    {
        timer.start();
    }

    public void stop()
    {
        timer.stop();
    }

    public void scheduleTaskOnce(Timer.Task task, float seconds)
    {
        timer.scheduleTask(task, seconds);
    }

    public void scheduleRepeatTask(Timer.Task task, float seconds, float interval)
    {
        timer.scheduleTask(task, seconds, interval);
    }

    public void scheduleRepeatTask(
        Timer.Task task, float seconds,
        float interval, int count)
    {
        timer.scheduleTask(task, seconds, interval, count);
    }

    public Timer.Task testTask = new Timer.Task() {
        @Override
        public void run() {
            System.out.println("This is a task");
        }
    };
}
