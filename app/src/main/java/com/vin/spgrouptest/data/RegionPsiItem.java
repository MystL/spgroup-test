package com.vin.spgrouptest.data;

import com.vin.spgrouptest.CommonConstants;

import java.util.Map;
import java.util.Objects;

public class RegionPsiItem {

    private String timestamp;
    private String updateTimeStamp;
    private Map<String, Double> readings;

    public RegionPsiItem(String timestamp, String updateTimeStamp, Map<String, Double> readings){
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

    public double getPsi24Hourly(){
        return readings.get(CommonConstants.PSI_24HR_KEY);
    }

    public double getO3SubIndex(){
        return readings.get(CommonConstants.O3_INDEX_KEY);
    }

    public double getCoSubIndex(){
        return readings.get(CommonConstants.CO_INDEX_KEY);
    }

    public double getSo2SubIndex(){
        return readings.get(CommonConstants.SO2_INDEX_KEY);
    }

    public double getPm10SubIndex(){
        return readings.get(CommonConstants.PM10_INDEX_KEY);
    }

    public double getPm25SubIndex(){
        return readings.get(CommonConstants.PM25_INDEX_KEY);
    }

    public double getNo2OneHour(){
        return readings.get(CommonConstants.NO2_1HR_KEY);
    }

    public double getO3EightHour(){
        return readings.get(CommonConstants.O3_8HR_KEY);
    }

    public double getCoEightHour(){
        return readings.get(CommonConstants.CO_8HR_KEY);
    }

    public double getSo2TwentyFourHour(){
        return readings.get(CommonConstants.SO2_24HR_KEY);
    }

    public double getPm10TwentyFourHour(){
        return readings.get(CommonConstants.PM10_24HR_KEY);
    }

    public double getPm25TwentyFourHour(){
        return readings.get(CommonConstants.PM25_24HR_KEY);
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
