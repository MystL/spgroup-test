package com.vin.spgrouptest;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.PsiResponses;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class ApiLoaderHelper {

    private ApiClient apiClient;
    private BehaviorSubject<PsiResponses> latestPsiResponseSubject;
    private PublishSubject<Boolean> getLatestPsiReadingsSubject;

    public ApiLoaderHelper(ApiClient apiClient) {
        this.apiClient = apiClient;
        latestPsiResponseSubject = BehaviorSubject.create();
        getLatestPsiReadingsSubject = PublishSubject.create();

        subscribeToGetLatestPsiReadingsSubject();
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
                    }
                }
            }
        });
    }

    public void fetchLatestPsiReadings() {
        getLatestPsiReadingsSubject.onNext(true);
    }

    public Observable<PsiResponses> getLatestPsiResponsesObs() {
        return latestPsiResponseSubject;
    }

}
