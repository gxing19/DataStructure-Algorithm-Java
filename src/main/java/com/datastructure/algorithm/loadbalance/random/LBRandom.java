package com.datastructure.algorithm.loadbalance.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LBRandom {

    public static void main(String[] args) {
        List<String> serverList = new ArrayList<>();
        serverList.add("192.168.1.1");
        serverList.add("192.168.1.2");
        serverList.add("192.168.1.3");

        for (int i = 0; i < 8; i++) {
            //生成随机数做为索引获取目标
            Random random = new Random();
            int index = random.nextInt(serverList.size());
            String serverIP = serverList.get(index);
            System.out.println(serverIP);
        }

    }
}
