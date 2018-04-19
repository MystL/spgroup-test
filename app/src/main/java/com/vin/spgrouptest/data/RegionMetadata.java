package com.vin.spgrouptest.data;

import java.util.Objects;

public class RegionMetadata implements Validatable{

    private String name;
    private RegionLocation label_location;

    public RegionMetadata(String name, RegionLocation label_location){
        this.name = name;
        this.label_location = label_location;
    }

    public String getName() {
        return name;
    }

    public RegionLocation getLocation() {
        return label_location;
    }

    @Override
    public boolean isValid() {
        if(name == null || label_location == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionMetadata metadata = (RegionMetadata) o;
        return Objects.equals(name, metadata.name) &&
                Objects.equals(label_location, metadata.label_location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, label_location);
    }
}
