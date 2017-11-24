package com.zyl_android.tenderinfo.mvp.view;

/**
 * Created by bibinet on 2017-11-23.
 */

public interface ModifyPasswordActivityView {
    void onGetVerifyCodeSucess();
    void onGetVerifyCodeFailed(String msg);
    void onModifyPasswordSucess();
    void onModifyPasswordFailed(String msg);
}
