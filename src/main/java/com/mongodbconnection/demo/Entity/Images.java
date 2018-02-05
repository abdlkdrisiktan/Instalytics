package com.mongodbconnection.demo.Entity;

public class Images {
    private LowResolution low_resolution;

    public Images(LowResolution low_resolution) {
        this.low_resolution = low_resolution;
    }

    public LowResolution getLow_resolution() {
        return low_resolution;
    }

    public void setLow_resolution(LowResolution low_resolution) {
        this.low_resolution = low_resolution;
    }

    public Images() {
    }
}
