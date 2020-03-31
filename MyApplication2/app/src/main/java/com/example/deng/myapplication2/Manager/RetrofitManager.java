package com.example.deng.myapplication2.Manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.deng.myapplication2.Application.MyApplication;
import com.example.deng.myapplication2.Exception.MyHostException;
import com.example.deng.myapplication2.Exception.MySocketException;

import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private Retrofit mRetrofit;
    private String baseUrl;
    static Context context;
    private static RetrofitManager mRetrofitManager;
    public static OkHttpClient okHttpClient;
    //静态块,获取OkHttpClient对象
    static {
        getOkHttpClient();
    }

    public  RetrofitManager(String baseUrl,Context context){
        this.baseUrl = baseUrl;
        this.context=context;
        initRetrofit(context);
    }

    public static synchronized RetrofitManager getInstance(String baseUrl,Context context) {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager(baseUrl,context);
                }
            }
        }
        return mRetrofitManager;
    }

    //单例模式获取okhttp
    public static OkHttpClient getOkHttpClient(){
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    try {
                        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                            @Override
                            public void log(String message) {
                                Log.w("zcb", "OkHttp====Message:" + message);
                            }
                        });
                        loggingInterceptor.setLevel(level);
                        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                                .Builder();
                        httpClientBuilder.addInterceptor(loggingInterceptor);
                        httpClientBuilder.followSslRedirects(false);
                        httpClientBuilder.followRedirects(false);
                        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS)
                                .readTimeout(15, TimeUnit.SECONDS)
                                .writeTimeout(15, TimeUnit.SECONDS);
                        return httpClientBuilder.build();
                    }
                    catch (Exception e){
                        throw  new RuntimeException(e);
                    }
                }
            }
        }
        return okHttpClient;
    }

    private void initRetrofit(Context context) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    /**
     * 创建相应的服务接口
     */
    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);

    }
}

