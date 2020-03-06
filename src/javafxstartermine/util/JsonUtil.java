/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxstartermine.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

/**
 *
 * @author EPS01
 */
public class JsonUtil {
    
    public static <K, V> JsonElement jsonfileToJson(FileReader fr) {
        JsonParser parser = new JsonParser();
        return parser.parse(fr);
    }

    public static <K, V> String mapToJson(Map<K, V> map) {
        return new Gson().toJson(map);
    }

    public static JsonElement stringToJson(String stringData) {
        return new JsonParser().parse(stringData);
    }

    public static <T> String listToJson(List<T> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    public static <T> ArrayList<T> jsonToArrayList(Class<T> t, JsonArray jsonArray) {
        ArrayList<T> arrList = (ArrayList<T>) fromJson(jsonArray.toString(), new TypeToken<ArrayList<T>>() {}.getType());
        return arrList;
    }

    public static <T> ArrayList<T> jsonToArrayList(T t, String jsonArray) {
        Type type = new TypeToken<List<T>>() {}.getType();
        return new Gson().fromJson(jsonArray, type);
    }

    private static Object fromJson(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }
    
    

}
