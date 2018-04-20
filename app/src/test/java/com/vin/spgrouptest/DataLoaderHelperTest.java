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

public class DataLoaderHelperTest extends TestCase{

    private ApiClient mockApiClient;
    private DataLoaderHelper dataLoaderHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockApiClient = Mockito.mock(ApiClient.class);
        dataLoaderHelper = new DataLoaderHelper(mockApiClient);
    }

    public void testGetPsiReadings() throws ApiException, InterruptedException {

        TestSubscriber<PsiResponses> testSubscriber = new TestSubscriber<>();
        dataLoaderHelper.getPsiResponsesObs().subscribe(testSubscriber);
        Mockito.when(mockApiClient.getPsiReadings()).thenReturn(TestModels.testPsiResponses);
        List<PsiResponses> expectedOnNext = new ArrayList<>();
        expectedOnNext.add(TestModels.testPsiResponses);

        dataLoaderHelper.fetchPsiReadings();
        Thread.sleep(1000);

        testSubscriber.assertReceivedOnNext(expectedOnNext);
    }
}