package com.cryptomkt.api;

import com.cryptomkt.api.entity.SocAuth;
import com.cryptomkt.api.entity.SocAuthResponse;
import com.cryptomkt.api.entity.orders.Order;
import com.cryptomkt.api.exception.CryptoMarketException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SocketTest extends TestCase {
    protected Client client;
    protected SocketIo socket;
    private ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    protected void printObject(Object object) {
        try {
            String jsonString = this.mapper.writeValueAsString(object);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    protected void setUp() {
        String apiKey = "";
        String apiSecret = "";
        try {
            List<String> allLines = Files.readAllLines(Paths.get("/home/ismael/cptmkt/keys.txt"));
            apiKey = allLines.get(0);
            apiSecret = allLines.get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = new ClientImpl(apiKey, apiSecret);
    }

    public void testSocket() {
        try {
            SocketIo socket = client.getSocket();

        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        try {
            for (int i=0; i<=30; i++) {
                System.out.println(i + " seconds");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //socket.subscribe("ETHCLP");
        assertTrue(true);
    }
}
