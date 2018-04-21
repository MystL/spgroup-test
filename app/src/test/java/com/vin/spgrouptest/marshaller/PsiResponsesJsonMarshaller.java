package com.vin.spgrouptest.marshaller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.exceptions.ConfigParserException;

public class PsiResponsesJsonMarshaller extends JsonMarshaller<PsiResponses> {

    private Gson gson = new Gson();

    public PsiResponsesJsonMarshaller(){

    }

    @Override
    public PsiResponses fromJson(String jsongString) throws ConfigParserException {
        try{
            if(jsongString != null){
                return gson.fromJson(jsongString, PsiResponses.class);
            }
            throw new ConfigParserException("PsiResponses jsongString Null");
        }catch (JsonSyntaxException e){
            throw new ConfigParserException(e);
        }
    }

    @Override
    public String toJson(PsiResponses item) throws ConfigParserException {
        if(item != null){
            return gson.toJson(item);
        }
        throw new ConfigParserException("PsiResponses null");
    }
}
