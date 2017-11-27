package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.AskExpertsBean;
import com.zyl_android.tenderinfo.project.bean.ExpertsDataBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-25.
 */

public interface ExpertsTalkActivityApi {
    @POST("appQuestion/expertListPage.json")
    Observable<ExpertsDataBean> getExpertsData();
    @POST("appQuestion/saveQuestion.json")
    Observable<AskExpertsBean> postExpertsQuestion(@Query("userId") String userId,@Query("enterpriseId") String enterpriseId,@Query("type") String type
            ,@Query("expertCodeStr") String expertCodeStr,@Query("title") String title,@Query("content") String content);

}
