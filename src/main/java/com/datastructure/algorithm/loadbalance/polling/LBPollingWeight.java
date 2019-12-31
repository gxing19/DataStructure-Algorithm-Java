package com.datastructure.algorithm.loadbalance.polling;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LBPollingWeight {

    private static AtomicInteger index = new AtomicInteger();

    public static void main(String[] args) {
        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.put("192.168.1.1", 2);
        serverMap.put("192.168.1.2", 6);
        serverMap.put("192.168.1.3", 10);

        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }

        for (int i = 0; i < 20; i++) {
            if (index.get() == serverList.size()) {
                index.set(0);
            }
            String server = serverList.get(index.get());
            index.set(index.get() + 1);

            System.out.println(server);
        }

    }
}
