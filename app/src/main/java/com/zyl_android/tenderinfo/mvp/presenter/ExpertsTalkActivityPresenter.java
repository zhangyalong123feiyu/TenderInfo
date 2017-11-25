package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.base.basepresenter.BasePresenter;
import com.zyl_android.tenderinfo.mvp.model.ExpertsTalkActivityModel;
import com.zyl_android.tenderinfo.mvp.view.ExpertsTalkActivityView;
import com.zyl_android.tenderinfo.project.bean.ExpertsDataBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-25.
 */

public class ExpertsTalkActivityPresenter extends BasePresenter {
    private ExpertsTalkActivityModel expertsTalkActivityModel;
    private ExpertsTalkActivityView expertsTalkActivityView;

    public ExpertsTalkActivityPresenter(ExpertsTalkActivityView expertsTalkActivityView) {
        this.expertsTalkActivityView = expertsTalkActivityView;
        this.expertsTalkActivityModel=new ExpertsTalkActivityModel();
    }
    public void getExpertsData(){
       Subscription subscription=expertsTalkActivityModel.getExpertsData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<ExpertsDataBean>() {
                    @Override
                    public void call(ExpertsDataBean expertsDataBean) {
                    expertsTalkActivityView.onGetExpertsDataSucess(expertsDataBean.getItems());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    expertsTalkActivityView.onGetExpertsDataFailed(throwable.getMessage());
                    }
                });
        addSubScription(subscription);
    }
}
