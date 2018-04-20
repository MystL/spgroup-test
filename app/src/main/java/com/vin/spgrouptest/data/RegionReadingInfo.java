package com.vin.spgrouptest.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegionReadingInfo implements Validatable{

    private RegionMetadata regionMetadata;
    private Map<String, Double> regionReadings = new HashMap<>();

    public RegionReadingInfo(RegionMetadata regionMetadata, Map<String, Double> regionReadings){
        if(regionMetadata == null || regionReadings == null){
            throw new IllegalArgumentException("cannot init RegionReadingInfo with null params");
        }
        this.regionMetadata = regionMetadata;
        this.regionReadings.clear();
        this.regionReadings.putAll(regionReadings);
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
