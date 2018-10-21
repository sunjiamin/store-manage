package com.sun.storemanager.common.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author sunjiamin
 * Date 2017/5/10 15:08
 * Describe : 让JSON操作变得简单的 Gson 封装类
 */
public class GsonUtil {



    private static Gson gson = null;
    static {
        if (gson == null) {
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapter(Number.class, new NumberAdapter());//处理空串 转 Number 默认为0
            gb.registerTypeAdapter(Integer.class, new IntAdapter());//处理空串 转 Integer 默认为0
            gb.registerTypeAdapter(int.class, new IntAdapter());//处理空串 转 int 默认为0
            gb.registerTypeAdapter(BigDecimal.class, new BigDecimalAdapter());//处理空串 转 BigDecimal 默认为0
            gson = gb.create();

        }
    }

    private GsonUtil() {
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String GsonToString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }



    public static <T> ArrayList<T> GsonToArrayList(String json, Class<T> classOfT) {
        Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
        ArrayList<JsonObject> jsonObjs = new Gson().fromJson(json, type);
        ArrayList<T> listOfT = new ArrayList<>();
        for (JsonObject jsonObj : jsonObjs) {
            listOfT.add(new Gson().fromJson(jsonObj, classOfT));
        }
        return listOfT;
    }


    /**
     * 转成list
     * 解决泛型问题
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> GsonToListGeneric(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }




   public static class BigDecimalAdapter implements JsonSerializer<BigDecimal>, JsonDeserializer<BigDecimal> {

        @Override
        public BigDecimal deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (json != null) {
                try {
                    if(!StringUtil.isBlank(json.toString())){
                        return new BigDecimal(json.getAsString());
                    }
                    return new BigDecimal(0);
                } catch (JsonParseException e) {
                    throw new JsonSyntaxException(e);
                }catch (Exception e){
                    throw new JsonSyntaxException(e);
                }
            }
            return new BigDecimal(0);
        }

        @Override
        public JsonElement serialize(BigDecimal value, Type type, JsonSerializationContext context) {
            if (value != null) {
                return new JsonPrimitive(value.toString());
            }
            return new JsonPrimitive("0");
        }

    }



    public static class IntAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {

        @Override
        public Integer deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (json != null) {
                try {
                    if(!StringUtil.isBlank(json.toString())){
                        return  json.getAsInt();
                    }
                    return 0;
                } catch (JsonParseException e) {
                    throw new JsonSyntaxException(e);
                }catch (Exception e){
                    throw new JsonSyntaxException(e);
                }
            }
            return 0;
        }

        @Override
        public JsonElement serialize(Integer value, Type type, JsonSerializationContext context) {
            if (value != null) {
                return new JsonPrimitive(value.toString());
            }
            return new JsonPrimitive("0");
        }

    }

    public static class NumberAdapter implements JsonSerializer<Number>, JsonDeserializer<Number> {

        @Override
        public Number deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            if (json != null) {
                try {
                    if(!StringUtil.isBlank(json.toString())){
                        return  json.getAsNumber();
                    }
                    return 0;
                } catch (JsonParseException e) {
                    throw new JsonSyntaxException(e);
                }catch (Exception e){
                    throw new JsonSyntaxException(e);
                }
            }
            return 0;
        }

        @Override
        public JsonElement serialize(Number value, Type type, JsonSerializationContext context) {
            if (value != null) {
                return new JsonPrimitive(value.toString());
            }
            return new JsonPrimitive("0");
        }

    }

}
