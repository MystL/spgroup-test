package com.vin.spgrouptest.data;

import java.util.HashMap;
import java.util.Map;

public class TestModels {

    public static RegionLocation testLocation1 = new RegionLocation(1.0, 100.0);
    public static RegionLocation testLocation2 = new RegionLocation(1.2, 102.0);
    public static PsiResponses testPsiResponses = new PsiResponses(getRegionMetadatas(), getPsiItems(), new ApiInfo("status"));

    public static RegionMetadata[] getRegionMetadatas(){
        return new RegionMetadata[]{regionMetadata1, regionMetadata2};
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
}
