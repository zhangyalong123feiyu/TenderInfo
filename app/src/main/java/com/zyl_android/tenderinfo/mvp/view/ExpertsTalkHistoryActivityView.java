package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.ExpertsAskAnswerResultBean;

import java.util.List;

/**
 * Created by bibinet on 2017-12-15.
 */

public interface ExpertsTalkHistoryActivityView {
    void onLodaTalkHistorySucess(List<ExpertsAskAnswerResultBean.ItemsBean> answerinfo);
    void onLodaTalkHistoryFailed(String msg);
}
