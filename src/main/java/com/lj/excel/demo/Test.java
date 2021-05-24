package com.lj.excel.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDao bean = ac.getBean(UserDao.class);
        bean.query();
    }
}
