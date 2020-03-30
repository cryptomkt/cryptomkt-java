package com.cryptomkt.api;

import com.cryptomkt.api.entity.SocAuthResponse;
import com.cryptomkt.api.entitySocket.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class SocketIoImpl implements SocketIo {
    // socket fields
    private static final ObjectMapper objectMapper = ObjectMapperProvider.createDefaultMapper();
    String url_worker = "https://worker.cryptomkt.com";
    IO.Options opts;
    Socket socket;

    //data fields
    OnCurrencies onCurrencies;
    OnBalance onBalance;
    CandlesData candlesData;
    OnOpenBook onOpenBook;
    OnHistoricalBook onHistoricalBook;
    OnHistoricalOrders onHistoricalOrders;
    OnOpenOrders onOpenOrders;
    OnOperated onOperated;
    OnBoard onBoard;



    //connect

    public SocketIoImpl(SocAuthResponse authToken) throws URISyntaxException {
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
                JSONObject obj = new JSONObject();
                try {
                    obj.put("socid", authToken.getSocAuth().getSocid());
                    obj.put("uid", authToken.getSocAuth().getUid());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("user-auth", obj);
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("disconected");
            }
        }).on("currencies", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onCurrencies = objectMapper.readValue(args[0].toString(), OnCurrencies.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*try {
                    System.out.println("onCurrencies");
                    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
                    String jsonString = mapper.writeValueAsString(onCurrencies);
                    System.out.println(jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }*/
            }
        }).on("balance", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onBalance = objectMapper.readValue(args[0].toString(), OnBalance.class);
                    for (Map.Entry<String, Balance> entry : onBalance.getData().entrySet()){
                        entry.getValue().setCurrencyData(onCurrencies.getData().get(entry.getKey()));
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("open-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onOpenOrders = objectMapper.readValue(args[0].toString(), OnOpenOrders.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("historical-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onHistoricalOrders = objectMapper.readValue(args[0].toString(), OnHistoricalOrders.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("operated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onOperated = objectMapper.readValue(args[0].toString(), OnOperated.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("open-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onOpenBook = objectMapper.readValue(args[0].toString(), OnOpenBook.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("historical-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onHistoricalBook = objectMapper.readValue(args[0].toString(), OnHistoricalBook.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("candles", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    CandleData candleData = objectMapper.readValue(args[0].toString(), CandleData.class);
                    candlesData.put(candleData.getStockId(), candleData);

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("board", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    System.out.println(args[0]);
                    onBoard = objectMapper.readValue(args[0].toString(), OnBoard.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });

        socket.connect();
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
