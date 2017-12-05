package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.ProjectInfoBean;

import java.util.List;

/**
 * Created by bibinet on 2017-12-5.
 */

public interface MoreProjectActivityView {
    void loadMoreSucess(List<ProjectInfoBean.ItemsBean> moreInfoList);
    void loadMoreFailed(String msg);
}
