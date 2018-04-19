package com.vin.spgrouptest.marshaller;

import com.vin.spgrouptest.exceptions.ConfigParserException;

public abstract class JsonMarshaller<T> {

    public abstract T fromJson(String json) throws ConfigParserException;

    public abstract String toJson(T item) throws ConfigParserException;
}
