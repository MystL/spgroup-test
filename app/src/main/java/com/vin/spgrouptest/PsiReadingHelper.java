package com.vin.spgrouptest;

import com.vin.spgrouptest.data.Location;
import com.vin.spgrouptest.data.PsiItem;
import com.vin.spgrouptest.data.PsiReading;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.RegionLocation;
import com.vin.spgrouptest.data.RegionMetadata;
import com.vin.spgrouptest.data.RegionPsiItem;
import com.vin.spgrouptest.data.RegionReadingInfo;
import com.vin.spgrouptest.utils.Tuple2;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PsiReadingHelper {

    private PsiResponses psiResponses;

    public PsiReadingHelper(PsiResponses psiResponses) {
        if (psiResponses == null) {
            throw new IllegalArgumentException("Cannot init with null PsiResponses");
        }
        this.psiResponses = psiResponses;
    }

    public RegionMetadata[] getAllRegionsMetadata() {
        return psiResponses.getRegionMetadata();
    }

    public RegionReadingInfo getRegionReadingInfoForLocation(Location location) {
        return new RegionReadingInfo(getRegionMetadataForLocation(location), getPsiReadingsForLocation(location));
    }

    public RegionMetadata getRegionMetadataForLocation(Location location) {
        if (location != null) {
            for (RegionMetadata metadata : psiResponses.getRegionMetadata()) {
                if (metadata.getName().equalsIgnoreCase(location.name())) {
                    return metadata;
                }
            }
        }
        return new RegionMetadata("unknown", new RegionLocation(Double.NaN, Double.NaN));
    }

    public Tuple2<String, Map<String, Double>> getPsiReadingsForLocation(DateTime dateTime, Location location) {
        Map<String, Double> readingsMap = new HashMap<>();
        if (location != null) {
            PsiItem latestItem = getLatestReadings(dateTime, psiResponses.getItems());
            for (Map.Entry<String, PsiReading> ev : latestItem.getReadings().entrySet()) {
                switch (location) {
                    case WEST:
                        readingsMap.put(ev.getKey(), ev.getValue().getWest());
                        break;
                    case NATIONAL:
                        readingsMap.put(ev.getKey(), ev.getValue().getNational());
                        break;
                    case EAST:
                        readingsMap.put(ev.getKey(), ev.getValue().getEast());
                        break;
                    case CENTRAL:
                        readingsMap.put(ev.getKey(), ev.getValue().getCentral());
                        break;
                    case SOUTH:
                        readingsMap.put(ev.getKey(), ev.getValue().getSouth());
                        break;
                    case NORTH:
                        readingsMap.put(ev.getKey(), ev.getValue().getNorth());
                        break;
                    default:
                        return new Tuple2<>(latestItem.getUpdateTimestamp(), readingsMap);
                }
            }
            return new Tuple2<>(latestItem.getUpdateTimestamp(), readingsMap);
        }
        return new Tuple2<>("", readingsMap);
    }

    public Tuple2<String, Map<String, Double>> getPsiReadingsForLocation(Location location) {
        return getPsiReadingsForLocation(DateTime.now(), location);
    }

    public PsiItem getLatestReadings(PsiItem[] items) {
        return getLatestReadings(DateTime.now(), items);
    }

    public List<RegionPsiItem> getAllDayReadingsForRegion(Location location){
        List<RegionPsiItem> items = new ArrayList<>();
        if(location != null){
            for(PsiItem item : psiResponses.getItems()){
                Map<String, Double> readings = new HashMap<>();
                for(Map.Entry<String, PsiReading> ev : item.getReadings().entrySet()){
                    switch (location){
                        case CENTRAL:
                            readings.put(ev.getKey(), ev.getValue().getCentral());
                            break;
                        case NORTH:
                            readings.put(ev.getKey(), ev.getValue().getNorth());
                            break;
                        case SOUTH:
                            readings.put(ev.getKey(), ev.getValue().getSouth());
                            break;
                        case EAST:
                            readings.put(ev.getKey(), ev.getValue().getEast());
                            break;
                        case WEST:
                            readings.put(ev.getKey(), ev.getValue().getWest());
                            break;
                        case NATIONAL:
                            readings.put(ev.getKey(), ev.getValue().getNational());
                            break;
                        default:
                            break;

                    }
                }
                items.add(new RegionPsiItem(item.getTimestamp(), item.getUpdateTimestamp(), readings));
            }
        }
        return items;
    }

    public PsiItem getLatestReadings(DateTime localDateTime, PsiItem[] items) {
        long targetDateTime = localDateTime.getMillis();
        long currentClosest = 0L;
        PsiItem closestPsiItem = null;
        for (PsiItem item : items) {
            long psiTime = new DateTime(item.getTimestamp()).getMillis();
            long diff = Math.abs(targetDateTime - psiTime);
            if (currentClosest == 0) {
                currentClosest = diff;
                closestPsiItem = item;
            } else {
                if (diff <= currentClosest) {
                    currentClosest = diff;
                    closestPsiItem = item;
                }
            }
        }
        return closestPsiItem;
    }
}
