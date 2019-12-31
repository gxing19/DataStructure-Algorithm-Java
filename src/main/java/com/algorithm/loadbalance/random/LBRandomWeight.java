package com.algorithm.loadbalance.random;

import java.util.*;

public class LBRandomWeight {

    public static void main(String[] args) {
        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.put("192.168.1.1", 1);
        serverMap.put("192.168.1.2", 3);
        serverMap.put("192.168.1.3", 5);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++){
                serverList.add(server);
            }
        }

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(serverList.size());
            String server = serverList.get(index);
            System.out.println(server);
        }
    }
}
