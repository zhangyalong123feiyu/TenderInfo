package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.HomeFiveProjectBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-7.
 */

public interface FragmentHomeApi {
    @POST("appData/selectPageProject.json")//首页项目信息
    Observable<HomeFiveProjectBean> getHomeProjectInfo(@Query("pageNum") String pagenumb,@Query("provinceId") String provinceId);
    @POST("appData/selectPageBiddingInfoIndex.json")//首页招标信息
    Observable<HomeFiveProjectBean> getHomeTenderInfo(@Query("pageNum") String pagenumb,@Query("provinceId") String provinceId);
    @POST("appData/selectPagePurchaseIndex.json")//首页采购信息
    Observable<HomeFiveProjectBean> getHomeBuyInfo(@Query("pageNum") String pagenumb,@Query("provinceId") String provinceId);
}
