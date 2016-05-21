package com.mingjun.service.hystrix;

import com.mingjun.service.remote.NetworkService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by mingjun on 16/5/21.
 */
@Service
public class HystrixServiceFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static NetworkService getNetworkServiceClient() {
        return (NetworkService) applicationContext.getBean("networkService");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
