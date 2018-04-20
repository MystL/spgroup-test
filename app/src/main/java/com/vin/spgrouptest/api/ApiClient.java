package com.vin.spgrouptest.api;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.exceptions.ApiException;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;

public class ApiClient {

    public static final int CACHE_MAX_SIZE = 10 * 1024 * 1024;
    private Api api;

    public ApiClient(Context context, URL apiEndpoint) {
        api = createRestAdapter(context, apiEndpoint).create(Api.class);
    }

    private RestAdapter createRestAdapter(Context context, URL apiEndpoint){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(new Cache(context.getCacheDir(), CACHE_MAX_SIZE));
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        return new RestAdapter.Builder()
                .setEndpoint(apiEndpoint.toString())
                .setClient(new OkClient(okHttpClient))
                .build();
    }

    // For testing purposes only
    public ApiClient(Api api){
        this.api = api;
    }

    public PsiResponses getPsiReadings() throws ApiException{
        try{
            return checkForNull(api.getPsiReadings());
        }catch (Exception e){
            throw new ApiException(e);
        }
    }

    private <T> T checkForNull(T item) throws ApiException{
        if(item == null){
            throw new ApiException("Item cannot be null");
        }
        return item;
    }

    public interface Api{

        @GET("/v1/environment/psi")
        PsiResponses getPsiReadings();

    }
}
