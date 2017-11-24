package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.base.basepresenter.BasePresenter;
import com.zyl_android.tenderinfo.mvp.model.ModifyPasswordActivityModel;
import com.zyl_android.tenderinfo.mvp.view.ModifyPasswordActivityView;
import com.zyl_android.tenderinfo.project.bean.CertifResetPasswordBean;

import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-23.
 */

public class ModifyPasswordActivityPresenter extends BasePresenter{
    private ModifyPasswordActivityModel modifyPasswordActivityModel;
    private ModifyPasswordActivityView modifyPasswordActivityView;

    public ModifyPasswordActivityPresenter(ModifyPasswordActivityView modifyPasswordActivityView) {
        this.modifyPasswordActivityView = modifyPasswordActivityView;
        modifyPasswordActivityModel=new ModifyPasswordActivityModel();
    }

    public void getVerifyCode(String cellPhone){//获取验证码
        Subscription subscription= modifyPasswordActivityModel.getVerifyCode(cellPhone).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        modifyPasswordActivityView.onGetVerifyCodeSucess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        modifyPasswordActivityView.onGetVerifyCodeFailed(throwable.getMessage());
                    }
                }
        );
        addSubScription(subscription);
    }
    public void modifyPassword(Map<String,String> modifyParams){
        Subscription subscription=modifyPasswordActivityModel.modifyPassword(modifyParams).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<CertifResetPasswordBean>() {
                              @Override
                              public void call(CertifResetPasswordBean certifResetPasswordBean) {
                                modifyPasswordActivityView.onModifyPasswordSucess();
                              }
                          }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                modifyPasswordActivityView.onModifyPasswordFailed(throwable.getMessage());
                            }
                        }
                );
        addSubScription(subscription);
    }
}
