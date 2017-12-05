package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.MoreProjectApi;
import com.zyl_android.tenderinfo.project.bean.ProjectInfoBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import rx.Observable;

/**
 * Created by bibinet on 2017-12-5.
 */

public class MoreProjectActivityModel {
    public Observable<ProjectInfoBean> getMoreProjectInfo(String pageNum, String type, String dateRange, String trad, String provinceId){
        return RetrofitUtil.creatHttpApi(MoreProjectApi.class).getMoreProjcetInfo(pageNum,type,dateRange,trad,provinceId);
    }
    public Observable<ProjectInfoBean> getMoreTenderInfo(String pageNum, String type, String dateRange, String trad, String provinceId){
        return RetrofitUtil.creatHttpApi(MoreProjectApi.class).getMoreTenderInfo(pageNum,type,dateRange,trad,provinceId);
    }
    public Observable<ProjectInfoBean> getMoreBuyInfo(String pageNum, String type, String dateRange, String trad, String provinceId){
        return RetrofitUtil.creatHttpApi(MoreProjectApi.class).getMoreBuyInfo(pageNum,type,dateRange,trad,provinceId);
    }
}
