package com.plugins.pools;

import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by zhangtao on 2015/12/14.
 * 池对象创建handler
 */
public class ServerMethodPoolHandler implements FactoryBean<GenericObjectPool<FastMethod>>{

    private FastMethod fastMethod;//池元素
    private ServerMethodPoolConfig config;//池配置信息

    private GenericObjectPool<FastMethod> pool;//池对象

    private ServerMethodPoolHandler(){}

    public ServerMethodPoolHandler(FastMethod _fastMethod, ServerMethodPoolConfig _config){
        this.fastMethod=_fastMethod;
        this.config=_config;
    }

    private void create() throws Exception{
        if(null==this.fastMethod){
            throw new NullPointerException();
        }
        try {
            if(this.config!=null){
                GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
                poolConfig.setMinIdle(this.config.getMinIdle());
                poolConfig.setMaxIdle(this.config.getMaxIdle());
                poolConfig.setMinEvictableIdleTimeMillis(this.config.getMinEvictableIdleTimeMillis());
                this.pool=new GenericObjectPool(new ServerMethodPoolFactory(this.fastMethod),poolConfig);
            }else{
                this.pool=new GenericObjectPool(new ServerMethodPoolFactory(this.fastMethod));
            }
        } catch (Exception e) {
            throw(e);
        }
    }

    @Override
    public GenericObjectPool<FastMethod> getObject() throws Exception {
        if(null==this.pool){
            create();
        }
        return this.pool;
    }

    @Override
    public Class<?> getObjectType() {
        return GenericObjectPool.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}