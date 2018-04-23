package com.vin.spgrouptest.utils;

import android.view.View;

import rx.Observable;
import rx.subjects.PublishSubject;

public class ObservablesOps {

    public static Observable<View> clicks(View view) {
        final PublishSubject<View> subj = PublishSubject.create();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subj.onNext(v);

            }
        });
        return subj;
    }

}
