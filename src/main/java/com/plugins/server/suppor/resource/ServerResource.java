package com.plugins.server.suppor.resource;

import com.plugins.pools.ServerPoolConfig;
import com.plugins.util.PropertiesUtil;
import net.sf.cglib.reflect.FastClass;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

/**
 * Created by zhangtao on 2015/12/8.
 * 静态资源
 */
public class ServerResource {

    public static final PropertiesUtil responsecode_prop=new PropertiesUtil("/properties/response_code.properties");//服务响应状态码配置信息对象
    public static GenericKeyedObjectPool<String,FastClass> serverPool=null;//服务池集合
    public static ServerPoolConfig poolConfig=new ServerPoolConfig();//代理类池配置



}
