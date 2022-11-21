package com.cryptomarket.sdk;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.squareup.moshi.JsonReader;

import okio.Okio;

public class KeyLoader {
    private static String apiKey;
    private static String apiSecret;
    static {
        Map<String, String> keys = new HashMap<String, String>();
        try {
            // create a JSON reader
            JsonReader reader = JsonReader.of(Okio.buffer(Okio.source(Paths.get("/home/ismael/cryptomarket/keys-v3.json").toFile())));

            // start top-level object
            reader.beginObject();

            // read all tokens
            while (reader.hasNext()) {
                String name = reader.nextName();
                String value = reader.nextString();
                keys.put(name, value);
            }
            reader.endObject();

            //close the writer
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        apiKey = keys.get("apiKey");
        apiSecret = keys.get("apiSecret");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getApiSecret() {
        return apiSecret;
    }
}
