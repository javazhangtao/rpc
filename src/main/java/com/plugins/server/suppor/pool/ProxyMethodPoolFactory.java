package com.plugins.server.suppor.pool;

import com.plugins.protocl.RpcRequest;
import com.plugins.proxy.server.ServerProxyFactoryBean;
import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by zhangtao on 2015/12/9.
 */
public class ProxyMethodPoolFactory extends BasePooledObjectFactory<FastMethod> {

    private RpcRequest request;

    public ProxyMethodPoolFactory(RpcRequest request){
        this.request=request;
    }

    @Override
    public FastMethod create() throws Exception {
        if(null==this.request){
            throw new NullPointerException();
        }
        return new ServerProxyFactoryBean(request.getServerName(),request.getMethondName(),request.getParamTypes()).createProxyMethod();
    }

    @Override
    public PooledObject<FastMethod> wrap(FastMethod obj) {
        return new DefaultPooledObject<FastMethod>(obj);
    }
}
