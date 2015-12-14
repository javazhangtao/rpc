package com.plugins.main;

import com.google.common.base.Joiner;
import com.plugins.pools.ServerMethodPoolHandler;
import com.plugins.protocl.RpcRequest;
import com.plugins.protocl.transforms.FastMethodTransform;
import com.plugins.server.suppor.annotations.RPCAnnotationsHandler;
import com.plugins.server.suppor.annotations.RPCServer;
import com.plugins.server.suppor.annotations.RPCServerMethod;
import com.plugins.server.suppor.resource.ServerResource;
import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zhangtao on 2015/12/9.
 */
@Component
public class ServerFactory implements InitializingBean , ApplicationContextAware{
    private ApplicationContext context;

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


    /**
     * 加载所有对外接口方法，并封装RpcRequest 创建接口方法对象池
     * @param servers
     * @throws Exception
     */
    private void _initServerMethod(Map<String,Object> servers) throws Exception {
        for (Map.Entry<String,Object> entry:servers.entrySet()){
            Method[] methods=entry.getValue().getClass().getDeclaredMethods();
            for (Method method:methods){
                RPCServerMethod annotation= method.getAnnotation(RPCServerMethod.class);
                Deprecated deprecated=method.getAnnotation(Deprecated.class);
                if(null!=deprecated){
                    continue;
                }
                if(null==annotation){
                    continue;
                }
                RPCAnnotationsHandler handler=new RPCAnnotationsHandler(entry.getValue(),method);
                _createMethodPool(handler.handler());
            }
        }
    }

    private void _createMethodPool(RpcRequest request) throws Exception {
        if(null==request){
            throw new NullPointerException();
        }
        try {
            final String poolKey= Joiner.on("_").join(request.getServerName(),request.getMethondName(),request.getServerVersion());
            if(ServerResource.serverPools.containsKey(poolKey)){
                return ;
            }
            GenericObjectPool<FastMethod> pool= new ServerMethodPoolHandler(new FastMethodTransform(request).getObject(), ServerResource.methodPoolConfig).getObject();
            ServerResource.serverPools.put(poolKey,pool);
        } catch (Exception e) {
           throw(e);
        }
    }
}
