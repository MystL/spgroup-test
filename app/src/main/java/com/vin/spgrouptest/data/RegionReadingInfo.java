package com.vin.spgrouptest.data;

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
        return regionReadings.get("psi_twenty_four_hourly");
    }

    public double getO3SubIndex(){
        return regionReadings.get("o3_sub_index");
    }

    public double getCoSubIndex(){
        return regionReadings.get("co_sub_index");
    }

    public double getSo2SubIndex(){
        return regionReadings.get("so2_sub_index");
    }

    public double getPm10SubIndex(){
        return regionReadings.get("pm10_sub_index");
    }

    public double getPm25SubIndex(){
        return regionReadings.get("pm25_sub_index");
    }

    public double getNo2OneHour(){
        return regionReadings.get("no2_one_hour_max");
    }

    public double getO3EightHour(){
        return regionReadings.get("o3_eight_hour_max");
    }

    public double getCoEightHour(){
        return regionReadings.get("co_eight_hour_max");
    }

    public double getSo2EightHour(){
        return regionReadings.get("so2_twenty_four_hourly");
    }

    public double getPm10TwentyFourHour(){
        return regionReadings.get("pm10_twenty_four_hourly");
    }

    public double getPm25TwentyFourHour(){
        return regionReadings.get("pm25_twenty_four_hourly");
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
