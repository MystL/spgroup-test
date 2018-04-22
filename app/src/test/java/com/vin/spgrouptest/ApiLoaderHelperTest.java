package com.vin.spgrouptest;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.data.TestModels;
import com.vin.spgrouptest.exceptions.ApiException;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.mockito.Mock;
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
        apiLoaderHelper.getLatestPsiResponsesObs().subscribe(testSubscriber);
        Mockito.when(mockApiClient.getLatestPsiReadings()).thenReturn(TestModels.testPsiResponses);
        List<PsiResponses> expectedOnNext = new ArrayList<>();
        expectedOnNext.add(TestModels.testPsiResponses);

        apiLoaderHelper.fetchLatestPsiReadings();
        Thread.sleep(1000);

        testSubscriber.assertReceivedOnNext(expectedOnNext);
    }

    public void testGetPsiReadingsForDate() throws ApiException, InterruptedException {

        TestSubscriber<PsiResponses> testSubscriber = new TestSubscriber<>();
        apiLoaderHelper.getLatestPsiResponsesObs().subscribe(testSubscriber);
        Mockito.when(mockApiClient.getPsiReadingsForDay("2018-04-22")).thenReturn(TestModels.testPsiResponses3_wholeDay);
        List<PsiResponses> expected = new ArrayList<>();
        expected.add(TestModels.testPsiResponses3_wholeDay);

        apiLoaderHelper.fetchReadingsForDate(new DateTime("2018-04-22"));
        Thread.sleep(1000);

        testSubscriber.assertReceivedOnNext(expected);
    }
}