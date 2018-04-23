package com.vin.spgrouptest.utils;

public class Tuple2<A, B> {
    private final A a;
    private final B b;

    public Tuple2(A a, B b) {

        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        Tuple2<A, B> that = (Tuple2<A, B>) o;
        return (this.getA().equals(that.getA()) && this.getB().equals(that.getB()));
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }
}
