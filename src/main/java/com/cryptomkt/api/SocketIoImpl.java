package com.cryptomkt.api;

import com.cryptomkt.api.entity.SocAuthResponse;
import com.cryptomkt.api.entitySocket.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
    OnCurrencies onCurrencies;
    OnBalance onBalance;
    OnCandles onCandles;
    OnOpenBook onOpenBook;
    OnHistoricalBook onHistoricalBook;
    OnHistoricalOrders onHistoricalOrders;
    OnOpenOrders onOpenOrders;
    OnOperated onOperated;
    OnBoard onBoard;



    //connect

    public SocketIoImpl(SocAuthResponse authToken) throws URISyntaxException {
        opts = new IO.Options();
        opts.reconnection = true;
        opts.reconnectionAttempts = 1;
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
        });
        socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("disconected");
            }
        });
        socket.on("currencies", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                /*try {
                    onCurrencies = objectMapper.readValue(args[0].toString(), OnCurrencies.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                /*try {
                    System.out.println("onCurrencies");
                    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
                    String jsonString = mapper.writeValueAsString(onCurrencies);
                    System.out.println(jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }*/

            }
        });
        socket.on("balance", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    onBalance = objectMapper.readValue(args[0].toString(), OnBalance.class);
                    for (Map.Entry<String, Balance> entry : onBalance.getData().entrySet()){
                        if
                        entry.getValue().setCurrencyData(onCurrencies.getData().get(entry.getKey()));
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("onCurrencies");
                    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
                    String jsonString = mapper.writeValueAsString(onBalance);
                    System.out.println(jsonString);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        socket.on("open-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //System.out.println(args[0]);
                /*socket.emit("open-orders", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });*/
            }
        });
        socket.on("historical-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //System.out.println(args[0]);
                /*socket.emit("historical-orders", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });*/
            }
        });
        socket.on("operated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //System.out.println(args[0]);
                /*socket.emit("operated", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });*/
            }
        });
        socket.on("open-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //System.out.println(args[0]);
                /*socket.emit("open-book", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });*/
            }
        });
        socket.on("historical-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //System.out.println(args[0]);
                /*socket.emit("historical-book", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });*/
            }
        });
        socket.on("candles", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //System.out.println(args[0]);
                /*socket.emit("candles", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });*/
            }
        });
        socket.on("board", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //System.out.println(args[0]);
                /*socket.emit("board", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });*/
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
