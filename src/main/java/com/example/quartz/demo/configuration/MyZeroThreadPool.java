package com.example.quartz.demo.configuration;

import org.quartz.simpl.ZeroSizeThreadPool;

public class MyZeroThreadPool extends ZeroSizeThreadPool {
    private int threadCount;
    private boolean makeThreadsDaemons;

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public boolean isMakeThreadsDaemons() {
        return makeThreadsDaemons;
    }

    public void setMakeThreadsDaemons(boolean makeThreadsDaemons) {
        this.makeThreadsDaemons = makeThreadsDaemons;
    }
}
