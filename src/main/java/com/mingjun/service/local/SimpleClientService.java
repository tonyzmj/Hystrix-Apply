package com.mingjun.service.local;

import com.mingjun.service.command.NetworkServiceCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by mingjun on 16/5/21.
 */
@Service
public class SimpleClientService {

    public String getNetworkResource(String url) {
        try {
            NetworkServiceCommand command = new NetworkServiceCommand(url);
            Future<String> future = command.queue();
            //return future.get(20, TimeUnit.MILLISECONDS);
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
