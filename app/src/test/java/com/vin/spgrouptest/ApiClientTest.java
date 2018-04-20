package com.vin.spgrouptest;

import android.test.AndroidTestCase;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.TestModels;
import com.vin.spgrouptest.exceptions.ApiException;

import junit.framework.TestCase;

import org.mockito.Mockito;

import static org.junit.Assert.*;

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
        Mockito.when(mockApi.getPsiReadings()).thenReturn(TestModels.testPsiResponses);

        assertNotNull(client.getPsiReadings());
    }

    public void testGetPsiReadings_returnNull(){
        Mockito.when(mockApi.getPsiReadings()).thenReturn(null);
        try{
            client.getPsiReadings();
            fail("should throw exceptions");
        }catch (Exception e){

        }
    }

}