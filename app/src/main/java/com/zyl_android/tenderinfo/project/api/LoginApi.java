package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.LoginResultBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-18.
 */

public interface LoginApi {
    @POST("user/login.json")
    Observable<LoginResultBean> doLogin(@Query("account") String account,@Query("password") String passwrod);
}
