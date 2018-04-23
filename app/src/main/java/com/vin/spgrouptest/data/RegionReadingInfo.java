package com.vin.spgrouptest.data;

import com.vin.spgrouptest.Tuple2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegionReadingInfo extends PsiMapReading implements Validatable {

    private RegionMetadata regionMetadata;
    private Map<String, Double> regionReadings = new HashMap<>();
    private Tuple2<String, Map<String, Double>> tuple2;
    private String updatedTimeStamp;

    public RegionReadingInfo(RegionMetadata regionMetadata, Tuple2<String, Map<String, Double>> tuple2) {
        super(tuple2.getB());
        if (regionMetadata == null) {
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

    public String getUpdatedTimestampString() {
        return updatedTimeStamp;
    }

    public Map<String, Double> getRegionReadings() {
        return regionReadings;
    }

    @Override
    public boolean isValid() {
        if (regionReadings.isEmpty()) {
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
