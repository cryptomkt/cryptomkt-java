package com.cryptomkt.api;

import com.cryptomkt.api.entity.SocAuthResponse;
import com.cryptomkt.api.entitySocket.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class SocketIoV2 implements SocketIo {
    // socket fields
    private static final ObjectMapper objectMapper = ObjectMapperProvider.createDefaultMapper();
    String url_worker = "https://worker.cryptomkt.com";
    IO.Options opts;
    Socket socket;

    //data fields
    CandlesData candlesData;
    VersionedContainerMap tickerData;



    //connect

    public SocketIoV2(SocAuthResponse authToken) throws URISyntaxException {
        candlesData = new CandlesData();

        opts = new IO.Options();
        opts.reconnection = true;
        opts.reconnectionAttempts = 5;
        opts.reconnectionDelay = 1000;
        opts.reconnectionDelayMax = 15000;
        //opts.transports = "websocket" TODO (inside array) ;
        System.out.println("creating socket");
        socket = IO.socket(url_worker);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected");
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("disconected");
            }
        }).on("board", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    System.out.println(args[0]);
                    tickerData = objectMapper.readValue(args[0].toString(), VersionedContainerMap.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("board-delta", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                /*JSONObject patch = new JSONObject(args[0]);
                if (tickerData.isCorrectVersion(patch)) {
                    tickerData.patch(patch);
                    tickerData.updateVersion(patch);
                }*/
            }
        });

        socket.connect();
        JSONObject obj = new JSONObject();
        try {
            obj.put("socid", authToken.getSocAuth().getSocid());
            obj.put("uid", authToken.getSocAuth().getUid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("user-auth", obj);
        this.subscribe("ETHCLP");
    }


    public void subscribe(String[] marketPairs){
        for ( String marketPair : marketPairs ){
            socket.emit("subscribe", marketPair);
        }
    }

    public void unsubscribe(String[] marketPairs){
        for ( String marketPair : marketPairs ){
            socket.emit("unsubscribe", marketPair);
        }
    }


    @Override
    public void subscribe(String marketPair) {
        socket.emit("subscribe", marketPair);
    }

    @Override
    public void subscribe(List<String> marketPairs) {
        for ( String marketPair : marketPairs ){
            socket.emit("subscribe", marketPair);
        }
    }

    @Override
    public void unsubscribe(String marketPair) {
        socket.emit("unsubscribe", marketPair);
    }

    @Override
    public void unsubscribe(List<String> marketPairs) {
        for ( String marketPair : marketPairs ){
            socket.emit("unsubscribe", marketPair);
        }
    }
}
