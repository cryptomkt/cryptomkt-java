package com.cryptomkt.api;

import com.cryptomkt.api.exception.CryptoMarketException;
import com.cryptomkt.api.utils.IntPair;
import com.cryptomkt.api.utils.JSONPatchException;
import com.cryptomkt.api.utils.JSONPatcher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SocketTest extends TestCase {
    protected Client client;
    protected Socket socket;
    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

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
            Socket socket = client.getSocket();
            socket.subscribe("ETHCLP");
            socket.onHistoricalBook(System.out::println);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        try {
            for (int i=0; i<=300; i++) {
                System.out.println(i + " seconds");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testmanyHandlersOneEvent() {
        try {
            Socket socket = client.getSocket();
            System.out.println(Thread.currentThread().getName());
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data.toString());
            });
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);
                try {
                    Thread.sleep(10000);
                    System.out.println("still here lady");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onTicker(data -> {
                System.out.println("hello: " + Thread.currentThread().getName());
                System.out.println(data);});
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        try {
            for (int i=0; i<=300; i++) {
                System.out.println(i + " seconds");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testmanyHandlersAllEventsOneToOne() {
        try {
            Socket socket = client.getSocket();
            System.out.println(Thread.currentThread().getName());
            socket.onBalance(data -> {
                System.out.println("balance. hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onCurrencies(data -> {
                System.out.println("currencies. hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onOpenBook(data -> {
                System.out.println("open book. hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onHistoricalBook(data -> {
                System.out.println("historical book hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onOpenOrders(data -> {
                System.out.println("open orders hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onHistoricalOrders(data -> {
                System.out.println("historical orders hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onOperated(data -> {
                System.out.println("operated hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onCandles(data -> {
                System.out.println("candles hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.onTicker(data -> {
                System.out.println("ticker hello: " + Thread.currentThread().getName());
                System.out.println(data);});
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        try {
            for (int i=0; i<=300; i++) {
                System.out.println(i + " seconds");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testBooksListeners() {
        try {
            Socket socket = client.getSocket();
            System.out.println(Thread.currentThread().getName());
            socket.onOpenBook(data -> {
                System.out.println("open book. hello: " + Thread.currentThread().getName());
                System.out.println(data);});
            socket.subscribe("ETHCLP");
            try {
                for (int i=0; i<=100; i++) {
                    System.out.println(i + " seconds");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testJsonObject() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject("{\"a\":\"amarillo\", \"b\":{\"bb\":\"blanco\"}}");
            jsonObject.put("c", "camarón");
            System.out.println(jsonObject);
            // put with the same key replaces old value
            jsonObject.put("c","dinosaurio");
            System.out.println(jsonObject);
            // can't change inner value as a pointer
            Object value = jsonObject.get("c");
            value = "working";
            System.out.println(jsonObject);
            // changing inner jsonObject of a jsonObject -> patch can be recursive over json objects
            JSONObject innerObject =(JSONObject) jsonObject.get("b");
            innerObject.put("bc", "barbacoa");
            System.out.println(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void testJsonArray() {
        try {
            JSONArray jsonArray= new JSONArray("[\"a\", \"amarillo\", \"b\",{\"bb\":\"blanco\"}]");
            System.out.println(jsonArray);
            // inserting in array replace old value
            jsonArray.put(1, "extra");
            System.out.println(jsonArray);
            // putting without index extends array
            jsonArray.put("last elem");
            System.out.println(jsonArray);
            // inserting in far index fill with null the unseted values
            jsonArray.put(12, "last elem");
            System.out.println(jsonArray);
            // reassigning a value has no effect -> patching cannot be recursive over a general object
            Object a = jsonArray.get(1);
            a = "damnation";
            System.out.println(jsonArray);
            // modifiing an inner object works -> patch can be recursive over jsonObjects
            JSONObject jsonObject =(JSONObject)  jsonArray.get(3);
            jsonObject.put("u", "unicode");
            System.out.println(jsonArray);
            // modifying an inner array also works -> patch can be recursive over jsonArrays
            jsonArray.put(5, new JSONArray("[1,2,3]"));
            System.out.println(jsonArray);
            ((JSONArray) jsonArray.get(5)).put(4);
            System.out.println(jsonArray);
            // reassigning an inner json does nothing -> patch cannot change the pointer, only the object in the pointer.
            // that's a lot of sense, the json array has the pointer, if the outer variable change its pointer, that
            // doesn't change the json array pointer, but, if the object pointed by the pointer in the json array change
            // then those changes can be seen from the json array.
            jsonObject = new JSONObject("{\"new\":\"json\"}");
            System.out.println(jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void testAddingTolist() {
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<10;i++) {
            list.add(i);
        }
        list.add(3,100);
        System.out.println(list);
    }
    public void testPairList() {
        List<IntPair> moveList = new ArrayList<>();
        moveList.add(new IntPair(1,2));
        moveList.add(new IntPair(40,8));
        moveList.add(new IntPair(21,12));
        moveList.add(new IntPair(18,2333));
        moveList.add(new IntPair(132,25));

        moveList.sort(Comparator.comparing(pair -> pair.snd));
        for (IntPair pair: moveList) {
            System.out.println(pair);
        }
    }

    public void testJsonPatcher() {
        String jsonString = "";
        String deltaString = "";
        try {
            jsonString = Files.readAllLines(Paths.get("/home/ismael/cptmkt/candles-data.txt")).get(0);
            deltaString = Files.readAllLines(Paths.get("/home/ismael/cptmkt/candles-delta.txt")).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject delta = new JSONObject(deltaString);
            JSONObject result = (JSONObject) JSONPatcher.patch(jsonObject.get("data"), delta.get("delta_data"));
            ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
            assert result != null;
            String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result.toString());
            System.out.println(indented);

        } catch (JSONException | JsonProcessingException | JSONPatchException e) {
            e.printStackTrace();
        }

    }

    public void testPatchString() {
        String patchString = "[\n" +
                "  \"@@ -638,17 +638,17 @@\\n via, Bra\\n-z\\n+s\\n il,  %0ACh\\n@@ -916,20 +916,13 @@\\n re a\\n-lso known as\\n+.k.a.\\n  Car\\n\",\n" +
                "  0,\n" +
                "  2\n" +
                "]";
        StringBuilder strb = new StringBuilder();
        strb.append("South America (Spanish: América del Sur, Sudamérica or");
        strb.append("Suramérica; Portuguese: América do Sul; Quechua and Aymara:");
        strb.append("Urin Awya Yala; Guarani: Ñembyamérika; Dutch: Zuid-Amerika;");
        strb.append("French: Amérique du Sud) is a continent situated in the");
        strb.append("Western Hemisphere, mostly in the Southern Hemisphere, with");
        strb.append("a relatively small portion in the Northern Hemisphere.");
        strb.append("The continent is also considered a subcontinent of the");
        strb.append("Americas.[2][3] It is bordered on the west by the Pacific");
        strb.append("Ocean and on the north and east by the Atlantic Ocean;");
        strb.append("North America and the Caribbean Sea lie to the northwest.");
        strb.append("It includes twelve countries: Argentina, Bolivia, Brazil,");
        strb.append("Chile, Colombia, Ecuador, Guyana, Paraguay, Peru, Suriname,");
        strb.append("Uruguay, and Venezuela. The South American nations that");
        strb.append("border the Caribbean Sea—including Colombia, Venezuela,");
        strb.append("Guyana, Suriname, as well as French Guiana, which is an");
        strb.append("overseas region of France—are also known as Caribbean South");
        strb.append("America. South America has an area of 17,840,000 square");
        strb.append("kilometers (6,890,000 sq mi). Its population as of 2005");
        strb.append("has been estimated at more than 371,090,000. South America");
        strb.append("ranks fourth in area (after Asia, Africa, and North America)");
        strb.append("and fifth in population (after Asia, Africa, Europe, and");
        strb.append("North America). The word America was coined in 1507 by");
        strb.append("cartographers Martin Waldseemüller and Matthias Ringmann,");
        strb.append("after Amerigo Vespucci, who was the first European to");
        strb.append("suggest that the lands newly discovered by Europeans were");
        strb.append("not India, but a New World unknown to Europeans.");
        String origin = strb.toString();
        StringBuilder strb2 = new StringBuilder();



        strb2.append("South America (Spanish: América del Sur, Sudamérica or");
        strb2.append("Suramérica; Portuguese: América do Sul; Quechua and Aymara:");
        strb2.append("Urin Awya Yala; Guarani: Ñembyamérika; Dutch: Zuid-Amerika;");
        strb2.append("French: Amérique du Sud) is a continent situated in the");
        strb2.append("Western Hemisphere, mostly in the Southern Hemisphere, with");
        strb2.append("a relatively small portion in the Northern Hemisphere.");
        strb2.append("The continent is also considered a subcontinent of the");
        strb2.append("Americas.[2][3] It is bordered on the west by the Pacific");
        strb2.append("Ocean and on the north and east by the Atlantic Ocean;");
        strb2.append("North America and the Caribbean Sea lie to the northwest.");
        strb2.append("It includes twelve countries: Argentina, Bolivia, Brasil,");
        strb2.append("Chile, Colombia, Ecuador, Guyana, Paraguay, Peru, Suriname,");
        strb2.append("Uruguay, and Venezuela. The South American nations that");
        strb2.append("border the Caribbean Sea—including Colombia, Venezuela,");
        strb2.append("Guyana, Suriname, as well as French Guiana, which is an");
        strb2.append("overseas region of France—are a.k.a. Caribbean South");
        strb2.append("America. South America has an area of 17,840,000 square");
        strb2.append("kilometers (6,890,000 sq mi). Its population as of 2005");
        strb2.append("has been estimated at more than 371,090,000. South America");
        strb2.append("ranks fourth in area (after Asia, Africa, and North America)");
        strb2.append("and fifth in population (after Asia, Africa, Europe, and");
        strb2.append("North America). The word America was coined in 1507 by");
        strb2.append("cartographers Martin Waldseemüller and Matthias Ringmann,");
        strb2.append("after Amerigo Vespucci, who was the first European to");
        strb2.append("suggest that the lands newly discovered by Europeans were");
        strb2.append("not India, but a New World unknown to Europeans.");

        String destiny = strb2.toString();

        try {
            JSONObject jsonOrigin = new JSONObject();
            jsonOrigin.put("data",origin);
            JSONObject delta = new JSONObject();
            delta.put("data",new JSONArray(patchString));
            Object result = JSONPatcher.patch(jsonOrigin, delta);
            // seeing the change from "s" to "z" in brazil y the "also known as" to "a.k.a"
            // it works
            System.out.println(origin);
            assert result != null;
            System.out.println(result.toString());
            // BUT, trying equalitiy fails...?
            System.out.println(destiny.equals(result));
        } catch (JSONException | JSONPatchException e ) {
            e.printStackTrace();
        }
    }

    public void testMakeTestData() {
        try {
            Socket socket = client.getSocket();
            socket.subscribe("ETHCLP");
            try {
                for (int i = 0; i <= 500; i++) {
                    System.out.println(i + " seconds");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    public void testComparativePatching() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("/home/ismael/cptmkt/testData.json"));
            String jsonString = new String(jsonData);
            JSONObject data = new JSONObject(jsonString);
            List<String> keys = new ArrayList<>();
            System.out.println("all keys");
            for (Iterator<String> it = data.keys(); it.hasNext(); ) {
                String key = it.next();
                System.out.println(key);
                if (!Objects.equals(key, "origin")) {
                        keys.add(key);
                }
            }
            System.out.println();
            JSONObject patchable = data.getJSONObject("origin");
            System.out.println("patcheble original version" + patchable.get("to_tx"));
            Collections.sort(keys);
            for (String key: keys) {
                System.out.println("doin " + key);
                if (!Objects.equals(patchable.get("to_tx"), data.getJSONObject(key).get("from_tx"))) {
                    System.out.println("error at " + key);
                    break;
                }
                patchable.put("data", JSONPatcher.patch(patchable.get("data"), data.getJSONObject(key).get("delta_data")));
                patchable.put("to_tx", data.getJSONObject(key).get("to_tx"));
                try {
                    FileOutputStream out = new FileOutputStream("/home/ismael/cptmkt/javaPatchs/" + key + ".json");
                    out.write(patchable.toString().getBytes());
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException | JSONException | JSONPatchException e) {
            e.printStackTrace();
        }
    }
}
