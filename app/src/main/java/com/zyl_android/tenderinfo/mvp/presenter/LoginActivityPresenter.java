package com.zyl_android.tenderinfo.mvp.presenter;

import com.zyl_android.tenderinfo.mvp.base.basepresenter.BasePresenter;
import com.zyl_android.tenderinfo.mvp.model.LoginActivityModel;
import com.zyl_android.tenderinfo.mvp.view.LoginActivityView;
import com.zyl_android.tenderinfo.project.bean.LoginResultBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bibinet on 2017-11-18.
 */

public class LoginActivityPresenter extends BasePresenter{
    private LoginActivityModel loginActivityModel;
    private LoginActivityView loginActivityView;

    public LoginActivityPresenter(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
        this.loginActivityModel=new LoginActivityModel();
    }
    public void doLogin(String account,String password){
        Subscription subscribe=loginActivityModel.doLogin(account,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<LoginResultBean>() {
                    @Override
                    public void call(LoginResultBean loginResultBean) {
                        String code = loginResultBean.getResCode();
                        if (code.equals("0000")) {
                            loginActivityView.onLoginSucess(loginResultBean);
                        }else {
                            loginActivityView.onLoginFailed(loginResultBean.getResMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loginActivityView.onLoginFailed(throwable.getMessage());
                    }
                }

        );
        addSubScription(subscribe);
    }
}
