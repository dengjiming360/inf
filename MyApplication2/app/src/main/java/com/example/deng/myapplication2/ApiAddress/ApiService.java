package com.example.deng.myapplication2.ApiAddress;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ApiService {
    @GET("framework/station_name.js?scriptVersion=1.0")
    Observable<ResponseBody> getStationMessage();

    @GET("query/train_list.js?scriptVersion=1.0")
    Observable<ResponseBody> getTrainMessage();
}