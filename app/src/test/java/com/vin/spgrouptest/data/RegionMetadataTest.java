package com.vin.spgrouptest.data;

import junit.framework.TestCase;

public class RegionMetadataTest extends TestCase {

    public void testInit_nullName(){
        RegionMetadata metadata = new RegionMetadata(null, TestModels.testLocation1);
        assertFalse(metadata.isValid());
    }

    public void testInit_nullLocation(){
        RegionMetadata metadata = new RegionMetadata("west", null);
        assertFalse(metadata.isValid());
    }

    public void testInit_bothValid(){
        RegionMetadata metadata = new RegionMetadata("national", TestModels.testLocation2);
        assertTrue(metadata.isValid());
    }

    public void testEquality(){
        RegionMetadata metadata = new RegionMetadata("west", TestModels.testLocation1);
        RegionMetadata metadata2 = new RegionMetadata("west", TestModels.testLocation1);
        assertEquals(metadata, metadata2);
    }

    public void testInequality(){
        RegionMetadata metadata = new RegionMetadata("central", TestModels.testLocation1);
        RegionMetadata metadata2 = new RegionMetadata("west", TestModels.testLocation1);
        assertNotSame(metadata, metadata2);
    }

}