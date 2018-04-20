package com.vin.spgrouptest.data;

import java.util.HashMap;
import java.util.Map;

public class TestModels {

    public static RegionLocation testLocation1 = new RegionLocation(1.0, 100.0);
    public static RegionLocation testLocation2 = new RegionLocation(1.2, 102.0);
    public static RegionMetadata[] getRegionMetadatas(){
        return new RegionMetadata[]{regionMetadata1, regionMetadata2};
    }

    public static RegionMetadata[] getRegionMetadatas2(){
        return new RegionMetadata[]{
                westRegion,
                nationalRegion,
                eastRegion,
                centralRegion,
                southRegion,
                northRegion
        };
    }

    public static PsiItem[] getPsiItems(){
        return new PsiItem[]{psiItem1};
    }


    public static RegionMetadata regionMetadata1 = new RegionMetadata("west", testLocation1);
    public static RegionMetadata regionMetadata2 = new RegionMetadata("central", testLocation2);
    public static PsiItem psiItem1 = new PsiItem("timestamp1", "timestamp2", getReadingsMap());


    public static Map<String, PsiReading> getReadingsMap(){
        Map<String, PsiReading> map = new HashMap<>();
        map.put("type1", new PsiReading(1,1,1,1,1,1));
        map.put("type2", new PsiReading(2,2,2,2,2,2));
        return map;
    }

    public static RegionMetadata westRegion = new RegionMetadata("west", new RegionLocation(1.35735, 103.7));
    public static RegionMetadata nationalRegion = new RegionMetadata("national", new RegionLocation(0,0));
    public static RegionMetadata eastRegion = new RegionMetadata("east", new RegionLocation(1.35735, 103.94));
    public static RegionMetadata centralRegion = new RegionMetadata("central", new RegionLocation(1.35735, 103.82));
    public static RegionMetadata southRegion = new RegionMetadata("south", new RegionLocation(1.29587, 103.82));
    public static RegionMetadata northRegion = new RegionMetadata("north", new RegionLocation(1.41803, 103.82));

    public static PsiReading o3_sub_index = new PsiReading(6,11,7,6,5,11);
    public static PsiReading co_sub_index = new PsiReading(6,6,4,4,5,5);
    public static PsiReading so2_sub_index = new PsiReading(3,4,3,3,4,2);
    public static PsiReading pm10_sub_index = new PsiReading(23,34,25,32,34,20);
    public static PsiReading pm25_sub_index = new PsiReading(46,56,46,54,56,51);
    public static PsiReading no2_one_hour_max = new PsiReading(12,18,15,18,8,5);
    public static PsiReading o3_eight_hour_max = new PsiReading(15,25,17,15,12,25);
    public static PsiReading co_eight_hour_max = new PsiReading(0.55,0.55,0.38,0.45,0.51,0.49);
    public static PsiReading so2_twenty_four_hourly = new PsiReading(4,6,4,5,6,4);
    public static PsiReading psi_twenty_four_hourly = new PsiReading(46,56,46,54,56,51);
    public static PsiReading pm10_twenty_four_hourly = new PsiReading(23,34,25,32,34,20);
    public static PsiReading pm25_twenty_four_hourly = new PsiReading(11,16,11,15,16,12);

    public static Map<String, PsiReading> getReadingsMap2(){
        Map<String, PsiReading> map = new HashMap<>();
        map.put("o3_sub_index", o3_sub_index);
        map.put("co_sub_index", co_sub_index);
        map.put("so2_sub_index", so2_sub_index);
        map.put("pm10_sub_index", pm10_sub_index);
        map.put("pm25_sub_index", pm25_sub_index);
        map.put("no2_one_hour_max", no2_one_hour_max);
        map.put("o3_eight_hour_max", o3_eight_hour_max);
        map.put("co_eight_hour_max", co_eight_hour_max);
        map.put("so2_twenty_four_hourly", so2_twenty_four_hourly);
        map.put("psi_twenty_four_hourly", psi_twenty_four_hourly);
        map.put("pm10_twenty_four_hourly", pm10_twenty_four_hourly);
        map.put("pm25_twenty_four_hourly", pm25_twenty_four_hourly);
        return map;
    }

    public static PsiItem psiItem2 = new PsiItem("2018-04-20T11:00:00+08:00", "2018-04-20T11:06:18+08:00", getReadingsMap2());
    public static PsiItem[] getPsiItems2() {
        return new PsiItem[]{psiItem2};
    }

    public static PsiResponses testPsiResponses = new PsiResponses(getRegionMetadatas(), getPsiItems(), new ApiInfo("status"));
    public static PsiResponses testPsiResponses2 = new PsiResponses(getRegionMetadatas2(), getPsiItems2(), new ApiInfo("healthy"));


}
