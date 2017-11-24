package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.BaseBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-24.
 */

public interface RegistActivityApi {
    @POST("user/sendSMS.json")
    Observable<Object> getVerifyCode(@Query("cellPhone") String phone,@Query("type") String type);
    @POST("user/appRegist.json")
    Observable<BaseBean> doRegist(@Query("enterpriseName") String companyName, @Query("userName") String userName, @Query("cellPhone") String phone, @Query("checkCode") String verifyCode);
}
