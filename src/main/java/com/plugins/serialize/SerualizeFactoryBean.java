package com.plugins.serialize;

import com.plugins.serialize.gson.GsonSerualize;
import com.plugins.serialize.protocbuff.ProtocSerualize;

/**
 * Created by zhangtao on 2015/12/8.
 */
public class SerualizeFactoryBean {

    public enum SerualizeEnum{
        GSON , PROTOC
    }

    private SerualizeFactoryBean(){}

    public static SerualizeUtil create(SerualizeEnum type){
        if(null==type) {
            return new BaseSerualize();
        }else if(type.equals(SerualizeEnum.GSON)){
            return new GsonSerualize();
        }else if(type.equals(SerualizeEnum.PROTOC)){
            return new ProtocSerualize();
        }else{
            return new BaseSerualize();
        }

    }




}
