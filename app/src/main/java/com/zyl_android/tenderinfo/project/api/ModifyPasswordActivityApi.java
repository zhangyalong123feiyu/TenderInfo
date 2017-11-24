package com.zyl_android.tenderinfo.project.api;

import com.zyl_android.tenderinfo.project.bean.CertifResetPasswordBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by bibinet on 2017-11-23.
 */

public interface ModifyPasswordActivityApi {
    @POST("user/updatePwdSendSMS.json")//获取验证码
    Observable<Object>  getVerifyCode(@Query("cellphone") String cellPhone);
    @POST("user/changePwd.json")
    Observable<CertifResetPasswordBean> modifyPassWord(@QueryMap Map<String,String> params);
}
