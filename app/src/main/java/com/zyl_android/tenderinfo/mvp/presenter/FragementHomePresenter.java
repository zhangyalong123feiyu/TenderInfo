package com.zyl_android.tenderinfo.mvp.presenter;

import android.support.annotation.NonNull;

import com.zyl_android.tenderinfo.mvp.base.basepresenter.BasePresenter;
import com.zyl_android.tenderinfo.mvp.base.baseview.BaseView;
import com.zyl_android.tenderinfo.mvp.model.FragementHomeModel;
import com.zyl_android.tenderinfo.mvp.view.FragmentHomeView;
import com.zyl_android.tenderinfo.project.bean.BannerBean;
import com.zyl_android.tenderinfo.project.bean.HomeFiveProjectBean;

import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-6.
 */

public class FragementHomePresenter extends BasePresenter {
    private FragmentHomeView fragmentHomeView;
    private FragementHomeModel fragementHomeModel;
    public FragementHomePresenter(FragmentHomeView fragmentHomeView){
        this.fragmentHomeView=fragmentHomeView;
        this.fragementHomeModel=new FragementHomeModel();
    }
    public void getBannerData(){
        Subscription subscription=fragementHomeModel.getBannerData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<BannerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentHomeView.onGetBannerFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        fragmentHomeView.onGetBannerSucess(bannerBean.getItem());
                    }
                }
        );
        addSubScription(subscription);
    }
    public void getHomeProjectData(String pageNum,String location){
        Subscription subscription=fragementHomeModel.getFragementHomeData(pageNum,location).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeFiveProjectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            fragmentHomeView.onGetHomeDataFailed(e.getMessage());
            }

            @Override
            public void onNext(HomeFiveProjectBean homeFiveProjectBean) {
            fragmentHomeView.onGetHomeDataSucess(homeFiveProjectBean.getItems());
            }
        });
        addSubScription(subscription);
    }
    public void getHomeTenderData(String pageNum,String location){
        Subscription subscription=fragementHomeModel.getFragementHomeTenderData(pageNum,location).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeFiveProjectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            fragmentHomeView.onGetHomeTenderFailed(e.getMessage());
            }

            @Override
            public void onNext(HomeFiveProjectBean homeFiveProjectBean) {
            fragmentHomeView.onGetHomeTenderSucess(homeFiveProjectBean.getItems());
            }
        });
        addSubScription(subscription);
    }
    public void getHomeBuyProjectInfo(String pageNum,String location){
        Subscription subscription=fragementHomeModel.getFragementHomeBuyData(pageNum,location).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeFiveProjectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                fragmentHomeView.onGetHomeBuyFailed(e.getMessage());
            }

            @Override
            public void onNext(HomeFiveProjectBean homeFiveProjectBean) {
                fragmentHomeView.onGetHomeBuySucess(homeFiveProjectBean.getItems());
            }
        });
        addSubScription(subscription);
    }
}
