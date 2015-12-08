package com.plugins.serialize.protocbuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.gson.GsonBuilder;
import com.plugins.serialize.SerualizeUtil;
import org.objenesis.ObjenesisStd;

/**
 * Created by zhangtao on 2015/12/8.
 */
public class ProtocSerualize implements SerualizeUtil {
    @Override
    public <T> byte[] serializeBinary(T obj) throws Exception {
        if(null==obj){
            throw new NullPointerException();
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Class<T> cls = (Class<T>) obj.getClass();
            Schema<T> schema = (Schema<T>) RuntimeSchema.createFrom(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public <T> T deserializeBinary(byte[] bytes, Class<T> cls) throws Exception {
        if(null==bytes) {
            throw new NullPointerException();
        }
        T message = (T) new ObjenesisStd(true).newInstance(cls);
        Schema<T> schema = (Schema<T>)RuntimeSchema.createFrom(cls);
        ProtostuffIOUtil.mergeFrom(bytes, message, schema);
        return message;
    }

    @Deprecated
    public <T> String serializeString(T obj, GsonBuilder... builder) throws Exception {
        return null;
    }

    @Deprecated
    public <T> T deserializeString(String str, Class<T> cls, GsonBuilder... builder) throws Exception {
        return null;
    }
}
