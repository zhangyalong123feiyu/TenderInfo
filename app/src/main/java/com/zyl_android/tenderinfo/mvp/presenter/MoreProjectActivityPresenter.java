package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.model.MoreProjectActivityModel;
import com.zyl_android.tenderinfo.mvp.view.MoreProjectActivityView;
import com.zyl_android.tenderinfo.project.bean.ProjectInfoBean;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-12-5.
 */

public class MoreProjectActivityPresenter {
    private MoreProjectActivityModel moreProjectActivityModel;
    private MoreProjectActivityView moreProjectActivityView;

    public MoreProjectActivityPresenter(MoreProjectActivityView moreProjectActivityView) {
        this.moreProjectActivityView = moreProjectActivityView;
        this.moreProjectActivityModel=new MoreProjectActivityModel();
    }
    public void getMoreProjectInfo(String pageNum, String type, String dateRange, String trad, String provinceId){
        moreProjectActivityModel.getMoreProjectInfo(pageNum,type,dateRange,trad,provinceId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ProjectInfoBean>() {
                    @Override
                    public void call(ProjectInfoBean projectInfoBean) {
                        moreProjectActivityView.loadMoreSucess(projectInfoBean.getItems());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        moreProjectActivityView.loadMoreFailed(throwable.getMessage());
                    }
                });
    }
    public void getMoreTenderInfo(String pageNum, String type, String dateRange, String trad, String provinceId){
        moreProjectActivityModel.getMoreTenderInfo(pageNum,type,dateRange,trad,provinceId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ProjectInfoBean>() {
                    @Override
                    public void call(ProjectInfoBean projectInfoBean) {
                        moreProjectActivityView.loadMoreSucess(projectInfoBean.getItems());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        moreProjectActivityView.loadMoreFailed(throwable.getMessage());
                    }
                });
    }
    public void getMoreBuyInfo(String pageNum, String type, String dateRange, String trad, String provinceId){
        moreProjectActivityModel.getMoreBuyInfo(pageNum,type,dateRange,trad,provinceId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ProjectInfoBean>() {
                    @Override
                    public void call(ProjectInfoBean projectInfoBean) {
                        moreProjectActivityView.loadMoreSucess(projectInfoBean.getItems());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        moreProjectActivityView.loadMoreFailed(throwable.getMessage());
                    }
                });
    }
}
