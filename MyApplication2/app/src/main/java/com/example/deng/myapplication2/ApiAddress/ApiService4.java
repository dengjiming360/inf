package com.example.deng.myapplication2.ApiAddress;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService4 {
    @Headers({
            "cache-control:no-cache",
            "Postman-Token:1c16a6ea-4822-4619-9faa-dcf158b11bc6",
            "User-Agent:PostmanRuntime/7.6.0",
            "Accept:*/*",
            "Host:api.ieclipse.cn",
    })
    @GET("wnl/lunar")
    Observable<ResponseBody>getDateMessge(@Query("date")String date);
}
