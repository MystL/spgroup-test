package com.vin.spgrouptest.data;

import java.util.Objects;

public class PsiReading {

    private int west;
    private int national;
    private int east;
    private int central;
    private int south;
    private int north;

    public PsiReading(int west, int national, int east, int central, int south, int north){
        this.west = west;
        this.national = national;
        this.east = east;
        this.central = central;
        this.south = south;
        this.north = north;
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
