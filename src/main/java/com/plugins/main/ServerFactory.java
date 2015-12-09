package com.plugins.main;

import com.plugins.protocl.RpcRequest;
import com.plugins.server.suppor.annotations.RPCAnnotationsHandler;
import com.plugins.server.suppor.annotations.RPCServer;
import com.plugins.server.suppor.annotations.RPCServerMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zhangtao on 2015/12/9.
 */
public class ServerFactory implements InitializingBean , ApplicationContextAware{
    ApplicationContext context=null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,Object> servers=context.getBeansWithAnnotation(RPCServer.class);
        if(null==servers || servers.isEmpty()){
            throw new NullPointerException();
        }
        _initServerMethod(servers);
    }


    void _initServerMethod(Map<String,Object> servers) throws Exception {
        for (Map.Entry<String,Object> entry:servers.entrySet()){
            Method[] methods=entry.getValue().getClass().getDeclaredMethods();
            for (Method method:methods){
                RpcRequest request=new RpcRequest();
                RPCServerMethod annotation= method.getAnnotation(RPCServerMethod.class);
                Deprecated deprecated=method.getAnnotation(Deprecated.class);
                if(null!=deprecated){
                    continue;
                }
                if(null==annotation){
                    continue;
                }
                RPCAnnotationsHandler handler=new RPCAnnotationsHandler(entry.getValue(),method);
                request=handler.handler();
            }
        }
    }
}
