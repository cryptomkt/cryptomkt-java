package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public class Balance extends Container {

    public void setCurrencyData(Container currency) {
        data.put("currency_kind", currency.getData().get("kind"));
        data.put("currency_name", currency.getData().get("name"));
        data.put("currency_big_name", currency.getData().get("big_name"));
        data.put("currency_prefix", currency.getData().get("prefix"));
        data.put("currency_postfix", currency.getData().get("postfix"));
        data.put("decimals", currency.getData().get("decimals"));
    }
}
