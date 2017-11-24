package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.ModifyPasswordActivityApi;
import com.zyl_android.tenderinfo.project.bean.CertifResetPasswordBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import java.util.Map;

import rx.Observable;

/**
 * Created by bibinet on 2017-11-23.
 */

public class ModifyPasswordActivityModel {
    public Observable<Object> getVerifyCode(String cellPhone){
        ModifyPasswordActivityApi modifyPasswordApi = RetrofitUtil.creatHttpsApi(ModifyPasswordActivityApi.class);
        return modifyPasswordApi.getVerifyCode(cellPhone);
    }
    public Observable<CertifResetPasswordBean> modifyPassword(Map<String,String> params){
        ModifyPasswordActivityApi modifyPasswordActivityApi=RetrofitUtil.creatHttpsApi(ModifyPasswordActivityApi.class);
        return modifyPasswordActivityApi.modifyPassWord(params);
    }
}
