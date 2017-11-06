package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.BannerApi;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.BannerBean;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bibinet on 2017-11-6.
 */

public class FragementHomeModel {
    public Observable<BannerBean> getBannerData(){
        OkHttpClient okHttpClient=new OkHttpClient();
        Retrofit retrofit=new Retrofit.Builder().client(okHttpClient).baseUrl(Constants.baseUrl_iip).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).build();
        BannerApi bannerApi = retrofit.create(BannerApi.class);
        Observable<BannerBean> bannerInfo = bannerApi.getBannerData();
        return bannerInfo;
    }
}
