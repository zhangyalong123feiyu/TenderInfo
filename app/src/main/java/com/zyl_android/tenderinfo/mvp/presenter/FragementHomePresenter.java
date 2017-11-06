package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.base.basepresenter.BasePresenter;
import com.zyl_android.tenderinfo.mvp.base.baseview.BaseView;
import com.zyl_android.tenderinfo.mvp.model.FragementHomeModel;
import com.zyl_android.tenderinfo.mvp.view.FragmentHomeView;
import com.zyl_android.tenderinfo.project.bean.BannerBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.Observer;
import rx.Subscription;

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
                new io.reactivex.Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BannerBean bannerBean) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );
    }
}
