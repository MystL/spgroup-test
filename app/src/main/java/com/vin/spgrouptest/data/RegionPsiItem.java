package com.vin.spgrouptest.data;

import java.util.Map;
import java.util.Objects;

public class RegionPsiItem extends PsiMapReading {

    private String timestamp;
    private String updateTimeStamp;
    private Map<String, Double> readings;

    public RegionPsiItem(String timestamp, String updateTimeStamp, Map<String, Double> readings) {
        super(readings);
        this.timestamp = timestamp;
        this.updateTimeStamp = updateTimeStamp;
        this.readings = readings;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public Map<String, Double> getReadings() {
        return readings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionPsiItem that = (RegionPsiItem) o;
        return Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(updateTimeStamp, that.updateTimeStamp) &&
                Objects.equals(readings, that.readings);
    }

    @Override
    public int hashCode() {

        return Objects.hash(timestamp, updateTimeStamp, readings);
    }
}
