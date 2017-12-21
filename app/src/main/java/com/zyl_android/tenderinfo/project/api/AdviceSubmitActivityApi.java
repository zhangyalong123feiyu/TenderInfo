package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.BaseBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by bibinet on 2017-12-19.
 */

public interface AdviceSubmitActivityApi {
    @POST("appFeedBack/apply.json")
    Call<BaseBean> postAdvice(@Query("content") String content, @Query("cellPhone") String cellPhone);
}
