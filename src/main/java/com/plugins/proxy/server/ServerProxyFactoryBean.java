package com.plugins.proxy.server;

import com.google.common.base.Strings;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.objenesis.ObjenesisStd;

/**
 * Created by zhangtao on 2015/12/9.
 * 方法动态代理工厂类
 */
public class ServerProxyFactoryBean{
    String serverClass;
    String serverMethod;
    Class<?>[] paramTypes;

    public ServerProxyFactoryBean(String serverClass,String serverMethod,Class<?>[] paramTypes){
        this.serverClass=serverClass;
        this.serverMethod=serverMethod;
        this.paramTypes=paramTypes;
    }

    public FastMethod createProxyMethod() throws Exception{
        if(Strings.isNullOrEmpty(this.serverClass) || Strings.isNullOrEmpty(this.serverMethod)){
            throw new NullPointerException();
        }
        Object targetObject=new ObjenesisStd(true).newInstance(Class.forName(this.serverClass));
        FastClass serviceFastClass = FastClass.create(targetObject.getClass());
        return serviceFastClass.getMethod(this.serverMethod,this.paramTypes);
    }
}
