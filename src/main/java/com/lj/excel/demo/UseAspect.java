package com.lj.excel.demo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Aop的目的就是增强多个方法，进而增强一个类
 */

@Component//2、申明这个类是个bean，或者说将这个bean加到IOC容器中
/**
 * 什么叫一个切面：连接点、切点以及通知所在的那个类，就叫一个切面。
 */
@Aspect//3、申明一个切面
public class UseAspect {
    /**
     * springAop当中连接点的最小单位就是一个方法，每一个方法叫一个连接点，一个表达式可以表达多个切点，多个连接点的集合就是一个切点。
     * （切点就像一张表，连接点就像表中的一条条的数据，所有的数据的集合就是这张表）
     *
     * @Pointcut("execution(* com.kong.demo.dao..*.*(..))")：作用就是将com.kong.demo.dao这个包下面的所有的方法增强
     * com.kong.demo.dao这个包下面有很多类，类里面有很多方法，每一个方法就是一个连接点，这些连接点加在一起就是切点pointcut
     */
    @Pointcut("execution(* com.lj.excel.demo..*.*(..))")//4、定义一个切点。
    public void myMethod(){

    }


    /**
     * 通知有两个维度的解释：
     * 1、切入连接点的时机：就是在连接点的前、后、返回之后、抛异常织入。即在连接点的哪个时间加入这个通知（when）
     * 2、切入连接点的内容：（what）
     * 下面这个通知就是：切入连接点的时机为：after；切入连接点的内容是："============"
     */
    @After("myMethod()")//5、申明通知
    public void advice(){
        System.out.println("============");
    }
}
