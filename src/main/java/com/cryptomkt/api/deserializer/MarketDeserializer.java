package com.cryptomkt.api.deserializer;


import com.cryptomkt.api.entity.Market;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class MarketDeserializer extends StdDeserializer<Market>{

    public MarketDeserializer() {
        super(Market.class);
    }

    @Override
    public Market deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        Market market = new Market();
        market.setName(p.getText());

        return market;
    }
}
