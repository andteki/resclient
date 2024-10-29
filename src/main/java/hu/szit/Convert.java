package hu.szit;

import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * JSON Java object converter.
 * 
 * This class dependency on Gson.
 * 
 * @author Sallai András
 * @author szit.hu
 * @version 1.0
 * @see     Gson
 */
public class Convert {

    /**
     * Create an instance of this class.
     */
    public Convert() {}

    /**
     * Convert JSON string to Java list object.
     * Returns a value of type T.
     * 
     * @param     json      The JSON string to be converted.
     * @param     <T>       The type of the Java object.
     * @param     type      The type of the Java object.
     * @return    The answer is the Java object.
     */
    public static <T> ArrayList<T> toListObject(String json, Class<T> type) {
        Type collType = TypeToken.getParameterized(ArrayList.class, type).getType();
        Gson gson = new Gson();        
        return gson.fromJson(json, collType);
    }

    /**
     * Convert JSON string to Java object.
     * Returns a value of type T.
     * 
     * @param     <T>       The type of the Java object.
     * @param     type      The type of the Java object.
     * @param     json      JSON string for conversion.
     * @return              The answer is the Java object.
     */
    public static <T> T toObject(String json, Class<T> type) {
        Gson gson = new Gson();        
        return gson.fromJson(json, type);
    }

    /**
     * Convert Java ArrayList object to JSON string.
     * Returns a JSON string.
     * 
     * @param     <T>       The type of the Java object.
     * @param     list      The Java ArrayList object to be converted.
     * @return              The answer is the JSON string.
     */    
    public static <T> String listToJson(ArrayList<T> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /**
     * Convert Java object to JSON string.
     * Returns a JSON string.
     * 
     * @param     <T>       The type of the Java object.
     * @param     object    The Java object to be converted.
     * @return              The answer is the JSON string.
     */    
    public static <T> String toJson(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
