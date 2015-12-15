package com.service.impl;

import com.plugins.server.suppor.annotations.RPCServer;
import com.plugins.server.suppor.annotations.RPCServerMethod;
import com.service.TestService;

/**
 * Created by zhangtao on 2015/12/9.
 */
@RPCServer(name="ccccc")
public class TestServiceImpl implements TestService {
    @Override
    @RPCServerMethod(version = "1.0")
    public void ceshi() {
        System.out.println("11111111111");
    }

    @Override
    @RPCServerMethod(version = "1.0")
    public String ceshi(String str) {
        return str;
    }
}
