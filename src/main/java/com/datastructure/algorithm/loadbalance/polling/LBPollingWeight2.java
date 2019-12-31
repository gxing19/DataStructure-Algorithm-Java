package com.datastructure.algorithm.loadbalance.polling;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 动态计算权重占比
 */
public class LBPollingWeight2 {

    private static AtomicInteger index = new AtomicInteger();

    public static void main(String[] args) {

        Map<String, Integer> serverMap = new HashMap<>();
        serverMap.put("192.168.1.1", 2);
        serverMap.put("192.168.1.2", 6);
        serverMap.put("192.168.1.3", 10);

        //总权重
        int allWeight = serverMap.values().stream().mapToInt(weight -> weight).sum();
        //初始权重比
        Map<String, Float> initWeightMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : serverMap.entrySet()) {
            float rate = (float) entry.getValue() / allWeight;
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数
            rate = Float.valueOf(df.format(rate));
            initWeightMap.put(entry.getKey(), rate);
        }

        List<String> serverList = serverMap.keySet().stream().collect(Collectors.toList());
        //当前权重
        Map<String, Integer> currentWeightMap = new HashMap<>();

        for (int i = 0; i < 18; i++) {
            String server = getServer(initWeightMap, serverList, currentWeightMap);
            System.out.println(server);
        }
    }

    private static String getServer(Map<String, Float> initWeightMap, List<String> serverList, Map<String, Integer> currentWeightMap) {
        if (index.get() == serverList.size()) {
            index.set(0);
        }

        for (String server : serverList) {
            if (!currentWeightMap.containsKey(server)) {
                index.set(index.get() + 1);
                currentWeightMap.put(server, 1);
                return server;
            } else {
                int num = currentWeightMap.get(server);
                int total = currentWeightMap.values().stream().mapToInt(times -> times).sum();
                float rate = (float) num / total;
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                rate = Float.valueOf(df.format(rate));
                if (rate <= initWeightMap.get(server)) {
                    index.set(index.get() + 1);
                    currentWeightMap.put(server, currentWeightMap.get(server) + 1);
                    return server;
                }
            }

        }
        return null;
    }
}
