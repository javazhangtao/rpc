package com.plugins.pools;

import com.plugins.server.suppor.resource.ServerResource;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.context.ApplicationContext;

/**
 * Created by zhangtao on 2015/12/15.
 * 创建服务对象池
 */
public class ServerPoolHandler{
    private ServerPoolConfig poolConfig;//池对象配置信息
    private ApplicationContext context;//当前上下文对象

    private ServerPoolHandler(){}

    public ServerPoolHandler(ApplicationContext _context,ServerPoolConfig _poolConfig){
        this.context=_context;
        this.poolConfig=_poolConfig;
    }

    /**
     * 创建池对象
     * @throws Exception
     */
    public void create() throws Exception{
        try {
            if(null==ServerResource.serverPool) {
                ServerResource.serverPool = new GenericKeyedObjectPool(new ServerPoolFactory(this.context));
                if (this.poolConfig != null) {
                    GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
                    config.setMaxTotal(this.poolConfig.getMaxTotal());
                    config.setMinIdlePerKey(this.poolConfig.getMaxTotal());
                    config.setMinEvictableIdleTimeMillis(this.poolConfig.getMinEvictableIdleTimeMillis());
                    ServerResource.serverPool.setConfig(config);
                }
            }
        } catch (Exception e) {
            throw(e);
        }
    }
}
