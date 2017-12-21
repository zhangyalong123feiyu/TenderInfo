package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.AdviceSubmitActivityApi;
import com.zyl_android.tenderinfo.project.application.Constants;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bibinet on 2017-12-19.
 */

public class AdviceSubmitActivityModel {
    public void submitAdvice(String content, String phone, Callback callback){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.baseUrl_pis).addConverterFactory(GsonConverterFactory.create()).build();
        retrofit.create(AdviceSubmitActivityApi.class).postAdvice(content,phone).enqueue(callback);
    }
}
