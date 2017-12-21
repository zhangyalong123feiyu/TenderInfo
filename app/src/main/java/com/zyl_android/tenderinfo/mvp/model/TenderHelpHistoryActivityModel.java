package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.application.Constants;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by bibinet on 2017-12-18.
 */

public class TenderHelpHistoryActivityModel {
    public void getTenderderHistoryData(String customerId, int pageNumb, Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody= new FormBody.Builder().add("customerId",customerId).add("pageNum",String.valueOf(pageNumb)).build();
        Request request=new Request.Builder().post(requestBody).url(Constants.baseUrl_pis+"appAssistance/selectPage.json").build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public void deleteHistoryData(String objectId,Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("objectId",objectId).build();
        Request request=new Request.Builder().post(requestBody).url(Constants.baseUrl_pis+"appAssistance/delete.json").build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
