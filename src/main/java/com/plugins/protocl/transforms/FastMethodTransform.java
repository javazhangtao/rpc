package com.plugins.protocl.transforms;

import com.plugins.protocl.RpcRequest;
import com.plugins.proxy.server.ServerMethodFactory;
import net.sf.cglib.reflect.FastMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by zhangtao on 2015/12/14.
 * 动态方法转换工具类
 */
public class FastMethodTransform implements FactoryBean<FastMethod> ,ApplicationContextAware{

    private ApplicationContext context;//当前上下文环境
    private RpcRequest request;//接口类及方法描述对象
    private FastMethod fastMethod;//动态方法对象

    private FastMethodTransform(){}

    public FastMethodTransform(RpcRequest _request){
        this.request=_request;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(null==this.context){
            this.context=applicationContext;
        }
    }

    private void transform() throws Exception{
        if(null==this.request){
            throw new NullPointerException();
        }
        try {
            Object targetBean=this.context.getBean(this.request.getServerName());
            this.fastMethod=new ServerMethodFactory(targetBean,this.request.getMethondName(),this.request.getParamTypes()).getObject();
        } catch (Exception e) {
            throw(e);
        }
    }

    @Override
    public FastMethod getObject() throws Exception {
        if(null==this.fastMethod){
            transform();
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
