package com.plugins.serialize;

import com.plugins.server.suppor.resource.ServerResource;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangtao on 2015/12/8.
 */
public class MainTest {
    static ExecutorService service= Executors.newFixedThreadPool(50);

    @Test
    public static void main(String[] args) {
        ApplicationContext context= new ClassPathXmlApplicationContext("/spring/context.xml");
        try {
            FastClass fastClass= ServerResource.serverPool.borrowObject("ccccc");
            FastMethod method=fastClass.getMethod("ceshi",null);
            method.invoke(context.getBean("ccccc"),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (int i=0;i<=1000000;i++){
//            service.execute(new TestCeShi(context,""+i));
//        }
//        try {
//
//            } catch (Exception e) {
//                e.printStackTrace();
//        }

//        TestService ts=new TestServiceImpl();
//        ts.ceshi();
//        System.out.println("aaa");
//        System.out.println("aaa");
//        SerualizeUtil serualizeUtil=SerualizeFactoryBean.create(SerualizeFactoryBean.SerualizeEnum.PROTOC);
//        for (int i=0;i<10000;i++){
//            TestBean bean=new TestBean("zhangsan"+i,"count"+i);
//            service.execute(new BeanSerialize(bean,serualizeUtil));
//        }
    }


//    static class TestCeShi implements Runnable{
//
//        ApplicationContext context;
//        String str;
//        public TestCeShi(ApplicationContext context,String str){
//            this.context=context;
//            this.str=str;
//        }
//        @Override
//        public void run() {
//            FastClass serverClass=null;
//            try {
//                serverClass = ServerResource.serverPools.get("ccccc").borrowObject();
//                method.invoke(context.getBean("ccccc"),new Object[]{str});
////                Thread.sleep(60*1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            finally {
//                try {
//                    ServerResource.serverPools.get("ccccc_ceshi_1.0").returnObject(method);
//                }catch (IllegalStateException e){
//
//                }
//            }
//        }
//    }

//    static class BeanSerialize implements Runnable{
//        TestBean bean;
//        SerualizeUtil util;
//        public BeanSerialize(TestBean bean,SerualizeUtil util){
//            this.bean=bean;
//            this.util=util;
//        }
//        @Override
//        public void run() {
//            try {
//                System.out.println(util.serializeBinary(bean));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally{
//                return ;
//            }
//        }
//    }
//
//    static class TestBean{
//        private String name;
//        private String phone;
//
//        public TestBean(String name,String phone){
//            this.name=name;
//            this.phone=phone;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//    }
}
