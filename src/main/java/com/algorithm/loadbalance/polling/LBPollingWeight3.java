package com.algorithm.loadbalance.polling;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LBPollingWeight3 {

    private static AtomicInteger index = new AtomicInteger();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String server = getServer();
            System.out.println(server);
        }

    }

    public static String getServer() {
        Map<String, SmoothWeightServer> serverMap = new TreeMap<>(SmoothServerManager.serverMap);

        /// 原始权重之和
        Integer weightSum = 0;
        /// 最大当前权重对象
        SmoothWeightServer maxWeightServer = null;

        /// 计算最大当前权重对象，同时求原始权重之和
        Iterator<String> iterator = serverMap.keySet().iterator();
        while (iterator.hasNext()){
            SmoothWeightServer smoothWeightServer = serverMap.get(iterator.next());
            if(smoothWeightServer != null){
                weightSum += smoothWeightServer.getOriginalWeight();
                if(maxWeightServer == null){
                    maxWeightServer = smoothWeightServer;
                }
                if(smoothWeightServer.getCurrentWeight() > maxWeightServer.getCurrentWeight()){
                    maxWeightServer = smoothWeightServer;
                }
            }
        }

        /**
         * 重新调整 currentWeight 权重：
         * maxWeightServer.currentWeight -= weightSum
         * 每个 smoothWeightServer.currentWeight += smoothWeightServer.originalWeight
         */
        if(maxWeightServer == null){
            return null;
        }
        maxWeightServer.setCurrentWeight(maxWeightServer.getCurrentWeight() - weightSum);

        iterator = serverMap.keySet().iterator();
        while (iterator.hasNext()){
            SmoothWeightServer smoothWeightServer = serverMap.get(iterator.next());
            if(smoothWeightServer != null){
                smoothWeightServer.setCurrentWeight(smoothWeightServer.getCurrentWeight() + smoothWeightServer.getOriginalWeight());
            }
        }

        return maxWeightServer.getServer();
    }

}
