package com.vin.spgrouptest.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PsiItem {

    private String timestamp;
    private String update_timestamp;
    private Map<String, PsiReading> readings = new HashMap<>();

    public PsiItem(String timestamp, String update_timestamp, Map<String, PsiReading> readings){
        this.timestamp = timestamp;
        this.update_timestamp = update_timestamp;
        this.readings.clear();
        this.readings.putAll(readings);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUpdateTimestamp() {
        return update_timestamp;
    }

    public Map<String, PsiReading> getReadings() {
        return readings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsiItem psiItem = (PsiItem) o;
        return Objects.equals(timestamp, psiItem.timestamp) &&
                Objects.equals(update_timestamp, psiItem.update_timestamp) &&
                Objects.equals(readings, psiItem.readings);
    }

    @Override
    public int hashCode() {

        return Objects.hash(timestamp, update_timestamp, readings);
    }
}
