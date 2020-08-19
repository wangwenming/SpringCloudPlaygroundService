package com.quanfangtongvip.springcloudplayground.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: JSON工具类,使用的是google的JSON
 * ClassName: GsonUtils.java
 * date: 2018年12月3日
 *
 * @author YGC
 * @since JDK 1.8
 */
public class GsonUtils {

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls()
            .registerTypeAdapter(byte.class, new ByteDefault0Adapter())
            .registerTypeAdapter(Byte.class, new ByteDefaultNullAdapter())
            .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
            .registerTypeAdapter(Integer.class, new IntegerDefaultNullAdapter())
            .registerTypeAdapter(long.class, new LongDefault0Adapter())
            .registerTypeAdapter(Long.class, new LongDefaultNullAdapter())
            .registerTypeAdapter(float.class, new FloatDefault0Adapter())
            .registerTypeAdapter(Float.class, new FloatDefaultNullAdapter())
            .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
            .registerTypeAdapter(Double.class, new DoubleDefaultNullAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    private static Gson lowerCaseUnderscoresInstance = new GsonBuilder().serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .disableHtmlEscaping().create();

    public static Gson getInstance() {
        return gson;
    }

    public static Gson getLowerCaseUnderscoresInstance() {
        return lowerCaseUnderscoresInstance;
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }

    /**
     * 转成bean
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }

    /**
     * 转成list中有map的
     *
     * @param json
     * @return
     */
    public static <T> List<Map<String, T>> toListMaps(String json) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param json
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String json) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * ""或者null转换为0
     */
    static class ByteDefault0Adapter implements JsonSerializer<Byte>, JsonDeserializer<Byte> {

        @Override
        public Byte deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return Byte.valueOf("0");
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsByte();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Byte src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为null
     */
    static class ByteDefaultNullAdapter implements JsonSerializer<Byte>, JsonDeserializer<Byte> {

        @Override
        public Byte deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return null;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsByte();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Byte src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为0
     */
    static class IntegerDefault0Adapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {

        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return 0;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsInt();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为null
     */
    static class IntegerDefaultNullAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {

        @Override
        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return null;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsInt();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为0
     */
    static class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {

        @Override
        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return 0L;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsLong();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为null
     */
    static class LongDefaultNullAdapter implements JsonSerializer<Long>, JsonDeserializer<Long> {

        @Override
        public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return null;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsLong();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为0.0
     */
    static class FloatDefault0Adapter implements JsonSerializer<Float>, JsonDeserializer<Float> {

        @Override
        public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return 0.0f;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsFloat();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Float src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为null
     */
    static class FloatDefaultNullAdapter implements JsonSerializer<Float>, JsonDeserializer<Float> {

        @Override
        public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return null;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsFloat();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Float src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为0.0
     */
    static class DoubleDefault0Adapter implements JsonSerializer<Double>, JsonDeserializer<Double> {

        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return 0.0;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsDouble();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }

    /**
     * ""或者null转换为null
     */
    static class DoubleDefaultNullAdapter implements JsonSerializer<Double>, JsonDeserializer<Double> {

        @Override
        public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                if ("".equals(json.getAsString()) || "null".equals(json.getAsString())) {
                    return null;
                }
            } catch (Exception ignore) {
                // do nothing
            }
            try {
                return json.getAsDouble();
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src);
        }
    }
}
