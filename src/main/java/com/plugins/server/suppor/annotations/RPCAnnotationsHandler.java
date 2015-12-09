package com.plugins.server.suppor.annotations;

import com.plugins.protocl.RpcRequest;

import java.lang.reflect.Method;

/**
 * Created by zhangtao on 2015/12/9.
 * 注解处理器
 */
public class RPCAnnotationsHandler {

    private Object targetObject;
    private Method method;

    public RPCAnnotationsHandler(Object targetObject,Method method){
        this.targetObject=targetObject;
        this.method=method;
    }

    public RpcRequest handler() throws Exception{
        if(null==this.targetObject || null==this.method){
            throw new NullPointerException();
        }
        RpcRequest request=new RpcRequest();
        _getBeanName(request);
        _getMethodName(request);
        return request;
    }

    /**
     * 获取类名称
     * @return
     */
    private void _getBeanName(RpcRequest request) throws Exception{
        RPCServer server=targetObject.getClass().getAnnotation(RPCServer.class);
        if(null!=server){
            request.setServerName(server.name());
        }
    }

    /**
     * 获取方法名称及版本号及方法参数类型
     * @return
     */
    private void _getMethodName(RpcRequest request) throws Exception{
        RPCServerMethod serverMethod=this.method.getAnnotation(RPCServerMethod.class);
        if(null!=serverMethod){
            request.setMethondName(serverMethod.name());
            request.setServerVersion(serverMethod.version());
        }
        request.setParamTypes(this.method.getParameterTypes());
    }


    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
