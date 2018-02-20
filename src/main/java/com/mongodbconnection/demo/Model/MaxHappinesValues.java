package com.mongodbconnection.demo.Model;

public class MaxHappinesValues {
    private double happinesValue;
    private int maxHappinesValueId;
    private String maxHappinesValueUrl;
    private CognitiveServiceMediaStatus cognitiveServiceMediaStatus;

    public MaxHappinesValues(double happinesValue, int maxHappinesValueId, String maxHappinesValueUrl, CognitiveServiceMediaStatus cognitiveServiceMediaStatus) {
        this.happinesValue = happinesValue;
        this.maxHappinesValueId = maxHappinesValueId;
        this.maxHappinesValueUrl = maxHappinesValueUrl;
        this.cognitiveServiceMediaStatus = cognitiveServiceMediaStatus;
    }

    public CognitiveServiceMediaStatus getCognitiveServiceMediaStatus() {
        return cognitiveServiceMediaStatus;
    }

    public void setCognitiveServiceMediaStatus(CognitiveServiceMediaStatus cognitiveServiceMediaStatus) {
        this.cognitiveServiceMediaStatus = cognitiveServiceMediaStatus;
    }

    public MaxHappinesValues() {
    }

    public double getHappinesValue() {
        return happinesValue;
    }

    public void setHappinesValue(double happinesValue) {
        this.happinesValue = happinesValue;
    }

    public int getMaxHappinesValueId() {
        return maxHappinesValueId;
    }

    public void setMaxHappinesValueId(int maxHappinesValueId) {
        this.maxHappinesValueId = maxHappinesValueId;
    }

    public String getMaxHappinesValueUrl() {
        return maxHappinesValueUrl;
    }

    public void setMaxHappinesValueUrl(String maxHappinesValueUrl) {
        this.maxHappinesValueUrl = maxHappinesValueUrl;
    }


}
