package com.mingjun.test;

import com.mingjun.service.local.SimpleClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mingjun on 16/5/21.
 */
public class NetworkServiceTest extends AbstractTest {

    @Autowired
    public SimpleClientService simpleClientService;

    @Test
    public void testQueryUrl() {
        String result = simpleClientService.getNetworkResource("http://tech.ifeng.com");
        System.out.println("====>: " + result);
    }

}
