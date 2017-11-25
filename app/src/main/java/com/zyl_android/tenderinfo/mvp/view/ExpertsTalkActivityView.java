package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.ExpertsDataBean;

import java.util.List;

/**
 * Created by bibinet on 2017-11-25.
 */

public interface ExpertsTalkActivityView {
   void onGetExpertsDataSucess(List<ExpertsDataBean.ItemsBean> expertsData);
   void onGetExpertsDataFailed(String msg);
}
