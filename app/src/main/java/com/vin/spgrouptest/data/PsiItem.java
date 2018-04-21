package com.vin.spgrouptest.data;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PsiItem implements Validatable{

    private String timestamp;
    private String update_timestamp;
    private Map<String, PsiReading> readings = new HashMap<>();

    public PsiItem(String timestamp, String update_timestamp, Map<String, PsiReading> readings) {
        if(timestamp == null || update_timestamp == null || readings == null){
            throw new IllegalArgumentException("cannot init PsiItem with null params");
        }
        checkTimingsValidity(timestamp, update_timestamp);
        this.timestamp = timestamp;
        this.update_timestamp = update_timestamp;
        this.readings.clear();
        this.readings.putAll(readings);
    }

    private void checkTimingsValidity(String...timestamps) {
        try{
            for(String time : timestamps){
                new DateTime(time);
            }
        }catch (Exception e){
            throw e;
        }

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

    @Override
    public boolean isValid() {
        if(timestamp != null && !timestamp.isEmpty()
                && update_timestamp != null && !update_timestamp.isEmpty()
                && !readings.isEmpty()){
            return true;
        }
        return false;
    }
}
