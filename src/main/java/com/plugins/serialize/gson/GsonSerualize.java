package com.plugins.serialize.gson;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plugins.serialize.SerualizeUtil;

/**
 * Created by zhangtao on 2015/12/8.
 * JSON序列化工具
 * 基于google   gson
 */
public class GsonSerualize implements SerualizeUtil{
    @Deprecated
    public <T> byte[] serializeBinary(T obj) throws Exception {
        return new byte[0];
    }

    @Deprecated
    public <T> T deserializeBinary(byte[] bytes, Class<T> cls) throws Exception {
        return null;
    }

    @Override
    public <T> String serializeString(T obj, GsonBuilder... builder) throws Exception {
        if(null==obj){
            throw new NullPointerException();
        }
        Gson gson=null;
        if(builder.length>0){
            gson=builder[0].setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }else{
            gson=new Gson();
        }
        return gson.toJson(obj);
    }

    @Override
    public <T> T deserializeString(String str, Class<T> cls, GsonBuilder ... builder) throws Exception {
        if(Strings.isNullOrEmpty(str)){
            throw new NullPointerException();
        }
        Gson gson=null;
        if(builder.length>0){
            gson=builder[0].setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }else{
            gson=new Gson();
        }
        return gson.fromJson(str,cls);
    }
}
