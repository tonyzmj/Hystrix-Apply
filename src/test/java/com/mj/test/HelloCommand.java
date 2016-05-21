package com.mj.test;

import com.netflix.hystrix.*;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by mingjun on 16/5/21.
 * simple test case
 */
public class HelloCommand extends HystrixCommand<String> {

    private static String commandKey = "Hello-Command";
    private static int timeout = 3000;
    private static int threadPoolSize = 3;
    private static Setter setter;
    private String name;

    static {
        setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(commandKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(
                                timeout))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(threadPoolSize));
    }

    protected HelloCommand(String name) {
        super(setter);
        this.name = name;
    }

    /**
     * 测试以下两个超时
     *
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        System.out.println("Hi name: " + name + " the current threadName: " + Thread.currentThread().getName());
        Thread.sleep(2000);
        //Thread.sleep(4000);
        return "HystrixCommand done, this time:" + new Date();
    }

    public static void main(String[] args) {
        try {
            //异步调用，可使用Future对应取值超时时间
            HelloCommand asyCommand = new HelloCommand("asynchronous_hystrix_test");
            Future<String> futureResult = asyCommand.queue();
            //同步调用，使用默认的timeout值
            HelloCommand synCommand = new HelloCommand("synchronous_hystrix_test");
            String result = futureResult.get(5000, TimeUnit.MILLISECONDS);
            System.out.println("result=" + result);
            result = synCommand.execute();
            System.out.println("result=" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
