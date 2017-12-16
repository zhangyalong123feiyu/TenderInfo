package com.zyl_android.tenderinfo.mvp.model;

import com.zyl_android.tenderinfo.project.application.Constants;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by bibinet on 2017-12-16.
 */

public class TenderHelpActivityModel {
    public void postTenderHelpData(String contact, String cellPhone, String content, String customerId, Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder().add("contact",contact).add("cellPhone",cellPhone).add("content",content).add("customerId",customerId).build();
        Request request=new Request.Builder().post(requestBody).url(Constants.baseUrl_iip+"").build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
