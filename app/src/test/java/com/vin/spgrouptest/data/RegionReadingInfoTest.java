package com.vin.spgrouptest.data;

import com.vin.spgrouptest.utils.Tuple2;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class RegionReadingInfoTest extends TestCase{

    public void testInit_null(){
        try{
            RegionReadingInfo info = new RegionReadingInfo(null,null);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testInit_notNull(){
        RegionReadingInfo info = new RegionReadingInfo(TestModels.regionMetadata1, new Tuple2<String, Map<String, Double>>("", new HashMap<String, Double>()));
        assertNotNull(info);

    }

    public void testInit_emptyReadings(){
        RegionReadingInfo info = new RegionReadingInfo(TestModels.regionMetadata1, new Tuple2<String, Map<String, Double>>("", new HashMap<String, Double>()));
        assertFalse(info.isValid());
    }

    public void testInit_nonEmptyReadings(){
        Map<String, Double> readings = new HashMap<>();
        readings.put("type", 0.0);
        RegionReadingInfo info = new RegionReadingInfo(TestModels.regionMetadata2, new Tuple2<String, Map<String, Double>>("", readings));
        assertTrue(info.isValid());
    }

}