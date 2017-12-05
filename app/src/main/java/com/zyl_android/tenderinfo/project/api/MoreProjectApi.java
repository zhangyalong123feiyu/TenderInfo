package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.ProjectInfoBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-12-5.
 */

public interface MoreProjectApi {
    @POST("generalProjects/selectPage.json")//更多项目信息
    Observable<ProjectInfoBean> getMoreProjcetInfo(@Query("pageNum") String pageNum,@Query("_type") String _type,
        @Query("dateRange") String dateRange,@Query("tradeFirst") String tradeFirst,@Query("provinceId") String provinceId);
    @POST("biddingInfos/selectPage.json")//更多招标信息
    Observable<ProjectInfoBean> getMoreTenderInfo(@Query("pageNum") String pageNum,@Query("_type") String _type,
        @Query("dateRange") String dateRange,@Query("tradeFirst") String tradeFirst,@Query("provinceId") String provinceId);
    @POST("purchaseInfos/selectPage.json")//更多采购信息
    Observable<ProjectInfoBean> getMoreBuyInfo(@Query("pageNum") String pageNum,@Query("_type") String _type,
        @Query("dateRange") String dateRange,@Query("tradeFirst") String tradeFirst,@Query("provinceId") String provinceId);
}
