package com.example.deng.myapplication2.ApiAddress;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("query/train_list.js?scriptVersion=1.0")
    Observable<ResponseBody> getTrainMessage();

    @GET("framework/station_name.js?scriptVersion=1.0")
    Observable<ResponseBody> getStationMessage();

}
