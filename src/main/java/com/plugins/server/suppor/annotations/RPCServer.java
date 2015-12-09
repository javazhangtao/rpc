package com.plugins.server.suppor.annotations;

import java.lang.annotation.*;

/**
 * Created by zhangtao on 2015/12/9.
 * 对外接口类注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RPCServer {

    String name();//接口类名称
}
