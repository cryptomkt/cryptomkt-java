package com.cryptomkt.api;
import com.cryptomkt.api.entity.SocAuthResponse;
import io.socket.client.*;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SocketIo {

  void subscribe(String marketPair);

  void subscribe(List<String> marketPairs);

  void unsubscribe(String marketPairs);

  void unsubscribe(List<String> marketPairs);

  // void on(callable);
}

