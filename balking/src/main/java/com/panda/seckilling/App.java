package com.panda.seckilling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args){
        Seckilling seckilling = new Seckilling();
        ExecutorService executorService = Executors.newFixedThreadPool(1000000000);

        for(int i = 0; i < 1000000000; i++){
            executorService.execute(seckilling::selling);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("ERROR: Waiting on executor service shutdown!");
        }
    }
}
