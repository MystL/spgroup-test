package com.vin.spgrouptest;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.PsiResponses;

import org.joda.time.DateTime;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class ApiLoaderHelper {

    private ApiClient apiClient;
    private BehaviorSubject<PsiResponses> latestPsiResponseSubject;
    private PublishSubject<Boolean> getLatestPsiReadingsSubject;
    private BehaviorSubject<PsiResponses> datePsiResponseSubject;
    private PublishSubject<DateTime> getPsiReadingsForDateSubject;

    public ApiLoaderHelper(ApiClient apiClient) {
        this.apiClient = apiClient;
        latestPsiResponseSubject = BehaviorSubject.create();
        datePsiResponseSubject = BehaviorSubject.create();
        getLatestPsiReadingsSubject = PublishSubject.create();
        getPsiReadingsForDateSubject = PublishSubject.create();

        subscribeToGetLatestPsiReadingsSubject();
        subscribeToGetPsiReadingsForDateSubject();
    }

    private void subscribeToGetLatestPsiReadingsSubject() {
        getLatestPsiReadingsSubject.observeOn(Schedulers.newThread()).subscribe(new LoggingSubscriber<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    try {
                        latestPsiResponseSubject.onNext(apiClient.getLatestPsiReadings());
                    } catch (Exception e) {
                        e.printStackTrace();
                        latestPsiResponseSubject.onNext(null);
                    }
                }
            }
        });
    }

    public void fetchLatestPsiReadings() {
        getLatestPsiReadingsSubject.onNext(true);
    }

    private void subscribeToGetPsiReadingsForDateSubject() {
        getPsiReadingsForDateSubject.observeOn(Schedulers.newThread()).subscribe(new LoggingSubscriber<DateTime>() {
            @Override
            public void onNext(DateTime dateTime) {
                try {
                    datePsiResponseSubject.onNext(apiClient.getPsiReadingsForDay(dateTime.toString("yyyy-MM-dd")));
                } catch (Exception e) {
                    datePsiResponseSubject.onNext(null);
                }

            }
        });
    }

    public void fetchReadingsForDate(DateTime dateTime) {
        getPsiReadingsForDateSubject.onNext(dateTime);
    }

    public Observable<PsiResponses> getPsiReadingsForDateObs(){
        return datePsiResponseSubject;
    }

    public Observable<PsiResponses> getLatestPsiResponsesObs() {
        return latestPsiResponseSubject;
    }

}
