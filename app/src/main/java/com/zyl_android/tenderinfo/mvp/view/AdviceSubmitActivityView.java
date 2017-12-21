package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.BaseBean;

/**
 * Created by bibinet on 2017-12-19.
 */

public interface AdviceSubmitActivityView {
    void onSubmitAdviceSucess(BaseBean baseInfo);
    void onSubmitAdviceFailed(String msg);
}
