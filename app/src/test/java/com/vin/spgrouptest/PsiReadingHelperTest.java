package com.vin.spgrouptest;

import com.vin.spgrouptest.data.Location;
import com.vin.spgrouptest.data.RegionLocation;
import com.vin.spgrouptest.data.RegionMetadata;
import com.vin.spgrouptest.data.RegionPsiItem;
import com.vin.spgrouptest.data.RegionReadingInfo;
import com.vin.spgrouptest.data.TestModels;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.rules.TestName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PsiReadingHelperTest extends TestCase{

    private PsiReadingHelper helper;
    private Map<String, Double> westReadings = new HashMap<>();
    private Map<String, Double> nationalReadings = new HashMap<>();
    private Map<String, Double> eastReadings = new HashMap<>();
    private Map<String, Double> centralReadings = new HashMap<>();
    private Map<String, Double> southReadings = new HashMap<>();
    private Map<String, Double> northReadings = new HashMap<>();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        helper = new PsiReadingHelper(TestModels.testPsiResponses2);

        westReadings.put("o3_sub_index", 6.0);
        westReadings.put("co_sub_index", 6.0);
        westReadings.put("so2_sub_index", 3.0);
        westReadings.put("pm10_sub_index", 23.0);
        westReadings.put("pm25_sub_index", 46.0);
        westReadings.put("no2_one_hour_max", 12.0);
        westReadings.put("o3_eight_hour_max", 15.0);
        westReadings.put("co_eight_hour_max", 0.55);
        westReadings.put("so2_twenty_four_hourly", 4.0);
        westReadings.put("psi_twenty_four_hourly", 46.0);
        westReadings.put("pm10_twenty_four_hourly", 23.0);
        westReadings.put("pm25_twenty_four_hourly", 11.0);

        nationalReadings.put("o3_sub_index", 11.0);
        nationalReadings.put("co_sub_index", 6.0);
        nationalReadings.put("so2_sub_index", 4.0);
        nationalReadings.put("pm10_sub_index", 34.0);
        nationalReadings.put("pm25_sub_index", 56.0);
        nationalReadings.put("no2_one_hour_max", 18.0);
        nationalReadings.put("o3_eight_hour_max", 25.0);
        nationalReadings.put("co_eight_hour_max", 0.55);
        nationalReadings.put("so2_twenty_four_hourly", 6.0);
        nationalReadings.put("psi_twenty_four_hourly", 56.0);
        nationalReadings.put("pm10_twenty_four_hourly", 34.0);
        nationalReadings.put("pm25_twenty_four_hourly", 16.0);

        eastReadings.put("o3_sub_index", 7.0);
        eastReadings.put("co_sub_index", 4.0);
        eastReadings.put("so2_sub_index", 3.0);
        eastReadings.put("pm10_sub_index", 25.0);
        eastReadings.put("pm25_sub_index", 46.0);
        eastReadings.put("no2_one_hour_max", 15.0);
        eastReadings.put("o3_eight_hour_max", 17.0);
        eastReadings.put("co_eight_hour_max", 0.38);
        eastReadings.put("so2_twenty_four_hourly", 4.0);
        eastReadings.put("psi_twenty_four_hourly", 46.0);
        eastReadings.put("pm10_twenty_four_hourly", 25.0);
        eastReadings.put("pm25_twenty_four_hourly", 11.0);

        centralReadings.put("o3_sub_index", 6.0);
        centralReadings.put("co_sub_index", 4.0);
        centralReadings.put("so2_sub_index", 3.0);
        centralReadings.put("pm10_sub_index", 32.0);
        centralReadings.put("pm25_sub_index", 54.0);
        centralReadings.put("no2_one_hour_max", 18.0);
        centralReadings.put("o3_eight_hour_max", 15.0);
        centralReadings.put("co_eight_hour_max", 0.45);
        centralReadings.put("so2_twenty_four_hourly", 5.0);
        centralReadings.put("psi_twenty_four_hourly", 54.0);
        centralReadings.put("pm10_twenty_four_hourly", 32.0);
        centralReadings.put("pm25_twenty_four_hourly", 15.0);

        southReadings.put("o3_sub_index", 5.0);
        southReadings.put("co_sub_index", 5.0);
        southReadings.put("so2_sub_index", 4.0);
        southReadings.put("pm10_sub_index", 34.0);
        southReadings.put("pm25_sub_index", 56.0);
        southReadings.put("no2_one_hour_max", 8.0);
        southReadings.put("o3_eight_hour_max", 12.0);
        southReadings.put("co_eight_hour_max", 0.51);
        southReadings.put("so2_twenty_four_hourly", 6.0);
        southReadings.put("psi_twenty_four_hourly", 56.0);
        southReadings.put("pm10_twenty_four_hourly", 34.0);
        southReadings.put("pm25_twenty_four_hourly", 16.0);

        northReadings.put("o3_sub_index", 11.0);
        northReadings.put("co_sub_index", 5.0);
        northReadings.put("so2_sub_index", 2.0);
        northReadings.put("pm10_sub_index", 20.0);
        northReadings.put("pm25_sub_index", 51.0);
        northReadings.put("no2_one_hour_max", 5.0);
        northReadings.put("o3_eight_hour_max", 25.0);
        northReadings.put("co_eight_hour_max", 0.49);
        northReadings.put("so2_twenty_four_hourly", 4.0);
        northReadings.put("psi_twenty_four_hourly", 51.0);
        northReadings.put("pm10_twenty_four_hourly", 20.0);
        northReadings.put("pm25_twenty_four_hourly", 12.0);

    }

    public void testInit(){
        PsiReadingHelper helper = new PsiReadingHelper(TestModels.testPsiResponses);
        assertNotNull(helper);
    }

    public void testInit_null(){
        try{
            new PsiReadingHelper(null);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testInit2(){
        PsiReadingHelper helper = new PsiReadingHelper(TestModels.testPsiResponses);
        assertNotNull(helper.getAllRegionsMetadata());
    }

    public void testGetRegionMetadataForLocation_west(){
        assertEquals(TestModels.westRegion, helper.getRegionMetadataForLocation(Location.WEST));
    }

    public void testGetReadingsForLocation_west(){
        assertEquals(westReadings, helper.getPsiReadingsForLocation(Location.WEST).getB());
    }

    public void testGetReadingsForLocation_national(){
        assertEquals(nationalReadings, helper.getPsiReadingsForLocation(Location.NATIONAL).getB());
    }

    public void testGetReadingsForLocation_east(){
        assertEquals(eastReadings, helper.getPsiReadingsForLocation(Location.EAST).getB());
    }

    public void testGetReadingsForLocation_central(){
        assertEquals(centralReadings, helper.getPsiReadingsForLocation(Location.CENTRAL).getB());
    }

    public void testGetReadingsForLocation_south(){
        assertEquals(southReadings, helper.getPsiReadingsForLocation(Location.SOUTH).getB());
    }

    public void testGetReadingsForLocation_north(){
        assertEquals(northReadings, helper.getPsiReadingsForLocation(Location.NORTH).getB());
    }

    public void testGetReadingsForLocation_null(){
        assertEquals(new Tuple2<>("",new HashMap<String, Double>()), helper.getPsiReadingsForLocation(null));
    }

    public void testGetRegionReadings_null(){
        assertEquals(new RegionReadingInfo(new RegionMetadata("unknown", new RegionLocation(Double.NaN,Double.NaN)),
                new Tuple2<String, Map<String, Double>>("", new HashMap<String, Double>())),
                helper.getRegionReadingInfoForLocation(null));
    }

    public void testGetRegionReadings_west(){
        RegionReadingInfo expected = new RegionReadingInfo(TestModels.westRegion,
                new Tuple2<>("2018-04-20T14:30:00+08:00", westReadings));

        assertEquals(expected, helper.getRegionReadingInfoForLocation(Location.WEST));
    }

    public void testGetRegionReadings_central(){
        RegionReadingInfo expected = new RegionReadingInfo(TestModels.centralRegion,
                new Tuple2<>("2018-04-20T14:30:00+08:00", centralReadings));

        assertEquals(expected, helper.getRegionReadingInfoForLocation(Location.CENTRAL));
    }

    public void testGetRegionReadings_south(){
        RegionReadingInfo expected = new RegionReadingInfo(TestModels.southRegion,
                new Tuple2<>("2018-04-20T14:30:00+08:00",southReadings));

        assertEquals(expected, helper.getRegionReadingInfoForLocation(Location.SOUTH));
    }

    public void testGetRegionReadings_national(){
        RegionReadingInfo expected = new RegionReadingInfo(TestModels.nationalRegion,
                new Tuple2<>("2018-04-20T14:30:00+08:00", nationalReadings));

        assertEquals(expected, helper.getRegionReadingInfoForLocation(Location.NATIONAL));
    }

    public void testGetRegionReadings_east(){
        RegionReadingInfo expected = new RegionReadingInfo(TestModels.eastRegion,
                new Tuple2<>("2018-04-20T14:30:00+08:00",eastReadings));

        assertEquals(expected, helper.getRegionReadingInfoForLocation(Location.EAST));
    }

    public void testGetRegionReadings_north(){
        RegionReadingInfo expected = new RegionReadingInfo(TestModels.northRegion,
                new Tuple2<>("2018-04-20T14:30:00+08:00", northReadings));

        assertEquals(expected, helper.getRegionReadingInfoForLocation(Location.NORTH));
    }

    public void testGetLatestRegionReadings(){
        PsiReadingHelper helper = new PsiReadingHelper(TestModels.testPsiResponses3_wholeDay);

        DateTime dateTime = new DateTime("2018-04-20T14:30:00+08:00");
        assertEquals(TestModels.psiItem5, helper.getLatestReadings(dateTime, TestModels.getPsiItems3_wholeDay()));
    }

    public void testGetRegionReadingsWithMultipleReadings_north(){
        PsiReadingHelper helper = new PsiReadingHelper(TestModels.testPsiResponses3_wholeDay);
        DateTime dateTime = new DateTime("2018-04-20T14:30:00+08:00");
        Map<String, Double> expected = new HashMap<>();
        expected.put("o3_sub_index", 12.0);
        expected.put("co_sub_index", 6.0);
        expected.put("so2_sub_index", 2.0);
        expected.put("pm10_sub_index", 21.0);
        expected.put("pm25_sub_index", 52.0);
        expected.put("no2_one_hour_max", 6.0);
        expected.put("o3_eight_hour_max", 35.0);
        expected.put("co_eight_hour_max", 0.59);
        expected.put("so2_twenty_four_hourly", 5.0);
        expected.put("psi_twenty_four_hourly", 52.0);
        expected.put("pm10_twenty_four_hourly", 21.0);
        expected.put("pm25_twenty_four_hourly", 13.0);

        assertEquals(expected, helper.getPsiReadingsForLocation(dateTime, Location.NORTH).getB());
    }

    public void testGetRegionAllReadings(){
        PsiReadingHelper helper = new PsiReadingHelper(TestModels.testPsiResponses3_wholeDay);
        Map<String, Double> allCentralReadings = new HashMap<>();
        allCentralReadings.put("o3_sub_index", 6.0);
        allCentralReadings.put("co_sub_index", 4.0);
        allCentralReadings.put("so2_sub_index", 3.0);
        allCentralReadings.put("pm10_sub_index", 32.0);
        allCentralReadings.put("pm25_sub_index", 54.0);
        allCentralReadings.put("no2_one_hour_max", 18.0);
        allCentralReadings.put("o3_eight_hour_max", 15.0);
        allCentralReadings.put("co_eight_hour_max", 0.45);
        allCentralReadings.put("so2_twenty_four_hourly", 5.0);
        allCentralReadings.put("psi_twenty_four_hourly", 54.0);
        allCentralReadings.put("pm10_twenty_four_hourly", 32.0);
        allCentralReadings.put("pm25_twenty_four_hourly", 15.0);

        Map<String, Double> allCentralReadings2 = new HashMap<>();
        allCentralReadings2.put("o3_sub_index", 7.0);
        allCentralReadings2.put("co_sub_index", 5.0);
        allCentralReadings2.put("so2_sub_index", 4.0);
        allCentralReadings2.put("pm10_sub_index", 33.0);
        allCentralReadings2.put("pm25_sub_index", 55.0);
        allCentralReadings2.put("no2_one_hour_max", 19.0);
        allCentralReadings2.put("o3_eight_hour_max", 15.0);
        allCentralReadings2.put("co_eight_hour_max", 0.55);
        allCentralReadings2.put("so2_twenty_four_hourly", 6.0);
        allCentralReadings2.put("psi_twenty_four_hourly", 55.0);
        allCentralReadings2.put("pm10_twenty_four_hourly", 33.0);
        allCentralReadings2.put("pm25_twenty_four_hourly", 16.0);

        List<RegionPsiItem> expected = new ArrayList<>();
        expected.add(new RegionPsiItem(TestModels.psiItem2.getTimestamp(), TestModels.psiItem2.getUpdateTimestamp(), allCentralReadings));
        expected.add(new RegionPsiItem(TestModels.psiItem3.getTimestamp(), TestModels.psiItem3.getUpdateTimestamp(), allCentralReadings));
        expected.add(new RegionPsiItem(TestModels.psiItem4.getTimestamp(), TestModels.psiItem4.getUpdateTimestamp(), allCentralReadings));
        expected.add(new RegionPsiItem(TestModels.psiItem5.getTimestamp(), TestModels.psiItem5.getUpdateTimestamp(), allCentralReadings2));

        List<RegionPsiItem> actual = helper.getAllDayReadingsForRegion(Location.CENTRAL);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getTimestamp(), actual.get(0).getTimestamp());
        assertEquals(expected.get(0).getUpdateTimeStamp(), actual.get(0).getUpdateTimeStamp());
        assertEquals(expected.get(0).getReadings(), actual.get(0).getReadings());
        assertEquals(expected.get(1), helper.getAllDayReadingsForRegion(Location.CENTRAL).get(1));
        assertEquals(expected.get(2), helper.getAllDayReadingsForRegion(Location.CENTRAL).get(2));
        assertEquals(expected.get(3), helper.getAllDayReadingsForRegion(Location.CENTRAL).get(3));
    }

    public void testGetRegionAllReadings_null(){
        PsiReadingHelper helper = new PsiReadingHelper(TestModels.testPsiResponses3_wholeDay);

        assertEquals(new ArrayList<RegionPsiItem>(), helper.getAllDayReadingsForRegion(null));
    }
}