package com.plugins.proxy.server;

import com.google.common.base.Strings;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by zhangtao on 2015/12/14.
 * 动态方法创建工厂
 */
@Deprecated
public class ServerMethodFactory implements FactoryBean<FastMethod>{
    private Object targetClass;//方法所在类对象
    private String methodName;//方法名
    private Class<?>[] paramTypes;//方法参数类型数组

    private FastMethod fastMethod;//动态方法对象

    private ServerMethodFactory(){}

    public ServerMethodFactory(Object _targetClass , String _methodName , Class<?>[] _paramTypes){
        this.targetClass=_targetClass;
        this.methodName=_methodName;
        this.paramTypes=_paramTypes;
    }

    private void create() throws Exception{
        if(null==this.targetClass || Strings.isNullOrEmpty(this.methodName)){
            throw new NullPointerException();
        }
        try {
            FastClass fastClass=FastClass.create(targetClass.getClass());
            this.fastMethod=fastClass.getMethod(this.methodName,this.paramTypes);
        } catch (Exception e) {
            throw(e);
        }
    }

    @Override
    public FastMethod getObject() throws Exception {
        if(null==this.fastMethod){
            create();
        }
        return this.fastMethod;
    }

    @Override
    public Class<?> getObjectType() {
        return FastMethod.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
