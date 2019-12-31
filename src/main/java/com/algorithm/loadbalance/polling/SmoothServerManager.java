package com.algorithm.loadbalance.polling;

import java.util.Map;
import java.util.TreeMap;

public class SmoothServerManager {

    public volatile static Map<String, SmoothWeightServer> serverMap = new TreeMap<>();

    static {
        serverMap.put("192.168.1.1", new SmoothWeightServer("192.168.1.1",1,2));
        serverMap.put("192.168.1.2", new SmoothWeightServer("192.168.1.2",2,6));
        serverMap.put("192.168.1.3", new SmoothWeightServer("192.168.1.3",3,10));
    }
}
