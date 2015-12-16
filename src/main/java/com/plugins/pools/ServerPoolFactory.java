package com.plugins.pools;

import com.google.common.base.Strings;
import com.plugins.proxy.server.ServerFactory;
import net.sf.cglib.reflect.FastClass;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.context.ApplicationContext;

/**
 * Created by zhangtao on 2015/12/15.
 * 池元素工厂
 */
public class ServerPoolFactory extends BaseKeyedPooledObjectFactory<String , FastClass>{

    private ApplicationContext context;//当前上下文环境

    private ServerPoolFactory(){}

    public ServerPoolFactory(ApplicationContext _context){
        this.context=_context;
    }
    @Override
    public FastClass create(String targetName) throws Exception {
        if(null==this.context || Strings.isNullOrEmpty(targetName)){
            throw new NullPointerException();
        }
        return new ServerFactory(this.context,targetName).getObject();
    }

    @Override
    public PooledObject<FastClass> wrap(FastClass value) {
        return new DefaultPooledObject<FastClass>(value);
    }
}
