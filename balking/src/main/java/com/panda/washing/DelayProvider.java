package com.panda.washing;

import java.util.concurrent.TimeUnit;

/**
 *  An interface to simulate delay while executing some work.
 */
public interface DelayProvider {
    void excuteAfterDelay(long interval, TimeUnit timeUnit, Runnable task);
}
