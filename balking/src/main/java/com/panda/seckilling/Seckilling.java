package com.panda.seckilling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Seckilling {

    private static final Logger LOGGER = LoggerFactory.getLogger(Seckilling.class);

    final private DelayProvider delayProvider;
    private SeckillingState seckillingState;
    private int TOTAL_ITEM = 100000000;

    public Seckilling(){
        this((interval, timeUnit, task) -> {
            try{
                Thread.sleep(timeUnit.toMillis(interval));
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            task.run();
        });
    }

    public Seckilling(DelayProvider delayProvider){
        this.delayProvider = delayProvider;
        this.seckillingState = SeckillingState.AVAILABLE;
    }

    public void selling(){
            LOGGER.info("{}: Begin selling, seckilling state is {}", Thread.currentThread().getName(), seckillingState);
            if(seckillingState == SeckillingState.SOLDOUT){
                LOGGER.error("ERROR: seckilling is soldout");
                return;
            }
            seckillingState = SeckillingState.AVAILABLE;
            LOGGER.info("{}: Seckilling is running. Remain:{}", Thread.currentThread().getName(),TOTAL_ITEM);
            TOTAL_ITEM--;
            if(TOTAL_ITEM == 0){
                this.outOfSelling();
            }
    }

    public void outOfSelling(){
        seckillingState = SeckillingState.SOLDOUT;
        LOGGER.info("{}:Selling is over", Thread.currentThread().getName());
    }
}
