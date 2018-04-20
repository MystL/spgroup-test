package com.vin.spgrouptest;

import com.vin.spgrouptest.api.ApiClient;
import com.vin.spgrouptest.data.PsiResponses;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class DataLoaderHelper {

    private ApiClient apiClient;
    private BehaviorSubject<PsiResponses> psiResponseSubject;
    private PublishSubject<Boolean> getPsiReadingsSubject;

    public DataLoaderHelper(ApiClient apiClient) {
        this.apiClient = apiClient;
        psiResponseSubject = BehaviorSubject.create();
        getPsiReadingsSubject = PublishSubject.create();

        subscribeToGetPsiReadingsSubject();
    }

    private void subscribeToGetPsiReadingsSubject() {
        getPsiReadingsSubject.observeOn(Schedulers.newThread()).subscribe(new LoggingSubscriber<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    try {
                        psiResponseSubject.onNext(apiClient.getPsiReadings());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void fetchPsiReadings() {
        getPsiReadingsSubject.onNext(true);
    }

    public Observable<PsiResponses> getPsiResponsesObs() {
        return psiResponseSubject;
    }

}
