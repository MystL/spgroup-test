package com.vin.spgrouptest.utils;

import rx.functions.Func2;

public class PairUp<A, B> implements Func2<A, B, Tuple2<A, B>> {
    @Override
    public Tuple2<A, B> call(A a, B b) {
        return new Tuple2<>(a, b);
    }
}
