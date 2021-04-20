package hewitt.jalisa.trueaccord.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class Util {
    private static Gson gson = null;

    private Util(){}

    public static void print(String text){
        System.out.println(text);
    }

    public static String convertToJson(Object o){
        if(gson == null){
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            gson = builder.create();
        }
        return gson.toJson(o);
    }
}
