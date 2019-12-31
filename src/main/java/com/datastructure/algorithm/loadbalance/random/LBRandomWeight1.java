package com.datastructure.algorithm.loadbalance.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LBRandomWeight1 {

    public static void main(String[] args) {

        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.put("192.168.1.1", 2);
        serverMap.put("192.168.1.2", 6);
        serverMap.put("192.168.1.3", 10);
        int sumScore = serverMap.values().stream().mapToInt(score -> score).sum();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String server = serverKey(serverMap, random, sumScore);
            System.out.println(server);

        }
    }

    private static String serverKey(Map<String, Integer> serverMap, Random random, int sumScore) {
        int score = random.nextInt(sumScore);
        for (Map.Entry<String, Integer> entry : serverMap.entrySet()) {
            if (entry.getValue() >= score) {
                return entry.getKey();
            }
            score = score - entry.getValue();
        }
        return "";
    }
}
