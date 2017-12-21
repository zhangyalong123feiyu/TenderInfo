package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.mvp.base.baseview.BaseView;
import com.zyl_android.tenderinfo.project.bean.BaseBean;

/**
 * Created by bibinet on 2017-12-16.
 */

public interface TenderHelpActivityView extends BaseView<BaseBean> {
    void onPostDataSucess(BaseBean postInfo);
    void onPostDataFailed(String msg);
}
