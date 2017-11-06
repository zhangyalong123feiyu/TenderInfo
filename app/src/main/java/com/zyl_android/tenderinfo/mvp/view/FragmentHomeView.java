package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.BannerBean;

import java.util.List;

/**
 * Created by bibinet on 2017-11-6.
 */

public interface FragmentHomeView {
    void onGetBannerSucess(List<BannerBean.ItemBean> bannerList);
    void onGetBannerFailed(String msg);
    void onGetHomeDataSucess();
    void onGetHomeDataFailed();
}
