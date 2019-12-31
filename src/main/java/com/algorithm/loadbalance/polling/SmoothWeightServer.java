package com.algorithm.loadbalance.polling;

public class SmoothWeightServer {

    private static final long serialVersionUID = 7246747589293111189L;

    private String server;
    private Integer originalWeight;
    private Integer currentWeight;

    public SmoothWeightServer(String server, Integer originalWeight, Integer currentWeight){
        this.server = server;
        this.originalWeight = originalWeight;
        this.currentWeight = currentWeight;
    }
    public Integer getOriginalWeight() {
        return originalWeight;
    }

    public void setOriginalWeight(Integer originalWeight) {
        this.originalWeight = originalWeight;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return "SmoothWeightServer{" +
                "server='" + server + '\'' +
                ", originalWeight=" + originalWeight +
                ", currentWeight=" + currentWeight +
                '}';
    }
}
