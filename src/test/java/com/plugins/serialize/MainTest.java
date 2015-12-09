package com.plugins.serialize;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangtao on 2015/12/8.
 */
public class MainTest {
    static ExecutorService service= Executors.newFixedThreadPool(100);

    @Test
    public static void main(String[] args) {
        SerualizeUtil serualizeUtil=SerualizeFactoryBean.create(SerualizeFactoryBean.SerualizeEnum.PROTOC);
        for (int i=0;i<10000;i++){
            TestBean bean=new TestBean("zhangsan"+i,"count"+i);
            service.execute(new BeanSerialize(bean,serualizeUtil));
        }
    }

    static class BeanSerialize implements Runnable{
        TestBean bean;
        SerualizeUtil util;
        public BeanSerialize(TestBean bean,SerualizeUtil util){
            this.bean=bean;
            this.util=util;
        }
        @Override
        public void run() {
            try {
                System.out.println(util.serializeBinary(bean));
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                return ;
            }
        }
    }

    static class TestBean{
        private String name;
        private String phone;

        public TestBean(String name,String phone){
            this.name=name;
            this.phone=phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

    }
}
