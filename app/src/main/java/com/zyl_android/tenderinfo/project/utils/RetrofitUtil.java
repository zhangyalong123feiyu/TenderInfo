package com.zyl_android.tenderinfo.project.utils;

import com.zyl_android.tenderinfo.project.application.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bibinet on 2017-11-7.
 */

public class RetrofitUtil {
    public static Retrofit getRetrofit(String baseUrl){
        OkHttpClient okHttpClient=new OkHttpClient();
       return new Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).//使其支持observable,默认支持call<String>有DefaultFactory支持
                addConverterFactory(GsonConverterFactory.create()).build();
    }
}
