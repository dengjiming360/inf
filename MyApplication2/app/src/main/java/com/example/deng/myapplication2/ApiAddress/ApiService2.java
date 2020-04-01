package com.example.deng.myapplication2.ApiAddress;

import java.util.HashMap;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService2 {
    @Headers({
            "cache-control:no-cache",
            "Postman-Token:fb444109-9440-4a8b-b7c5-8f0d107affb5",
            "User-Agent:PostmanRuntime/7.6.0",
            "Accept:*/*",
            "Host:kyfw.12306.cn",
            "cookie:JSESSIONID=B37AF76235DF4C716931F68CDEE099D9; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=384827914.38945.0000",
    })
    @GET("leftTicket/{curl}")
    Observable<ResponseBody> getTrainByMessage(@Path(value = "curl", encoded = true) String curl, @Query("leftTicketDTO.train_date") String date, @Query("leftTicketDTO.from_station") String from,
                                               @Query("leftTicketDTO.to_station") String to, @Query("purpose_codes") String type);



}
