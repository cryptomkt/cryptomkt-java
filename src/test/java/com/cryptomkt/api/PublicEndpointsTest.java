package com.cryptomkt.api;

import com.cryptomkt.api.entity.Market;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.spec.ECField;
import java.util.List;

public class PublicEndpointsTest extends TestCase{

    protected CryptoMarket cryptoMarket;

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
        cryptoMarket= new CryptoMarketBuilder()
                .withApiKey(apiKey, apiSecret)
                .build();

    }

    public void testGetMarkets() {
        try {
            List<Market> markets = cryptoMarket.getMarkets().getMarkets();
            /* print as a list of objects, doesn't use toString of inner objects...
            System.out.println(markets);*/
            /* print as json
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(markets);
            System.out.println((json));*/
            /* print as a list of marketpairs
            for (Market market : markets) {
                System.out.println((market.toSting()));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testGetBook() {
        try {
            List<Market> markets = cryptoMarket.getMarkets().getMarkets();
            /* print as a list of objects, doesn't use toString of inner objects...
            System.out.println(markets);*/
            /* print as json
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(markets);
            System.out.println((json));*/
            /* print as a list of marketpairs
            for (Market market : markets) {
                System.out.println((market.toSting()));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }


}
