package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.api.ExpertsTalkActivityApi;
import com.zyl_android.tenderinfo.project.bean.AskExpertsBean;
import com.zyl_android.tenderinfo.project.bean.ExpertsDataBean;
import com.zyl_android.tenderinfo.project.utils.RetrofitUtil;

import rx.Observable;

/**
 * Created by bibinet on 2017-11-25.
 */

public class ExpertsTalkActivityModel {
    public Observable<ExpertsDataBean> getExpertsData(){
        return RetrofitUtil.creatHttpsApi(ExpertsTalkActivityApi.class).getExpertsData();
    }
    public Observable<AskExpertsBean> postExpertsData(String userId, String enterpriseId,String type,String expertCodeStr,String title,String content){
        return RetrofitUtil.creatHttpsApi(ExpertsTalkActivityApi.class).postExpertsQuestion(userId,enterpriseId,type,expertCodeStr,title,content);
    }
}
