package com.vin.spgrouptest.marshaller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vin.spgrouptest.data.PsiReading;
import com.vin.spgrouptest.exceptions.ConfigParserException;

public class PsiReadingJsonMarshaller extends JsonMarshaller<PsiReading> {

    private Gson gson = new Gson();

    @Override
    public PsiReading fromJson(String jsonString) throws ConfigParserException {
        try{
            if(jsonString != null){
                return gson.fromJson(jsonString, PsiReading.class);
            }
            throw new ConfigParserException("PsiReading JsonString is null");
        }catch (JsonSyntaxException e){
            throw new ConfigParserException(e);
        }
    }

    @Override
    public String toJson(PsiReading item) throws ConfigParserException {
        if(item != null){
            return gson.toJson(item);
        }
        throw new ConfigParserException("PsiReading is null");
    }
}
