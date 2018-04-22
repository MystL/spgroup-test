package com.vin.spgrouptest.api;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.vin.spgrouptest.data.PsiResponses;
import com.vin.spgrouptest.exceptions.ApiException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;

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

    public PsiResponses getLatestPsiReadings() throws ApiException{
        try{
            return checkForNull(api.getLatestPsiReadings());
        }catch (Exception e){
            throw new ApiException(e);
        }
    }

    public PsiResponses getPsiReadingsForDay(String date) throws ApiException{
        try{
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            LocalDate localDate = formatter.parseLocalDate(date);
            return checkForNull(api.getPsiReadingsForDay(localDate.toString("yyyy-MM-dd")));
        }catch (Exception e){
            e.printStackTrace();
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
        PsiResponses getLatestPsiReadings();

        @GET("/v1/environment/psi")
        PsiResponses getPsiReadingsForDay(@Query("date") String date);

    }
}
