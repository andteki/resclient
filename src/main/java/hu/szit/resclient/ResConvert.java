/*
 * Copyright (C) 2024 Sallai András
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hu.szit.resclient;

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
 * @version 1.1.2
 * @see     Gson
 */
public class ResConvert {

    /**
     * Create an instance of this class.
     */
    public ResConvert() {}

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
