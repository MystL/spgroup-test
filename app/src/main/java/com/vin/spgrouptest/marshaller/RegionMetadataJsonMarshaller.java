package com.vin.spgrouptest.marshaller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vin.spgrouptest.data.RegionMetadata;
import com.vin.spgrouptest.exceptions.ConfigParserException;

public class RegionMetadataJsonMarshaller extends JsonMarshaller<RegionMetadata>{

    private Gson gson = new Gson();

    @Override
    public RegionMetadata fromJson(String jsonString) throws ConfigParserException {
        try{
            if(jsonString != null){
                return gson.fromJson(jsonString, RegionMetadata.class);
            }
            throw new ConfigParserException("RegionMetadata jsonString is null");
        }catch (JsonSyntaxException e){
            throw new ConfigParserException(e);
        }
    }

    @Override
    public String toJson(RegionMetadata item) throws ConfigParserException {
        if(item != null){
            return  gson.toJson(item);
        }
        throw new ConfigParserException("RegionMetadata is null");
    }
}
