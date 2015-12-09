package com.plugins.server.suppor.annotations;

import java.lang.annotation.*;

/**
 * Created by zhangtao on 2015/12/9.
 * 接口类方法注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RPCServerMethod {

    String name();
    String version();
}
