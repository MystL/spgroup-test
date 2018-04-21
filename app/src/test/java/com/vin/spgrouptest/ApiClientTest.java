package com.vin.spgrouptest;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.TestModels;
import com.vin.spgrouptest.exceptions.ApiException;

import junit.framework.TestCase;

import org.joda.time.LocalDate;
import org.mockito.Mockito;

public class ApiClientTest extends TestCase{

    private ApiClient.Api mockApi;
    private ApiClient client;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockApi = Mockito.mock(ApiClient.Api.class);
        client = new ApiClient(mockApi);
    }

    public void testGetPsiReadings() throws ApiException {
        Mockito.when(mockApi.getLatestPsiReadings()).thenReturn(TestModels.testPsiResponses);

        assertNotNull(client.getLatestPsiReadings());
    }

    public void testGetPsiReadings_returnNull(){
        Mockito.when(mockApi.getLatestPsiReadings()).thenReturn(null);
        try{
            client.getLatestPsiReadings();
            fail("should throw exceptions");
        }catch (Exception e){

        }
    }

    public void testGetPsiReadingsForDay_validDate() throws ApiException {
        String localDate = "2018-04-20";
        Mockito.when(mockApi.getPsiReadingsForDay(localDate)).thenReturn(TestModels.testPsiResponses3_wholeDay);

        PsiResponses responses = client.getPsiReadingsForDay(localDate);
        assertNotNull(responses);
        assertTrue(responses.getItems().length > 2);
    }

    public void testGetPsiReadingForDay_invalidDateFormat(){
        String dateString = "2018-04-20T11:00:00+08:00";
        try{
            client.getPsiReadingsForDay(dateString);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

    public void testGetPsiReadingForDay_invalidDate() {
        String localDate = "asdasds";
        try{
            client.getPsiReadingsForDay(localDate);
            fail("should throw exception");
        }catch (Exception e){

        }
    }

}