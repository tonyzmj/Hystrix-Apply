package com.mingjun.util;

import com.netflix.hystrix.HystrixCommand;

/**
 * Created by mingjun on 16/5/21.
 */
public abstract class SupportHystrixCommand<T> extends HystrixCommand<T> {

    protected SupportHystrixCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected T run() throws Exception {
        try {
            return subRun();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract T subRun();
}
