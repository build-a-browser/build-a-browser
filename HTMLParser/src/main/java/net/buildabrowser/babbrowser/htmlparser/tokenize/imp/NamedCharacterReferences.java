package net.buildabrowser.babbrowser.htmlparser.tokenize.imp;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class NamedCharacterReferences {
  
  private NamedCharacterReferences() {}

  public static Map<String, String> loadReferences() {
    JsonObject refObj = JsonParser.parseReader(new InputStreamReader(
      ClassLoader.getSystemClassLoader().getResourceAsStream("ua/charrefs.json")))
      .getAsJsonObject();
    Map<String, String> refMap = new HashMap<>();
    for (Entry<String, JsonElement> entry: refObj.entrySet()) {
      if (entry.getKey().startsWith("_")) continue;
      refMap.put(
        entry.getKey(),
        entry.getValue().getAsJsonObject()
          .get("characters").getAsString());
    }

    return refMap;
  }

}
