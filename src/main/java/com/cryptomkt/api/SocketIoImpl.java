package com.cryptomkt.api;

import com.cryptomkt.api.entity.SocAuthResponse;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.Map;

public class SocketIoImpl implements SocketIo {
    String url_worker = "https://worker.cryptomkt.com";
    IO.Options opts = new IO.Options();
    Socket socket;

    //connect

    public SocketIoImpl(SocAuthResponse authToken) throws URISyntaxException {
        //opts.query = "auth_token=" + authToken;
        opts.forceNew = true;
        opts.reconnectionAttempts = 1;
        opts.reconnectionDelay = 1000;
        opts.reconnectionDelayMax = 15000;
        //opts.transports = "websocket" TODO (inside array) ;

        socket = IO.socket(url_worker,opts);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.emit("user-auth",authToken);
            }
        });
    }


    public void subscribe(String[] marketPairs){
        for ( String marketPair : marketPairs ){
            socket.emit("subscribe",marketPair);
        }
    }

    public void unsubscribe(String[] marketPairs){
        for ( String marketPair : marketPairs ){
            socket.emit("unsubscribe",marketPair);
        }
    }

    @Override
    public void currencies(){
        socket.on("currencies", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });
    }

    @Override
    public void balance(){
        socket.on("balance", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("balance", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public void openOrders(){
        socket.on("open-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("open-orders", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public void historicalOrders(){
        socket.on("historical-orders", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("historical-orders", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public void operated(){
        socket.on("operated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("operated", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public void openBook(){
        socket.on("open-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("open-book", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public void historicalBook(){
        socket.on("historical-book", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("historical-book", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public void candles(){
        socket.on("candles", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("candles", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

    @Override
    public void board(){
        socket.on("board", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                socket.emit("board", new Ack() {
                    @Override
                    public void call(Object... args) {

                    }
                });
            }
        });
    }

}
