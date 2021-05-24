package com.lj.excel.xianchengchi;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：线程体部分没有使用lambda表达式
 * 资料：https://www.itzhai.com/the-executorservice-common-method-newfixedthreadpool-of-create-fixed-size-thread-pool.html
 * https://www.cnblogs.com/handsomeye/p/6225033.html
 *
 * @author: lijie
 * @date: 2021/4/15 15:40
 * @version: V1.0
 */
public class ExecutorServiceTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建一个固定大小的线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            System.out.println("创建线程" + i);
            Runnable run = new Runnable() {

                public void run() {
                    System.out.println("我是run方法"+Thread.currentThread());
                }
            };
            // 在未来某个时间执行给定的命令
            service.execute(run);
        }
        // 关闭启动线程
        service.shutdown();
        // 等待子线程结束，再继续执行下面的代码
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("all thread complete");
    }
}
