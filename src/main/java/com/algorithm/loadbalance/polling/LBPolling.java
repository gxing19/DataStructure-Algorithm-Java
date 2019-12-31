package com.algorithm.loadbalance.polling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LBPolling {

    private static AtomicInteger index = new AtomicInteger();

    public static void main(String[] args) {
        List<String> serverList = new ArrayList<>();
        serverList.add("192.168.1.1");
        serverList.add("192.168.1.2");
        serverList.add("192.168.1.3");

        for (int i = 0; i < 10; i++) {

            if(index.get() == serverList.size()){
                index.set(0);
            }

            String server = serverList.get(index.get());
            index.set(index.get() + 1);
            System.out.println(server);
        }
    }
}
