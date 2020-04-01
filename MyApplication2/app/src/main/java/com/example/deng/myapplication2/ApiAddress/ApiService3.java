package com.example.deng.myapplication2.ApiAddress;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService3 {
    @Headers({
            "cache-control:no-cache",
            "Postman-Token:25a87113-9a7c-4ba0-967f-e91760ce3dde",
            "User-Agent:PostmanRuntime/7.6.0",
            "Accept:*/*",
            "Host:kyfw.12306.cn",
            "cookie:JSESSIONID=B37AF76235DF4C716931F68CDEE099D9; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=384827914.38945.0000",
    })
    @GET("czxx/queryByTrainNo")
    Observable<ResponseBody> getAllStation(@Query("train_no")String trainno, @Query("from_station_telecode")String fromstation,
                                          @Query("to_station_telecode") String tostation, @Query("depart_date") String date);
}
