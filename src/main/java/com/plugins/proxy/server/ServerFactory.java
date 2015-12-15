package com.plugins.proxy.server;

import com.google.common.base.Strings;
import net.sf.cglib.reflect.FastClass;
import org.springframework.context.ApplicationContext;

/**
 * Created by zhangtao on 2015/12/15.
 * 动态方法创建工厂
 */
public class ServerFactory {
    private ApplicationContext context;//当前上下文对象
    private String targetName;//目标对象服务名称

    private ServerFactory(){}

    public ServerFactory(ApplicationContext _context,String _targetName){
        this.context=_context;
        this.targetName=_targetName;
    }


    public FastClass getObject() throws Exception{
        if(Strings.isNullOrEmpty(this.targetName) || null==this.context){
            throw new NullPointerException();
        }
        return create();
    }

    private FastClass create() throws Exception{
        try {
            return FastClass.create(this.context.getBean(this.targetName).getClass());
        } catch (Exception e) {
            throw(e);
        }
    }
}
