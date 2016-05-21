package com.mingjun.util;

import com.netflix.hystrix.*;

/**
 * Created by mingjun on 16/5/21.
 * 生成服务setter，不做线程资源分组隔离
 * 可参考文档：http://hot66hot.iteye.com/blog/2155036
 */
public class HystrixConfig {

    public static SupportHystrixCommand.Setter getHystrixSetter(String commandGroupKey, String commandKey,
                                                         int timeout, int threadCoreNum) {
        return SupportHystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(commandGroupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeout))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(threadCoreNum));
    }


}
