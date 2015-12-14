package com.plugins.server.suppor.resource;

import com.google.common.collect.Maps;
import com.plugins.pools.ServerMethodPoolConfig;
import com.plugins.util.PropertiesUtil;
import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.util.Map;

/**
 * Created by zhangtao on 2015/12/8.
 * 静态资源
 */
public class ServerResource {

    public static final PropertiesUtil responsecode_prop=new PropertiesUtil("/properties/response_code.properties");
    public static Map<String,GenericObjectPool<FastMethod>> serverPools= Maps.newConcurrentMap();//服务池集合    key:服务方法名称    value:代理方法池
    public static ServerMethodPoolConfig methodPoolConfig=new ServerMethodPoolConfig();



}
