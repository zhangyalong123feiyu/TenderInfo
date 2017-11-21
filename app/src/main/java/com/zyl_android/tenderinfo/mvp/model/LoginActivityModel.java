package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.LoginApi;
import com.zyl_android.tenderinfo.project.bean.LoginResultBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import rx.Observable;

/**
 * Created by bibinet on 2017-11-18.
 */

public class LoginActivityModel {
    public Observable<LoginResultBean> doLogin(String account,String password){
        LoginApi loginResultApi = RetrofitUtil.creatHttpsApi(LoginApi.class);
        return loginResultApi.doLogin(account,password);
    }
}
