package com.plugins.serialize;

import com.plugins.main.ServerInvoke;
import com.plugins.protocl.RpcRequest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangtao on 2015/12/8.
 */
public class MainTest {
    static ExecutorService service= Executors.newFixedThreadPool(500);

    @Test
    public static void main(String[] args) {
        ApplicationContext context= new ClassPathXmlApplicationContext("/spring/context.xml");
        try {
            for (int i=0;i<1000000;i++){
                RpcRequest request=new RpcRequest();
                request.setRequestId(i+"");
                request.setServerName("ccccc");
                request.setMethondName("ceshi");
                request.setParams(new Object[]{"_"+i});
                request.setParamTypes(new Class<?>[]{String.class});
                service.execute(new TestCeShi(context,request));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class TestCeShi implements Runnable{

        ApplicationContext context;
        private RpcRequest request;
        String str;
        public TestCeShi(ApplicationContext context,RpcRequest request){
            this.context=context;
            this.request=request;
        }
        @Override
        public void run() {
            try {
                ServerInvoke invoke=(ServerInvoke)this.context.getBean("serverInvoke");
                System.out.println(invoke.invoke(this.request).getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
