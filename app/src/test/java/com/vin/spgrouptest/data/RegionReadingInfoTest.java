package com.vin.spgrouptest.data;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RegionReadingInfoTest extends TestCase{

    public void testInit_null(){
        try{
            RegionReadingInfo info = new RegionReadingInfo(null,null);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testInit_notNull(){
        RegionReadingInfo info = new RegionReadingInfo(TestModels.regionMetadata1, new HashMap<String, Double>());
        assertNotNull(info);

    }

    public void testInit_emptyReadings(){
        RegionReadingInfo info = new RegionReadingInfo(TestModels.regionMetadata1, new HashMap<String, Double>());
        assertFalse(info.isValid());
    }

    public void testInit_nonEmptyReadings(){
        Map<String, Double> readings = new HashMap<>();
        readings.put("type", 0.0);
        RegionReadingInfo info = new RegionReadingInfo(TestModels.regionMetadata2, readings);
        assertTrue(info.isValid());
    }

}