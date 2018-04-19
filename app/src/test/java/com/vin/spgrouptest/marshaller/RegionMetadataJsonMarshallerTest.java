package com.vin.spgrouptest.marshaller;

import com.vin.spgrouptest.data.RegionMetadata;
import com.vin.spgrouptest.data.TestModels;

import junit.framework.TestCase;

public class RegionMetadataJsonMarshallerTest extends TestCase {

    private RegionMetadataJsonMarshaller marshaller;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        marshaller = new RegionMetadataJsonMarshaller();
    }

    public void testFromJson_null() {
        try {
            marshaller.fromJson(null);
            fail("should throw exception");
        } catch (Exception e) {

        }
    }

    public void testFromJson_badJson() {
        try {
            marshaller.fromJson("asd");
            fail("should throw exception");
        } catch (Exception e) {

        }
    }

    public void testFromJson() {
        RegionMetadata regionMetadata = new RegionMetadata("west", TestModels.testLocation1);
        String jsonString = "{\"name\":\"west\",\"label_location\":{\"latitude\":1.0,\"longitude\":100.0}}";

        assertEquals(regionMetadata.getLocation(), marshaller.fromJson(jsonString).getLocation());
        assertEquals(regionMetadata.getName(), marshaller.fromJson(jsonString).getName());
    }

    public void testToJson_null() {
        try {
            marshaller.toJson(null);
            fail("should throw exception");
        } catch (Exception e) {

        }
    }

    public void testToJson() {
        RegionMetadata regionMetadata = new RegionMetadata("west", TestModels.testLocation1);
        String expected = "{\"name\":\"west\",\"label_location\":{\"latitude\":1.0,\"longitude\":100.0}}";

        assertEquals(expected, marshaller.toJson(regionMetadata));
    }

    public void testRoundTrip() {
        RegionMetadata regionMetadata = new RegionMetadata("west", TestModels.testLocation1);

        assertEquals(regionMetadata, marshaller.fromJson(marshaller.toJson(regionMetadata)));

    }

}