package com.mingjun.service.command;

import com.mingjun.service.hystrix.HystrixServiceFactory;
import com.mingjun.util.HystrixConfig;
import com.mingjun.util.SupportHystrixCommand;
import org.apache.commons.lang.StringUtils;

/**
 * Created by mingjun on 16/5/21.
 */
public class NetworkServiceCommand extends SupportHystrixCommand<String> {

    private String url;

    public NetworkServiceCommand(String url) {
        super(HystrixConfig.getHystrixSetter("networkService", "getNetworkResources", 20000, 10));
        this.url = url;
    }

    @Override
    public String subRun() {
        return HystrixServiceFactory.getNetworkServiceClient().getNetworkResources(url);
    }

    @Override
    protected String getFallback() {
        return StringUtils.EMPTY;
    }
}
