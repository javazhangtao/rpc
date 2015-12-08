package com.plugins.serialize;

import com.google.common.base.Strings;
import com.google.gson.GsonBuilder;

/**
 * Created by zhangtao on 2015/12/8.
 * 原始序列化
 */
public class BaseSerualize implements SerualizeUtil{
    @Deprecated
    public <T> byte[] serializeBinary(T obj) throws Exception {
        return new byte[0];
    }

    @Deprecated
    public <T> T deserializeBinary(byte[] bytes, Class<T> cls) throws Exception {
        return null;
    }

    @Override
    public <T> String serializeString(T obj, GsonBuilder ... builder) throws Exception {
        if(null==obj) {
            throw new NullPointerException();
        }
        return obj.toString();
    }

    @Override
    public <T> T deserializeString(String str, Class<T> cls, GsonBuilder... builder) throws Exception {
        if(Strings.isNullOrEmpty(str)){
            throw new NullPointerException();
        }
        return (T) str;
    }
}
