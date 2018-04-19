package com.vin.spgrouptest.marshaller;

import com.vin.spgrouptest.data.ApiInfo;
import com.vin.spgrouptest.data.PsiItem;
import com.vin.spgrouptest.data.PsiReading;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.RegionMetadata;
import com.vin.spgrouptest.data.TestModels;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PsiResponsesJsonMarshallerTest extends TestCase{

    private PsiResponsesJsonMarshaller marshaller;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        marshaller = new PsiResponsesJsonMarshaller();
    }

    public void testFromJson_null(){
        try{
            marshaller.fromJson(null);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testFromJson(){
        String jsonString = "{\n" +
                "\t\"region_metadata\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"name\":\"west\",\n" +
                "\t\t\t\"label_location\":{\n" +
                "\t\t\t\t\"latitude\":1.0,\n" +
                "\t\t\t\t\"longitude\":100.0\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\":\"national\",\n" +
                "\t\t\t\"label_location\":{\n" +
                "\t\t\t\t\"latitude\":1.2,\n" +
                "\t\t\t\t\"longitude\":102.0\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"items\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"timestamp\":\"2018-04-19T13:00:00+08:00\",\n" +
                "\t\t\t\"update_timestamp\":\"2018-04-19T13:06:18+08:00\",\n" +
                "\t\t\t\"readings\":{\n" +
                "\t\t\t\t\"o3_sub_index\":{\n" +
                "\t\t\t\t\t\"west\":22,\n" +
                "\t\t\t\t\t\"national\":22,\n" +
                "\t\t\t\t\t\"east\":10,\n" +
                "\t\t\t\t\t\"central\":7,\n" +
                "\t\t\t\t\t\"south\":14,\n" +
                "\t\t\t\t\t\"north\":12\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"pm10_twenty_four_hourly\":{\n" +
                "\t\t\t\t\t\"west\":18,\n" +
                "\t\t\t\t\t\"national\":32,\n" +
                "\t\t\t\t\t\"east\":32,\n" +
                "\t\t\t\t\t\"central\":30,\n" +
                "\t\t\t\t\t\"south\":27,\n" +
                "\t\t\t\t\t\"north\":17\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"pm10_sub_index\":{\n" +
                "\t\t\t\t\t\"west\":18,\n" +
                "\t\t\t\t\t\"national\":32,\n" +
                "\t\t\t\t\t\"east\":32,\n" +
                "\t\t\t\t\t\"central\":30,\n" +
                "\t\t\t\t\t\"south\":27,\n" +
                "\t\t\t\t\t\"north\":17\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"api_info\":{\n" +
                "\t\t\"status\":\"healthy\"\n" +
                "\t}\n" +
                "}";
        Map<String, PsiReading> readings = new HashMap<>();
        readings.put("o3_sub_index", new PsiReading(22,22,10,7,14,12));
        readings.put("pm10_twenty_four_hourly", new PsiReading(18,32,32,30,27,17));
        readings.put("pm10_sub_index", new PsiReading(18,32,32,30,27,17));
        PsiResponses expected = new PsiResponses(new RegionMetadata[]{new RegionMetadata("west", TestModels.testLocation1), new RegionMetadata("national", TestModels.testLocation2)},
                new PsiItem[]{new PsiItem("2018-04-19T13:00:00+08:00","2018-04-19T13:06:18+08:00", readings)},
                new ApiInfo("healthy"));

        PsiResponses actual = marshaller.fromJson(jsonString);

        assertEquals(expected, actual);

        //Check parsing of RegionMetadata
        assertTrue(Arrays.equals(expected.getRegionMetadata(), actual.getRegionMetadata()));
        assertEquals(expected.getRegionMetadata().length, actual.getRegionMetadata().length);
        assertEquals(expected.getRegionMetadata()[0], actual.getRegionMetadata()[0]);
        assertEquals(expected.getRegionMetadata()[1], actual.getRegionMetadata()[1]);

        //Check parsing of ApiInfo
        assertEquals(expected.getApiInfo(), actual.getApiInfo());
        assertEquals(expected.getApiInfo().getStatus(), actual.getApiInfo().getStatus());

        //Check parsing of PsiItem
        PsiItem expectedItem = expected.getItems()[0];
        PsiItem actualItem = actual.getItems()[0];
        assertEquals(expectedItem.getTimestamp(), actualItem.getTimestamp());
        assertEquals(expectedItem.getUpdateTimestamp(), actualItem.getUpdateTimestamp());
        assertEquals(expectedItem.getReadings(), actualItem.getReadings());
        assertEquals(expectedItem.getReadings().size(), actualItem.getReadings().size());
        assertEquals(expectedItem.getReadings().get("o3_sub_index"), actualItem.getReadings().get("o3_sub_index"));
    }

    public void testToJson_null(){
        try{
            marshaller.toJson(null);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testToJson(){
        String jsonString = "{" +
                "\"region_metadata\":[" +
                "{" +
                "\"name\":\"west\"," +
                "\"label_location\":{" +
                "\"latitude\":1.0," +
                "\"longitude\":100.0" +
                "}" +
                "}," +
                "{" +
                "\"name\":\"national\"," +
                "\"label_location\":{" +
                "\"latitude\":1.2," +
                "\"longitude\":102.0" +
                "}" +
                "}" +
                "]," +
                "\"items\":[" +
                "{" +
                "\"timestamp\":\"2018-04-19T13:00:00+08:00\"," +
                "\"update_timestamp\":\"2018-04-19T13:06:18+08:00\"," +
                "\"readings\":{" +
                "\"o3_sub_index\":{" +
                "\"west\":22," +
                "\"national\":22," +
                "\"east\":10," +
                "\"central\":7," +
                "\"south\":14," +
                "\"north\":12" +
                "}," +
                "\"pm10_twenty_four_hourly\":{" +
                "\"west\":18," +
                "\"national\":32," +
                "\"east\":32," +
                "\"central\":30," +
                "\"south\":27," +
                "\"north\":17" +
                "}," +
                "\"pm10_sub_index\":{" +
                "\"west\":18," +
                "\"national\":32," +
                "\"east\":32," +
                "\"central\":30," +
                "\"south\":27," +
                "\"north\":17" +
                "}" +
                "}" +
                "}" +
                "]," +
                "\"api_info\":{" +
                "\"status\":\"healthy\"" +
                "}" +
                "}";;

        Map<String, PsiReading> readings = new HashMap<>();
        readings.put("o3_sub_index", new PsiReading(22,22,10,7,14,12));
        readings.put("pm10_twenty_four_hourly", new PsiReading(18,32,32,30,27,17));
        readings.put("pm10_sub_index", new PsiReading(18,32,32,30,27,17));
        PsiResponses responses = new PsiResponses(new RegionMetadata[]{new RegionMetadata("west", TestModels.testLocation1), new RegionMetadata("national", TestModels.testLocation2)},
                new PsiItem[]{new PsiItem("2018-04-19T13:00:00+08:00","2018-04-19T13:06:18+08:00", readings)},
                new ApiInfo("healthy"));

        assertEquals(jsonString, marshaller.toJson(responses));
    }

    public void testRoundTrip(){
        Map<String, PsiReading> readings = new HashMap<>();
        readings.put("o3_sub_index", new PsiReading(22,22,10,7,14,12));
        readings.put("pm10_twenty_four_hourly", new PsiReading(18,32,32,30,27,17));
        readings.put("pm10_sub_index", new PsiReading(18,32,32,30,27,17));
        PsiResponses expected = new PsiResponses(new RegionMetadata[]{new RegionMetadata("west", TestModels.testLocation1), new RegionMetadata("national", TestModels.testLocation2)},
                new PsiItem[]{new PsiItem("2018-04-19T13:00:00+08:00","2018-04-19T13:06:18+08:00", readings)},
                new ApiInfo("healthy"));

        assertEquals(expected, marshaller.fromJson(marshaller.toJson(expected)));
    }

}