package com.lj.excel.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.lj.excel.demo")
@Configuration
@EnableAspectJAutoProxy//1、支持aspectJ的风格
public class AppConfig {
}
