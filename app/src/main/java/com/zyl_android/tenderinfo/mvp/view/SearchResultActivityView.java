package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.SearchResultBean;

import java.util.List;

/**
 * Created by bibinet on 2017-11-9.
 */

public interface SearchResultActivityView {
    void onGetSearchResultSucess(List<SearchResultBean.ItemsBean> searchResultInfo);
    void onGetSearchResultFailed(String msg);
}
