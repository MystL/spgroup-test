package com.vin.spgrouptest.data;

import java.util.Objects;

public class PsiReading {

    private double west;
    private double national;
    private double east;
    private double central;
    private double south;
    private double north;

    public PsiReading(double west, double national, double east, double central, double south, double north){
        this.west = west;
        this.national = national;
        this.east = east;
        this.central = central;
        this.south = south;
        this.north = north;
    }

    public double getWest() {
        return west;
    }

    public double getNational() {
        return national;
    }

    public double getEast() {
        return east;
    }

    public double getCentral() {
        return central;
    }

    public double getSouth() {
        return south;
    }

    public double getNorth() {
        return north;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsiReading that = (PsiReading) o;
        return west == that.west &&
                national == that.national &&
                east == that.east &&
                central == that.central &&
                south == that.south &&
                north == that.north;
    }

    @Override
    public int hashCode() {
        return Objects.hash(west, national, east, central, south, north);
    }
}
