package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.ExpertsDataBean;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-25.
 */

public interface ExpertsTalkActivityApi {
    @POST("appQuestion/expertListPage.json")
    Observable<ExpertsDataBean> getExpertsData();
}
