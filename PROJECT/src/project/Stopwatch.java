package com.mycompany.flashcards;

public class Stopwatch {
    private long startTime;
    private long accumulatedTime;
    private boolean running;

    public Stopwatch() {
        this.accumulatedTime = 0;
        this.running = false;
    }

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    public void stop() {
        if (running) {
            accumulatedTime += System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    public long getElapsedTime() {
        if (running) {
            return accumulatedTime + (System.currentTimeMillis() - startTime);
        }
        return accumulatedTime;
    }

    public void reset() {
        accumulatedTime = 0;
    }
}
