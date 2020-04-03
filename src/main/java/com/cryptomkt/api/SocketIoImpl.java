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

public class SocketIoImpl implements SocketIo {
    // socket fields
    private static final ObjectMapper objectMapper = ObjectMapperProvider.createDefaultMapper();
    String url_worker = "https://worker.cryptomkt.com";
    IO.Options opts;
    Socket socket;

    //data fields
    VersionedContainerMap currenciesData;
    BalanceData balanceData;
    CandlesData candlesData;
    OpenBookData openBookData;
    HistoricalBookData onHistoricalBookData;
    VersionedContainerList historicalOrdersData;
    VersionedContainerList openOrdersData;
    VersionedContainer operatedData;
    VersionedContainerMap tickerData;



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
                    currenciesData = objectMapper.readValue(args[0].toString(), VersionedContainerMap.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("onCurrencies");
                    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
                    String jsonString = mapper.writeValueAsString(currenciesData);
                    System.out.println(jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("balance", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    balanceData = objectMapper.readValue(args[0].toString(), BalanceData.class);
                    for (Map.Entry<String, Balance> entry : balanceData.getData().entrySet()){
                        entry.getValue().setCurrencyData(currenciesData.getData().get(entry.getKey()));
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("open-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    openOrdersData = objectMapper.readValue(args[0].toString(), VersionedContainerList.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("historical-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    historicalOrdersData = objectMapper.readValue(args[0].toString(), VersionedContainerList.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("operated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    operatedData = objectMapper.readValue(args[0].toString(), VersionedContainer.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("open-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    openBookData = objectMapper.readValue(args[0].toString(), OpenBookData.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }).on("historical-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onHistoricalBookData = objectMapper.readValue(args[0].toString(), HistoricalBookData.class);
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
                    tickerData = objectMapper.readValue(args[0].toString(), VersionedContainerMap.class);
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
