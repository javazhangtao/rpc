package com.plugins.server.suppor.pool;

import com.plugins.protocl.RpcRequest;
import com.plugins.server.suppor.resource.ServerResource;
import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by zhangtao on 2015/12/9.
 */
public class ProxyMethodPool {

    private ProxyMethodPoolConfig config;
    private RpcRequest request;

    public ProxyMethodPool(ProxyMethodPoolConfig config,RpcRequest request){
        this.config=config;
        this.request=request;
    }

    public void createPool() throws Exception{
        if(null==request){
            throw new NullPointerException();
        }
        String poolKey=request.getServerName()+"_"+request.getMethondName();
        GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
        if(!ServerResource.serverPools.containsKey(poolKey)){
            if(null!=config){
                poolConfig.setMinIdle(config.getMinIdle());
                poolConfig.setMaxIdle(config.getMaxIdle());
                poolConfig.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
            }
            GenericObjectPool<FastMethod> pool=new GenericObjectPool(new ProxyMethodPoolFactory(request),poolConfig);
            ServerResource.serverPools.put(poolKey,pool);
        }
    }

    public ProxyMethodPoolConfig getConfig() {
        return config;
    }

    public void setConfig(ProxyMethodPoolConfig config) {
        this.config = config;
    }

    public RpcRequest getRequest() {
        return request;
    }

    public void setRequest(RpcRequest request) {
        this.request = request;
    }
}
