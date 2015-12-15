package com.plugins.pools;

import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by zhangtao on 2015/12/14.
 */
@Deprecated
public class ServerMethodPoolFactory extends BasePooledObjectFactory<FastMethod> {
    private FastMethod fastMethod;

    private ServerMethodPoolFactory(){}

    public ServerMethodPoolFactory(FastMethod _fastMethod){
        this.fastMethod=_fastMethod;
    }

    @Override
    public FastMethod create() throws Exception {
        if(null==this.fastMethod){
            throw new NullPointerException();
        }
        return this.fastMethod;
    }

    @Override
    public PooledObject<FastMethod> wrap(FastMethod obj) {
        return new DefaultPooledObject<FastMethod>(obj);
    }
}
