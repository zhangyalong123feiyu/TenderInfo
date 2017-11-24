package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.RegistActivityApi;
import com.zyl_android.tenderinfo.project.bean.BaseBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import rx.Observable;

/**
 * Created by bibinet on 2017-11-24.
 */

public class RegistActivityModel {
    public Observable<Object> getVerifyCode(String cellphone){
       return RetrofitUtil.creatHttpsApi(RegistActivityApi.class).getVerifyCode(cellphone,"1");
    }
    public Observable<BaseBean> doRegist(String companyname,String userName,String cellPhone,String verifyCode){
     return RetrofitUtil.creatHttpsApi(RegistActivityApi.class).doRegist(companyname,userName,cellPhone,verifyCode);
    }
}
