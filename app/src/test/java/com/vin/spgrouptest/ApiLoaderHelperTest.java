package com.vin.spgrouptest;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.TestModels;
import com.vin.spgrouptest.exceptions.ApiException;

import junit.framework.TestCase;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import rx.observers.TestSubscriber;

public class ApiLoaderHelperTest extends TestCase{

    private ApiClient mockApiClient;
    private ApiLoaderHelper apiLoaderHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockApiClient = Mockito.mock(ApiClient.class);
        apiLoaderHelper = new ApiLoaderHelper(mockApiClient);
    }

    public void testGetPsiReadings() throws ApiException, InterruptedException {

        TestSubscriber<PsiResponses> testSubscriber = new TestSubscriber<>();
        apiLoaderHelper.getPsiResponsesObs().subscribe(testSubscriber);
        Mockito.when(mockApiClient.getPsiReadings()).thenReturn(TestModels.testPsiResponses);
        List<PsiResponses> expectedOnNext = new ArrayList<>();
        expectedOnNext.add(TestModels.testPsiResponses);

        apiLoaderHelper.fetchPsiReadings();
        Thread.sleep(1000);

        testSubscriber.assertReceivedOnNext(expectedOnNext);
    }
}