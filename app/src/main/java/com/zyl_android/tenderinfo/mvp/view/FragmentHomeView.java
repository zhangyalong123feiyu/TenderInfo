package com.zyl_android.tenderinfo.mvp.view;

import com.zyl_android.tenderinfo.project.bean.BannerBean;
import com.zyl_android.tenderinfo.project.bean.HomeFiveProjectBean;

import java.util.List;

/**
 * Created by bibinet on 2017-11-6.
 */

public interface FragmentHomeView {
    void onGetBannerSucess(List<BannerBean.ItemBean> bannerList);
    void onGetBannerFailed(String msg);
    void onGetHomeDataSucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo );
    void onGetHomeDataFailed(String msg);
    void onGetHomeTenderSucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo );
    void onGetHomeTenderFailed(String msg);
    void onGetHomeBuySucess(List<HomeFiveProjectBean.ItemsBean> homeProjectInfo );
    void onGetHomeBuyFailed(String msg);
}
