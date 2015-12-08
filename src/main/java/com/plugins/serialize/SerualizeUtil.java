package com.plugins.serialize;

import com.google.gson.GsonBuilder;

/**
 * Created by zhangtao on 2015/12/8.
 */
public interface SerualizeUtil {

    public <T> byte[] serializeBinary(T obj) throws Exception;

    public <T>T deserializeBinary(byte[] bytes, Class<T> cls) throws Exception;

    public <T> String serializeString(T obj, GsonBuilder ... builder) throws Exception;

    public <T>T deserializeString(String str, Class<T> cls, GsonBuilder ... builder) throws Exception;
}
