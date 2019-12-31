package com.algorithm.loadbalance.polling;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LBPollingWeight1 {

    private static AtomicInteger index = new AtomicInteger();

    public static void main(String[] args) {

        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.put("192.168.1.1", 2);
        serverMap.put("192.168.1.2", 6);
        serverMap.put("192.168.1.3", 10);

        int allWeight = serverMap.values().stream().mapToInt(score -> score).sum();
        for (int i = 0; i < 20; i++) {
            String server = serverKey(serverMap, allWeight);
            System.out.println(server);

        }
    }

    private static String serverKey(Map<String, Integer> serverMap, int allWeight) {
        int idx = (index.get() + 1) % allWeight;
        for (Map.Entry<String, Integer> entry : serverMap.entrySet()) {
            if (entry.getValue() >= idx) {
                index.set(index.get() + 1);
                return entry.getKey();
            }
            idx = idx - entry.getValue();
        }
        return "";
    }
}
