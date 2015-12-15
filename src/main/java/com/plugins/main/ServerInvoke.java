package com.plugins.main;

import com.google.common.base.Strings;
import com.plugins.protocl.RpcRequest;
import com.plugins.protocl.RpcResponse;
import com.plugins.server.suppor.resource.ServerResource;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by zhangtao on 2015/12/15.
 * 接口调用工具类
 */
@Component("serverInvoke")
@Scope("prototype")
public class ServerInvoke implements ApplicationContextAware{

    private ApplicationContext context;

    /**
     * 方法执行，返回response
     * @param request
     * @return
     * @throws Exception
     */
    public RpcResponse invoke(final RpcRequest request) throws Exception{
        RpcResponse response=new RpcResponse();
        FastClass fastClass=null;
        try {
            fastClass=getFastClass(request.getServerName());
            if(null==fastClass){
                response.setCode(ServerResource.responsecode_prop.getProperty("NOTFOUNT"));
                response.setMessage("not found server");
                return response;
            }
            FastMethod method=createMethod(fastClass,request.getMethondName(),request.getParamTypes());
            if(null==fastClass){
                response.setCode(ServerResource.responsecode_prop.getProperty("SERVERDISABLED"));
                response.setMessage("not found server's method ");
                return response;
            }
            Object _result=method.invoke(this.context.getBean(request.getServerName()),request.getParams());
            response.setRequestId(request.getRequestId());
            response.setCode(ServerResource.responsecode_prop.getProperty("SUCCESS"));
            response.setData((null==_result)?null:_result);
            return response;
        } catch (Exception e) {
            response.setCode(ServerResource.responsecode_prop.getProperty("ERROR"));
            response.setError(e);
            throw(e);
        }finally {
            returnFastClass(request.getServerName(),fastClass);
            return response;
        }
    }

    /**
     * 对象池内获取动态类对象
     * @param targetName
     * @return
     * @throws Exception
     */
    private FastClass getFastClass(final String targetName) throws Exception{
        if(Strings.isNullOrEmpty(targetName)){
            throw new NullPointerException();
        }
        FastClass fastClass=ServerResource.serverPool.borrowObject(targetName);
        if(null!=fastClass){
            return fastClass;
        }else{
            throw new NullPointerException("not found server ");
        }
    }

    /**
     * 创建动态方法
     * @param fastClass 代理类
     * @param methodName    调用方法名
     * @param paramTypes    参数类型
     * @return
     * @throws Exception
     */
    private FastMethod createMethod(final FastClass fastClass , final String methodName , final Class<?>[] paramTypes) throws Exception{
        if(null==fastClass){
            throw new NullPointerException("not found server ");
        }
        try {
            FastMethod method= fastClass.getMethod(methodName,paramTypes);
            if(null!=method){
                return method;
            }else{
                throw new NullPointerException("not found method ");
            }
        } catch (Exception e) {
           throw(e);
        }
    }

    /**
     * 代理类归还池
     * @param targetName
     * @param fastClass
     * @throws Exception
     */
    private void returnFastClass(String targetName , final FastClass fastClass) throws Exception{
        synchronized (ServerResource.serverPool){
            try {
                ServerResource.serverPool.returnObject(targetName,fastClass);
            } catch (IllegalStateException e) {}
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
}
