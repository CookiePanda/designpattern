package com.panda.seckilling;

import java.util.concurrent.TimeUnit;

/**
 *  Interface to simulate execute after delay
 */
public interface DelayProvider {
    void executeAfterDelay(long interval, TimeUnit timeUnit, Runnable task);
}
