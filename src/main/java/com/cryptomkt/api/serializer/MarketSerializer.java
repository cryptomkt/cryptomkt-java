package com.cryptomkt.api.serializer;

import com.cryptomkt.api.entity.Market;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class MarketSerializer  extends StdSerializer<Market>{

    public MarketSerializer() {
        super(Market.class);
    }

    @Override
    public void serialize(Market value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getName());
    }
}
