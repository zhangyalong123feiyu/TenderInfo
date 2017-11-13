package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.BannerApi;
import com.zyl_android.tenderinfo.project.api.FragmentHomeApi;
import com.zyl_android.tenderinfo.project.application.Constants;
import com.zyl_android.tenderinfo.project.bean.BannerBean;
import com.zyl_android.tenderinfo.project.bean.HomeFiveProjectBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-6.
 */

public class FragementHomeModel {
    public Observable<BannerBean> getBannerData(){
        BannerApi bannerApi = RetrofitUtil.creatApi(BannerApi.class);
        Observable<BannerBean> bannerInfo = bannerApi.getBannerData();
        return bannerInfo;
    }
    public Observable<HomeFiveProjectBean> getFragementHomeData(String pageNum,String location){
        FragmentHomeApi fragmentHomeApi = RetrofitUtil.creatApi(FragmentHomeApi.class);
        Observable<HomeFiveProjectBean> fiveProjectBeanObservable = fragmentHomeApi.getHomeProjectInfo(pageNum,location);
        return fiveProjectBeanObservable;
    }
    public Observable<HomeFiveProjectBean> getFragementHomeTenderData(String pageNum,String location){
        FragmentHomeApi fragmentHomeApi = RetrofitUtil.creatApi(FragmentHomeApi.class);
        Observable<HomeFiveProjectBean> fiveProjectBeanObservable = fragmentHomeApi.getHomeTenderInfo(pageNum,location);
        return fiveProjectBeanObservable;
    }
    public Observable<HomeFiveProjectBean> getFragementHomeBuyData(String pageNum,String location){
        FragmentHomeApi fragmentHomeApi = RetrofitUtil.creatApi(FragmentHomeApi.class);
        Observable<HomeFiveProjectBean> fiveProjectBeanObservable = fragmentHomeApi.getHomeTenderInfo(pageNum,location);
        return fiveProjectBeanObservable;
    }
}
