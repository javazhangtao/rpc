package com.plugins.server.suppor.annotations;

import java.lang.annotation.*;

/**
 * Created by zhangtao on 2015/12/9.
 * 接口类方法注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface RPCServerMethod {
    String version();//版本号
}
