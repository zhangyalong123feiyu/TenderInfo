package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.LoginResultBean;

import java.util.List;

/**
 * Created by bibinet on 2017-11-18.
 */

public interface LoginActivityView {
    void onLoginSucess(LoginResultBean loginResultBean);
    void onLoginFailed(String msg);
}
