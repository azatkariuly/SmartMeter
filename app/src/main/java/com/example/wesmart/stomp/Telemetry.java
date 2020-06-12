package com.example.wesmart.stomp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Telemetry {
    @Expose
    @SerializedName(value = "sensorId")
    private Long sensorId;

    @Expose
    @SerializedName(value = "current")
    private Double current;

    @Expose
    @SerializedName(value = "voltage")
    private Double voltage;

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }
}
