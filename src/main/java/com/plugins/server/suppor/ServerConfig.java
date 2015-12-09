package com.plugins.server.suppor;

/**
 * Created by zhangtao on 2015/12/8.
 */
public class ServerConfig {

    final Integer DEFAULT_INITCOUNT=10;//每个server服务方法对象池初始化数量
    final Integer DEFAULT_MAXCOUNT=50;//每个server服务方法对象池最大数量


    String host;
    Integer port;
    Integer initCount=DEFAULT_INITCOUNT;
    Integer maxCount=DEFAULT_MAXCOUNT;
}
