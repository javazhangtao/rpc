package com.plugins.pools;

import com.plugins.server.suppor.resource.ServerResource;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.context.ApplicationContext;

/**
 * Created by zhangtao on 2015/12/15.
 */
public class ServerPoolHandler{
    private ServerPoolConfig poolConfig;//池对象配置信息
    private ApplicationContext context;

    private ServerPoolHandler(){}

    public ServerPoolHandler(ApplicationContext _context,ServerPoolConfig _poolConfig){
        this.context=_context;
        this.poolConfig=_poolConfig;
    }

    public void create() throws Exception{
        try {
            if(null==ServerResource.serverPool) {
                ServerResource.serverPool = new GenericKeyedObjectPool(new ServerPoolFactory(this.context));
                if (this.poolConfig != null) {
                    GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
                    config.setMaxTotal(this.poolConfig.getMaxTotal());
                    config.setMinEvictableIdleTimeMillis(this.poolConfig.getMinEvictableIdleTimeMillis());
                    ServerResource.serverPool.setConfig(config);
                }
            }
        } catch (Exception e) {
            throw(e);
        }
    }
}
