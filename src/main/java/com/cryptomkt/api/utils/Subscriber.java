package com.cryptomkt.api.utils;

import org.json.JSONObject;

import java.util.function.Consumer;

public class Subscriber extends Thread{

    private final Consumer<JSONObject> consumer;

    private final SyncJson syncJson;

    public Subscriber(Consumer<JSONObject> callable, SyncJson syncJson) {
        this.consumer = callable;
        this.syncJson = syncJson;
    }

    @Override
    public void run() {
        JSONObject data;
        while (true) {
            try {
                synchronized (syncJson) {
                    syncJson.wait();
                    data = syncJson.getData();
                }
                consumer.accept(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
