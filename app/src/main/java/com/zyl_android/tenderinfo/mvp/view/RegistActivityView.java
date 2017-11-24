package com.zyl_android.tenderinfo.mvp.view;

/**
 * Created by bibinet on 2017-11-24.
 */

public interface RegistActivityView {
    void onGetVerifyCodeSucess();
    void onGetVerifyCodeFailed(String msg);
    void onDoRegistSucess();
    void onDoRegistFailed(String msg);
}
