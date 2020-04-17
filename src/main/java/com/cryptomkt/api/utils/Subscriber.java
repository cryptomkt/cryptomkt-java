package com.cryptomkt.api.utils;

import org.json.JSONObject;

public class Subscriber extends Thread{

    private final Listener listener;

    private final SyncJson syncJson;

    public Subscriber(Listener listener, SyncJson syncJson) {
        this.listener = listener;
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
                listener.call(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
