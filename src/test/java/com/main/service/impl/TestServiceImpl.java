package com.main.service.impl;

import com.main.service.TestService;
import com.plugins.server.suppor.annotations.RPCServer;
import com.plugins.server.suppor.annotations.RPCServerMethod;

/**
 * Created by zhangtao on 2015/12/9.
 */
@RPCServer(name="ccccc")
public class TestServiceImpl implements TestService {
    @Override
    @RPCServerMethod(name = "1231",version = "1.0")
    public void ceshi() {
        System.out.println("11111111111");
    }
}
