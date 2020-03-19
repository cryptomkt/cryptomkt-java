package com.cryptomkt.api;
import com.cryptomkt.api.entity.SocAuthResponse;
import io.socket.client.*;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public interface SocketIo {


  void currencies();

  void balance();

  void openOrders();

  void historicalOrders();

  void operated();

  void openBook();

  void historicalBook();

  void candles();

  void board();
}

