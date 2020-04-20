package com.cryptomkt.api.utils;

import org.json.JSONObject;

public class Subscriber extends Thread{

    private final Callable callable;

    private final SyncJson syncJson;

    public Subscriber(Callable callable, SyncJson syncJson) {
        this.callable = callable;
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
                callable.call(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
