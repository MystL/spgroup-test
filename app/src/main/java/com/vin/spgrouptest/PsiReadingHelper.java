package com.vin.spgrouptest;

import com.vin.spgrouptest.data.Location;
import com.vin.spgrouptest.data.PsiItem;
import com.vin.spgrouptest.data.PsiReading;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.RegionLocation;
import com.vin.spgrouptest.data.RegionMetadata;
import com.vin.spgrouptest.data.RegionReadingInfo;

import java.util.HashMap;
import java.util.Map;

public class PsiReadingHelper {

    private PsiResponses psiResponses;

    public PsiReadingHelper(PsiResponses psiResponses) {
        if(psiResponses == null){
            throw new IllegalArgumentException("Cannot init with null PsiResponses");
        }
        this.psiResponses = psiResponses;
    }

    public RegionMetadata[] getAllRegionsMetadata(){
        return psiResponses.getRegionMetadata();
    }

    public RegionReadingInfo getRegionReadingInfoForLocation(Location location) {
        return new RegionReadingInfo(getRegionMetadataForLocation(location), getPsiReadingsForLocation(location));
    }

    public RegionMetadata getRegionMetadataForLocation(Location location){
        if(location != null){
            for(RegionMetadata metadata : psiResponses.getRegionMetadata()){
                if(metadata.getName().equalsIgnoreCase(location.name())){
                    return metadata;
                }
            }
        }
        return new RegionMetadata("unknown", new RegionLocation(Double.NaN,Double.NaN));
    }

    public Map<String, Double> getPsiReadingsForLocation(Location location){
        Map<String, Double> readingsMap = new HashMap<>();
        if(location != null){
            for(PsiItem item : psiResponses.getItems()){
                for(Map.Entry<String, PsiReading> ev : item.getReadings().entrySet()){
                    switch (location){
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
                            return readingsMap;
                    }
                }
            }
        }
        return readingsMap;
    }
}
