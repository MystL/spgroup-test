package com.vin.spgrouptest.data;

import com.vin.spgrouptest.CommonConstants;
import com.vin.spgrouptest.Tuple2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegionReadingInfo implements Validatable{

    private RegionMetadata regionMetadata;
    private Map<String, Double> regionReadings = new HashMap<>();
    private Tuple2<String, Map<String, Double>> tuple2;
    private String updatedTimeStamp;

    public RegionReadingInfo(RegionMetadata regionMetadata, Tuple2<String, Map<String, Double>> tuple2){
        if(regionMetadata == null || tuple2 == null){
            throw new IllegalArgumentException("cannot init RegionReadingInfo with null params");
        }
        this.updatedTimeStamp = tuple2.getA();
        this.regionMetadata = regionMetadata;
        this.regionReadings.clear();
        this.regionReadings.putAll(tuple2.getB());
    }

    public RegionMetadata getRegionMetadata() {
        return regionMetadata;
    }

    public String getUpdatedTimestampString(){
        return updatedTimeStamp;
    }

    public Map<String, Double> getRegionReadings() {
        return regionReadings;
    }

    public double getPsi24Hourly(){
        return regionReadings.get(CommonConstants.PSI_24HR_KEY);
    }

    public double getO3SubIndex(){
        return regionReadings.get(CommonConstants.O3_INDEX_KEY);
    }

    public double getCoSubIndex(){
        return regionReadings.get(CommonConstants.CO_INDEX_KEY);
    }

    public double getSo2SubIndex(){
        return regionReadings.get(CommonConstants.SO2_INDEX_KEY);
    }

    public double getPm10SubIndex(){
        return regionReadings.get(CommonConstants.PM10_INDEX_KEY);
    }

    public double getPm25SubIndex(){
        return regionReadings.get(CommonConstants.PM25_INDEX_KEY);
    }

    public double getNo2OneHour(){
        return regionReadings.get(CommonConstants.NO2_1HR_KEY);
    }

    public double getO3EightHour(){
        return regionReadings.get(CommonConstants.O3_8HR_KEY);
    }

    public double getCoEightHour(){
        return regionReadings.get(CommonConstants.CO_8HR_KEY);
    }

    public double getSo2TwentyFourHour(){
        return regionReadings.get(CommonConstants.SO2_24HR_KEY);
    }

    public double getPm10TwentyFourHour(){
        return regionReadings.get(CommonConstants.PM10_24HR_KEY);
    }

    public double getPm25TwentyFourHour(){
        return regionReadings.get(CommonConstants.PM25_24HR_KEY);
    }

    @Override
    public boolean isValid() {
        if(regionReadings.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionReadingInfo info = (RegionReadingInfo) o;
        return Objects.equals(regionMetadata, info.regionMetadata) &&
                Objects.equals(regionReadings, info.regionReadings);
    }

    @Override
    public int hashCode() {

        return Objects.hash(regionMetadata, regionReadings);
    }
}
