package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.base.basepresenter.BasePresenter;
import com.zyl_android.tenderinfo.mvp.model.RegistActivityModel;
import com.zyl_android.tenderinfo.mvp.view.RegistActivityView;
import com.zyl_android.tenderinfo.project.bean.BaseBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-24.
 */

public class RegistActivityPresenter extends BasePresenter{
    public RegistActivityModel registActivityModel;
    public RegistActivityView registActivityView;

    public RegistActivityPresenter(RegistActivityView registActivityView) {
        this.registActivityView = registActivityView;
        this.registActivityModel=new RegistActivityModel();
    }
    public void getVerifyCode(String cellPhone){
        Subscription subscription=registActivityModel.getVerifyCode(cellPhone).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                    registActivityView.onGetVerifyCodeSucess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        registActivityView.onGetVerifyCodeFailed(throwable.getMessage());
                    }
                }
        );
        addSubScription(subscription);
    }
    public void doRegist(String companyname,String userName,String cellPhone,String verifyCode){
        Subscription subscription=registActivityModel.doRegist(companyname,userName,cellPhone,verifyCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseBean>() {
                    @Override
                    public void call(BaseBean baseBean) {
                    registActivityView.onDoRegistSucess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        registActivityView.onDoRegistFailed(throwable.getMessage());
                    }
                });
    }
}
