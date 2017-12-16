package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.application.Constants;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by bibinet on 2017-12-15.
 */

public class ExpertsTalkHistoryActivityModel {
    public void getExptertsTalkHistory(String userId,int pageNum,Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody= new FormBody.Builder().add("creator",userId).add("pageNum",String.valueOf(pageNum)).build();
        Request request=new Request.Builder().url(Constants.baseUrl_iip+"appQuestion/selectPage.json").post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
