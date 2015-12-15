package com.plugins.main;

import com.plugins.pools.ServerPoolConfig;
import com.plugins.pools.ServerPoolHandler;
import com.plugins.server.suppor.annotations.RPCServer;
import com.plugins.server.suppor.resource.ServerResource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zhangtao on 2015/12/15.
 */
@Component
public class ServerHandler  implements InitializingBean, ApplicationContextAware {
    private ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,Object> servers=context.getBeansWithAnnotation(RPCServer.class);
        if(null==ServerResource.serverPool){
            new ServerPoolHandler(this.context,new ServerPoolConfig()).create();
        }
        for (Map.Entry<String , Object> entry:servers.entrySet()){
                ServerResource.serverPool.addObject(entry.getKey());
        }
    }
}
