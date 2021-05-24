package com.lj.excel.xianchengchi;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 功能描述：这个里面使用了lambda表达式。
 * 资料：https://www.itzhai.com/the-executorservice-common-method-newfixedthreadpool-of-create-fixed-size-thread-pool.html
 * https://www.cnblogs.com/handsomeye/p/6225033.html
 * 线程池的submit和execute方法区别
 * 1、接收的参数不一样
 * 2、submit有返回值，而execute没有
 * 3、submit方便Exception处理
 *
 * @author: lijie
 * @date: 2021/4/15 15:40
 * @version: V1.0
 */
public class ExecutorServiceTest2 {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        // 创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            System.out.println("i的值是：" + i);
            Future future = service.submit(() ->{
                System.out.println("我是run方法"+Thread.currentThread());
                return "aa";
            });
            Object o = future.get();
            System.out.println(o);
        }

        // 关闭启动线程
        service.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("all thread complete");
    }
}
